package com.example.android.sunshine.app;

/**
 * Created by harirajan on 10/14/15.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment
{

    private String TAG = ForecastFragment.class.getSimpleName();
    private ArrayAdapter<String> mForecastAdapter;
    public String[] mForecastArray;

    public ForecastFragment()
    {
    }

    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        int id = item.getItemId();
        if (id==R.id.action_refresh)
        {
            FetchWeatherTask fetchWeatherTask = new FetchWeatherTask();
            fetchWeatherTask.execute("95014");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        String[] forecastArray = {
                "Today - Sunny - 88/87",
                "Tomorrow - Cloudy - 88/33",
                "Day after - Sunny - 33/33",
                "Thursday - Hot - Miserable",
                "Friday - more heat - more misery",
                "Saturday - Brrr - Brrr",
                "Sunday - Doesn't matter. its Sunday!"
        };

        ArrayList<String> weekForecast = new ArrayList<String>(
                Arrays.asList(forecastArray));

        mForecastAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textview,
                weekForecast
        );


        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);

        if (mForecastAdapter == null)
        {
            Log.v(TAG, "adapter null!");
        }
        else if (listView == null)
        {
            Log.v(TAG, "listview null");
        }
        else
        {
            listView.setAdapter(mForecastAdapter);
        }

        return rootView;
    }


}