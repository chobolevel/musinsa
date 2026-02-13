create table musinsa.post_comments
(
  id         bigint auto_increment comment '아이디'
        primary key,
  user_id    bigint            not null comment '회원 아이디',
  post_id    bigint            not null comment '게시글 아이디',
  parent_id  bigint            null comment '부모 게시글 댓글 아이디',
  comment    varchar(255)      not null comment '댓글 내용',
  is_deleted tinyint default 0 not null comment '삭제 여부',
  created_at datetime          not null comment '생성 일자',
  updated_at datetime          not null comment '수정 일자',
  constraint post_comments_posts_id_fk
    foreign key (post_id) references musinsa.posts (id),
  constraint post_comments_users_id_fk
    foreign key (user_id) references musinsa.users (id)
)
  comment '게시글 댓글 테이블';

create index post_comments_parent_id_index
  on musinsa.post_comments (parent_id);

create table musinsa.post_comments_histories
(
  id            bigint       not null,
  revision_id   bigint       not null,
  revision_type tinyint      not null,
  user_id       bigint       not null,
  post_id       bigint       not null,
  parent_id     bigint       null,
  comment       varchar(255) not null,
  is_deleted    tinyint      not null,
  created_at    datetime     not null,
  updated_at    datetime     not null,
  primary key (id, revision_id)
)
  comment '게시글 댓글 이력 테이블';

