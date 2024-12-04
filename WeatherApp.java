package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class WeatherApp extends JFrame {
    private JLabel tempLabel;
    private JLabel humidityLabel;
    private JLabel windSpeedLabel;
    private JLabel descriptionLabel;
    private JLabel activityGuideLabel; // New label for activity guide
    private JTextField locationField;
    private JButton fetchButton;
    private JLabel logoLabel; // Label for the logo image

    public WeatherApp() {
        setTitle("Weather App");
        setSize(600, 600); // Adjust size for better layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel for Logo and Input
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(135, 206, 250)); // Sky blue background

        // Add logo
        logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Load the logo image
        File logoFile = new File("src/image/weather_logo.png");
        if (logoFile.exists()) {
            ImageIcon logoIcon = new ImageIcon(logoFile.getAbsolutePath());
            Image scaledLogo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledLogo));
        } else {
            logoLabel.setText("Weather App");
            logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        }

        topPanel.add(logoLabel, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(173, 216, 230)); // Light blue
        locationField = new JTextField(15);
        fetchButton = new JButton("Fetch Weather");
        fetchButton.setBackground(new Color(30, 144, 255)); // Dodger blue
        fetchButton.setForeground(Color.BLACK);
        fetchButton.setFont(new Font("Arial", Font.BOLD, 14));

        inputPanel.add(new JLabel("Enter Location: "));
        inputPanel.add(locationField);
        inputPanel.add(fetchButton);

        topPanel.add(inputPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // Center Panel for Weather Data
        JPanel centerPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        tempLabel = new JLabel("Temperature: ", JLabel.CENTER);
        humidityLabel = new JLabel("Humidity: ", JLabel.CENTER);
        windSpeedLabel = new JLabel("Wind Speed: ", JLabel.CENTER);
        descriptionLabel = new JLabel("Condition: ", JLabel.CENTER);
        activityGuideLabel = new JLabel("<html><i>Activity suggestions will appear here...</i></html>", JLabel.CENTER);

        // Set fonts for labels
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        tempLabel.setFont(labelFont);
        humidityLabel.setFont(labelFont);
        windSpeedLabel.setFont(labelFont);
        descriptionLabel.setFont(labelFont);
        activityGuideLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        centerPanel.add(tempLabel);
        centerPanel.add(humidityLabel);
        centerPanel.add(windSpeedLabel);
        centerPanel.add(descriptionLabel);
        centerPanel.add(activityGuideLabel);
        add(centerPanel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(240, 248, 255)); // Alice blue
        JLabel footerLabel = new JLabel("Powered by OpenWeatherMap");
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);
    }

    public String getUserLocation() {
        return locationField.getText();
    }

    public void setWeatherData(String temp, String humidity, String windSpeed, String description, String activityGuide) {
        SwingUtilities.invokeLater(() -> {
            tempLabel.setText("Temperature: " + temp);
            humidityLabel.setText("Humidity: " + humidity);
            windSpeedLabel.setText("Wind Speed: " + windSpeed);
            descriptionLabel.setText("Condition: " + description);
            activityGuideLabel.setText(activityGuide); // Update activity guide
        });
    }

    public JButton getFetchButton() {
        return fetchButton;
    }
}
