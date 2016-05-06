package weather.lab4gr2.compumovil.udea.edu.co.l4g2_weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lis on 28/04/16.
 */
public class System {
    @SerializedName("message")
    String msg;
    @SerializedName("country")
    String country;
    @SerializedName("sunrise")
    String sunrise;
    @SerializedName("sunset")
    String sunset;

    public String getMsg() {
        return msg;
    }

    public String getCountry() {
        return country;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }
}
