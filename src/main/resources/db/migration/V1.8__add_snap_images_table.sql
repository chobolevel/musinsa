create table musinsa.snap_images
(
  id         bigint auto_increment comment '아이디'
        primary key,
  snap_id    bigint       not null comment '스냅 아이디',
  path       varchar(255) not null comment '스냅 사진 경로',
  width      int          not null comment '스냅 사진 너비',
  height     int          not null comment '스냅 사진 높이',
  sort_order int          not null comment '스냅 사진 정렬순서',
  is_deleted bit          not null comment '삭제여부',
  created_at datetime     not null comment '생성일자',
  updated_at datetime     not null comment '수정일자',
  constraint snap_images_snaps_id_fk
    foreign key (snap_id) references musinsa.snaps (id)
)
  comment '스냅 사진 테이블';

create table musinsa.snap_images_histories
(
  id            bigint       not null,
  revision_id   bigint       not null,
  revision_type tinyint      not null,
  snap_id       bigint       not null,
  path          varchar(255) not null,
  width         int          not null,
  height        int          not null,
  sort_order    int          not null,
  is_deleted    bit          not null,
  created_at    datetime     not null,
  updated_at    datetime     null,
  primary key (id, revision_id)
)
  comment '스냅 사진 이력 테이블';

