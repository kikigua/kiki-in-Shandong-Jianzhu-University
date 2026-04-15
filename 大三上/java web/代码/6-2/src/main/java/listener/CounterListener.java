package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class CounterListener
 *
 */
@WebListener
public class CounterListener implements HttpSessionListener {
	private int onlineNumber=0;
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	System.out.println("sessionCreated");
    	onlineNumber++;
    	arg0.getSession().getServletContext().setAttribute("onlineNumber",onlineNumber);
    	
    }
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	System.out.println("sessionDestroyed");
    	onlineNumber--;
    	arg0.getSession().getServletContext().setAttribute("onlineNumber",onlineNumber);
    }
	
}
