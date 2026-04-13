package ru.yandex.practicum.mymarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemSortParameter {
    NO (""),
    ALPHA ("title"),
    PRICE ("price");

    public final String fieldName;
}
