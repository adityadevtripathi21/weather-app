<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Weather App</title>

<link rel="stylesheet" href="style.css" />
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />

<style>
body {
    background: #1e1e2f;
    color: white;
    font-family: Arial;
}

.mainContainer {
    text-align: center;
    margin-top: 40px;
}
</style>

</head>

<body>

<div class="mainContainer">

    <!-- 🔍 SEARCH FORM -->
    <form action="MyServlet" method="post" class="searchInput">
        <input 
            type="text"
            placeholder="Enter City Name"
            name="city"
            required
        />
        <button type="submit">
            <i class="fa-solid fa-magnifying-glass"></i>
        </button>
    </form>

    <!-- ❌ ERROR MESSAGE -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <h3 style="color:red;"><%= error %></h3>
    <%
        }
    %>

    <!-- ✅ SHOW WEATHER ONLY IF DATA EXISTS -->
    <%
        if (request.getAttribute("city") != null) {
    %>

    <div class="weatherDetails">

        <div class="weatherIcon">
            <img id="weather-icon" src="" alt="weather">
            <h2>${temperature} °C</h2>
            <input type="hidden" id="wc" value="${weatherCondition}">
        </div>

        <div class="cityDetails">
            <div class="desc"><strong>${city}</strong></div>
            <div class="date">
                ${date} | <span id="time_span"></span>
            </div>
        </div>

        <div class="windDetails">

            <div class="humidityBox">
                <img src="https://cdn-icons-png.flaticon.com/512/4148/4148460.png">
                <div class="humidity">
                    <span>Humidity</span>
                    <h2>${humidity}%</h2>
                </div>
            </div>

            <div class="windSpeed">
                <img src="https://cdn-icons-png.flaticon.com/512/172/172922.png">
                <div class="wind">
                    <span>Wind Speed</span>
                    <h2>${windSpeed} km/h</h2>
                </div>
            </div>

        </div>

    </div>

    <%
        }
    %>

</div>

<script src="myScript.js"></script>

</body>
</html>