package com.save.app;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);
	Gson gson = new Gson();

	@ResponseBody
	@RequestMapping(value = {"/","/data"}, method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		try {
			//System.out.println("Request Received.......");
			if (request.getParameter("location") != null) {
				logger.info("---- Location=" + request.getParameter("location"));
				String location[] = request.getParameter("location").split(",");
				GpsCoordinates coordinates1 = new GpsCoordinates();
				coordinates1.setLatitude(location[0]);
				coordinates1.setLongitude(location[1]);
				coordinates1.setTimestamp(TimeStampUtil.getCurrentTimeStamp());
				GpsUtil.setCoordinates(coordinates1);

			}
		} catch (Exception e) {
			logger.error("Exception:  " + e.getMessage());
		}
		return "ok";
	}

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public ModelAndView map(ModelAndView view, HttpServletRequest request) {

		view.setViewName("map");
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String sendData(HttpServletRequest request) {
		GpsCoordinates coordinates = null;
		try {
			coordinates = GpsUtil.getCoordinates();
			if (coordinates == null || (coordinates.getLatitude() == null || coordinates.getLongitude() == null)) {
				coordinates = new GpsCoordinates();
				coordinates.setLatitude("17.45434811");
				coordinates.setLongitude(" 78.3986823");
				coordinates.setTimestamp("Coordinates are null , setting to default");
			}
			//logger.info("SendData:"+coordinates);
		} catch (Exception e) {
			logger.error("Exception:  " + e.getMessage());
		}
		return gson.toJson(coordinates);
	}
}
