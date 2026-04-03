package ru.yandex.practicum.mymarket.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.mymarket.repository.ItemRepository;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

}
