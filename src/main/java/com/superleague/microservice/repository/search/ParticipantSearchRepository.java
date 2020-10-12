package com.superleague.microservice.repository.search;

import com.superleague.microservice.domain.Participant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Participant} entity.
 */
public interface ParticipantSearchRepository extends ElasticsearchRepository<Participant, Long> {
}
