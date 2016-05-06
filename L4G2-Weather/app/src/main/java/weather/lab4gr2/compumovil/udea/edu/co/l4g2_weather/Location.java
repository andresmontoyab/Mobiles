package weather.lab4gr2.compumovil.udea.edu.co.l4g2_weather;

/**
 * Created by lis on 28/04/16.
 */
public class Location {
    String latitude;
    String Longitude;

    public void setLatitude(String a){

        latitude=a;
    }
    public void setLongitude(String b){

        Longitude=b;
    }

    public String getLongitude(){

       return Longitude;   }

    public String getLatitude(){
        return latitude;
    }
}
