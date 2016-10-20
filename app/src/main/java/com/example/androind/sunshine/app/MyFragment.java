package com.example.androind.sunshine.app;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by chidr on 10/8/16.
 */

public class MyFragment extends ListFragment implements AdapterView.OnItemClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /*ScrollView scroller = new ScrollView(getActivity());
        TextView text = new TextView(getActivity());

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());

        text.setPadding(padding, padding, padding, padding);
        scroller.addView(text);
        text.setText("Hello world. This is chid");

        return scroller;*/

        View rootView = inflater.inflate(R.layout.main_fragment, container, false);
        return rootView;


    }

    private List<String> sevenDaysFromNow() {
        Calendar now = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        List<String> days = new ArrayList<>();

        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2; //add 2 if your week start on monday
        now.add(Calendar.DAY_OF_MONTH, delta );
        for (int i = 0; i < 7; i++)
        {
            days.add(i, format.format(now.getTime()));

            now.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            List<String> weather = new WeatherInfoFetcher().execute("http://api.openweathermap.org/data/2.5/forecast/daily?zip=94041%2Cus&appid=52b03e99eaa81400047e68273f853df2&cnt=7").get();
            //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Countdown, android.R.layout.simple_list_item_1);
            System.out.println(weather);

            ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, sevenDaysFromNow());

            setListAdapter(ad);
            getListView().setOnItemClickListener(this);
        } catch (InterruptedException ie) {
            System.out.println("Interrupted exception");
            ie.printStackTrace();
            System.exit(0);
        } catch (ExecutionException e) {
            System.out.println("Execution exception");
            e.printStackTrace();
            System.exit(0);
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getContext(), DetailedActivity.class);
        intent.putExtra("com.example.android.sunshine.app.DAY", position);
        startActivity(intent);
    }

}
