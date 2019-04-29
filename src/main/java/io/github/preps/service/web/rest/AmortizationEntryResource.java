package io.github.preps.service.web.rest;

import io.github.preps.service.service.AmortizationEntryService;
import io.github.preps.service.web.rest.errors.BadRequestAlertException;
import io.github.preps.service.web.rest.util.HeaderUtil;
import io.github.preps.service.web.rest.util.PaginationUtil;
import io.github.preps.service.service.dto.AmortizationEntryDTO;
import io.github.preps.service.service.dto.AmortizationEntryCriteria;
import io.github.preps.service.service.AmortizationEntryQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing AmortizationEntry.
 */
@RestController
@RequestMapping("/api")
public class AmortizationEntryResource {

    private final Logger log = LoggerFactory.getLogger(AmortizationEntryResource.class);

    private static final String ENTITY_NAME = "prepaymentsserviceAmortizationEntry";

    private final AmortizationEntryService amortizationEntryService;

    private final AmortizationEntryQueryService amortizationEntryQueryService;

    public AmortizationEntryResource(AmortizationEntryService amortizationEntryService, AmortizationEntryQueryService amortizationEntryQueryService) {
        this.amortizationEntryService = amortizationEntryService;
        this.amortizationEntryQueryService = amortizationEntryQueryService;
    }

    /**
     * POST  /amortization-entries : Create a new amortizationEntry.
     *
     * @param amortizationEntryDTO the amortizationEntryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new amortizationEntryDTO, or with status 400 (Bad Request) if the amortizationEntry has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/amortization-entries")
    public ResponseEntity<AmortizationEntryDTO> createAmortizationEntry(@Valid @RequestBody AmortizationEntryDTO amortizationEntryDTO) throws URISyntaxException {
        log.debug("REST request to save AmortizationEntry : {}", amortizationEntryDTO);
        if (amortizationEntryDTO.getId() != null) {
            throw new BadRequestAlertException("A new amortizationEntry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AmortizationEntryDTO result = amortizationEntryService.save(amortizationEntryDTO);
        return ResponseEntity.created(new URI("/api/amortization-entries/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * PUT  /amortization-entries : Updates an existing amortizationEntry.
     *
     * @param amortizationEntryDTO the amortizationEntryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated amortizationEntryDTO, or with status 400 (Bad Request) if the amortizationEntryDTO is not valid, or with status 500
     * (Internal Server Error) if the amortizationEntryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/amortization-entries")
    public ResponseEntity<AmortizationEntryDTO> updateAmortizationEntry(@Valid @RequestBody AmortizationEntryDTO amortizationEntryDTO) throws URISyntaxException {
        log.debug("REST request to update AmortizationEntry : {}", amortizationEntryDTO);
        if (amortizationEntryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AmortizationEntryDTO result = amortizationEntryService.save(amortizationEntryDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, amortizationEntryDTO.getId().toString())).body(result);
    }

    /**
     * GET  /amortization-entries : get all the amortizationEntries.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of amortizationEntries in body
     */
    @GetMapping("/amortization-entries")
    public ResponseEntity<List<AmortizationEntryDTO>> getAllAmortizationEntries(AmortizationEntryCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AmortizationEntries by criteria: {}", criteria);
        Page<AmortizationEntryDTO> page = amortizationEntryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/amortization-entries");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /amortization-entries/count : count all the amortizationEntries.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the count in body
     */
    @GetMapping("/amortization-entries/count")
    public ResponseEntity<Long> countAmortizationEntries(AmortizationEntryCriteria criteria) {
        log.debug("REST request to count AmortizationEntries by criteria: {}", criteria);
        return ResponseEntity.ok().body(amortizationEntryQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /amortization-entries/:id : get the "id" amortizationEntry.
     *
     * @param id the id of the amortizationEntryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the amortizationEntryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/amortization-entries/{id}")
    public ResponseEntity<AmortizationEntryDTO> getAmortizationEntry(@PathVariable Long id) {
        log.debug("REST request to get AmortizationEntry : {}", id);
        Optional<AmortizationEntryDTO> amortizationEntryDTO = amortizationEntryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(amortizationEntryDTO);
    }

    /**
     * DELETE  /amortization-entries/:id : delete the "id" amortizationEntry.
     *
     * @param id the id of the amortizationEntryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/amortization-entries/{id}")
    public ResponseEntity<Void> deleteAmortizationEntry(@PathVariable Long id) {
        log.debug("REST request to delete AmortizationEntry : {}", id);
        amortizationEntryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/amortization-entries?query=:query : search for the amortizationEntry corresponding to the query.
     *
     * @param query    the query of the amortizationEntry search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/amortization-entries")
    public ResponseEntity<List<AmortizationEntryDTO>> searchAmortizationEntries(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AmortizationEntries for query {}", query);
        Page<AmortizationEntryDTO> page = amortizationEntryService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/amortization-entries");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
