create table musinsa.users
(
  id         bigint auto_increment comment '아이디'
        primary key,
  email      varchar(80)  not null comment '이메일',
  password   varchar(255) not null comment '비밀번호',
  nickname   varchar(80)  null comment '닉네임',
  resigned   bit          not null comment '탈퇴여부',
  created_at datetime     not null comment '생성 일자',
  updated_at datetime     not null comment '수정 일자'
)
  comment '회원 테이블';

create index users_email_index
  on musinsa.users (email);

create table musinsa.users_histories
(
  id            bigint       not null,
  revision_id   bigint       not null,
  revision_type tinyint      not null,
  email         varchar(80)  not null,
  password      varchar(255) not null,
  nickname      varchar(80)  not null,
  resigned      bit          not null,
  created_at    datetime     not null,
  updated_at    datetime     not null,
  primary key (id, revision_id)
)
  comment '회원 이력 테이블';

