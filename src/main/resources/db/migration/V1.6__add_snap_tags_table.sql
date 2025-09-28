create table musinsa.snap_tags
(
  id         bigint auto_increment comment '아이디'
        primary key,
  type       varchar(20)  not null comment '스냅 태그 유형',
  name       varchar(100) not null comment '스냅 태그명',
  is_deleted bit          not null comment '삭제 여부',
  created_at datetime     not null comment '생성일자',
  updated_at datetime     not null comment '수정일자'
)
  comment '스냅 태그 테이블';

create index snap_tags_type_index
  on musinsa.snap_tags (type);

create table musinsa.snap_tags_histories
(
  id            bigint       not null,
  revision_id   bigint       not null,
  revision_type tinyint      not null,
  type          varchar(20)  not null,
  name          varchar(100) not null,
  is_deleted    bit          not null,
  created_at    datetime     not null,
  updated_at    datetime     not null,
  primary key (id, revision_id)
)
  comment '스냅 태그 이력 테이블';

