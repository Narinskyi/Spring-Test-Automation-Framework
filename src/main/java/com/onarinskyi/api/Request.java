package com.onarinskyi.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onarinskyi.annotations.Delete;
import com.onarinskyi.annotations.Get;
import com.onarinskyi.annotations.Post;
import com.onarinskyi.annotations.Put;
import com.onarinskyi.environment.Api;
import com.onarinskyi.environment.UrlResolver;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Component
public abstract class Request {

    @JsonIgnore
    private Logger log = Logger.getLogger(this.getClass());

    @JsonIgnore
    protected Map<String, String> headers = new HashMap<>();

    @JsonIgnore
    protected Map<String, String> parameters = new HashMap<>();

    @JsonIgnore
    @Autowired
    private Api api;

    @JsonIgnore
    @Autowired
    private UrlResolver urlResolver;

    @Step("Sending request")
    public <T extends Response> T sendAndExpect(Class<T> responseClass) {

        RestAssured.baseURI = api.getBaseUrl();

        T response = null;

        try {
            RequestSpecification request = given()
                    .relaxedHTTPSValidation()
                    .auth().none()
                    .contentType("application/json")
                    .headers(headers)
                    .body(this);

            parameters.keySet().forEach(key -> request.queryParam(key, parameters.get(key)));

            String url = urlResolver.getResolvedUrlFor(this);

            Class clazz = this.getClass();

            log.info("Sending request: " + this.getClass().getSimpleName() + " to: " + url);

            String responseBody = clazz.isAnnotationPresent(Get.class) ?
                    request.get(url).getBody().asString() :
                    clazz.isAnnotationPresent(Put.class) ?
                            request.put(url).getBody().toString() :
                            clazz.isAnnotationPresent(Post.class) ?
                                    request.post(url).getBody().toString() :
                                    clazz.isAnnotationPresent(Delete.class) ?
                                            request.delete(url).getBody().toString() : "";

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            response = mapper.readValue(responseBody, responseClass);
            response.setBody(responseBody);
        } catch (IOException e) {
            log.error("Reading response object failed");
            Assert.fail(e.getMessage());
        }

        attach(this);
        attach(response);

        return response;
    }

    @Attachment(type = "application/json", value = "Request")
    private String attach(Request request) {
        return request.toString();
    }

    @Attachment(type = "application/json", value = "Response")
    private String attach(Response response) {
        return response.toString();
    }

    public <T extends Request> T withHeaders(Map<String, String> headers) {
        this.headers = headers;
        return (T) this;
    }

    public <T extends Request> T withParameter(String key, String value) {
        this.parameters.put(key, value);
        return (T) this;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error("Parsing request object to JSON failed");
            Assert.fail(e.getMessage());
        }
        return "";
    }
}
