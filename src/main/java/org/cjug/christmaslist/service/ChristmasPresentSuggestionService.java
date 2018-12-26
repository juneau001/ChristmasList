/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cjug.christmaslist.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

/**
 *
 * @author Juneau
 */
@Path("christmasPresentSuggestionService")
@ApplicationScoped
public class ChristmasPresentSuggestionService {
    
    private final long TIMEOUT = 500;
    private final long SLEEPTIME = 1000;

    @Inject
    private ChristmasListService christmasListService;

    int retryCounter = 0;

    @GET
    @Path("{id}")
    @Retry(maxRetries = 4, retryOn = {RuntimeException.class})
    public String getPresentById(@PathParam("id") int id) {
        System.out.println("Called getPresentById a total of " + ++retryCounter + " times");
        if (id >= christmasListService.obtainIdeas().size()) {
            return "No such present. Try a number lower than " + christmasListService.obtainIdeas().size();
        }
        if (isDown()) {
            throw new RuntimeException();
        }
        return christmasListService.obtainIdeas().get(id).getItem();
    }

    private boolean isDown() {
        // approx 80% chance
        return Math.random() > 0.2;
    }
    
    @GET
    @Fallback(fallbackMethod = "getAllPresentsFallback")
    @Timeout(TIMEOUT)
    public String getAllPresents() throws InterruptedException {
        if (isSlow()) return christmasListService.obtainIdeas().toString();
        return christmasListService.obtainIdeas().toString();
    }

    public String getAllPresentsFallback() {
        return "It took longer than expected to get all presents. Try again later!";
    }

    private boolean isSlow() throws InterruptedException {
        if (Math.random() > 0.4) {
            // approx 60% chance
            Thread.sleep(SLEEPTIME);
            return true;
        }
        return false;
    }
}
