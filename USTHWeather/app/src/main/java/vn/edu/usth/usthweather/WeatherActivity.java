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



public class WeatherActivity extends AppCompatActivity {

    public static String TAG = "WeatherActivity";
    private static final int REQUEST_WRITE_STORAGE = 112;
    private static final String MP3_FILE_NAME = "your_audio_file.mp3";

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

        requestStoragePermissions();

        copyFileToExternalStorage(R.raw.music, "music.mp3");

        playMusic();

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }

    private void requestStoragePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
            } else {
                // Permission denied
            }
        }
    }

    private void copyFileToExternalStorage(int resourceId, String fileName) {
        InputStream in = null;
        OutputStream out = null;
        try {
            // Input stream to read from the resource
            in = getResources().openRawResource(resourceId);

            // Path where you want to copy the file
            File path = Environment.getExternalStorageDirectory();
            File file = new File(path, fileName);

            // Output stream to write to the destination
            out = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            // Close streams
            in.close();
            out.close();

            Toast.makeText(this, "File copied to external storage", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playMusic() {
        File path = Environment.getExternalStorageDirectory();
        File file = new File(path, "music.mp3");

        if (file.exists()) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(file.getPath());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
        }
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
        int id = item.getItemId();

        if (id == R.id.refresh) {
            Toast.makeText(this, "Refresh clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.settings) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
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