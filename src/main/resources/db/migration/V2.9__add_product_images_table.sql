create table musinsa.product_images
(
  id         bigint auto_increment comment '아이디'
        primary key,
  product_id bigint                             not null comment '상품 아이디',
  path       varchar(255)                       not null comment '상품 이미지 경로',
  width      int      default 0                 not null comment '상품 이미지 너비',
  height     int      default 0                 not null comment '상품 이미지 높이',
  sort_order int                                not null comment '상품 이미지 정렬 순서',
  is_deleted tinyint  default 0                 not null comment '삭제 여부',
  created_at datetime default CURRENT_TIMESTAMP not null comment '생성 일자',
  updated_at datetime default CURRENT_TIMESTAMP not null comment '수정 일자'
)
  comment '상품 이미지 테이블';

create index product_id_index
  on musinsa.product_images (product_id, is_deleted, sort_order);

create table musinsa.product_images_histories
(
  id            bigint       not null,
  revision_id   bigint       not null,
  revision_type tinyint      not null,
  product_id    bigint       not null,
  path          varchar(255) not null,
  width         int          not null,
  height        int          not null,
  sort_order    int          not null,
  is_deleted    tinyint      not null,
  created_at    datetime     not null,
  updated_at    datetime     not null,
  primary key (id, revision_id)
)
  comment '상품 이미지 이력 테이블';



