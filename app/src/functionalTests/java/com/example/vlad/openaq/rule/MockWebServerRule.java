package com.example.vlad.openaq.rule;

import android.support.annotation.NonNull;

import com.example.vlad.openaq.util.TestUtils;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.lang.reflect.Method;

import okhttp3.mockwebserver.MockWebServer;

public class MockWebServerRule implements TestRule {

    @NonNull
    private final Object testClassInstance;

    public MockWebServerRule(@NonNull Object testClassInstance) {
        this.testClassInstance = testClassInstance;
    }

    @Override
    @NonNull
    public Statement apply(@NonNull Statement base, @NonNull Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                final NeedsMockWebServer needsMockWebServer =
                        description.getAnnotation(NeedsMockWebServer.class);

                if (needsMockWebServer != null) {
                    final MockWebServer mockWebServer = new MockWebServer();
                    mockWebServer.start();

                    changeBaseUrl(mockWebServer);

                    if (needsMockWebServer.setupMethods().length != 0) {
                        invokeSetupMethods(needsMockWebServer.setupMethods(), mockWebServer);
                    }

                    try {
                        base.evaluate();
                    } finally {
                        mockWebServer.shutdown();
                    }
                } else {
                    base.evaluate();
                }
            }
        };
    }

    private void changeBaseUrl(@NonNull MockWebServer mockWebServer) {
        TestUtils.getChangeableBaseUrl()
                .setUrl(mockWebServer.url("").toString());
    }

    private void invokeSetupMethods(@NonNull String[] setupMethodsArr,
                                    @NonNull MockWebServer mockWebServer) throws ReflectiveOperationException {
        for (String setupMethodString : setupMethodsArr) {
            final Method setupMethod = testClassInstance
                    .getClass()
                    .getDeclaredMethod(
                            setupMethodString,
                            MockWebServer.class
                    );

            setupMethod.invoke(testClassInstance, mockWebServer);
        }
    }
}

