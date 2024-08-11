package com.todorian.advertisement.domain.repository;

import com.todorian.advertisement.domain.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

}
