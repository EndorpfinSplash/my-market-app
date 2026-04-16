package ru.yandex.practicum.mymarket.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.mymarket.model.Order;
import ru.yandex.practicum.mymarket.repository.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order findById(Long oderId) {
        return orderRepository.findById(oderId)
                .orElseThrow(()->  new RuntimeException("Order not found"));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
