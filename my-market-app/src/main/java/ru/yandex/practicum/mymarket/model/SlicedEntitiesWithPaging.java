package ru.yandex.practicum.mymarket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.mymarket.model.template.Paging;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlicedEntitiesWithPaging {
    List<List<Item>> slicedEntities;
    Paging paging;
}
