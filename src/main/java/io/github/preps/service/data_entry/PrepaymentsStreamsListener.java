package io.github.preps.service.data_entry;

import io.github.preps.service.service.PrepaymentEntryService;
import io.github.preps.service.service.dto.PrepaymentEntryDTO;
import io.github.preps.service.service.impl.PrepaymentEntryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class PrepaymentsStreamsListener {


    @Autowired
    private PrepaymentEntryService prepaymentEntryService;

    @StreamListener(PrepaymentEntryStreams.INPUT)
    public void handlePrepaymentEntryStreamItem(@Payload PrepaymentEntryEVM streamItem) {
        log.info("Received prepaymentEntry @ index #: {}", streamItem.getRowIndex());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        PrepaymentEntryDTO dto = new PrepaymentEntryDTO();
        dto.setAccountNumber(streamItem.getAccountNumber());
        dto.setAccountName(streamItem.getAccountName());
        dto.setPrepaymentId(streamItem.getPrepaymentId());
        dto.setPrepaymentDate(LocalDate.parse(streamItem.getPrepaymentDate(), dtf));
        dto.setParticulars(streamItem.getParticulars());
        dto.setServiceOutlet(streamItem.getServiceOutlet());
        dto.setPrepaymentAmount(new BigDecimal(streamItem.getPrepaymentAmount().replaceAll(",", "")));
        dto.setMonths(streamItem.getMonths());
        dto.setSupplierName(streamItem.getSupplierName());
        dto.setInvoiceNumber(streamItem.getInvoiceNumber());

        PrepaymentEntryDTO result = prepaymentEntryService.save(dto);

        log.debug("Prepayment entry item saved : {}", result.getId());
    }
}
