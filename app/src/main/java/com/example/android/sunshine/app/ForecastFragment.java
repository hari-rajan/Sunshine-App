package com.example.android.sunshine.app;

/**
 * Created by harirajan on 10/14/15.
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment implements LocationListener
{
    private String TAG = ForecastFragment.class.getSimpleName();
    private ArrayAdapter<String> mForecastAdapter;
    public String[] mForecastArray;
    LocationManager locationManager;


    public ForecastFragment()
    {

    }

    @Override
    public void onLocationChanged (Location location)
    {

    }

    @Override
    public void onStatusChanged (String str, int k, Bundle bundle)
    {

    }

    @Override
    public void onProviderDisabled (String str)
    {

    }

    @Override
    public void onProviderEnabled (String str)
    {

    }

    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION);

        Location location;

        if (permissionCheck != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION))
            {

            }
        }
        else
        {
             location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
             Log.v(TAG, "location " + location.toString());

        }


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
            fetchWeatherTask.setParent(this);
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
                "Saturday - Brrr",
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

    public Void setWeatherData (String[] weatherArray)
    {
        Log.v(TAG, "setWeatherData(). " + weatherArray);
        if (weatherArray != null)
        {
            mForecastAdapter.clear();
            mForecastAdapter.addAll(weatherArray);
        }
        return null;
    }
}