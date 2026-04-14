package ru.yandex.practicum.mymarket.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.mymarket.model.Action;
import ru.yandex.practicum.mymarket.model.Item;
import ru.yandex.practicum.mymarket.model.ItemSortParameter;
import ru.yandex.practicum.mymarket.model.SlicedEntitiesWithPaging;
import ru.yandex.practicum.mymarket.model.template.Paging;
import ru.yandex.practicum.mymarket.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private static final int ROW_SIZE = 3;

    public SlicedEntitiesWithPaging findItemsPage(String search,
                                          ItemSortParameter itemSortParameter,
                                          int pageNumber,
                                          int pageSize) {
        List<Item> searchedItems = findItems(search, itemSortParameter);

        int lastPage = (int) Math.ceil((double) searchedItems.size() / pageSize);
        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, searchedItems.size());
        List<Item> itemList = searchedItems.subList(fromIndex, toIndex);

        List<List<Item>> slicedItems = getSlicedEntities(itemList, ROW_SIZE);

        Paging paging = Paging.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .hasPrevious(pageNumber > 1)
                .hasNext(pageNumber < lastPage)
                .build();

        return SlicedEntitiesWithPaging.builder()
                .slicedEntities(slicedItems)
                .paging(paging)
                .build();

    }

    private List<Item> findItems(String search,
                                 ItemSortParameter itemSortParameter
    ) {
        if ((search == null || search.isEmpty()) && itemSortParameter.equals(ItemSortParameter.NO)) {
            return itemRepository.findAll();
        }

        if ((search == null || search.isEmpty())) {
            return itemRepository.findAllByOrderBy(Sort.by(itemSortParameter.getFieldName()));
        }

        if (itemSortParameter.equals(ItemSortParameter.NO)) {
            return itemRepository.findAllByTitleContainsIgnoreCaseOrDescriptionContainingIgnoreCase(search, search);
        }

        return itemRepository.findAllByTitleContainsIgnoreCaseOrDescriptionContainingIgnoreCaseOrderBy(
                search,
                search,
                Sort.by(itemSortParameter.getFieldName())
        );
    }

    private <T> List<List<T>> getSlicedEntities(List<T> itemList, int rowSize) {
        List<List<T>> slicedItems = new ArrayList<>();
        if (itemList == null) {
            return slicedItems;
        }
        int fullSlicesCount = itemList.size() / rowSize;
        int remainSlice = itemList.size() % rowSize;

        for (int i = 0; i < fullSlicesCount; i++) {
            slicedItems.add(itemList.subList(i * rowSize, i * rowSize + rowSize));
        }

        slicedItems.add(itemList.subList(fullSlicesCount * rowSize, remainSlice));

        return slicedItems;
    }

    public Item changeItemQuantity(Long itemId, Action action) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        Item item = itemOptional.orElseThrow(() -> new RuntimeException("Item not found"));
        action.execute(item);
        return itemRepository.save(item);
    }
}
