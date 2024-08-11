package com.todorian.advertisement.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_advertisement")
public class Advertisement {

    @Id
    @Column(name = "advertisement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long advertisementId;

    private String advertisementName;

    private AdvertisementType advertisementType;

    private Integer advertisementLength;

    private String advertisementImageUrl;

    private String advertisementMovieUrl;

    public Advertisement(String advertisementName, AdvertisementType advertisementType,
        Integer advertisementLength, String advertisementImageUrl, String advertisementMovieUrl) {
        this.advertisementName = advertisementName;
        this.advertisementType = advertisementType;
        this.advertisementLength = advertisementLength;
        this.advertisementImageUrl = advertisementImageUrl;
        this.advertisementMovieUrl = advertisementMovieUrl;
    }

    public Advertisement() {
    }

    public long getAdvertisementId() {
        return advertisementId;
    }

    public String getAdvertisementName() {
        return advertisementName;
    }

    public AdvertisementType getAdvertisementType() {
        return advertisementType;
    }

    public Integer getAdvertisementLength() {
        return advertisementLength;
    }

    public String getAdvertisementImageUrl() {
        return advertisementImageUrl;
    }

    public String getAdvertisementMovieUrl() {
        return advertisementMovieUrl;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
            "advertisementId=" + advertisementId +
            ", advertisementName='" + advertisementName + '\'' +
            ", advertisementType=" + advertisementType +
            ", advertisementLength=" + advertisementLength +
            ", advertisementImageUrl='" + advertisementImageUrl + '\'' +
            ", advertisementMovieUrl='" + advertisementMovieUrl + '\'' +
            '}';
    }
}