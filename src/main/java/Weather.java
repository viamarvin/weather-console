import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Weather {
    public static String appWeatherKey = "f54c044be80b7c1336d43367cf867da0";

    public static String getWeatherData(String cityName) {
        if (cityName.length() == 0) {
            return "";
        }

        StringBuffer content = new StringBuffer();
        String urlAddress = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + appWeatherKey + "&lang=ru";

        try {
            URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }

            bufferedReader.close();

            return content.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "";
    }

    public static void main (String[] args) {
        String cityName;
        if (args.length > 0) {
            cityName = args[0];
        } else {
            System.out.print("Введите название города: ");

            Scanner scanner = new Scanner(System.in);
            cityName = scanner.nextLine();
        }

        String weatherData = getWeatherData(cityName);
        if (!weatherData.isEmpty()) {
            JSONObject jsonObject = new JSONObject(weatherData);

            Double temp = jsonObject.getJSONObject("main").getDouble("temp");
            Double feelsLike = jsonObject.getJSONObject("main").getDouble("feels_like");
            Double tempMin = jsonObject.getJSONObject("main").getDouble("temp_min");
            Double tempMax = jsonObject.getJSONObject("main").getDouble("temp_max");
            Double pressure = jsonObject.getJSONObject("main").getDouble("pressure");
            Double windSpeed = jsonObject.getJSONObject("wind").getDouble("speed");

            System.out.println("Температура: " + temp);
            System.out.println("Ощущается: " + feelsLike);
            System.out.println("Минимальная температура: " + tempMin);
            System.out.println("Максимальная температура: " + tempMax);
            System.out.println("Давление: " + pressure);
            System.out.println("Скорость ветра: " + windSpeed);
        }
    }
}
