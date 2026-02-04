package com.puerto_rico.app.services;

import com.puerto_rico.app.services.AuditService;
import com.puerto_rico.app.core.ResourceNotFoundException;
import com.puerto_rico.app.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import com.puerto_rico.app.entities.TouristPlace;
import com.puerto_rico.app.repositories.TouristPlaceRepository;

@Service
public class TouristPlaceService {

    private final TouristPlaceRepository placeRepository;
    private final AuditService auditService;

    public TouristPlaceService(TouristPlaceRepository placeRepository, AuditService auditService) {
        this.placeRepository = placeRepository;
        this.auditService = auditService;
    }

    public List<TouristPlace> findActive() {
        return placeRepository.findByActiveTrue();
    }

    public TouristPlace create(TouristPlace request) {
        TouristPlace place = new TouristPlace();
        place.setName(request.getName());
        place.setDescription(request.getDescription());
        place.setImageUrl(request.getImageUrl());
        if (request.getImageUrls() != null && !request.getImageUrls().isEmpty()) {
            place.setImageUrls(request.getImageUrls());
            place.setImageUrl(request.getImageUrls().get(0));
        }
        place.setMapsUrl(request.getMapsUrl());
        place.setActive(request.isActive());

        TouristPlace saved = placeRepository.save(place);
        auditService.log("TouristPlace", "CREATE", SecurityUtils.currentUsername());
        return saved;
    }

    public TouristPlace update(Long id, TouristPlace request) {
        TouristPlace place = placeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Lugar turistico no encontrado"));

        place.setName(request.getName());
        place.setDescription(request.getDescription());
        place.setImageUrl(request.getImageUrl());
        if (request.getImageUrls() != null && !request.getImageUrls().isEmpty()) {
            place.setImageUrls(request.getImageUrls());
            place.setImageUrl(request.getImageUrls().get(0));
        }
        place.setMapsUrl(request.getMapsUrl());
        place.setActive(request.isActive());

        TouristPlace saved = placeRepository.save(place);
        auditService.log("TouristPlace", "UPDATE", SecurityUtils.currentUsername());
        return saved;
    }
}
