package ru.yandex.practicum.mymarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.yandex.practicum.mymarket.mapper.ItemMapper;
import ru.yandex.practicum.mymarket.model.Action;
import ru.yandex.practicum.mymarket.model.CartItem;
import ru.yandex.practicum.mymarket.model.Item;
import ru.yandex.practicum.mymarket.model.template.ItemTemplate;
import ru.yandex.practicum.mymarket.service.CartItemService;

import java.util.Optional;

@Controller
@RequestMapping("/items")
public class ItemController {

    CartItemService cartItemService;
    ItemMapper itemMapper;

    @GetMapping("/{id}")
    public ModelAndView getItem(@PathVariable Long id) {
        Item item = new Item(); // Загружаем товар с помощью сервиса

        // Указываем название шаблона
        ModelAndView modelAndView = new ModelAndView("item"); // classpath:/templates/users/page.html

        // Передаём данные (model)
        modelAndView.addObject("item", item);

        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView getItem(@PathVariable(name = "id") Long cartItemId,
                                @RequestParam Action action) {

        Optional<CartItem> cartItemOptional = cartItemService.findById(cartItemId);
        CartItem cartItem = cartItemOptional.orElseThrow(() -> new RuntimeException("Item not found"));
        action.execute(cartItem);
        ItemTemplate itemTemplate = itemMapper.toTemplateItem(cartItem);
        // Указываем название шаблона
        ModelAndView modelAndView = new ModelAndView("item"); // classpath:/templates/users/page.html

        // Передаём данные (model)
        modelAndView.addObject("item", itemTemplate);

        return modelAndView;
    }
}
