package weather.lab4gr2.compumovil.udea.edu.co.l4g2_weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lis on 28/04/16.
 */
public class Cordinate {
    @SerializedName("lat")
    String lat;
    @SerializedName("lon")
    String lon;

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }


}
