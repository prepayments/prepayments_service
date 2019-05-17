package io.github.preps.service.service.impl;

import io.github.preps.service.repository.AmortizationEntryRepository;
import io.github.preps.service.service.AmortizationEntriesReportService;
import io.github.preps.service.service.dto.AmortizationEntryDTO;
import io.github.preps.service.service.mapper.AmortizationEntryMapper;
import io.github.preps.service.service.mapper.AmortizationEntryMapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("amortizationEntriesReortService")
@Transactional
@Slf4j
public class AmortizationEntriesReportServiceImpl implements AmortizationEntriesReportService {


    private final AmortizationEntryMapper amortizationEntryMapper;
    private final AmortizationEntryRepository amortizationEntryRepository;

    @Autowired
    public AmortizationEntriesReportServiceImpl(final AmortizationEntryMapper amortizationEntryMapper,
                                                final @Qualifier("amortizationEntryRepository") AmortizationEntryRepository amortizationEntryRepository) {
        this.amortizationEntryMapper = amortizationEntryMapper;
        this.amortizationEntryRepository = amortizationEntryRepository;
    }


    /**
     * Get all the amortizationEntries.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AmortizationEntryDTO> findAll() {

        log.debug("Request to fetch all amortizationEntries from DB...");

        return amortizationEntryMapper.toDto(amortizationEntryRepository.findAll());
    }
}
