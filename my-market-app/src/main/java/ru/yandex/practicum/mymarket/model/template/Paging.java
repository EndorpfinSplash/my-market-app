package ru.yandex.practicum.mymarket.model.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paging {
    private int pageSize;
    private int pageNumber;
    private Boolean hasPrevious;
    private Boolean hasNext;
}
