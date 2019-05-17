package io.github.preps.service.service;

import io.github.preps.service.service.dto.AmortizationEntryDTO;

import java.util.List;

/**
 * This interface is created to allow the service to pool all the dat from the
 * backend without limiting it to 20, for reporting purposes
 */
public interface AmortizationEntriesReportService {

    /**
     * Get all the amortizationEntries.
     *
     * @return the list of entities
     */
    List<AmortizationEntryDTO> findAll();
}
