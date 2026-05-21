package com.weather.kafka.config;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class CityConfig {

    private CityConfig() {}

    public static final List<String> CITIES = List.of(
            "Paris",
            "Lyon",
            "Marseille",
            "Lille",
            "Toulouse",
            "Nice",
            "Nantes",
            "Strasbourg",
            "Montpellier",
            "Bordeaux",
            "Rennes",
            "Reims",
            "Le Havre",
            "Saint-Étienne",
            "Toulon",
            "Grenoble",
            "Dijon",
            "Angers",
            "Nîmes",
            "Villeurbanne"
    );

    public static final Map<String, String> CITY_REGION_MAP = new HashMap<>();

    static {

        CITY_REGION_MAP.put("paris", "Nord");
        CITY_REGION_MAP.put("lille", "Nord");
        CITY_REGION_MAP.put("reims", "Nord");
        CITY_REGION_MAP.put("le havre", "Nord");

        CITY_REGION_MAP.put("lyon", "Est");
        CITY_REGION_MAP.put("strasbourg", "Est");
        CITY_REGION_MAP.put("grenoble", "Est");
        CITY_REGION_MAP.put("dijon", "Est");
        CITY_REGION_MAP.put("villeurbanne", "Est");
        CITY_REGION_MAP.put("saint etienne", "Est");

        CITY_REGION_MAP.put("marseille", "Sud");
        CITY_REGION_MAP.put("nice", "Sud");
        CITY_REGION_MAP.put("montpellier", "Sud");
        CITY_REGION_MAP.put("toulon", "Sud");
        CITY_REGION_MAP.put("nimes", "Sud");

        CITY_REGION_MAP.put("toulouse", "Ouest");
        CITY_REGION_MAP.put("nantes", "Ouest");
        CITY_REGION_MAP.put("bordeaux", "Ouest");
        CITY_REGION_MAP.put("rennes", "Ouest");
        CITY_REGION_MAP.put("angers", "Ouest");
    }

    public static String normalize(String city) {

        if (city == null) return "";

        return Normalizer.normalize(city, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replace("-", " ")
                .toLowerCase(Locale.ROOT)
                .trim();
    }

    public static String getRegion(String city) {

        String normalizedCity = normalize(city);

        return CITY_REGION_MAP.getOrDefault(
                normalizedCity,
                "Unknown"
        );
    }
}