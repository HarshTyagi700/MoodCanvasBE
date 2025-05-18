package com.harsh.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.harsh.service.ElasticReindexService;

@RestController
@RequestMapping("/admin")
public class ReindexController {

    @Autowired
    private ElasticReindexService reindexService;

    @PostMapping("/reindex")
    public String reindex() {
        return reindexService.reindexAllPinsToElastic();
    }
}