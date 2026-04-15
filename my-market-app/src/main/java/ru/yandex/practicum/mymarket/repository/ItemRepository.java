package ru.yandex.practicum.mymarket.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.mymarket.model.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByOrderBy(Sort sort);

    List<Item> findAllByTitleContainsIgnoreCaseOrDescriptionContainingIgnoreCase(String title,
                                                                                 String description);

    List<Item> findAllByTitleContainsIgnoreCaseOrDescriptionContainingIgnoreCaseOrderBy(String title,
                                                                                        String description,
                                                                                        Sort sort);

    List<Item> findAllByCountIsGreaterThan(Long itemsCount);
}
