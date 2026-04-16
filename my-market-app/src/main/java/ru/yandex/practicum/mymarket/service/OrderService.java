package ru.yandex.practicum.mymarket.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.mymarket.mapper.ItemMapper;
import ru.yandex.practicum.mymarket.model.CartItem;
import ru.yandex.practicum.mymarket.model.Item;
import ru.yandex.practicum.mymarket.model.Order;
import ru.yandex.practicum.mymarket.repository.ItemRepository;
import ru.yandex.practicum.mymarket.repository.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public Order findById(Long oderId) {
        return orderRepository.findById(oderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order createOrder() {
        List<Item> itemsList = itemRepository.findAllByCountIsGreaterThan(0L);
        Order newOrder = new Order();
        List<CartItem> cartItems = itemsList.stream()
                .map(item -> itemMapper.toCartItem(item, newOrder))
                .toList();
        Long totalCartSum = itemsList.stream()
                .map(item -> item.getPrice() * item.getCount())
                .reduce(0L, Long::sum);
        newOrder.setItems(cartItems);
        newOrder.setTotalPrice(totalCartSum);
        return orderRepository.save(newOrder);
    }
}
