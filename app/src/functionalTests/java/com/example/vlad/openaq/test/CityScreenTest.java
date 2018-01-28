package com.example.vlad.openaq.test;

import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.vlad.openaq.rule.MockWebServerRule;
import com.example.vlad.openaq.rule.NeedsMockWebServer;
import com.example.vlad.openaq.screen.CityScreen;
import com.example.vlad.openaq.ui.activity.main.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static com.example.vlad.openaq.Constants.Api.MIN_CITY_COUNT;

@RunWith(AndroidJUnit4.class)
public class CityScreenTest {

    @Rule
    public RuleChain rules = RuleChain.emptyRuleChain()
            .around(new MockWebServerRule(this))
            .around(new ActivityTestRule<>(MainActivity.class));

    private CityScreen cityScreen;

    @Before
    public void setUp() {
        cityScreen = new CityScreen();
    }

    @Test
    @NeedsMockWebServer(setupMethods = "enqueueSuccessfulResponse")
    public void shouldDisplayAllItemsFromSuccessfulResponse() {
        cityScreen.shouldDisplayExpectedAmountOfItems(2)
                .shouldNotDisplayErrorText()
                .shouldNotDisplayRepeatRequestButton();

        for (int i = 1; i < 3; i++) {
            cityScreen.shouldDisplayItemWithName("Name " + i)
                    .shouldDisplayItemWithCount(MIN_CITY_COUNT + i);
        }
    }

    @Test
    @NeedsMockWebServer(setupMethods = "enqueueUnsuccessfulResponse")
    public void shouldDisplayErrorUiFromUnsuccessfulResponse() {
        cityScreen.shouldNotDisplayItems()
                .shouldDisplayErrorText()
                .shouldDisplayRepeatRequestButton();
    }

    @Test
    @NeedsMockWebServer(setupMethods = {"enqueueUnsuccessfulResponse", "enqueueSuccessfulResponse"})
    public void shouldDisplayItemsAfterTryAgainButtonClick() {
        cityScreen.shouldNotDisplayItems()
                .shouldDisplayErrorText()
                .shouldDisplayRepeatRequestButton()
                .clickOnRepeatRequestButton()
                .shouldDisplayExpectedAmountOfItems(2)
                .shouldNotDisplayErrorText()
                .shouldNotDisplayRepeatRequestButton();
    }

    public void enqueueSuccessfulResponse(@NonNull MockWebServer mockWebServer) {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\n" +
                        "   \"meta\":{\n" +
                        "      \"name\":\"openaq-api\",\n" +
                        "      \"license\":\"CC BY 4.0\",\n" +
                        "      \"website\":\"https://docs.openaq.org/\",\n" +
                        "      \"page\":1,\n" +
                        "      \"limit\":2,\n" +
                        "      \"found\":2021\n" +
                        "   },\n" +
                        "   \"results\":[\n" +
                        "      {\n" +
                        "         \"city\":\"Name 1\",\n" +
                        "         \"country\":\"AD\",\n" +
                        "         \"locations\":2,\n" +
                        "         \"count\":" + (MIN_CITY_COUNT + 1) + "\n" +
                        "      },\n" +
                        "      {\n" +
                        "         \"city\":\"Name 2\",\n" +
                        "         \"country\":\"AD\",\n" +
                        "         \"locations\":1,\n" +
                        "         \"count\":" + (MIN_CITY_COUNT + 2) + "\n" +
                        "      }\n" +
                        "   ]\n" +
                        "}"
                )
        );
    }

    public void enqueueUnsuccessfulResponse(@NonNull MockWebServer mockWebServer) {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404));
    }
}
