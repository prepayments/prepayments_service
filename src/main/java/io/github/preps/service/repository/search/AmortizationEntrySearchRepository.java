package io.github.preps.service.repository.search;

import io.github.preps.service.domain.AmortizationEntry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AmortizationEntry entity.
 */
public interface AmortizationEntrySearchRepository extends ElasticsearchRepository<AmortizationEntry, Long> {
}
