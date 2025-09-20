create table musinsa.application_members
(
  id             bigint auto_increment comment '아이디'
        primary key,
  application_id bigint      not null comment '애플리케이션 아이디',
  user_id        bigint      not null comment '회원 아이디',
  type           varchar(20) not null comment '맴버 유형(OWNER, MEMBER)',
  is_deleted     bit         not null comment '삭제 여부',
  created_at     datetime    not null comment '생성일자',
  updated_at     datetime    not null comment '수정일자'
)
  comment '애플리케이션 맴버 테이블';

create index application_members_application_id_index
  on musinsa.application_members (application_id);

create index application_members_user_id_index
  on musinsa.application_members (user_id);

create table musinsa.application_members_histories
(
  id             bigint      not null,
  revision_id    bigint      not null,
  revision_type  tinyint     not null,
  application_id bigint      not null,
  user_id        bigint      not null,
  type           varchar(20) not null,
  is_deleted     bit         not null,
  created_at     datetime    not null,
  updated_at     datetime    not null,
  primary key (id, revision_id)
)
  comment '애플리케이션 맴버 이력 테이블';

