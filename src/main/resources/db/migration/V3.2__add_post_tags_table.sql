create table musinsa.post_tags
(
  id         bigint auto_increment comment '아이디'
        primary key,
  name       varchar(100)       not null comment '게시글 태그명',
  sort_order int     default 10 not null comment '정렬 순서',
  is_deleted tinyint default 0  not null comment '삭제여부',
  created_at datetime           not null comment '생성일자',
  updated_at datetime           not null comment '수정일자'
)
  comment '게시글 태그 테이블';

