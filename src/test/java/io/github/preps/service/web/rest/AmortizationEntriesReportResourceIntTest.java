package io.github.preps.service.web.rest;

import io.github.preps.service.PrepaymentsserviceApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the AmortizationEntriesReportResource REST controller.
 *
 * @see AmortizationEntriesReportResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PrepaymentsserviceApp.class)
public class AmortizationEntriesReportResourceIntTest {

    private MockMvc restMockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        AmortizationEntriesReportResource amortizationEntriesReportResource = new AmortizationEntriesReportResource();
        restMockMvc = MockMvcBuilders
            .standaloneSetup(amortizationEntriesReportResource)
            .build();
    }

    /**
     * Test getAllReportAmortizationEntries
     */
    @Test
    public void testGetAllReportAmortizationEntries() throws Exception {
        restMockMvc.perform(get("/api/amortization-entries-report/get-all-report-amortization-entries"))
            .andExpect(status().isOk());
    }
}
