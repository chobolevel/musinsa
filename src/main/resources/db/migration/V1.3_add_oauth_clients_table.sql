create table musinsa.oauth_clients
(
  id            bigint auto_increment comment '아이디'
        primary key,
  client_id     varchar(100) not null,
  client_secret varchar(255) not null,
  redirect_url  varchar(255) not null comment '콜백 경로',
  is_deleted    bit          not null comment '삭제 여부',
  created_at    datetime     not null comment '생성 일자',
  updated_at    datetime     not null comment '수정 일자'
)
  comment 'SSO 테이블';

create index client_id_index
  on musinsa.oauth_clients (client_id);

create table musinsa.oauth_clients_histories
(
  id            bigint       not null,
  revision_id   bigint       not null,
  revision_type tinyint      not null,
  client_id     varchar(100) not null,
  client_secret varchar(255) not null,
  redirect_url  varchar(255) not null,
  is_deleted    bit          not null,
  created_at    datetime     not null,
  updated_at    datetime     not null,
  primary key (id, revision_id)
)
  comment 'SSO 이력 테이블';

