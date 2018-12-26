/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cjug.christmaslist.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.cjug.christmaslist.entity.ChristmasPresentIdea;
import org.cjug.christmaslist.service.exception.ChristmasListExceptionMapper;
import org.cjug.christmaslist.service.interfaces.ChristmasListDataService;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

/**
 *
 * @author Juneau
 */
@RequestScoped
@Path("/christmasListService")
@Tag(name = "Christmas List service", description = "Get the correct gifts for the correct person!")
public class ChristmasListService {
    
    private final long TIMEOUT=500;

    @Inject
    @ConfigProperty(name = "username", defaultValue = "admin")
    private Provider<String> username;

    @Inject
    Config config;

    private int retryCounter = 0;

    @GET
    public List<ChristmasPresentIdea> obtainIdeas() {
        String[] listIdeas = config.getValue("christmas.list", String[].class);
        List<ChristmasPresentIdea> ideas = new ArrayList<>();
        for (int idx = 1; idx <= listIdeas.length; idx++) {

            ChristmasPresentIdea present = new ChristmasPresentIdea();
            present.setId(idx);
            present.setItem(listIdeas[idx - 1]);
            ideas.add(present);
        }
        return ideas;
    }

    @GET
    @Path("/user")
    @Produces(MediaType.TEXT_PLAIN)
    public String obtainUsername() {
        return username.get();
    }

    @GET
    @Path("/dataListPresents")
    @Fallback(fallbackMethod = "obtainDataListPresentsFallback")
    @Timeout(TIMEOUT)
    public List<ChristmasPresentIdea> obtainDataListPresents() {
        URL apiUrl = null;
        try {
            apiUrl = new URL("http://localhost:8081/ChristmasListDataService/webresources/christmaspresentidea");
        } catch (MalformedURLException ex) {
            Logger.getLogger(ChristmasListService.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChristmasListDataService presentSvc
                = RestClientBuilder.newBuilder()
                        .baseUrl(apiUrl)
                        .register(ChristmasListExceptionMapper.class)
                        .build(ChristmasListDataService.class);
        System.out.println("Present Service: " + presentSvc);
        System.out.println("Total: " + presentSvc.getPresentIdeas().size());
        List<ChristmasPresentIdea> presents = presentSvc.getPresentIdeas();
        return presents;
    }
    
    public List<ChristmasPresentIdea> obtainDataListPresentsFallback() {
        List<ChristmasPresentIdea> presentList = new ArrayList();
        ChristmasPresentIdea present = new ChristmasPresentIdea();
        present.setId(0);
        present.setItem("Error retrieving presents.  Try again later.");
        presentList.add(present);
        return presentList;
    }

}
