package model;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class WeatherData {
    private static final String API_KEY = "4db1975addf1f5f27a43ca0e94d3ce91";

    
    private String temperature;
    private String humidity;
    private String windSpeed;
    private String description;
    private String activityGuide; 

    public void fetchWeatherData(String location) {
        try {
            // Construct the API URL
            String endpoint = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + API_KEY + "&units=metric";
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check the response code
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error: Unable to fetch data, Response Code: " + responseCode);
                return;
            }

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

           

            // Parse the response
            parseWeatherData(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseWeatherData(String jsonData) {
        JSONObject json = new JSONObject(jsonData);
        JSONObject main = json.getJSONObject("main");
        JSONObject wind = json.getJSONObject("wind");
        JSONObject weather = json.getJSONArray("weather").getJSONObject(0);

        // Extract data and store in instance variables
        this.temperature = main.getDouble("temp") + " °C";
        this.humidity = main.getInt("humidity") + "%";
        this.windSpeed = wind.getDouble("speed") + " m/s";
        this.description = weather.getString("description");

        // Generate activity guide based on temperature
        this.activityGuide = generateActivityGuide(main.getDouble("temp"));

       
    }

    private String generateActivityGuide(double temp) {
        if (temp < -15) {
            return "<html><b>Advice:</b> Stay indoors and keep warm!<br>" +
                   "<b>Activities:</b> Relax with indoor hobbies like reading or baking.<br>" +
                   "<b>Precautions:</b> Ensure adequate heating; avoid prolonged outdoor exposure.</html>";
        } else if (temp <= 0) {
            return "<html><b>Advice:</b> Wear appropriate clothing and be careful.<br>" +
                   "<b>Activities:</b> Short outdoor walks or winter photography.<br>" +
                   "<b>Precautions:</b> Dress in layers; be cautious of frostbite and slippery surfaces.</html>";
        } else if (temp <= 10) {
            return "<html><b>Advice:</b> It can get chilly but shouldn’t be a problem!<br>" +
                   "<b>Activities:</b> Go for a jog or enjoy outdoor sightseeing.<br>" +
                   "<b>Precautions:</b> Wear a warm jacket; check for icy conditions.</html>";
        } else if (temp <= 20) {
            return "<html><b>Advice:</b> Perfect for light outdoor activities.<br>" +
                   "<b>Activities:</b> Picnics, cycling, or light hiking.<br>" +
                   "<b>Precautions:</b> A light jacket or hoodie may be enough.</html>";
        } else if (temp <= 30) {
            return "<html><b>Advice:</b> Ideal weather for outdoor fun!<br>" +
                   "<b>Activities:</b> Beach trips, sports, or barbecues.<br>" +
                   "<b>Precautions:</b> Stay hydrated and use sunscream.</html>";
        } else {
            return "<html><b>Advice:</b> Stay cool and hydrated.<br>" +
                   "<b>Activities:</b> Swimming or exploring shaded areas.<br>" +
                   "<b>Precautions:</b> Avoid prolonged sun exposure; wear breathable clothing.</html>";
        }
    }

 
    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getDescription() {
        return description;
    }

    public String getActivityGuide() {
        return activityGuide;
    }
}
