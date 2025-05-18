package com.harsh.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.entity.Pin;          // MongoDB entity
import com.harsh.entity.ElasticPin;  // Elasticsearch entity
import com.harsh.repository.PinRepository;
import com.harsh.elasticRepository.ElasticPinRepository;

@Service
public class ElasticReindexService {

	@Autowired
	private PinRepository mongoPinRepo;

    @Autowired
    private ElasticPinRepository elasticPinRepo;
    
    public String reindexAllPinsToElastic() {
        List<Pin> mongoPins = mongoPinRepo.findAll();

        if (mongoPins.isEmpty()) {
            return "No pins found in MongoDB to reindex.";
        }

        List<ElasticPin> elasticPins = mongoPins.stream()
            .map(pin -> new ElasticPin(
                pin.getId(),
                pin.getTitle(),
                pin.getAbout(),
                pin.getUserEmail()
            ))
            .collect(Collectors.toList());

        elasticPinRepo.saveAll(elasticPins);

        return elasticPins.size() + " pins reindexed to Elasticsearch.";
    }
}

