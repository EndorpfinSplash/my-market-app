package ru.yandex.practicum.mymarket.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.mymarket.model.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllOrderBy(Sort sort);

    List<Item> findAllByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String title,
                                                                               String description);

    List<Item> findAllByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String title,
                                                                               String description,
                                                                               Sort sort);

    List<Item> findAllByCountIsGreaterThan(Long itemsCount);
}
