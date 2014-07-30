package com.nvr.spider.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 7/28/14
 * Time: 11:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpiderBootstrapConfiguration extends Configuration{
    @NotNull
    private TwitterClientConfig twitterClientConfig;

    @JsonProperty
    public TwitterClientConfig getTwitterClientConfig() {
        return twitterClientConfig;
    }

    @JsonProperty
    public void setTwitterClientConfig(TwitterClientConfig twitterClientConfig) {
        this.twitterClientConfig = twitterClientConfig;
    }
}
