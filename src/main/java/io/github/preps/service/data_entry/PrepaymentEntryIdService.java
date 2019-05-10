package io.github.preps.service.data_entry;

import io.github.preps.service.repository.PrepaymentEntryRepository;
import io.github.preps.service.repository.search.PrepaymentEntrySearchRepository;
import io.github.preps.service.service.PrepaymentEntryService;
import io.github.preps.service.service.impl.PrepaymentEntryServiceImpl;
import io.github.preps.service.service.mapper.PrepaymentEntryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * The prepayment model is designed unique by both prepaymentDate and prepaymentId. This class uses its repository to
 * implement a method of finding a prepayment by those two fields
 */
@Transactional
@Service("prepaymentIdService")
@Slf4j
public class PrepaymentEntryIdService extends PrepaymentEntryServiceImpl implements PrepaymentEntryService, IPrepaymentEntryIdService {

    private final PrepaymentEntryRepository prepaymentEntryRepository;;

    public PrepaymentEntryIdService(final PrepaymentEntryRepository prepaymentEntryRepository, final PrepaymentEntryMapper prepaymentEntryMapper,
                                    final PrepaymentEntrySearchRepository prepaymentEntrySearchRepository) {
        super(prepaymentEntryRepository, prepaymentEntryMapper, prepaymentEntrySearchRepository);
        this.prepaymentEntryRepository = prepaymentEntryRepository;
    }


    /**
     * This method return the DB domain Id whose date and business Id is as given
     *
     * @param prepaymentEntryId
     * @param prepaymentEntryDate
     * @return
     */
    @Override
    @Cacheable("prepaymentByIdAndDate")
    public Long findByIdAndDate(final String prepaymentEntryId, final String prepaymentEntryDate) {

        log.debug("Finding prepayment with the Id : {} dated : {}", prepaymentEntryId, prepaymentEntryDate);

        //TODO Convert this using system-wide converter
        DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return Optional.of(
            prepaymentEntryRepository
                .findFirstByPrepaymentIdAndPrepaymentDate(
                    prepaymentEntryId, LocalDate.parse(prepaymentEntryDate, dtf)).getId())
                       .orElseThrow(
                           () -> new IllegalStateException("Prepayment with business id: "+ prepaymentEntryId + " and dated : " + prepaymentEntryDate + " was not found"));
    }
}
