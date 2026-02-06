create table musinsa.post_tag_mappings
(
  id          bigint auto_increment comment '아이디'
        primary key,
  post_id     bigint                             not null comment '게시글 아이디',
  post_tag_id bigint                             not null comment '게시글 태그 아이디',
  created_at  datetime default CURRENT_TIMESTAMP not null comment '생성일자',
  updated_at  datetime default CURRENT_TIMESTAMP not null comment '수정일자',
  constraint post_tag_mappings_post_tags_id_fk
    foreign key (post_tag_id) references musinsa.post_tags (id),
  constraint post_tag_mappings_posts_id_fk
    foreign key (post_id) references musinsa.posts (id)
)
  comment '게시글 태그 매핑 테이블';

