package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.WeatherData;
import view.WeatherApp;

public class WeatherController {
    private WeatherData model;
    private WeatherApp view;

    public WeatherController(WeatherData model, WeatherApp view) {
        this.model = model;
        this.view = view;

        // Add action listener to the fetch button
        view.getFetchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchWeather();
            }
        });
    }

    private void fetchWeather() {
        String location = view.getUserLocation();
        if (!location.isEmpty()) {
            model.fetchWeatherData(location);

            // Pass weather data and activity guide to the view
            view.setWeatherData(
                model.getTemperature(),
                model.getHumidity(),
                model.getWindSpeed(),
                model.getDescription(),
                model.getActivityGuide()
            );
        } else {
            JOptionPane.showMessageDialog(view, "Please enter a location.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
