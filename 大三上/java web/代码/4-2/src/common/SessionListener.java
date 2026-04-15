package common;

import java.util.HashSet;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@WebListener
public class SessionListener implements HttpSessionListener {
 
       public void sessionCreated(HttpSessionEvent event) {
              
       }
 
       public void sessionDestroyed(HttpSessionEvent event) {
              HttpSession session = event.getSession();
              ServletContext application = session.getServletContext();
              HashSet sessions = (HashSet) application.getAttribute("sessions");
              
              if (sessions!= null) sessions.remove(session);
       }
}