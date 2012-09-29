package org.k2htm.server;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class SendInfo
 */
@WebServlet("/SendInfo")
public class SendInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultipart=false;
	private ServletContext context;
	private Date now;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	private void analyzeRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		isMultipart=ServletFileUpload.isMultipartContent(request);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		analyzeRequest(request);
		
		now=new Date();
		context=getServletContext();
		File imagesFolder=(File) context.getAttribute(ServletListener.IMAGES_FOLDER);
		System.out.println(context.getRealPath("/"));
		if(!isMultipart){
			return;
		}
		DiskFileItemFactory factory=new DiskFileItemFactory();
		String imagesName=Long.toString(now.getTime());
		
	}

}
