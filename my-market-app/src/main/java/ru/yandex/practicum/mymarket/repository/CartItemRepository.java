package ru.yandex.practicum.mymarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.mymarket.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
