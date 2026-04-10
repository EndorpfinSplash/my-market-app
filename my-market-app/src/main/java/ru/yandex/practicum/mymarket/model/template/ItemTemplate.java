package ru.yandex.practicum.mymarket.model.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemTemplate {
    private Long id;
    private String title;
    private String description;
    private String imgPath;
    private BigDecimal price;
    private Long count;
}
