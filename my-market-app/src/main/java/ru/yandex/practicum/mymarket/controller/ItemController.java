package ru.yandex.practicum.mymarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.yandex.practicum.mymarket.model.Item;

@Controller
public class ItemController {

    @GetMapping("/item/{id}")
    public ModelAndView getItem(@PathVariable Long id) {
        Item item = new Item(); // Загружаем товар с помощью сервиса

        // Указываем название шаблона
        ModelAndView modelAndView = new ModelAndView("item"); // classpath:/templates/users/page.html

        // Передаём данные (model)
        modelAndView.addObject("item", item);

        return modelAndView;
    }
}
