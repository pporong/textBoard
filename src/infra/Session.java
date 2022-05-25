package infra;

import java.util.HashMap;
import java.util.Map;

public class Session {

    private Map<String, Object> store;


    // 생성자
    public Session(){
        this.store = new HashMap<>();
    }

    public void setAttribute(String key, Object value){
        store.put(key, value);
    }     // 셋으로 입력

    public Object getAttribute(String key){
        return store.get(key);
    }     // get으로 꺼내고

    public void removeAttribute(String key){
        store.remove(key);
    }

    public boolean hasAttribute(String key){
        return store.containsKey(key);
    }


}
