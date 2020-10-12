package com.superleague.microservice.repository.search;

import com.superleague.microservice.domain.Batch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Batch} entity.
 */
public interface BatchSearchRepository extends ElasticsearchRepository<Batch, Long> {
}
