package com.harsh.api;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.cache.PinBoardCache;
import com.harsh.dto.PinBoardDTO;
import com.harsh.exception.PinterestException;
import com.harsh.service.PinBoardService;

@RestController
@RequestMapping(value= "/pinboard-api")
public class PinBoardAPI {
	
	@Autowired
	private PinBoardService pinBoardService;
	
	@Autowired
	private PinBoardCache pinBoardCache;

	
	@PostMapping(value="/addToPinBoard")
	public ResponseEntity<String> addPinsToDashboard(@RequestBody PinBoardDTO pinBoardDTO) {
		
		String response=pinBoardService.addPinsToBoard(pinBoardDTO);
		pinBoardCache.reloadCache(pinBoardDTO.getUseremail());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping(value="/getPinboards")
	public ResponseEntity<List<PinBoardDTO>> getAllPinBoardsOfUser(@RequestParam String useremail) throws PinterestException, ExecutionException{
		return new ResponseEntity<> (pinBoardCache.getAllPinBoardsOfUser(useremail), HttpStatus.OK );
	}
	
	@PostMapping(value="/deleteFromPinBoard")
	public ResponseEntity<String> deletePinsFromPinBoard(@RequestBody PinBoardDTO pinBoardDTO) throws PinterestException{
		pinBoardCache.reloadCache(pinBoardDTO.getUseremail());
		return new ResponseEntity<> (pinBoardService.deletePinsFromPinBoard(pinBoardDTO),HttpStatus.OK);
	}
	

}
