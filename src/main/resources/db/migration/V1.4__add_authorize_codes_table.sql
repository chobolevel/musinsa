create table musinsa.authorize_codes
(
  id         bigint auto_increment comment '아이디'
        primary key,
  code       varchar(255) not null comment '코드',
  created_at datetime     not null comment '생성 일자',
  updated_at datetime     not null comment '수정 일자'
)
  comment '인가 코드 테이블';

create table musinsa.authorize_codes_histories
(
  id            bigint       not null,
  revision_id   bigint       not null,
  revision_type tinyint      not null,
  code          varchar(255) not null,
  created_at    datetime     not null,
  updated_at    datetime     not null,
  primary key (id, revision_id)
)
  comment '인가 코드 이력 테이블';

