create table musinsa.product_categories
(
  id              bigint auto_increment comment '아이디'
        primary key,
  parent_id       bigint       null comment '부모 상품 카테고리 아이디',
  name            varchar(100) not null comment '상품 카테고리명',
  icon_image_path varchar(255) not null comment '상품 카테고리 아이콘 이미지 경로',
  is_deleted      bit          not null comment '삭제여부',
  created_at      datetime     not null comment '생성일자',
  updated_at      datetime     not null comment '수정일자'
)
  comment '상품 카테고리 테이블';

create index product_categories_parent_id_index
  on musinsa.product_categories (parent_id);

create table musinsa.product_categories_histories
(
  id              bigint       not null,
  revision_id     bigint       not null,
  revision_type   tinyint      not null,
  parent_id       bigint       null,
  name            varchar(100) not null,
  icon_image_path varchar(255) not null,
  is_deleted      bit          not null,
  created_at      datetime     not null,
  updated_at      datetime     null,
  primary key (id, revision_id)
)
  comment '상품 카테고리 이력 테이블';

