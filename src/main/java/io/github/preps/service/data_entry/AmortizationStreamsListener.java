package io.github.preps.service.data_entry;

import io.github.preps.service.domain.AmortizationEntry;
import io.github.preps.service.service.AmortizationEntryService;
import io.github.preps.service.service.dto.AmortizationEntryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Temporary setup to see if messagin works. We are going to listen to streams of data and upload it to the server
 *
 *
 */
@Component
@Slf4j
public class AmortizationStreamsListener {

    private AmortizationEntryService amortizationEntryService;

    public AmortizationStreamsListener(final AmortizationEntryService amortizationEntryService) {
        this.amortizationEntryService = amortizationEntryService;
    }

    @StreamListener(AmortizationEntryStreams.INPUT)
    public void handleGreetings(@Payload AmortizationEntryEVM amortizationEntryEVM) {
        log.info("Received amortizationEntry #: {}", amortizationEntryEVM.getRowIndex());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        AmortizationEntryDTO dto = new AmortizationEntryDTO();
        dto.setAmortizationDate(LocalDate.parse(amortizationEntryEVM.getAmortizationDate(), dtf));
        dto.setAmortizationAmount(new BigDecimal(amortizationEntryEVM.getAmortizationAmount().replaceAll(",", "")));
        dto.setParticulars(amortizationEntryEVM.getParticulars());
        dto.setPosted(false);
        dto.setServiceOutlet(amortizationEntryEVM.getServiceOutlet());
        dto.setAccountNumber(amortizationEntryEVM.getAccountNumber());
        dto.setAccountName(amortizationEntryEVM.getAccountName());
        // PREPAYMENT ID is assumed to be id # as stored in the db for prepayments
        dto.setPrepaymentEntryId((long) amortizationEntryEVM.getRowIndex());
        AmortizationEntryDTO result = amortizationEntryService.save(dto);

        log.debug("Amortization entry item saved : #{}", result.getId());


    }
}
