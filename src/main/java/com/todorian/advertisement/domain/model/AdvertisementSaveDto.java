package com.todorian.advertisement.domain.model;

public class AdvertisementSaveDto {

    private long advertisementId;
    private String advertisementName;
    private AdvertisementType advertisementType;
    private Integer advertisementLength;
    private String advertisementImageUrl;
    private String advertisementMovieUrl;

    public AdvertisementSaveDto() {
    }

    public AdvertisementSaveDto(String advertisementName, AdvertisementType advertisementType,
        Integer advertisementLength, String advertisementImageUrl, String advertisementMovieUrl) {
        this.advertisementName = advertisementName;
        this.advertisementType = advertisementType;
        this.advertisementLength = advertisementLength;
        this.advertisementImageUrl = advertisementImageUrl;
        this.advertisementMovieUrl = advertisementMovieUrl;
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
        return "AdvertisementSaveDto{" +
            "advertisementId=" + advertisementId +
            ", advertisementName='" + advertisementName + '\'' +
            ", advertisementType=" + advertisementType +
            ", advertisementLength=" + advertisementLength +
            ", advertisementImageUrl='" + advertisementImageUrl + '\'' +
            ", advertisementMovieUrl='" + advertisementMovieUrl + '\'' +
            '}';
    }
}