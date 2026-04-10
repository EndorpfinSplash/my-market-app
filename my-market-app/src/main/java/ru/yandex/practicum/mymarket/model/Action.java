package ru.yandex.practicum.mymarket.model;

import lombok.Getter;

@Getter
public enum Action {
    MINUS {
        @Override
        public CartItem execute(CartItem cartItem) {
            cartItem.setItemsCounter(cartItem.getItemsCounter() - 1);
            return cartItem;
        }
    },
    PLUS {
        @Override
        public CartItem execute(CartItem cartItem) {
            cartItem.setItemsCounter(cartItem.getItemsCounter() + 1);
            return cartItem;
        }
    };

    public abstract CartItem execute(CartItem cartItem);
}
