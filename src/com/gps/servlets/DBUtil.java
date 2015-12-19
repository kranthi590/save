package com.gps.servlets;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

public class DBUtil {

	final static Logger logger = Logger.getLogger(DBUtil.class);

	@SuppressWarnings("resource")
	public static MongoCollection<Document> getColl() {

		return new MongoClient("192.168.1.222", 27017).getDatabase("gps").getCollection("coordinates");
	}
}
