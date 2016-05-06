package weather.lab4gr2.compumovil.udea.edu.co.l4g2_weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lis on 28/04/16.
 */
public class Wind {
    @SerializedName("speed")
    String speed;
    @SerializedName("deg")
    String deg;

    public String getSpeed() {
        return speed;
    }

    public String getDeg() {
        return deg;
    }
}
