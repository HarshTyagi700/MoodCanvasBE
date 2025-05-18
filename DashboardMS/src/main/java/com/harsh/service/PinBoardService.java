package com.harsh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.harsh.dto.PinBoardDTO;
import com.harsh.dto.UserDTO;
import com.harsh.entity.PinBoard;
import com.harsh.exception.PinterestException;
import com.harsh.repository.PinBoardRepository;

@Service
@Transactional
public class PinBoardService {

	
	@Autowired
	private PinBoardRepository pinBoardRepo;
	
	@Autowired
	private RestTemplate restTemp;
	

	
	

	public String addPinsToBoard(PinBoardDTO pinBoardDTO) {
		if(pinBoardDTO.getPinBoardName()==null || pinBoardDTO.getPinBoardName().isBlank())
			pinBoardDTO.setPinBoardName("Saved_Pins");
		String pinBoardId;
		Optional<PinBoard> pinBoardOp=pinBoardRepo.findByUseremailAndPinBoardName(pinBoardDTO.getUseremail(), pinBoardDTO.getPinBoardName());
		
		//If no Existing Board is there so we've to create a new one
		if(pinBoardOp.isEmpty()) {
			PinBoard pinBoard = new PinBoard(pinBoardDTO.getPinBoardName(),pinBoardDTO.getUseremail(),pinBoardDTO.getPinIdList());
			pinBoardId=pinBoardRepo.save(pinBoard).getPinBoardId();
			if(pinBoardDTO.getPinIdList().isEmpty())
				return "Empty Pin Board Created with PinBoardId : "+pinBoardId;
			return "Pins saved to new PinBoard with PinBoardId : "+pinBoardId;
		}
		
		//PinBoard Already exists so we'll update that board
		PinBoard pinBoard=pinBoardOp.get();
		Set<String> pinIdList=pinBoard.getPinIdList();
		for(String pid:pinBoardDTO.getPinIdList()) {
			pinIdList.add(pid);
		}
		pinBoard.setPinIdList(pinIdList);
		pinBoardId=pinBoardRepo.save(pinBoard).getPinBoardId();
	
		return "Pins added to existing PinBoard with PinBoardId : "+pinBoardId;
		
	}
	
	public List<PinBoardDTO> getAllPinBoardsOfUser(String useremail) throws PinterestException{
		String uri="http://localhost:8082/pinterest/user-api/fetch-user";
		UserDTO userDTO=new UserDTO();
		userDTO.setEmailId(useremail);
		userDTO = restTemp.postForObject(uri,userDTO,UserDTO.class);

		if(userDTO==null)
			throw new PinterestException("PinBoardService.USER_NOT_FOUND");
		List<PinBoard> pinBoardList=pinBoardRepo.findAllByUseremail(useremail);
		List<PinBoardDTO> pinBoardDTOList= new ArrayList<>();
		for(PinBoard pb:pinBoardList) {
			PinBoardDTO pinBoardDTO= new PinBoardDTO();
			pinBoardDTO.setPinBoardId(pb.getPinBoardId());
			pinBoardDTO.setPinBoardName(pb.getPinBoardName());
			pinBoardDTO.setUseremail(pb.getUseremail());
			pinBoardDTO.setPinIdList(pb.getPinIdList());
			pinBoardDTOList.add(pinBoardDTO);
		}
		return pinBoardDTOList;
		
		
	}

	public String deletePinsFromPinBoard(PinBoardDTO pinBoardDTO) throws PinterestException {
		StringBuilder response=new StringBuilder("");

		if(pinBoardDTO.getPinBoardName()==null || pinBoardDTO.getPinBoardName().isBlank())
			pinBoardDTO.setPinBoardName("Saved_Pins");
		if(pinBoardDTO.getPinBoardId()==null || pinBoardDTO.getPinBoardId().isBlank())
			throw new PinterestException("PinBoardId is empty. Please give PinBoardId from where the pins have to be deleted !!");
		
		PinBoard pinBoard=pinBoardRepo.findById(pinBoardDTO.getPinBoardId()).orElseThrow( ()-> new PinterestException("No PinBoard exist with this id!!") );
		//next we'll check the pinids that are coming from pinBoardDTO and delete if they exist in PinBoard 
		Set<String> savedPIDList=pinBoard.getPinIdList();
		
		for(String pid:pinBoardDTO.getPinIdList()) {
			Boolean isPIDPresent= savedPIDList.contains(pid);
			if(isPIDPresent==false)
				response.append("Pin with PID : "+pid+ "doesn't exist in PinBoard\n");
			else {
				savedPIDList.remove(pid);
				response.append("Pin with PID : "+pid+ "removed.\n");
			}
		}
		pinBoard.setPinIdList(savedPIDList);
		pinBoardRepo.save(pinBoard);
		response.append("PinBoard updated sucessfully !!");
		return response.toString();
	}
	
}








