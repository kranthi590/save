<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title>Gps Location Finder</title>

<meta name="apple-mobile-web-app-title" content="Karma Webapp">
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false">
        </script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
  var map;
  var marker, i;
  var geocoder;
  $(document).ready(function() {

	getData();
    setInterval(function() {
      //marker.setVisible(false);      
      console.log("removed marker....");
      getData();
     
    }, 5000);

   function getData(){
	   $.ajax({

	        url : "/save/get",
	        type : "GET",
	        success : function(data) {
	          
	          var obj = JSON.parse(data);
	          console.log(/* "Coordinates recieved: " + */ obj);
	          var latLng = new google.maps.LatLng(parseFloat(obj.latitude),parseFloat( obj.longitude));
	          marker.setPosition(latLng);
	          $("#last_known").empty().html("Last Known Coordinates: <b>"+obj.latitude+","
	              + obj.longitude+" At: "+obj.timestamp+" </b>");
	          geocoder.geocode({
	            'latLng' : latLng
	          }, function(results, status) {
	            if (status == google.maps.GeocoderStatus.OK) {
	              if (results[1]) {
	                $("#address").empty().val("Location: "
	                    + results[1].formatted_address);

	                
	              }
	            }
	          });
	          console.log("added marker....");
	        }
	      });
    }
  });
  function initMap() {
    var myLatLng = {
      lat : 17.45434811,
      lng : 78.3986823
    };

    map = new google.maps.Map(document.getElementById('map-canvas'), {
      zoom : 12,
      panControl : true,
      zoomControl : true,
      mapTypeControl : true,
      scaleControl : true,
      streetViewControl : true,
      overviewMapControl : true,
      rotateControl : true,
      center : myLatLng
    });

    marker = new google.maps.Marker({
      
      map : map,
      title : 'Hello World!'
    });
    geocoder = new google.maps.Geocoder();
    geocoder.geocode({
      'latLng' : myLatLng
    }, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        if (results[1]) {
          document.getElementById('address').value = "Location: "
              + results[1].formatted_address;
        }
      }
    });
  }
</script>
</head>
<body onload="initMap()">



  <table style="left: 50px; position: relative;">
    <tr>
      <td><textarea id="address" style="width: 400px;"></textarea></td>
      <td id="last_known" >
      </td>
    </tr>
  </table>


  <div id="map-canvas"
    style="width: 1000px; height: 500px; left: 50px; top: 20px;"
    class="gmnoprint img"></div>



</body>
</html>