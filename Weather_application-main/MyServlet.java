package Mypackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String apiKey = "YOUR_API_KEY";

        // ✅ Get city
        String city = request.getParameter("city");

        // ✅ Encode city (IMPORTANT)
        String encodedCity = URLEncoder.encode(city, "UTF-8");

        // ✅ Use metric directly
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q="
                + encodedCity + "&appid=" + apiKey + "&units=metric";

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        // ✅ Handle error
        if (responseCode != 200) {
            request.setAttribute("error", "City not found!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // ✅ Read data
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

        StringBuilder responseContent = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            responseContent.append(line);
        }

        reader.close();

        // ✅ Parse JSON
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);

        // ✅ Extract values
        long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
        String date = new Date(dateTimestamp).toString();

        double temperature = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
        int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
        double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();

        String weatherCondition = jsonObject.getAsJsonArray("weather")
                .get(0).getAsJsonObject()
                .get("main").getAsString();

        // ✅ Send to JSP
        request.setAttribute("date", date);
        request.setAttribute("city", city);
        request.setAttribute("temperature", (int) temperature);
        request.setAttribute("weatherCondition", weatherCondition);
        request.setAttribute("humidity", humidity);
        request.setAttribute("windSpeed", windSpeed);

        connection.disconnect();

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}