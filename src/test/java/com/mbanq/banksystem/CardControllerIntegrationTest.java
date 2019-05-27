package com.mbanq.banksystem;


import com.mbanq.banksystem.dto.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerIntegrationTest {


    @Autowired
    MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    private String token;
    private static final String AUTH_SIGN_IN = "/v1/api/auth/signin";

    private static final String URL_CARD_LIST = "/v1/api/card/list";
    private static final String URL_CARD_DETAIL = "/v1/api/card/detail";
    private static final String URLCARD_ACTIVATE = "/v1/api/card/activate";
    private static final String UR_CARD_DEACTIVATE = "/v1/api/card/deactivate";
    private static final String URL_CARD_DAILY_LIMIT = "/v1/api/card/daily-limit";

    private static final  Long consumerID = 3L;
    private static final  Long cardID     = 5L;


    @Before
    public void setUp() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mvc = builder.build();

        // Signup
        AuthSignInRequest request = new AuthSignInRequest();
        request.setPhoneNumber("093883292");
        request.setPassword("123456");

        try {
            token =  signInAndGetAccessToken(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(token).isNotNull();
        token =  "Bearer "+ token;

    }


    @Test
    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        ResultMatcher forbidden = MockMvcResultMatchers.status()
                .isForbidden();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(URL_CARD_LIST);

        this.mvc.perform(builder)
                .andExpect(forbidden);
    }


    @Test
    public void testListCardByConsumer() throws Exception {
        assertThat(token).isNotNull();

        ResultMatcher ok = MockMvcResultMatchers.status()
                .isOk();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(URL_CARD_LIST)
                .contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
                .header("Authorization", token);

        this.mvc.perform(builder)
                .andExpect(ok);

    }


    @Test
    public void testDetailCard() throws Exception {
        assertThat(token).isNotNull();

        ResultMatcher ok = MockMvcResultMatchers.status()
                .isOk();

        // request
        CardDetailRequest request = new CardDetailRequest();
        request.setId(cardID);


        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(URL_CARD_DETAIL)
                .contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(request))
                .header("Authorization", token);


        this.mvc.perform(builder)
                .andExpect(ok);


    }




    @Test
    public void activateShouldBeReturnOK() throws Exception {
        assertThat(token).isNotNull();

        ResultMatcher ok = MockMvcResultMatchers.status()
                .isOk();

        // request
        CardActivateRequest request = new CardActivateRequest();
        request.setId(cardID);


        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(URLCARD_ACTIVATE)
                .contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(request))
                .header("Authorization", token);


        this.mvc.perform(builder)
                .andExpect(ok);


    }


    @Test
    public void deactivateShouldBeReturnOK() throws Exception {
        assertThat(token).isNotNull();

        ResultMatcher ok = MockMvcResultMatchers.status()
                .isOk();

        // request
        CardDeactivateRequest request = new CardDeactivateRequest();
        request.setId(cardID);


        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(UR_CARD_DEACTIVATE)
                .contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(request))
                .header("Authorization", token);


        this.mvc.perform(builder)
                .andExpect(ok);


    }

    @Test
    public void changeDailyLimitShouldBeReturnOK() throws Exception {
        assertThat(token).isNotNull();

        ResultMatcher ok = MockMvcResultMatchers.status()
                .isOk();

        // request
        CardDailyLimitRequest request = new CardDailyLimitRequest();
        request.setId(cardID);
        request.setDailyLimit(3000.00);


        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(URL_CARD_DAILY_LIMIT)
                .contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(request))
                .header("Authorization", token);


        this.mvc.perform(builder)
                .andExpect(ok);


    }


    private String signInAndGetAccessToken(AuthSignInRequest request) throws Exception {

        ResultMatcher ok = MockMvcResultMatchers.status()
                .isOk();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(AUTH_SIGN_IN)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(request))
                .contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
                .accept("application/json;charset=UTF-8");

        ResultActions result = this.mvc.perform(builder)
                .andExpect(ok);

        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();

        return jsonParser.parseMap(resultString).get("token").toString();
    }
}
