package vn.edu.usth.usthweather;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WeatherActivity extends AppCompatActivity {

    public static String TAG = "WeatherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //WeatherFragment firstFragment = new WeatherFragment();
        //getSupportFragmentManager().beginTransaction().add(R.id.main, firstFragment).commit();

        //ForecastFragment secondFragment = new ForecastFragment();
        // Add the fragment to the 'container' FrameLayout
        // getSupportFragmentManager().beginTransaction().add(R.id.main, secondFragment).commit();
    }


    @Override
    public void onPause()
    {
        super.onPause();
        Log.i(TAG, "on pause");
    }
    @Override
    public void onStart()
    {
        super.onStart();
        Log.i(TAG, "on start");
    }
    @Override
    public void onResume()
    {
        super.onResume();
        Log.i(TAG, "on resume");
    }
    @Override
    public void onStop()
    {
        super.onStop();
        Log.i(TAG, "on stop");
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i(TAG, "on destroy");
    }

}