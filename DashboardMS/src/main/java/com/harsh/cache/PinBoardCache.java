package com.harsh.cache;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.harsh.dto.PinBoardDTO;
import com.harsh.service.PinBoardService;

@Configuration
public class PinBoardCache {

	@Autowired
	private PinBoardService pinBoardService;
	
    private static final Logger logger = LoggerFactory.getLogger(PinBoardCache.class);
	
	private CacheLoader<String, List<PinBoardDTO>> cacheLoader= new CacheLoader<>(){
		
		public List<PinBoardDTO> load(String key) throws Exception{
            logger.info("Loading data from service class for key: {}", key);
			return pinBoardService.getAllPinBoardsOfUser(key);
		}
	};
	
	private LoadingCache<String, List<PinBoardDTO>> pinBoardCache= CacheBuilder
															.newBuilder()
															.build(cacheLoader);
	public void reloadCache(String key) {
        logger.info("Refreshing cache for key: {}", key);
		pinBoardCache.refresh(key);
	}
	
	public List<PinBoardDTO> getAllPinBoardsOfUser(String key) throws  ExecutionException{
        logger.info("Fetching PinBoards from cache for key: {}", key);
		return pinBoardCache.get(key);
	}
	
}
