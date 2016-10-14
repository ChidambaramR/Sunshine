package com.example.androind.sunshine.app;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chidr on 10/13/16.
 */

class WeatherInfoFetcher extends AsyncTask<String, Integer, List<String>> {
    @Override
    protected List<String> doInBackground(String... params) {
        int count = params.length;
        List<String> weather = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();

                URL url = new URL(params[i]);
                WeatherJsonParser weatherObj = objectMapper.readValue(url, WeatherJsonParser.class);
                Log.d("Debug", weatherObj.getCity().getName());
                publishProgress((int) ((i / (float) count) * 100));

                for (WeatherJsonParser.WeatherInfo info : weatherObj.getWeatherInfoList()) {
                    for (WeatherJsonParser.WeatherInfo.Weather weather1 : info.getWeather()) {
                        weather.add(weather1.getMain());
                    }
                }

            } catch (IOException e) {
                System.out.println("Got io exception");
                e.printStackTrace();
            }
        }

        return weather;
    }
}
