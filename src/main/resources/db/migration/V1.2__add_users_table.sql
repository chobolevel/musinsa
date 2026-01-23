create table musinsa.users
(
  id            bigint auto_increment comment '아이디'
        primary key,
  username      varchar(100) null comment '로그인 아이디',
  password      varchar(255) null comment '비밀번호',
  social_id     varchar(255) null comment '소셜 플랫폼 아이디',
  sign_up_type  varchar(20)  not null comment '회원 가입 유형',
  email         varchar(255) not null comment '이메일',
  name          varchar(100) not null comment '회원명',
  phone         varchar(20)  not null comment '전화번호',
  gender        varchar(20)  not null comment '성별',
  birth_date    date         not null comment '생년월일',
  status        varchar(20)  not null comment '상태',
  grade         varchar(20)  not null comment '등급',
  role          varchar(20)  not null comment '권한',
  point_balance int          not null comment '포인트',
  is_deleted    bit          not null comment '삭제 여부',
  created_at    datetime     not null comment '생성 일자',
  updated_at    datetime     not null comment '수정 일자'
)
  comment '회원 테이블';

create index users_social_id_sign_up_type_index
  on musinsa.users (social_id, sign_up_type);

create index users_username_sign_up_type_index
  on musinsa.users (username, sign_up_type);



create table musinsa.users_histories
(
  id            bigint       not null,
  revision_id   bigint       not null,
  revision_type tinyint      not null,
  username      varchar(100) null,
  password      varchar(255) null,
  social_id     varchar(255) null,
  sign_up_type  varchar(20)  not null,
  email         varchar(255) not null,
  name          varchar(100) not null,
  phone         varchar(20)  not null,
  gender        varchar(20)  not null,
  birth_date    date         not null,
  status        varchar(20)  not null,
  grade         varchar(20)  not null,
  role          varchar(20)  not null,
  point_balance int          not null,
  is_deleted    bit          not null,
  created_at    datetime     not null,
  updated_at    datetime     not null,
  primary key (id, revision_id)
)
  comment '회원 이력 테이블';

