package com.example.vlad.openaq.entity;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
@JsonDeserialize(builder = AutoValue_CityResponse.Builder.class)
public abstract class CityResponse {
    private static final String JSON_PROPERTY_RESULTS = "results";

    public abstract List<CityInfo> results();

    @AutoValue.Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static abstract class Builder {

        @JsonProperty(JSON_PROPERTY_RESULTS)
        public abstract CityResponse.Builder results(@NonNull List<CityInfo> results);

        public abstract CityResponse build();
    }
}
