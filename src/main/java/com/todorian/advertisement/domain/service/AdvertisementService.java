package com.todorian.advertisement.domain.service;

import com.todorian.advertisement.domain.model.Advertisement;
import com.todorian.advertisement.domain.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public void save(Advertisement advertisement) {
        advertisementRepository.save(advertisement);
    }
}
