create table musinsa.snaps
(
  id         bigint auto_increment comment '아이디'
        primary key,
  writer_id  bigint   not null comment '작성자 아이디',
  content    text     null comment '스냅 내용',
  is_deleted bit      not null comment '삭제여부',
  created_at datetime not null comment '생성일자',
  updated_at datetime not null comment '수정일자',
  constraint snaps_users_id_fk
    foreign key (writer_id) references musinsa.users (id)
)
  comment '스냅 테이블';

create table musinsa.snaps_histories
(
  id            bigint   not null,
  revision_id   bigint   not null,
  revision_type tinyint  not null,
  writer_id     bigint   not null,
  content       text     null,
  is_deleted    bit      not null,
  created_at    datetime not null,
  updated_at    datetime not null,
  primary key (id, revision_id)
)
  comment '스냅 이력 테이블';

