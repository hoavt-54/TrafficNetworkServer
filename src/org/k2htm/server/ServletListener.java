package org.k2htm.server;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletListener
 *
 */
@WebListener
public class ServletListener  implements ServletContextListener {
	public static final String TAG="ServletListener";
	public static final String IMAGES_FOLDER="Images";
    /**
     * Default constructor. 
     */
    public ServletListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	ServletContext context=arg0.getServletContext();
    	System.out.println(TAG+":"+"Init");
    	//System.out.println(arg0.getServletContext().getRealPath("/"));
    	//System.out.println(arg0.getServletContext().getServletContextName());
    	File imagesFolder=new File(context.getRealPath("/")+File.separator+IMAGES_FOLDER);
    	if(!imagesFolder.exists()){
    		imagesFolder.mkdir();
    	}
    	context.setAttribute(IMAGES_FOLDER, imagesFolder);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
