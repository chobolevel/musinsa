create table musinsa.posts
(
  id         bigint auto_increment comment '아이디'
        primary key,
  user_id    bigint                             not null comment '회원(작성자) 아이디',
  title      varchar(100)                       not null comment '게시글 제목',
  content    text                               not null comment '게시글 내용',
  is_deleted tinyint                            not null comment '삭제 여부',
  created_at datetime default CURRENT_TIMESTAMP not null comment '생성일자',
  updated_at datetime default CURRENT_TIMESTAMP not null comment '수정일자',
  constraint posts_users_id_fk
    foreign key (user_id) references musinsa.users (id)
)
  comment '게시글 테이블';

create fulltext index posts_content_fulltext_index
    on musinsa.posts (content);

create fulltext index posts_title_fulltext_index
    on musinsa.posts (title);

create table musinsa.posts_histories
(
  id            bigint       not null,
  revision_id   bigint       not null,
  revision_type tinyint      not null,
  user_id       bigint       not null,
  title         varchar(100) not null,
  content       text         not null,
  is_deleted    tinyint      not null,
  created_at    datetime     null,
  updated_at    datetime     not null,
  primary key (id, revision_id)
)
  comment '게시글 이력 테이블';

