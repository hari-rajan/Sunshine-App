package com.example.harirajan.sunshine.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        String [] fakeData = {
                "Today - Sunny - 88/65",
                "Tomorrow - Cloudy - 75/72",
                "Wednesday - Rainy - 44/44",
                "Thursday - Rainy - 85/54",
                "Friday - Rainy - 66/55",
                "Saturday - Does it matter",
                "Sunday - Gloomy - 55/55"
        };

        List<String> weekForecast = new ArrayList<String>(Arrays.asList(fakeData));

        ArrayAdapter<String> mForecastAdapter = new ArrayAdapter<String> (
                                                getActivity(),
                                                R.layout.listitemforecast,
                                                R.id.list_view_forecast,
                                                weekForecast);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.list_view_forecast);
        listView.setAdapter(mForecastAdapter);

        return rootView;
    }
}
