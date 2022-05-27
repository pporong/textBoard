package utils;

import java.util.HashMap;
import java.util.Map;

public class UriParser {

    // members/join
    // members/modify?loginId=test
    // articles/detail?id=1
    // 컨트롤러 종류/행위?파라미터

    private String URI;

    private String controllerCode;

    private String target;

    private boolean isValid = false;

    // key (key) / value (Object)
    private Map<String, Object> parameter = new HashMap<>();

    // 생성자 생성
    public UriParser(String uri){
        this.URI = uri;
        parse();
    }

    private void parse(){
        // "?"
        String[] uriSplit = this.URI.split("\\?",2);
        // uriSplit = [articles/detail, id=1]

        if (uriSplit.length == 2){

            //     id=1
            String paramBody = uriSplit[1];
            String[] splitParam = paramBody.split("=", 2);
            // splitParam = [id, 1]

            parameter.put(splitParam[0], splitParam[1]);   // id : 1

        }

        String[] uriBodySplit = uriSplit[0].split("/");

        this.controllerCode = uriBodySplit[1];
        this.target = uriBodySplit[2];

        isValid = true;


    }

    // getter 만들기 alt+insert
    public String getControllerCode() {
        return controllerCode;
    }

    public String getTarget() {
        return target;
    }

    public Map<String, Object> getParameter() {
        return parameter;
    }

    public String getURI(){
        return URI;
    }

}
