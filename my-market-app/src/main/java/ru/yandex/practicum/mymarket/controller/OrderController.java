package ru.yandex.practicum.mymarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.yandex.practicum.mymarket.model.Order;
import ru.yandex.practicum.mymarket.service.OrderService;

import java.util.List;

@Controller
@RequestMapping
public class OrderController {

    OrderService orderService;

    @GetMapping("/orders}")
    public ModelAndView getOrders() {
        List<Order> orders = orderService.findAll();

        ModelAndView modelAndView = new ModelAndView("orders");

        modelAndView.addObject("orders", orders);

        return modelAndView;
    }

    @GetMapping("/orders/{id}")
    public ModelAndView getOrder(@PathVariable(name = "id") Long orderId,
                                 @RequestParam(required = false, defaultValue = "false") Boolean newOrder) {
        Order order = orderService.findById(orderId);

        ModelAndView modelAndView = new ModelAndView("order");

        modelAndView.addObject("order", order);

        return modelAndView;
    }

}
