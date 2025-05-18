package com.harsh.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.harsh.dto.PinDTO;
import com.harsh.elasticRepository.ElasticPinRepository;
import com.harsh.entity.ElasticPin;
import com.harsh.entity.Pin;
import com.harsh.exception.PinterestException;
import com.harsh.repository.PinRepository;

@Service
@Transactional
public class PinsService {

//	@Autowired
//    private MongoTemplate mongoTemplate;
	
	@Autowired
	private PinRepository pinsRepo;
	
	@Autowired
	private ElasticPinRepository elPinRepo;

	public Pin uploadPin(PinDTO pinDTO,MultipartFile file) throws IOException {
		//Username exists or not check (we've to ensure only loggedin user is able to upload pins)
		Pin pin = new Pin();
		pin.setTitle(pinDTO.getTitle());
		pin.setAbout(pinDTO.getAbout());
		pin.setUserEmail(pinDTO.getUserEmail());
		pin.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
		
		Pin pinFromRepo=pinsRepo.insert(pin);
		
		//adding Pin Details to Elastic Repositorys
		addToElasticRepo(pinFromRepo);
	
		return pinFromRepo;
		
	}
	
	public List<Pin> getAllPinsOfUser(String useremail){
		return pinsRepo.findAllByUserEmail(useremail);
	}
	
	public List<ElasticPin> getPinsByKeyword(String keyword) {
//	    Query query = new Query(Criteria.where("about").regex(keyword, "i"));
//	    return mongoTemplate.find(query, Pin.class);
		return elPinRepo.findByAboutOrTitle(keyword);
		
	}
    
    public List<Pin> findAllPins(){
    	return  pinsRepo.findAll();
    	
    	
    }

	public Pin getPinByPinId(String pinId) throws PinterestException {
		// TODO Auto-generated method stub
		Optional<Pin> pinOp=pinsRepo.findById(pinId);
		if(pinOp.isEmpty())
			throw new PinterestException("No Pin exist with this ID. Please try again !!");
		
		return pinOp.get();
	}
	
	public void addToElasticRepo(Pin pin) {
		ElasticPin elPin=new ElasticPin();
		elPin.setId(pin.getId());
		elPin.setTitle(pin.getTitle());
		elPin.setUserEmail(pin.getUserEmail());
		elPin.setAbout(pin.getAbout());
		elPinRepo.save(elPin);
	}
}
