package com.superleague.microservice.repository.search;

import com.superleague.microservice.domain.Learning;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Learning} entity.
 */
public interface LearningSearchRepository extends ElasticsearchRepository<Learning, Long> {
}
