package com.example.vlad.openaq;

public final class Constants {
    public static final String DO_NOT_CREATE_INSTANCE_OF_THIS_CLASS = "You shouldn't create instance of this class";

    public static final class Api {
        public static final String BASE_URL = "https://api.openaq.org/";

        public static final int MIN_CITY_COUNT = 10000;
        public static final int CITY_LIMIT = 100;
    }

    public static final class HttpClient {
        public static final int CONNECT_TIMEOUT_MS = 10000;
        public static final int READ_TIMEOUT_MS = 10000;
        public static final int WRITE_TIMEOUT_MS = 10000;
    }

    private Constants() {
        throw new IllegalStateException(DO_NOT_CREATE_INSTANCE_OF_THIS_CLASS);
    }
}