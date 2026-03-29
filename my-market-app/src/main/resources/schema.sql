create table if not exists item
(
    id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title       varchar(256) not null,
    description varchar(256),
    imgPath     varchar(256),
    price       integer
);
