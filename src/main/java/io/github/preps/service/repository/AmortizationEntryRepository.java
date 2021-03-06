package io.github.preps.service.repository;

import io.github.preps.service.domain.AmortizationEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AmortizationEntry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AmortizationEntryRepository extends JpaRepository<AmortizationEntry, Long>, JpaSpecificationExecutor<AmortizationEntry> {

}
