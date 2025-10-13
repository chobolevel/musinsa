create table musinsa.snap_likes
(
  id         bigint auto_increment comment '아이디'
        primary key,
  snap_id    bigint   not null comment '스냅 아이디',
  user_id    bigint   not null comment '회원 아이디',
  created_at datetime not null comment '생성일자',
  updated_at datetime not null comment '수정일자',
  constraint snap_likes_snaps_id_fk
    foreign key (snap_id) references musinsa.snaps (id),
  constraint snap_likes_users_id_fk
    foreign key (user_id) references musinsa.users (id)
)
  comment '스냅 좋아요 테이블';

