package com.superleague.microservice.repository.search;

import com.superleague.microservice.domain.Sprint;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Sprint} entity.
 */
public interface SprintSearchRepository extends ElasticsearchRepository<Sprint, Long> {
}
