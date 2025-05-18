package com.harsh.api;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.harsh.dto.PinDTO;
import com.harsh.entity.ElasticPin;
import com.harsh.entity.Pin;
import com.harsh.exception.PinterestException;
import com.harsh.service.ElasticReindexService;
import com.harsh.service.PinsService;

@RestController
@RequestMapping(value= "/pins-api")
public class PinsAPI {
	
	@Autowired
	PinsService pinsService;
	Logger logger = LoggerFactory.getLogger(PinsAPI.class);
	
	@Autowired
    private ElasticReindexService reindexService;
	
	@PostMapping("/reindex")
    public String reindex() {
        return reindexService.reindexAllPinsToElastic();
    }
	
	@GetMapping(value="/pin/{pinId}")
	public ResponseEntity<Pin> getPin(@PathVariable String pinId) throws PinterestException {
		return new ResponseEntity<>( pinsService.getPinByPinId(pinId), HttpStatus.OK);
	}
	
	
	@PostMapping(value="/upload")
	public ResponseEntity<Pin> uploadPin(@RequestParam("image") MultipartFile image,@RequestParam("title") String title, 
			  @RequestParam("about") String about,@RequestParam("useremail") String useremail) throws IOException
	{
		PinDTO pinDTO = new PinDTO();
		pinDTO.setTitle(title);
		pinDTO.setAbout(about);
		pinDTO.setUserEmail(useremail);
	 return new ResponseEntity<> ( pinsService.uploadPin(pinDTO, image) ,HttpStatus.CREATED);
	}
	
	@GetMapping(value="/{useremail}")
	public ResponseEntity<List<Pin>> getAllPinsOfUser(@PathVariable String useremail) throws IOException
	{
	 return new ResponseEntity<> ( pinsService.getAllPinsOfUser(useremail) ,HttpStatus.OK);
	}
	
	@GetMapping(value="/all")
	public ResponseEntity<List<Pin>> getAllPins()
	{
	 return new ResponseEntity<> ( pinsService.findAllPins() ,HttpStatus.OK);
	}
	
	@GetMapping(value="/search/")
	public ResponseEntity<List<ElasticPin>> getAllPinsContainingText(@RequestParam String keyword){
		List<ElasticPin> searchResults=pinsService.getPinsByKeyword(keyword);
		logger.info(searchResults.toString());
		return new ResponseEntity<>(searchResults,HttpStatus.OK);
	}
	
	

}
