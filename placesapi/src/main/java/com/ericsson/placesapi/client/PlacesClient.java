package com.ericsson.placesapi.client;

import com.ericsson.placesapi.entity.model.Outcome;
import com.ericsson.placesapi.entity.model.Result;
import com.ericsson.placesapi.entity.model.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlacesClient implements IPlacesClient {

    private static final String BASE_API = "https://maps.googleapis.com/maps/api/place";
    private static final String NEARBY_SEARCH = "/nearbysearch";
    private static final String OUT_JSON = "/json";

    @Value("${GOOGLE_APIKEY}")
    private String GOOGLE_APIKEY;

    private final RestTemplate restTemplate;

    @Autowired
    public PlacesClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String urlBilder(float lat, float lng, float radius) {
        StringBuilder sb = new StringBuilder(BASE_API);
        sb.append(NEARBY_SEARCH);
        sb.append(OUT_JSON + "?");
        sb.append("&location=" + lat + "," + lng);
        sb.append("&radius=" + radius);
        sb.append("&key=" + GOOGLE_APIKEY);

        return sb.toString();
    }

    public Outcome paginate(String url, String pageToken) {
        if (!pageToken.isEmpty()) {
            url += "&pagetoken=" + pageToken;
        }
        return restTemplate.getForObject(url, Outcome.class);
    }

    @Override
    public List<Result> nearBySearch(SearchDTO search) {
        String url = urlBilder(search.getLatitude(), search.getLongitude(), search.getRadius());
        Outcome response = paginate(url, "");
        if (response == null) {
            return new ArrayList<>();
        }

        List<Result> list = response.getResults();

        while (response.getNextPageToken() != null) {
            response = paginate(url, response.getNextPageToken());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.addAll(response.getResults());
        }

        return list;
    }
}
