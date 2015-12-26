package com.save.app;

import org.apache.log4j.Logger;

public class GpsUtil {

	private static final Logger logger = Logger.getLogger(GpsUtil.class);
	private static GpsCoordinates coordinates = new GpsCoordinates();

	public static GpsCoordinates getCoordinates() {

		synchronized (coordinates) {
			return coordinates;
		}

	}

	public static void setCoordinates(GpsCoordinates coordinates1) {

		synchronized (coordinates) {

			try {
				coordinates.setLatitude(coordinates1.getLatitude());
				coordinates.setLongitude(coordinates1.getLongitude());
				coordinates.setTimestamp(coordinates1.getTimestamp());
				logger.info("Coordinates has bee set to object:" + coordinates);
			} catch (Exception e) {
				logger.error("Exception:" + e.getMessage());
			}
		}
	}

}
