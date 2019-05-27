package com.mbanq.banksystem;


import com.mbanq.banksystem.dto.AuthSignInRequest;
import com.mbanq.banksystem.dto.AuthSignUpRequest;
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
public class AuthControllerIntegrationTest {


    @Autowired
    MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    private static final String AUTH_SIGN_IN = "/v1/api/auth/signin";
    private static final String AUTH_SIGN_UP = "/v1/api/auth/signup";

    @Before
    public void setUp() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mvc = builder.build();
    }

    @Test
    public void authSignInWillShouldReturnToken() {

        try {
            AuthSignInRequest request = new AuthSignInRequest();
            request.setPhoneNumber("093883292");
            request.setPassword("123456");

          String token =   signInAndGetAccessToken(request);
            assertThat(token).isNotNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void authSignUpillShouldReturnToken() {

        try {
            AuthSignUpRequest request = new AuthSignUpRequest();
            request.setPhoneNumber("08199999");
            request.setPassword("123456");
            request.setRole("consumer");

          String token =   singUpAndGetToken(request);
          assertThat(token).isNotNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void phoneNumberAlreadySignUpItShouldBeReturn400Status(){

    }

    private String signInAndGetAccessToken(AuthSignInRequest request) throws Exception {

        ResultMatcher ok = MockMvcResultMatchers.status()
                .isOk();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(AUTH_SIGN_IN)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(request))
                .contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
                .contentType("application/json;charset=UTF-8")
                .accept("application/json;charset=UTF-8");

        ResultActions result = this.mvc.perform(builder)
                .andExpect(ok);

        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();

        return jsonParser.parseMap(resultString).get("token").toString();
    }




    private String singUpAndGetToken(AuthSignUpRequest request) throws Exception {

        ResultMatcher created = MockMvcResultMatchers.status()
                .isCreated();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(AUTH_SIGN_UP)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(request))
                .contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
                .contentType("application/json;charset=UTF-8")
                .accept("application/json;charset=UTF-8");

        ResultActions result = this.mvc.perform(builder)
                .andExpect(created);

        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();

        return jsonParser.parseMap(resultString).get("token").toString();
    }



}
