package vn.edu.usth.usthweather;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.PagerAdapter;

import android.media.MediaPlayer;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Build;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import static android.app.PendingIntent.getActivity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;



public class WeatherActivity extends AppCompatActivity {

    public static String TAG = "WeatherActivity";
    private RefreshHandler refreshHandler;

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

        PagerAdapter adapter = new HomeFragmentPagerAdapter( getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                String content = msg.getData().getString("server_response");
                Toast.makeText(WeatherActivity.this, content, Toast.LENGTH_SHORT).show();
            }
        };

        refreshHandler = new RefreshHandler(handler);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for the toolbar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                refreshHandler.NetworkRequest();
                return true;
            default:
                super.onOptionsItemSelected(item);
        }
        return false;
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