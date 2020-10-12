package com.superleague.microservice.repository.search;

import com.superleague.microservice.domain.SubjectMatterExpert;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link SubjectMatterExpert} entity.
 */
public interface SubjectMatterExpertSearchRepository extends ElasticsearchRepository<SubjectMatterExpert, Long> {
}
