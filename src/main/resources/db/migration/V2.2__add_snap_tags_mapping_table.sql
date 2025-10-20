create table musinsa.snap_tags_mapping
(
  id          bigint auto_increment comment '아이디'
        primary key,
  snap_id     bigint   not null comment '스냅 아이디',
  snap_tag_id bigint   not null comment '스냅 태그 아이디',
  `order`     int      not null comment '정렬순서',
  created_at  datetime not null comment '생성일자',
  updated_at  datetime not null comment '수정일자',
  constraint snap_tags_mapping_snap_tags_id_fk
    foreign key (snap_tag_id) references musinsa.snap_tags (id),
  constraint snap_tags_mapping_snaps_id_fk
    foreign key (snap_id) references musinsa.snaps (id)
)
  comment '스냅 태그 매핑 테이블';

