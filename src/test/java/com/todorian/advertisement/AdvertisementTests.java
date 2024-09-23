package com.todorian.advertisement;

import com.todorian.advertisement.domain.model.Advertisement;
import com.todorian.advertisement.domain.model.AdvertisementType;
import com.todorian.advertisement.domain.service.AdvertisementService;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdvertisementTests {

    @Autowired
    private AdvertisementService advertisementService;

    private static Stream<Arguments> saveAdvertisement() {
        return Stream.of(
            Arguments.of("동영상 광고", AdvertisementType.MOVIE, 15, null, "www.naver.com"),
            Arguments.of("이미지 광고", AdvertisementType.IMAGE, 0, "www.daum.net", null)
        );
    }

    @DisplayName("광고 엔티티 save 테스트")
    @ParameterizedTest
    @MethodSource("saveAdvertisement")
    void saveAdvertisement(String advertisementName, AdvertisementType advertisementType,
        Integer advertisementLength,
        String advertisementImageUrl, String advertisementMovieUrl) {
        Assertions.assertDoesNotThrow(
            () -> advertisementService.save(
                new Advertisement(advertisementName, advertisementType, advertisementLength,
                    advertisementImageUrl, advertisementMovieUrl)
            )
        );
    }
}