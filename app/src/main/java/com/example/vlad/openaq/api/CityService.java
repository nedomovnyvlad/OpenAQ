package com.example.vlad.openaq.api;

import com.example.vlad.openaq.entity.CityResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

import static com.example.vlad.openaq.Constants.Api.CITY_LIMIT;

public interface CityService {

    @GET("v1/cities/?country=US&limit=" + CITY_LIMIT)
    Single<CityResponse> getCities();
}
