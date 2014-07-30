package com.nvr.spider.controller;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 7/28/14
 * Time: 11:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/spider")
@Produces(MediaType.APPLICATION_JSON)
public class SpiderResource {
    @GET
    @Timed
    public String sayHello(@QueryParam("name") Optional<String> name) {
        return "Hello "+name.or("Sir !");
    }

}
