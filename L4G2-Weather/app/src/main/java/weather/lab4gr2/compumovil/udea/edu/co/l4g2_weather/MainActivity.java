package weather.lab4gr2.compumovil.udea.edu.co.l4g2_weather;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String IMG_URL = "http://openweathermap.org/img/w/";
    private static String APPI_KEY="&APPID=c4c29d0c4be972ee5a3d7571699d6e92";
    private String img_id;
    private String Location;
    private TextView temp;
    private TextView hum;
    private TextView desc;
    private AutoCompleteTextView city;
    private ImageView img;
    private String[] CITIES;
    private RequestQueue queue;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temp=(TextView)findViewById(R.id.text_temp);
        hum=(TextView)findViewById(R.id.text_hum);
        desc=(TextView)findViewById(R.id.text_desc);
        img=(ImageView)findViewById(R.id.imageView);
        city=(AutoCompleteTextView)findViewById(R.id.city);

        CITIES = getResources().getStringArray(R.array.cities);;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,CITIES);
        city.setAdapter(adapter);

        queue = Volley.newRequestQueue(this);

        gson = new Gson();

    }

    public void onClick(View view) {

        temp.setText("");
        hum.setText("");
        desc.setText("");
        img.setImageDrawable(null);

        Location = city.getText().toString().trim();
        city.setText(Location);
        city.clearFocus();

        String url =BASE_URL+Location+APPI_KEY;

        //Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("isa",response);
                        Gson gson=new Gson(); //instantiating the GSON class
                        ResponseData responseData=gson.fromJson(response,ResponseData.class);

                        String codigo = responseData.getCod();
                        if(codigo.trim().charAt(0) == '4') {
                            Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Log.d("isaa", responseData.getCityName() + " " + responseData.getCordinate().getLat());

                        temp.setText(String.format("%.2f",(Double.parseDouble(responseData.getMain().getTemp()) - 273.15))+"°C");
                        hum.setText(responseData.getMain().getHumidity()+"%");

                        String descript = (responseData.getWeathers()[0].getIcon());

                        if(descript.trim().equalsIgnoreCase("01d") || descript.trim().equalsIgnoreCase("01n")) {
                            desc.setText(R.string.clear);
                        } else if(descript.trim().equalsIgnoreCase("02d") || descript.trim().equalsIgnoreCase("02n")) {
                            desc.setText(R.string.few);
                        } else if(descript.trim().equalsIgnoreCase("03d") || descript.trim().equalsIgnoreCase("03n")) {
                            desc.setText(R.string.scattered);
                        } else if(descript.trim().equalsIgnoreCase("04d") || descript.trim().equalsIgnoreCase("04n")) {
                            desc.setText(R.string.broken);
                        } else if(descript.trim().equalsIgnoreCase("09d") || descript.trim().equalsIgnoreCase("09n")) {
                            desc.setText(R.string.shower);
                        } else if(descript.trim().equalsIgnoreCase("10d") || descript.trim().equalsIgnoreCase("01n")) {
                            desc.setText(R.string.rain);
                        } else if(descript.trim().equalsIgnoreCase("11d") || descript.trim().equalsIgnoreCase("11n")) {
                            desc.setText(R.string.thunderstorm);
                        } else if(descript.trim().equalsIgnoreCase("13d") || descript.trim().equalsIgnoreCase("13n")) {
                            desc.setText(R.string.snow);
                        } else {
                            desc.setText(R.string.mist);
                        }

                        img_id = responseData.getWeathers()[0].getIcon();

                        ImageRequest request = new ImageRequest(IMG_URL+img_id+".png",
                                new Response.Listener<Bitmap>() {
                                    @Override
                                    public void onResponse(Bitmap bitmap) {
                                        img.setImageBitmap(bitmap);
                                    }
                                }, 0, 0, null,
                                new Response.ErrorListener() {
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, R.string.error_img, Toast.LENGTH_SHORT).show();
                                    }
                                });
                        queue.add(request);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("is","dont work");
                Toast.makeText(MainActivity.this, R.string.error404, Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}