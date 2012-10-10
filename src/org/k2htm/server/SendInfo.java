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
	public static final String TAG = "SendInfo";
	public static final String USER_NAME="USER_NAME";
	public static final String LAT="LAT";
	public static final String LNG="LNG";
	public static final String COMMENT="COMMENT";
	public static final String CAUTION_TYPE="CAUTION_TYPE";
	
	private boolean isMultipart=false;
	private ServletContext context;
	private Date now;
	private String userName;
	private double lat;
	private double lng;
	private String comment;
	private short cautionType;
	
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
		context=getServletContext();
		System.out.println(TAG+":doPost");
		System.out.println(context.getRealPath("/"));
	}

	private void analyzeRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		isMultipart=ServletFileUpload.isMultipartContent(request);
		userName=request.getParameter(USER_NAME);
		lat=Double.parseDouble(request.getParameter(LAT));
		lng=Double.parseDouble(request.getParameter(LNG));
		cautionType=Short.parseShort(request.getParameter(CAUTION_TYPE));
		comment=request.getParameter(COMMENT);
		
		
		    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		analyzeRequest(request);
		System.out.println(TAG+":doPost");
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
