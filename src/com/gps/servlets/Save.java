package com.gps.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.MongoCollection;

@WebServlet("/")
public class Save extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(Save.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			if (request.getParameter("location") != null) {
				MongoCollection<Document> collection = DBUtil.getColl();
				logger.info("Location { " + request.getParameter("location") + " }");
				String location[] = request.getParameter("location").split(",");
				Document document = new Document();
				document.append(WebConstants.LATITUDE, location[0]);
				document.append(WebConstants.LONGITUDE, location[1]);
				document.append(WebConstants.TIMESTAMP, TimeStampUtil.getCurrentTimeStamp());
				if (collection != null) {
					collection.insertOne(document);
				}
			}
		} catch (Exception e) {
			logger.error("Exception:  " + e.getMessage());
		}
	}

}
