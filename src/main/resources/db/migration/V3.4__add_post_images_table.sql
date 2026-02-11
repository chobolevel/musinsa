create table musinsa.post_images
(
  id         bigint auto_increment comment '아이디'
        primary key,
  post_id    bigint         not null comment '게시글 아이디',
  type       varchar(20)    not null comment '게시글 이미지 유형',
  path       varchar(255)   not null comment '게시글 이미지 경로',
  width      int default 0  not null comment '게시글 이미지 너비',
  height     int default 0  not null comment '게시글 이미지 높이',
  sort_order int default 10 not null comment '정렬 순서',
  created_at datetime       not null comment '생성 일자',
  updated_at datetime       not null comment '수정 일자',
  constraint post_images_posts_id_fk
    foreign key (post_id) references musinsa.posts (id)
)
  comment '게시글 이미지 테이블';

create table musinsa.post_images_histories
(
  id            bigint       not null,
  revision_id   bigint       not null,
  revision_type tinyint      not null,
  post_id       bigint       not null,
  type          varchar(20)  not null,
  path          varchar(255) not null,
  width         int          not null,
  height        int          not null,
  sort_order    int          not null,
  created_at    datetime     not null,
  updated_at    datetime     not null,
  primary key (id, revision_id)
)
  comment '게시글 이미지 이력 테이블';

