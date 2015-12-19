package com.gps.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

@WebServlet("/get")
public class Get extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Get.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		MongoCollection<Document> collection = DBUtil.getColl();
		Document myObj = collection.find().sort(new BasicDBObject(WebConstants.TIMESTAMP, -1)).first();
		logger.info("Sending data: " + myObj.toJson());
		out.println(myObj.toJson());

		out.close();
	}
}
