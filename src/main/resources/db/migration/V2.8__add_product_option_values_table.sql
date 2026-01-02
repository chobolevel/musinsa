create table musinsa.product_option_values
(
  id                bigint auto_increment comment '아이디'
        primary key,
  product_option_id bigint       not null comment '상품 옵션 아이디',
  name              varchar(100) not null comment '상품 옵션 값 명',
  sort_order        int          not null comment '상품 옵션 값 정렬 순서',
  is_deleted        tinyint      not null comment '상품 옵션 값 삭제 여부',
  created_at        datetime     not null comment '생성일자',
  updated_at        datetime     not null comment '수정일자',
  constraint product_option_values_product_options_id_fk
    foreign key (product_option_id) references musinsa.product_options (id)
)
  comment '상품 옵션 값 테이블';

create index product_option_values_product_option_id_index
  on musinsa.product_option_values (product_option_id);

create table musinsa.product_option_values_histories
(
  id                bigint       not null,
  revision_id       bigint       not null,
  revision_type     tinyint      not null,
  product_option_id bigint       not null,
  name              varchar(100) not null,
  sort_order        int          not null,
  is_deleted        tinyint      not null,
  created_at        datetime     not null,
  updated_at        datetime     not null,
  primary key (id, revision_id)
)
  comment '상품 옵션 값 테이블 이력 테이블';

