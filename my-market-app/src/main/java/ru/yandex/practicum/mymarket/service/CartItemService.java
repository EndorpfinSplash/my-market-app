package ru.yandex.practicum.mymarket.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.mymarket.model.CartItem;
import ru.yandex.practicum.mymarket.repository.CartItemRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public Optional<CartItem> findById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId);
    }
}
