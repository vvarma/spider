package com.nvr.spider.consumer.flow;

import io.dropwizard.lifecycle.Managed;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.dstream.DStream;

import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 7/30/14
 * Time: 12:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class StreamReader<T> implements Managed {
    private JavaStreamingContext jssc;
    private Queue<T> messageQueue;

    public StreamReader() {
        jssc = new JavaStreamingContext("local[2]", "SpiderStreamReader", new Duration(1000));

    }

    @Override
    public void start() throws Exception {
        jssc.start();
    }

    @Override
    public void stop() throws Exception {
        jssc.stop();
    }
}
