package com.nvr.spider.controller;

import com.nvr.spider.client.twitter.TwitterClient;
import com.nvr.spider.config.SpiderBootstrapConfiguration;
import com.nvr.spider.consumer.TweetConsumer;
import com.twitter.hbc.core.event.Event;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 7/28/14
 * Time: 11:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpiderApplication extends Application<SpiderBootstrapConfiguration> {
    public static void main(String[] args) throws Exception {
        new SpiderApplication().run(args);
    }
    @Override
    public void initialize(Bootstrap<SpiderBootstrapConfiguration> spiderBootstrapConfigurationBootstrap) {
    }

    @Override
    public void run(SpiderBootstrapConfiguration spiderBootstrapConfiguration, Environment environment) throws Exception {
        final SpiderResource spiderResource=new SpiderResource();
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(100000);
        BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>(1000);
        final TweetConsumer tweetConsumer=new TweetConsumer(msgQueue,eventQueue);
        final TwitterClient twitterClient=new TwitterClient(spiderBootstrapConfiguration.getTwitterClientConfig(),
                msgQueue,eventQueue);
        environment.jersey().register(spiderResource);
        environment.lifecycle().manage(twitterClient);
        environment.lifecycle().manage(tweetConsumer);
    }
}
