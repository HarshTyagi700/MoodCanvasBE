package com.harsh.elasticRepository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.harsh.entity.ElasticPin;

public interface ElasticPinRepository extends ElasticsearchRepository<ElasticPin,String> {
	
	@Query("{\"bool\": {\"should\": [{\"match\": {\"about\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}, {\"match\": {\"title\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}]}}")
	List<ElasticPin> findByAboutOrTitle(String keyword);

}
