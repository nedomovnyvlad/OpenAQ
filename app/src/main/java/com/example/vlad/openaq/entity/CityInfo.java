package com.example.vlad.openaq.entity;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

import java.util.Comparator;

@AutoValue
@JsonDeserialize(builder = AutoValue_CityInfo.Builder.class)
public abstract class CityInfo {
    private static final String JSON_PROPERTY_NAME = "city";
    private static final String JSON_PROPERTY_COUNT = "count";

    public static CityInfo create(@NonNull String name, int count) {
        return new AutoValue_CityInfo.Builder()
                .name(name)
                .count(count)
                .build();
    }

    public abstract String name();

    public abstract Integer count();

    @AutoValue.Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    @SuppressWarnings("NullableProblems")
    public static abstract class Builder {

        @JsonProperty(JSON_PROPERTY_NAME)
        public abstract CityInfo.Builder name(@NonNull String name);

        @JsonProperty(JSON_PROPERTY_COUNT)
        public abstract CityInfo.Builder count(@NonNull Integer count);

        public abstract CityInfo build();
    }

    public static final class DescByCountComparator implements Comparator<CityInfo> {
        @Override
        public int compare(CityInfo o1, CityInfo o2) {
            return o2.count().compareTo(o1.count());
        }
    }
}
