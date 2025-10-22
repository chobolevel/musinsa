create table musinsa.snap_comments
(
  id         bigint auto_increment comment '아이디'
        primary key,
  parent_id  bigint       null comment '부모 스냅 코멘트 아이디',
  snap_id    bigint       not null comment '스냅 아이디',
  writer_id  bigint       not null comment '작성자 아이디',
  comment    varchar(255) not null comment '코멘트',
  is_deleted bit          not null comment '삭제여부',
  created_at datetime     not null comment '생성일자',
  updated_at datetime     not null comment '수정일자',
  constraint snap_comments_snaps_id_fk
    foreign key (snap_id) references musinsa.snaps (id),
  constraint snap_comments_users_id_fk
    foreign key (writer_id) references musinsa.users (id)
)
  comment '스냅 코멘트 테이블';

create index snap_comments_parent_id_index
  on musinsa.snap_comments (parent_id);

create table musinsa.snap_comments_histories
(
  id            bigint       not null,
  revision_id   bigint       not null,
  revision_type tinyint      not null,
  parent_id     bigint       null,
  snap_id       bigint       not null,
  writer_id     bigint       not null,
  comment       varchar(255) not null,
  is_deleted    bit          not null,
  created_at    datetime     not null,
  updated_at    datetime     not null,
  primary key (id, revision_id)
)
  comment '스냅 코멘트 이력 테이블';

