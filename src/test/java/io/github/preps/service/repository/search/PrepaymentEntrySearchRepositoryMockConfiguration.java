package io.github.preps.service.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of PrepaymentEntrySearchRepository to test the application without starting Elasticsearch.
 */
@Configuration
public class PrepaymentEntrySearchRepositoryMockConfiguration {

    @MockBean
    private PrepaymentEntrySearchRepository mockPrepaymentEntrySearchRepository;

}
