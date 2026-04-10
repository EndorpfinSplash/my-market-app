create table if not exists item
(
    id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title       varchar(256) not null,
    description varchar(256),
    imgPath     varchar(256),
    price       integer
);

create table if not exists "order"
(
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY
);

create table if not exists "cart_item"
(
    id            INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_id      int references "order" (id),
    item_id       int references item (id),
    items_counter int
);

