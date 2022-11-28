create table category
(
    id   bigint       not null
        primary key,
    name varchar(255) null
);

create table customer
(
    id       bigint       not null
        primary key,
    email    varchar(255) null,
    password varchar(255) null
);

create table hibernate_sequence
(
    next_val bigint null
);

create table order_item
(
    id       bigint       not null
        primary key,
    image    varchar(255) null,
    name_ar  varchar(255) null,
    name_en  varchar(255) null,
    price    bigint       null,
    quantity bigint       null
);

create table orders
(
    id bigint not null
        primary key
);

create table customer_orders
(
    customer_id bigint not null,
    orders_id   bigint not null,
    constraint UK_4m0sjmnfkb97mpn89e5xnw3v3
        unique (orders_id),
    constraint FK39sxykqbp8npv4p80lt3p23i8
        foreign key (orders_id) references orders (id),
    constraint FK7ntkighomv9fa5287rev8a3wy
        foreign key (customer_id) references customer (id)
);

create table orders_order_items
(
    order_id       bigint not null,
    order_items_id bigint not null,
    constraint UK_9d47gapmi35omtannusv6btu3
        unique (order_items_id),
    constraint FK3l8rktw0f4w5t6tift31e2d7c
        foreign key (order_id) references orders (id),
    constraint FK7nw03p9mxq154wvbsonaq0qrw
        foreign key (order_items_id) references order_item (id)
);

create table product
(
    id                bigint       not null
        primary key,
    image             varchar(255) null,
    limit_quantity    bigint       null,
    name_ar           varchar(255) null,
    name_en           varchar(255) null,
    num_of_sold_units bigint       null,
    price             bigint       null,
    quantity          bigint       null
);

create table category_products
(
    category_id bigint not null,
    products_id bigint not null,
    constraint UK_fdnk3mk70n1rc08vw1cj65kqw
        unique (products_id),
    constraint FKe9irm5a62pmolhvr468cip3v3
        foreign key (products_id) references product (id),
    constraint FKqwkr0l0xbluhhkm7s0c1tg8en
        foreign key (category_id) references category (id)
);

