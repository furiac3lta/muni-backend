package com.puerto_rico.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.puerto_rico.app.core.StringListConverter;

@Entity
@Table(name = "tourist_places")
@Getter
@Setter
@NoArgsConstructor
public class TouristPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;

    @Column(name = "image_urls", columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    private List<String> imageUrls;

    private String mapsUrl;

    private boolean active = true;
}
