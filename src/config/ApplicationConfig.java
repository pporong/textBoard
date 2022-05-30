package config;

public class ApplicationConfig {

    // 배열

    // 권한(로그인)이 필요 없는 요청
    public static String[] anonymousUrlList = {
        "/members/join", "/member/join",
        "/members/login", "/member/login"
    };

    // 권한이 필요한 요청
    public static String[] hasAuthUrlList = {
        "/members/logout", "/member/logout",
        "/members/detail", "/member/detail",
        "/members/modify", "/member/modify"
    };

}
