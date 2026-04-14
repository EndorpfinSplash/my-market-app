package ru.yandex.practicum.mymarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.yandex.practicum.mymarket.model.Action;
import ru.yandex.practicum.mymarket.model.Item;
import ru.yandex.practicum.mymarket.model.ItemSortParameter;
import ru.yandex.practicum.mymarket.model.SlicedEntitiesWithPaging;
import ru.yandex.practicum.mymarket.service.ItemService;

@Controller
@RequestMapping
public class ItemController {

    ItemService itemService;

    @GetMapping(path = {"/items", "/"})
    public ModelAndView getItems(@RequestParam(required = false) String search,
                                 @RequestParam(value = "sort", defaultValue = "NO") ItemSortParameter itemSortParameter,
                                 @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                 @RequestParam(name = "pageSize", defaultValue = "5") int pageSize
    ) {
        SlicedEntitiesWithPaging slicedEntitiesWithPaging = itemService.findItemsPage(search, itemSortParameter, pageNumber, pageSize);

        // Указываем название шаблона
        ModelAndView modelAndView = new ModelAndView("items"); // classpath:/templates/users/page.html

        // Передаём данные (model)
        modelAndView.addObject("items", slicedEntitiesWithPaging.getSlicedEntities());
        modelAndView.addObject("paging", slicedEntitiesWithPaging.getPaging());

        return modelAndView;
    }

    @GetMapping("/items/{id}")
    public ModelAndView getItem(@PathVariable Long id) {
        Item item = new Item(); // Загружаем товар с помощью сервиса

        // Указываем название шаблона
        ModelAndView modelAndView = new ModelAndView("item"); // classpath:/templates/users/page.html

        // Передаём данные (model)
        modelAndView.addObject("item", item);

        return modelAndView;
    }

    @PostMapping("/items/{id}")
    public ModelAndView changeItemQuantity(@PathVariable(name = "id") Long itemId,
                                           @RequestParam Action action) {

        Item item = itemService.changeItemQuantity(itemId, action);
        // Указываем название шаблона
        ModelAndView modelAndView = new ModelAndView("item"); // classpath:/templates/users/page.html

        // Передаём данные (model)
        modelAndView.addObject("item", item);

        return modelAndView;
    }
}
