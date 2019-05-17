package io.github.preps.service.web.rest;

import io.github.preps.service.service.AmortizationEntriesReportService;
import io.github.preps.service.service.dto.AmortizationEntryDTO;
import io.github.preps.service.service.impl.AmortizationEntriesReportServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * AmortizationEntriesReportResource controller
 */
@Slf4j
@RestController
@RequestMapping("/api/amortization-entries-report")
public class AmortizationEntriesReportResource {


    private final AmortizationEntriesReportService amortizationEntriesReportService;

    @Autowired
    public AmortizationEntriesReportResource(final AmortizationEntriesReportService amortizationEntriesReportService) {
        this.amortizationEntriesReportService = amortizationEntriesReportService;
    }

    /**
    * GET getAllReportAmortizationEntries
    */
    @GetMapping("/get-all-report-amortization-entries")
    public ResponseEntity<List<AmortizationEntryDTO>> getAllReportAmortizationEntries() {

        return ResponseEntity.ok().body(amortizationEntriesReportService.findAll());
    }

}
