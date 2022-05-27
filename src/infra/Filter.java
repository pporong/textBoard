package infra;

import config.ApplicationConfig;

public class Filter {

    // 필드 생성
    public Request request;

    // 생성자
    public Filter(Request request){
        this.request = request;
    }

    // 메서드
    public boolean isValidRequest(){
        String originCode = request.getOriginUrl();

        String sortCode = sorting(originCode);

        boolean isLogon = request.isLogon();

        if (sortCode.equals("ANONYMOUS")){ // 권한 없는 url
            if (isLogon) { // 로그인 O
                return false;
            } else if (sortCode.equals("HAS AUTH")) { // 권한 있는 url
                if (!isLogon) { // 로그인 X
                    return false;
                }
            }

        }

        return true;
    }

    private String sorting(String url){

        if (isAnonymous(url)){
            return "ANONYMOUS";
        }
        if (isNeedAuth(url)){
            return "HAS AUTH";
        }
        return "PERMIT ALL";
    }

    private boolean isNeedAuth(String url){

        // 회원 권한
        String[] hasAuthUrlList = ApplicationConfig.hasAuthUrlList;

        for (String regUrl : hasAuthUrlList) {
            if (regUrl.equals(url)){
                return true;
            }
        }
        return false;
    }

    private boolean isAnonymous(String url){

        // 비회원 권한
        String[] anonymousUrlList = ApplicationConfig.anonymousUrlList;

        for (String regUrl : anonymousUrlList) {
            if (regUrl.equals(url)){
                return true;
            }
        }
        return false;
    }

}
