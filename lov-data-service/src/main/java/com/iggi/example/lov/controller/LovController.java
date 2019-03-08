package com.iggi.example.lov.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iggi.example.lov.data.elasticsearch.document.EsLovData;
import com.iggi.example.lov.data.elasticsearch.service.LovDataEsService;
import com.iggi.example.lov.data.projection.LovDataProjection;
import com.iggi.example.lov.data.service.LovDataService;
import com.iggi.example.lov.model.LovDataRequest;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student/lov")
public class LovController {

    public static final Logger LOGGER = LoggerFactory.getLogger(LovController.class);

    private LovDataService lovDataService;

    private LovDataEsService lovDataEsService;

    @Autowired
    public LovController(LovDataService lovDataService, LovDataEsService lovDataEsService) {
        super();
        this.lovDataService = lovDataService;
        this.lovDataEsService = lovDataEsService;
    }

    @GetMapping
    public ResponseEntity<?> getLovData(@Valid LovDataRequest request) {
        List<LovDataProjection> lovData = null;
        LOGGER.info("Received lovType Request: {}", request);
        try {
            lovData = lovDataService.getLovDataByLovType(request);
        } catch (Exception exception) {
            LOGGER.error("Error while fetching Lov details", exception);
        }
        if (lovData == null || lovData.isEmpty()) {
            return new ResponseEntity<>("No LovData Found for given criteria", HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(lovData);
        }
    }

    @GetMapping("/elastic")
    public ResponseEntity<?> getElasticLovData(@Valid LovDataRequest request) {
        List<EsLovData> lovData = null;
        LOGGER.info("Received enumType Request: {}", request);
        try {
            lovData = lovDataEsService.getLovDataByCriteria(request);
        } catch (Exception exception) {
            LOGGER.error("Error while fetching Lov details from Elastic", exception);
        }

        if (lovData == null || lovData.isEmpty()) {
            return new ResponseEntity<>("No LovData Found for given criteria", HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(lovData);
        }
    }
}
