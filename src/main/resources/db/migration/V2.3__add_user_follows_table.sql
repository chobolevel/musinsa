create table musinsa.user_follows
(
  id           bigint auto_increment comment '아이디'
        primary key,
  follower_id  bigint   not null comment '팔로워 아이디',
  following_id bigint   not null comment '팔로잉 아이디',
  created_at   datetime not null comment '생성일자',
  updated_at   datetime not null comment '수정일자',
  constraint user_follows_users_id_fk
    foreign key (follower_id) references musinsa.users (id),
  constraint user_follows_users_id_fk_2
    foreign key (following_id) references musinsa.users (id)
)
  comment '회원 팔로우 테이블';

create index user_follows_follower_id_index
  on musinsa.user_follows (follower_id);

