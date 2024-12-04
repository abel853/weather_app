import javax.swing.SwingUtilities;
import model.WeatherData;
import view.WeatherApp;
import controller.WeatherController;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WeatherApp view = new WeatherApp();
            WeatherData model = new WeatherData();
            new WeatherController(model, view);

            view.setVisible(true); 
        });
    }
}
