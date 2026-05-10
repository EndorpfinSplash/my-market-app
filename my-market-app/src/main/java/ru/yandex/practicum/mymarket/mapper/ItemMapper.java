package ru.yandex.practicum.mymarket.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.practicum.mymarket.model.CartItem;
import ru.yandex.practicum.mymarket.model.Item;
import ru.yandex.practicum.mymarket.model.Order;
import ru.yandex.practicum.mymarket.model.template.ItemTemplate;

@Mapper(componentModel = "spring")
public interface ItemMapper {

//    @Mapping(target = "title", source = "item.title")
//    @Mapping(target = "price", source = "item.price")
//    @Mapping(target = "imgPath", source = "item.imgPath")
//    @Mapping(target = "description", source = "item.description")
//    @Mapping(target = "count", source = "itemsCounter")
//    ItemTemplate toTemplateItem(CartItem cartItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", source = "order")
    @Mapping(target = "item", source = "item")
    @Mapping(target = "count", source = "item.count")
    CartItem toCartItem(Item item, Order order);
}
