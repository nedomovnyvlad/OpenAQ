package com.example.vlad.openaq.rule;

import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
public @interface NeedsMockWebServer {

    @NonNull String[] setupMethods() default {};
}
