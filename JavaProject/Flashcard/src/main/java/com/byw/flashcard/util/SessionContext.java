package com.byw.flashcard.util;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author 王碧云
 * @description: TODO
 * @date 2020/4/24/024 21:36
 */
public class SessionContext {
    private static SessionContext instance;
    private HashMap mymap;

    private SessionContext() {
        mymap = new HashMap();
    }

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }
        return instance;
    }

    public synchronized void AddSession(HttpSession session) {
        if (session != null) {
            mymap.put(session.getId(), session);
        }
    }

    public synchronized void DelSession(HttpSession session) {
        if (session != null) {
            mymap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String session_id) {
        if (session_id == null) return null;
        return (HttpSession) mymap.get(session_id);
    }
}

