package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyFirstListener1
 *
 */
@WebListener
public class MyFirstListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce)  { 
   	 //获取web.xml中配置的属性
       String value=sce.getServletContext().getInitParameter("projectname");
       System.out.println(value);
    }
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }
}
