package com.musinsa.common.exception

enum class ErrorCode(val defaultMessage: String) {
    INVALID_PARAMETER("파라미터가 유효하지 않습니다."),

    // AUTH
    EXPIRED_TOKEN("만료된 토큰입니다."),
    INVALID_TOKEN("유효하지 않은 토큰입니다."),
    AUTHORIZE_CODE_NOT_FOUND("인가 코드를 찾을 수 없습니다."),

    // USER
    USERNAME_ALREADY_EXISTS("이미 존재하는 아이디입니다."),
    SOCIAL_ID_ALREADY_EXISTS("이미 가입한 소셜 계정입니다."),
    EMAIL_ALREADY_EXIST("이미 존재하는 이메일입니다."),
    NAME_ALREADY_EXISTS("이미 존재하는 회원명입니다."),
    PHONE_NUMBER_ALREADY_EXISTS("이미 존재하는 핸드폰번호입니다."),
    USER_NOT_FOUND("회원을 찾을 수 없습니다."),
    PASSWORD_DOES_NOT_MATCHED("비밀번호가 일치하지 않습니다."),
    SOCIAL_USER_CAN_NOT_CHANGE_PASSWORD("소셜 회원은 비밀번호를 변경할 수 없습니다."),

    // APPLICATION
    APPLICATION_NOT_FOUND("애플리케이션을 찾을 수 없습니다."),
    ACCESSIBLE_ONLY_OWNER_MEMBER_ON_APPLICATION("애플리케이션의 소유자만 접근할 수 있습니다."),

    // SNAP
    SNAP_NOT_FOUND("스냅을 찾을 수 없습니다."),
    ACCESSIBLE_ONLY_WRITER_ON_SNAP("스냅 작성자만 접근할 수 있습니다."),
    SNAP_TAG_NOT_FOUND("스냅 태그를 찾을 수 없습니다."),

    // SNAP LIKE
    ALREADY_LIKED_SNAP("이미 좋아요한 스냅입니다."),
    NOT_LIKED_SNAP("좋아요하지 않는 스냅입니다."),

    // SNAP COMMENT
    SNAP_COMMENT_NOT_FOUND("스냅 코멘트를 찾을 수 없습니다."),
    ACCESSIBLE_ONLY_SNAP_COMMENT_WRITER("스냅 코멘트 작성자만 접근할 수 있습니다."),

    // BRAND
    BRAND_NOT_FOUND("브랜드를 찾을 수 없습니다."),

    // PRODUCT CATEGORY
    PRODUCT_CATEGORY_NOT_FOUND("상품 카테고리를 찾을 수 없습니다."),
}
