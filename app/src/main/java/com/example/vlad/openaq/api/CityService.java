package com.example.vlad.openaq.api;

import com.example.vlad.openaq.entity.CityResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CityService {

    @GET("v1/cities/?country=US&order_by=count&sort=desc")
    Single<CityResponse> getCities();
}
