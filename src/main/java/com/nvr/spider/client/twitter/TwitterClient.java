package com.nvr.spider.client.twitter;

import com.google.common.collect.Lists;
import com.nvr.spider.config.TwitterClientConfig;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 7/29/14
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class TwitterClient implements Managed {

    private static Logger logger= LoggerFactory.getLogger(TwitterClient.class);
    private final BlockingQueue<String> msgQueue;
    private final BlockingQueue<Event> eventQueue;
    private final TwitterClientConfig twitterClientConfig;
    private Client hosebirdClient;


    public TwitterClient(TwitterClientConfig twitterClientConfig, BlockingQueue<String> msgQueue, BlockingQueue<Event> eventQueue) {
        this.msgQueue = msgQueue;
        this.eventQueue = eventQueue;
        this.twitterClientConfig = twitterClientConfig;
    }


    @Override
    public void start() throws Exception {
        logger.info("Initiate twitter client");
        this.initTwitterClient(twitterClientConfig);
        logger.info("Initiatedx twitter client");

    }

    @Override
    public void stop() throws Exception {
        logger.info("Stopping twitter client");
        hosebirdClient.stop();
    }

    private void initTwitterClient(TwitterClientConfig twitterClientConfig) {
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();

        List<Long> followings = Lists.newArrayList(1234L, 566788L);
        List<String> terms = Lists.newArrayList("flipkart");
        hosebirdEndpoint.followings(followings);
        hosebirdEndpoint.trackTerms(terms);

        Authentication hosebirdAuth = new OAuth1(twitterClientConfig.getConsumerKey(), twitterClientConfig.getConsumerSecret(),
                twitterClientConfig.getToken(), twitterClientConfig.getSecret());

        ClientBuilder builder = new ClientBuilder()
                .name("Spider-Hosebird-Client")                              // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue))
                .eventMessageQueue(eventQueue);                          // optional: use this if you want to process client events

        this.hosebirdClient = builder.build();
        hosebirdClient.connect();

    }
}

