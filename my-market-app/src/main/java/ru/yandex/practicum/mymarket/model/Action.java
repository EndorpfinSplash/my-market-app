package ru.yandex.practicum.mymarket.model;

import lombok.Getter;

@Getter
public enum Action {
    MINUS {
        @Override
        public void execute(Item item) {
            item.setCount(item.getCount() - 1);
        }
    },
    PLUS {
        @Override
        public void execute(Item cartItem) {
            cartItem.setCount(cartItem.getCount() + 1);
        }
    };

    public abstract void execute(Item item);
}
