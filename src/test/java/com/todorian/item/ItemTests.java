package com.todorian.item;

import com.todorian.item.domain.ItemCategory;
import com.todorian.item.dto.ItemDto;
import com.todorian.item.service.ItemService;
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
//            Arguments.of(3L, "어마무시한 칼", "어~~마무시한 칼입니다..", ItemCategory.WEAPON),
//            Arguments.of(4L, "어마무시한 모자", "어~~마무시한 모자입니다,,", ItemCategory.WEAPON)
            Arguments.of("어마무시한 칼", "어~~마무시한 칼입니다..", ItemCategory.WEAPON),
            Arguments.of("어마무시한 모자", "어~~마무시한 모자입니다,,", ItemCategory.WEAPON)
            );
    }

    @DisplayName("테이블 만들기 테스트")
    @ParameterizedTest
    @MethodSource("createItem")
    void createItem(String itemName, String itemDescription,
        ItemCategory itemCategory) {
        ItemDto itemDto = ItemDto.builder()
            .itemName(itemName)
            .itemDescription(itemDescription)
            .itemCategory(itemCategory)
            .build();

        Assertions.assertDoesNotThrow(() -> itemService.save(itemDto));
    }
}