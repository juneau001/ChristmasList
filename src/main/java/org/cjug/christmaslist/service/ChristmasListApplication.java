/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cjug.christmaslist.service;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

/**
 *
 * @author Juneau
 */
@ApplicationPath("/rest")
@OpenAPIDefinition(info = @Info(
            title = "Christmas List application", 
            version = "1.0.0", 
            contact = @Contact(
                    name = "Josh Juneau", 
                    email = "josh.juneau@cjug.org",
                    url = "http://jj-blogger.blogspot.com")
            ),
            servers = {
                @Server(url = "/ChristmasList-1.0-SNAPSHOT",description = "localhost")
            }
    )
public class ChristmasListApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.cjug.christmaslist.service.ChristmasListService.class);
        resources.add(org.cjug.christmaslist.service.ChristmasPresentSuggestionService.class);
        resources.add(org.cjug.christmaslist.service.exception.ChristmasListExceptionMapper.class);
    }
}
