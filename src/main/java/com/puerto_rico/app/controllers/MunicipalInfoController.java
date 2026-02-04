package com.puerto_rico.app.controllers;

import com.puerto_rico.app.core.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.puerto_rico.app.entities.MunicipalInfo;
import com.puerto_rico.app.services.MunicipalInfoService;

@RestController
@RequestMapping
public class MunicipalInfoController {

    private final MunicipalInfoService municipalInfoService;

    public MunicipalInfoController(MunicipalInfoService municipalInfoService) {
        this.municipalInfoService = municipalInfoService;
    }

    @GetMapping("/api/municipality/info")
    public ResponseEntity<ApiResponse<MunicipalInfo>> getInfo() {
        return ResponseEntity.ok(ApiResponse.ok("Informacion municipal", municipalInfoService.getInfo()));
    }

    @PutMapping("/api/admin/municipality/info")
    public ResponseEntity<ApiResponse<MunicipalInfo>> updateInfo(@RequestBody MunicipalInfo request) {
        return ResponseEntity.ok(ApiResponse.ok("Informacion actualizada", municipalInfoService.updateInfo(request)));
    }
}
