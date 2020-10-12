package com.superleague.microservice.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link LearningSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class LearningSearchRepositoryMockConfiguration {

    @MockBean
    private LearningSearchRepository mockLearningSearchRepository;

}
