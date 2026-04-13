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
import ru.yandex.practicum.mymarket.model.ItemSortParameter;
import ru.yandex.practicum.mymarket.model.template.ItemTemplate;
import ru.yandex.practicum.mymarket.service.CartItemService;
import ru.yandex.practicum.mymarket.service.ItemService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/items")
public class ItemController {

    CartItemService cartItemService;
    ItemService itemService;
    ItemMapper itemMapper;

    @GetMapping("/")
    public ModelAndView getItems(@RequestParam(required = false) String search,
                                 @RequestParam(value = "sort", defaultValue = "NO") ItemSortParameter itemSortParameter,
                                 @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                 @RequestParam(name = "pageSize", defaultValue = "5") int pageSize
    ) {
        List<Item> items = itemService.findItemsPage(search,itemSortParameter, pageNumber, pageSize); // Загружаем товар с помощью сервиса

        // Указываем название шаблона
        ModelAndView modelAndView = new ModelAndView("items"); // classpath:/templates/users/page.html

        // Передаём данные (model)
        modelAndView.addObject("item", items);

        return modelAndView;
    }

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
