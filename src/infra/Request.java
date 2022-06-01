package infra;

import utils.UriParser;

import java.util.Map;

public class Request {

    private UriParser uriParser;

    // 생성자 생성
    public Request(String uri){
        this.uriParser = new UriParser(uri);
    }

    public boolean hasParam(String key){
        Map<String, Object> parameter = uriParser.getParameter();
        return parameter.get(key) != null;
    }


    public String getOriginUrl(){
        return uriParser.getURI();
    }

    public boolean isValidRequest(){
        return uriParser.isValid();
    }

    public Object getParameterValue(String key, Class cls){

        Map<String, Object> parameter = uriParser.getParameter();
        return cls.cast(parameter.get(key));

    }

    public String getParameterStrValue(String key){
        Map<String, Object> parameter = uriParser.getParameter();
        return parameter.get(key).toString();
    }

    public int getParameterIntValue(String key){
        Map<String, Object> parameter = uriParser.getParameter();
        return Integer.parseInt(parameter.get(key).toString());
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
