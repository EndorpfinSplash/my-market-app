package ru.yandex.practicum.mymarket.controller;

import org.jspecify.annotations.NonNull;
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

import java.util.List;

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

    @PostMapping(path = "/items")
    public String getItems(@RequestParam(value = "id") Long itemId,
                           @RequestParam(required = false) String search,
                           @RequestParam(value = "sort", defaultValue = "NO") ItemSortParameter itemSortParameter,
                           @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                           @RequestParam Action action
    ) {
        itemService.changeItemQuantity(itemId, action);
        return String.format("redirect:/items?search=%s&sort=%s&pageNumber=%d&pageSize=%d",
                search,
                itemSortParameter,
                pageNumber,
                pageSize
        );
    }

    @GetMapping("/items/{id}")
    public ModelAndView getItem(@PathVariable(name = "id") Long itemId) {
        Item item = itemService.getItem(itemId);

        ModelAndView modelAndView = new ModelAndView("item");

        modelAndView.addObject("item", item);

        return modelAndView;
    }

    @PostMapping("/items/{id}")
    public ModelAndView changeItemQuantity(@PathVariable(name = "id") Long itemId,
                                           @RequestParam Action action) {
        Item item = itemService.changeItemQuantity(itemId, action);

        ModelAndView modelAndView = new ModelAndView("item");

        modelAndView.addObject("item", item);

        return modelAndView;
    }

    @GetMapping("/cart/items")
    public ModelAndView getCartItems() {
        return getCartModelAndView();
    }

    @PostMapping("/cart/items")
    public ModelAndView changeCartItemsQuantity(@RequestParam(name = "id") Long itemId,
                                                @RequestParam Action action) {
        itemService.changeItemQuantity(itemId, action);
        return getCartModelAndView();
    }

    @NonNull
    private ModelAndView getCartModelAndView() {
        List<Item> items = itemService.getCartItems();

        ModelAndView modelAndView = new ModelAndView("cart");

        modelAndView.addObject("items", items);
        Long total = items.stream()
                .map(item -> item.getPrice()*item.getCount())
                .reduce(0L, Long::sum);
        modelAndView.addObject("total", total);

        return modelAndView;
    }

}
