package vn.edu.usth.usthweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Objects;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private String titles[] = new String[] { "Hanoi", "Paris", "Toulouse" };


    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    @Override
    public Fragment getItem(int page) {
    // returns an instance of Fragment corresponding to the specified page
        switch (page) {
        case 0: return WeatherAndForecastFragment.newInstance("Paris", String.valueOf(R.drawable.weather_thunderstorm));
        case 1: return WeatherAndForecastFragment.newInstance("Hanoi", String.valueOf(R.drawable.weather_rain));
        case 2: return WeatherAndForecastFragment.newInstance("Toulouse", String.valueOf(R.drawable.weather_cloudy));
    }
    return new WeatherAndForecastFragment(); // failsafe
    }

    @Override
    public CharSequence getPageTitle(int page) {
    // returns a tab title corresponding to the specified page
        return titles[page];
    }

    }
