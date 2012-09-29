package org.k2htm.server;

import java.sql.Statement;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletListener
 * 
 */
@WebListener
public class ServletListener implements ServletContextListener {
	public static final String TAG = "ServletListener";
	public static final String IMAGES_FOLDER = "Images";
	private static final String DB_URL = "jdbc:mysql//localhost:3308/test";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "";
	private static final String DB_USER_TABLENAME = "user";
	private static final String DB_USER_USERNAME_COL = "username";
	private static final String DB_USER_PASSWORD_COL = "password";
	private static final String DB_CAUTION_USERNAME_COL = "username";
	private static final String DB_CAUTION_TABLENAME = "caution";
	private static final String DB_CAUTION_TIME_COL = "time";
	private static final String DB_CAUTION_LAT_COL = "lat";
	private static final String DB_CAUTION_LNG_COL = "lng";
	private static final String DB_CAUTION_IMAGE_COL = "image";
	private static final String DB_CAUTION_COMMENT_COL = "comment";
	private ServletContext context;

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
		System.out.println(TAG + ":" + "Init");
		context = arg0.getServletContext();
		initImagesFolder();
		initDatabases();
	}

	private void initDatabases() {
		// TODO Auto-generated method stub

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(DB_URL,
					DB_USERNAME, DB_PASSWORD);
			Statement statement = connection.createStatement();
			String createTable = "CREATE TABLE IF NOT EXISTS`"
					+ DB_USER_TABLENAME + "` (" + "`" + DB_USER_USERNAME_COL
					+ "` varchar(50) NOT NULL," + "`" + DB_USER_PASSWORD_COL
					+ "` varchar(50) NOT NULL," + "PRIMARY KEY (`username`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
			statement.execute(createTable);
			System.out.println(TAG+":create table"+DB_USER_TABLENAME);
			createTable = "CREATE TABLE `" + DB_CAUTION_TABLENAME + "` ("
					+ "`ID` int(11) NOT NULL AUTO_INCREMENT," + "`"
					+ DB_CAUTION_USERNAME_COL + "` varchar(50) NOT NULL," + "`"
					+ DB_CAUTION_TIME_COL + "` mediumtext NOT NULL," + "`"
					+ DB_CAUTION_LAT_COL + "` double NOT NULL," + "`"
					+ DB_CAUTION_LNG_COL + "` double NOT NULL," + "`"
					+ DB_CAUTION_IMAGE_COL + "` varchar(500) DEFAULT NULL,"
					+ "`" + DB_CAUTION_COMMENT_COL
					+ "` varchar(500) DEFAULT NULL," + "PRIMARY KEY (`ID`),"
					+ "KEY `" + DB_CAUTION_USERNAME_COL + "` (`"
					+ DB_CAUTION_USERNAME_COL + "`),"
					+ "CONSTRAINT `caution_ibfk_1` FOREIGN KEY (`"
					+ DB_CAUTION_USERNAME_COL + "`) REFERENCES `"
					+ DB_USER_TABLENAME + "` (`" + DB_USER_USERNAME_COL
					+ "`) ON UPDATE CASCADE"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
			statement.execute(createTable);
			System.out.println(TAG+":create table"+DB_CAUTION_TABLENAME);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initImagesFolder() {
		// TODO Auto-generated method stub
		File imagesFolder = new File(context.getRealPath("/") + File.separator
				+ IMAGES_FOLDER);
		if (!imagesFolder.exists()) {
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
