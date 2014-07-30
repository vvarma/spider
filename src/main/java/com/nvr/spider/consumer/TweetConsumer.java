package com.nvr.spider.consumer;

import com.twitter.hbc.core.event.Event;
import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 7/29/14
 * Time: 12:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class TweetConsumer implements Managed {
    private static final Logger logger = LoggerFactory.getLogger(TweetConsumer.class);
    protected final BlockingQueue<String> msgQueue;
    protected final BlockingQueue<Event> eventQueue;
    private volatile boolean running;

    public TweetConsumer(BlockingQueue<String> msgQueue, BlockingQueue<Event> eventQueue) {
        this.msgQueue = msgQueue;
        this.eventQueue = eventQueue;
    }

    @Override
    public void start() throws Exception {
        running = true;
        run();
    }

    @Override
    public void stop() throws Exception {
        running = false;
    }

    private void run() {
        while (running) {
            this.consume();
        }
    }

    protected void consume() {
        try {
            String message = msgQueue.poll(1000, TimeUnit.MILLISECONDS);
            if (message != null) {
                logger.info(message);
            }
            Event event = eventQueue.poll(1000, TimeUnit.MILLISECONDS);
            if (event != null) {
                logger.info(event.getEventType().name() + " : " + event.getMessage());
            }
        } catch (InterruptedException e) {
            logger.error("Exception in tweet consumer ", e);
        }
    }
}
