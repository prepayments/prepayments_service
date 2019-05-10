package io.github.preps.service.data_entry;

import io.github.preps.service.service.PrepaymentEntryService;
import org.springframework.cache.annotation.Cacheable;

public interface IPrepaymentEntryIdService extends PrepaymentEntryService {
    /**
     * This method return the DB domain Id whose date and business Id is as given
     *
     * @param prepaymentEntryId
     * @param prepaymentEntryDate
     * @return
     */
    Long findByIdAndDate(String prepaymentEntryId, String prepaymentEntryDate);
}
