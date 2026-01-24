create table musinsa.product_inventories
(
  id         bigint auto_increment comment '아이디'
        primary key,
  product_id bigint                             not null comment '상품 아이디',
  stock      int                                not null comment '재고 수량',
  created_at datetime default CURRENT_TIMESTAMP not null comment '생성 일자',
  updated_at datetime default CURRENT_TIMESTAMP not null comment '수정 일자',
  constraint product_inventories_products_id_fk
    foreign key (product_id) references musinsa.products (id)
)
  comment '상품 재고 테이블';

create table musinsa.product_inventory_values
(
  id                      bigint auto_increment comment '아이디'
        primary key,
  product_inventory_id    bigint                             not null comment '상품 재고 아이디',
  product_option_value_id bigint                             not null comment '상품 옵션 값 아이디',
  created_at              datetime default CURRENT_TIMESTAMP not null comment '생서 일자',
  updated_at              datetime default CURRENT_TIMESTAMP not null comment '수정 일자',
  constraint product_inventory_values_product_inventories_id_fk
    foreign key (product_inventory_id) references musinsa.product_inventories (id),
  constraint product_inventory_values_product_option_values_id_fk
    foreign key (product_option_value_id) references musinsa.product_option_values (id)
)
  comment '상품 재고 값 테이블';

create index product_inventory_values_product_option_value_id_index
  on musinsa.product_inventory_values (product_option_value_id);

