package com.project.chawchaw.config.response;

public class ResponseMessage {
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String NOT_FOUND_COUNTRY="해당 국가를 지원하지 않습니다.";
    public static final String NOT_FOUND_LANGUAGE="해당 언어를 지원하지 않습니다.";
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String FOLLOW = "좋아요 성공";
    public static final String SEND_MAIL = "인증 메일이 발송되었습니다.";
    public static final String FAIL_SOCIAL_LOGIN="소셜 인증 실패";
    public static final String VERIFICATION_MAIL_SUCCESS = "메일 인증에 성공하였습니다.";
    public static final String VERIFICATION_MAIL_FAIL = "메일 인증에 실패하였습니다.";
    public static final String DUPLICATE_USER="동일한 회원이 존재합니다.";
    public static final String UNFOLLOW = "좋아요 취소 성공";
    public static final String EMAIL_DUPLICATE = "회원 중복 조회 성공";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String DELETE_USER = "회원 탈퇴 성공";
    public static final String IMAGE_UPLOAD_SUCCESS="이미지 업로드 성공";
    public static final String IMAGE_UPLOAD_FAIL="이미지 업로드 실패";
    public static final String IMAGE_DELETE_SUCCESS="이미지 삭제 성공";
    public static final String IMAGE_DELETE_FAIL="이미지 삭제 실패";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
}
