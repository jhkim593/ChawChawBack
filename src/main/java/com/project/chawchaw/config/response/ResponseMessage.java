package com.project.chawchaw.config.response;

public class ResponseMessage {
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "아이디 또는 비밀번호를 확인해주세요.";
    public static final String NOT_FOUND_COUNTRY="해당 국가를 지원하지 않습니다.";
    public static final String NOT_FOUND_LANGUAGE="해당 언어를 지원하지 않습니다.";
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String FOLLOW = "좋아요 성공";
    public static final String SEND_MAIL = "인증 메일이 발송되었습니다.";
    public static final String SOCIAL_LOGIN_CONNECT_FAIL="프로바이더,인가코드 또는 액세스 토큰을 확인해주세요.";
    public static final String SOCIAL_LOGIN_FAIL="소셜 로그인 실패 , 회원가입이 필요합니다.";
    public static final String VERIFICATION_MAIL_SUCCESS = "메일 인증에 성공하였습니다.";
    public static final String VERIFICATION_MAIL_FAIL = "메일 인증에 실패하였습니다.";
    public static final String DUPLICATE_USER="동일한 회원이 존재합니다.";
    public static final String UNFOLLOW = "좋아요 취소 성공";
    public static final String EMAIL_DUPLICATE = "회원 중복 조회 성공";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String ALREADY_FOLLOW = "이미 팔로우 중입니다.";
    public static final String NOT_FOUND_FOLLOW = "팔로우 중이 아닙니다.";
    public static final String CREATED_USER_FAIL = "회원 가입 실패";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String DELETE_USER = "회원 탈퇴 성공";
    public static final String IMAGE_UPLOAD_SUCCESS="이미지 업로드 성공";
    public static final String IMAGE_UPLOAD_FAIL="이미지 업로드 실패";
    public static final String IMAGE_DELETE_SUCCESS="이미지 삭제 성공";
    public static final String ENTRYPOINT_EXCEPTION="해당 리소스의 접근하기 위한 권한이 없습니다.";
    public static final String IMAGE_GET_FAIL="이미지 불러오기에 실패하였습니다.";
    public static final String SET_REP="대표국가,언어,희망언어를 입력해주세요";
    public static final String ACCESS_DENIED="보유하신 권한으로 접근할 수 없습니다.";
    

    public static final String IMAGE_DELETE_FAIL="이미지 삭제 실패";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
}
