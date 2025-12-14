create table musinsa.products
(
  id                  bigint auto_increment comment '아이디'
        primary key,
  product_brand_id    bigint                                not null comment '상품 브랜드 아이디',
  product_category_id bigint                                not null comment '상품 카테고리 아이디',
  name                varchar(100)                          not null comment '상품명',
  description         varchar(255)                          null comment '상품 설명',
  standard_price      int                                   not null comment '기준가',
  sale_status         varchar(20) default 'DISCONTINUED'    not null comment '상품 판매 상태(SALE = 판매중, SOLD_OUT = 품절, DISCONTINUED = 판매 중지)',
  sort_order          int         default 10                not null comment '상품 정렬 순서',
  is_deleted          tinyint     default 0                 not null comment '삭제 여부(0: 정상, 1: 삭제)',
  created_at          datetime    default CURRENT_TIMESTAMP not null comment '생성일자',
  updated_at          datetime    default CURRENT_TIMESTAMP not null comment '수정일자',
  constraint products_product_brands_id_fk
    foreign key (product_brand_id) references musinsa.product_brands (id),
  constraint products_product_categories_id_fk
    foreign key (product_category_id) references musinsa.product_categories (id)
)
  comment '상품 테이블';

create fulltext index products_name_index
    on musinsa.products (name);

create index products_product_brand_id_index
  on musinsa.products (product_brand_id asc, sale_status asc, is_deleted asc, sort_order asc, created_at desc);

create index products_product_category_id_index
  on musinsa.products (product_category_id asc, sale_status asc, is_deleted asc, sort_order asc, created_at desc);

create table musinsa.products_histories
(
  id                  bigint       not null,
  revision_id         bigint       not null,
  revision_type       tinyint      not null,
  product_brand_id    bigint       not null,
  product_category_id bigint       not null,
  name                varchar(100) not null,
  description         varchar(255) null,
  standard_price      int          not null,
  sale_status         varchar(20)  not null,
  sort_order          int          not null,
  is_deleted          tinyint      not null,
  created_at          datetime     not null,
  updated_at          datetime     null,
  primary key (id, revision_id)
)
  comment '상품 이력 테이블';

