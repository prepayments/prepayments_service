package io.github.preps.service.service;

import io.github.jhipster.service.QueryService;
import io.github.preps.service.domain.AmortizationEntry;
import io.github.preps.service.domain.AmortizationEntry_;
import io.github.preps.service.domain.PrepaymentEntry_;
import io.github.preps.service.repository.AmortizationEntryRepository;
import io.github.preps.service.repository.search.AmortizationEntrySearchRepository;
import io.github.preps.service.service.dto.AmortizationEntryCriteria;
import io.github.preps.service.service.dto.AmortizationEntryDTO;
import io.github.preps.service.service.mapper.AmortizationEntryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import java.util.List;

/**
 * Service for executing complex queries for AmortizationEntry entities in the database. The main input is a {@link AmortizationEntryCriteria} which gets converted to {@link Specification}, in a way
 * that all the filters must apply. It returns a {@link List} of {@link AmortizationEntryDTO} or a {@link Page} of {@link AmortizationEntryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AmortizationEntryQueryService extends QueryService<AmortizationEntry> {

    private final Logger log = LoggerFactory.getLogger(AmortizationEntryQueryService.class);

    private final AmortizationEntryRepository amortizationEntryRepository;

    private final AmortizationEntryMapper amortizationEntryMapper;

    private final AmortizationEntrySearchRepository amortizationEntrySearchRepository;

    public AmortizationEntryQueryService(AmortizationEntryRepository amortizationEntryRepository, AmortizationEntryMapper amortizationEntryMapper,
                                         AmortizationEntrySearchRepository amortizationEntrySearchRepository) {
        this.amortizationEntryRepository = amortizationEntryRepository;
        this.amortizationEntryMapper = amortizationEntryMapper;
        this.amortizationEntrySearchRepository = amortizationEntrySearchRepository;
    }

    /**
     * Return a {@link List} of {@link AmortizationEntryDTO} which matches the criteria from the database
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AmortizationEntryDTO> findByCriteria(AmortizationEntryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AmortizationEntry> specification = createSpecification(criteria);
        return amortizationEntryMapper.toDto(amortizationEntryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AmortizationEntryDTO} which matches the criteria from the database
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AmortizationEntryDTO> findByCriteria(AmortizationEntryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AmortizationEntry> specification = createSpecification(criteria);
        return amortizationEntryRepository.findAll(specification, page).map(amortizationEntryMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AmortizationEntryCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AmortizationEntry> specification = createSpecification(criteria);
        return amortizationEntryRepository.count(specification);
    }

    /**
     * Function to convert AmortizationEntryCriteria to a {@link Specification}
     */
    private Specification<AmortizationEntry> createSpecification(AmortizationEntryCriteria criteria) {
        Specification<AmortizationEntry> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), AmortizationEntry_.id));
            }
            if (criteria.getAmortizationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmortizationDate(), AmortizationEntry_.amortizationDate));
            }
            if (criteria.getAmortizationAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmortizationAmount(), AmortizationEntry_.amortizationAmount));
            }
            if (criteria.getParticulars() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParticulars(), AmortizationEntry_.particulars));
            }
            if (criteria.getPosted() != null) {
                specification = specification.and(buildSpecification(criteria.getPosted(), AmortizationEntry_.posted));
            }
            if (criteria.getServiceOutlet() != null) {
                specification = specification.and(buildStringSpecification(criteria.getServiceOutlet(), AmortizationEntry_.serviceOutlet));
            }
            if (criteria.getAccountNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountNumber(), AmortizationEntry_.accountNumber));
            }
            if (criteria.getAccountName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountName(), AmortizationEntry_.accountName));
            }
            if (criteria.getPrepaymentEntryId() != null) {
                specification = specification.and(buildSpecification(criteria.getPrepaymentEntryId(), root -> root.join(AmortizationEntry_.prepaymentEntry, JoinType.LEFT).get(PrepaymentEntry_.id)));
            }
        }
        return specification;
    }
}
