package io.github.preps.service.service.mapper;

import io.github.preps.service.domain.AmortizationEntry;
import io.github.preps.service.service.dto.AmortizationEntryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity AmortizationEntry and its DTO AmortizationEntryDTO.
 */
@Mapper(componentModel = "spring", uses = {PrepaymentEntryMapper.class})
public interface AmortizationEntryMapper extends EntityMapper<AmortizationEntryDTO, AmortizationEntry> {

    @Mapping(source = "prepaymentEntry.id", target = "prepaymentEntryId")
    AmortizationEntryDTO toDto(AmortizationEntry amortizationEntry);

    @Mapping(source = "prepaymentEntryId", target = "prepaymentEntry")
    AmortizationEntry toEntity(AmortizationEntryDTO amortizationEntryDTO);

    default AmortizationEntry fromId(Long id) {
        if (id == null) {
            return null;
        }
        AmortizationEntry amortizationEntry = new AmortizationEntry();
        amortizationEntry.setId(id);
        return amortizationEntry;
    }
}
