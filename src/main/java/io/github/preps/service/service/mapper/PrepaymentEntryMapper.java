package io.github.preps.service.service.mapper;

import io.github.preps.service.domain.PrepaymentEntry;
import io.github.preps.service.service.dto.PrepaymentEntryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity PrepaymentEntry and its DTO PrepaymentEntryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PrepaymentEntryMapper extends EntityMapper<PrepaymentEntryDTO, PrepaymentEntry> {


    @Mapping(target = "amortizationEntries", ignore = true)
    PrepaymentEntry toEntity(PrepaymentEntryDTO prepaymentEntryDTO);

    default PrepaymentEntry fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrepaymentEntry prepaymentEntry = new PrepaymentEntry();
        prepaymentEntry.setId(id);
        return prepaymentEntry;
    }
}
