package io.github.preps.service.repository;

import io.github.preps.service.domain.PrepaymentEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


/**
 * Spring Data  repository for the PrepaymentEntry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrepaymentEntryRepository extends JpaRepository<PrepaymentEntry, Long>, JpaSpecificationExecutor<PrepaymentEntry> {

    PrepaymentEntry findFirstByPrepaymentIdAndPrepaymentDate(String prepaymentId, LocalDate prepaymentDate);
}
