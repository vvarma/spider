package com.nvr.spider.consumer.flow;

import com.nvr.spider.consumer.TweetConsumer;
import com.twitter.hbc.core.event.Event;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 7/30/14
 * Time: 12:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class TwitterStreamReader extends TweetConsumer{
    private JavaStreamingContext jssc;


    public TwitterStreamReader(BlockingQueue<String> msgQueue, BlockingQueue<Event> eventQueue) {
        super(msgQueue, eventQueue);
        jssc = new JavaStreamingContext("local[2]", "TwitterStreamReader", new Duration(1000));


    }


    @Override
    protected void consume() {
        JavaStreamingContext javaStreamingContext;
    }
}
