package io.github.preps.service.repository.search;

import io.github.preps.service.domain.PrepaymentEntry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PrepaymentEntry entity.
 */
public interface PrepaymentEntrySearchRepository extends ElasticsearchRepository<PrepaymentEntry, Long> {
}
