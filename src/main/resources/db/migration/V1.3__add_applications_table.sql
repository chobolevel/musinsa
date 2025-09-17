create table musinsa.applications
(
  id         bigint auto_increment comment '아이디'
        primary key,
  name       varchar(100) not null comment '애플리케이션명',
  `key`      varchar(100) not null comment '애플리케이션 키',
  secret_key varchar(255) not null comment '애플리케이션 비밀키',
  is_deleted bit          not null comment '삭제 여부',
  created_at datetime     not null comment '생성일자',
  updated_at datetime     not null comment '수정일자',
  constraint applications_pk_2
    unique (`key`)
)
  comment '애플리케이션 테이블';

create index applications_name_index
  on musinsa.applications (name);

create table musinsa.applications_histories
(
  id            bigint       not null,
  revision_id   bigint       not null,
  revision_type tinyint      not null,
  name          varchar(100) not null,
  `key`         varchar(100) not null,
  secret_key    varchar(255) not null,
  is_deleted    bit          not null,
  created_at    datetime     not null,
  updated_at    datetime     not null,
  primary key (id, revision_id)
)
  comment '애플리케이션 이력 테이블';

