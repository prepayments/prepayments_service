package io.github.preps.service.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of AmortizationEntrySearchRepository to test the application without starting Elasticsearch.
 */
@Configuration
public class AmortizationEntrySearchRepositoryMockConfiguration {

    @MockBean
    private AmortizationEntrySearchRepository mockAmortizationEntrySearchRepository;

}
