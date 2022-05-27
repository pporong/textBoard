package infra;

import utils.UriParser;

import java.util.Map;

public class Request {

    private UriParser uriParser;

    // 생성자 생성
    public Request(String uri){
        this.uriParser = new UriParser(uri);
    }

    public String getOriginUrl(){
        return uriParser.getURI();
    }

    public boolean isValidRequest(){
        return false;
    }

    public Object getParameterValue(String key, Class cls){

        Map<String, Object> parameter = uriParser.getParameter();
        return cls.cast(parameter.get(key));

    }

    public String getControllerCode(){
        return uriParser.getControllerCode();
    }

    public String getTarget(){
        return uriParser.getTarget();
    }


    // 메서드생성
    private Object getSessionAttribute(String key){
        Session session = Container.session;
        return session.getAttribute(key);
    }
    private void setSessionAttribute(String key, Object value){
        Session session = Container.session;
        session.setAttribute(key, value);
    }
    public boolean hasSessionAttribute(String key){
        Session session = Container.session;
        return session.hasAttribute(key);
    }
    public void removeSessionAttribute(String key){
        Session session = Container.session;
        session.removeAttribute(key);
    }

    public void login(String loginId){
        setSessionAttribute("logonMember", loginId);
    }
    public boolean isLogon(){
        return hasSessionAttribute("logonMember");
    }
    public void logout(){
        removeSessionAttribute("logonMember");
    }
    public String getLogonMember(){
        return (String) getSessionAttribute("logonMember");
    }



}
