create table musinsa.product_brands
(
  id             bigint auto_increment comment '아이디'
        primary key,
  name           varchar(100)             not null comment '브랜드명',
  english_name   varchar(100)             null comment '브랜드 영문명',
  year_of_launch int                      not null comment '브랜드 출범년도',
  nation         varchar(20)              not null comment '브랜드 국가',
  description    varchar(255)             not null comment '브랜드 설명',
  is_deleted     bit                      not null comment '삭제여부',
  created_at     datetime default (now()) not null comment '생성일자',
  updated_at     datetime default (now()) not null comment '수정일자'
)
  comment '상품 브랜드 테이블';

create index brands_english_name_fulltext_index
  on musinsa.product_brands (english_name);

create fulltext index brands_name_fulltext_index
    on musinsa.product_brands (name);

create table musinsa.product_brands_histories
(
  id             bigint       not null,
  revision_id    bigint       not null,
  revision_type  tinyint      not null,
  name           varchar(100) not null,
  english_name   varchar(100) null,
  year_of_launch int          not null,
  nation         varchar(20)  not null,
  description    varchar(255) not null,
  is_deleted     bit          not null,
  created_at     datetime     not null,
  updated_at     datetime     not null,
  primary key (id, revision_id)
)
  comment '상품 브랜드 이력 테이블';

