/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cjug.christmaslist.utility;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;    
@Health
@ApplicationScoped
public class ChristmasListHealth implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.
                named("xmas").
                up().
                withData("Santa", "Rudolph").
                build();
    }

}