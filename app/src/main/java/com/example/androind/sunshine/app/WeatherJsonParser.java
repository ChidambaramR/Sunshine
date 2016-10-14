package com.example.androind.sunshine.app;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by chidr on 10/13/16.
 */

@Getter @Setter
public class WeatherJsonParser {
    private City city;
    private List<WeatherInfo> weatherInfoList;
    private Integer cod;
    private Double message;
    private Integer cnt;


    @JsonSetter("list")
    public void setWeatherInfo(List<WeatherInfo> weatherInfo) {
        this.weatherInfoList = weatherInfo;
    }

    @JsonSetter("city")
    public void setCity(City city) {
        this.city = city;
    }

    @Getter @Setter
    public static class City {
        private Long id;
        private String name;
        private String country;
        private Integer population;
        private Coord coord;

        @Getter @Setter
        public static class Coord {
            Double lat;
            Double lon;
        }
    }

    @Getter @Setter
    public static class WeatherInfo {
        private Long dt;
        private Temperature temp;
        private Double pressure;
        private Double humidity;
        private List<Weather> weather;
        private Double speed;
        private Double deg;
        private Integer clouds;
        private Double rain;


        @Getter @Setter
        public static class Temperature {
            Double day;
            Double min;
            Double max;
            Double night;
            Double eve;
            Double morn;
        }

        @Getter @Setter
        public static class Weather {
            Integer id;
            String main;
            String description;
            String icon;
        }

    }
}
