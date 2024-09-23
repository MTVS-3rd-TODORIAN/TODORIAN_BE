package com.todorian.item;

import com.todorian.item.domain.model.ItemCategory;
import com.todorian.item.domain.model.ItemCreateDto;
import com.todorian.item.domain.service.ItemService;
import jakarta.transaction.Transactional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class ItemTests {

    @Autowired
    private ItemService itemService;

    private static Stream<Arguments> createItem() {
        return Stream.of(
            Arguments.of("어마무시한 칼", "어~~마무시한 칼입니다..", 10L, ItemCategory.WEAPON),
            Arguments.of("어마무시한 모자", "어~~마무시한 모자입니다,,", 5L, ItemCategory.WEAPON)
        );
    }

    @DisplayName("아이템 추가 테스트")
    @ParameterizedTest
    @MethodSource("createItem")
    void createItem(String itemName, String itemDescription, long itemPrice,
        ItemCategory itemCategory) {
        ItemCreateDto itemCreateDto = new ItemCreateDto(itemName, itemDescription, itemPrice,
            itemCategory);
        Assertions.assertDoesNotThrow(
            () -> itemService.save(itemCreateDto)
        );
    }
}