package com.nvr.spider.config;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 7/29/14
 * Time: 12:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class TwitterClientConfig {
    @NotEmpty
    private String consumerKey;
    @NotEmpty
    private String consumerSecret;
    @NotEmpty
    private String token;
    @NotEmpty
    private String secret;

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
