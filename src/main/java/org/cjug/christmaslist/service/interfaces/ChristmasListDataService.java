/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cjug.christmaslist.service.interfaces;

import java.util.List;
import javax.ws.rs.GET;
import org.cjug.christmaslist.entity.ChristmasPresentIdea;

/**
 *
 * @author Juneau
 */
public interface ChristmasListDataService {
    
    @GET
    public List<ChristmasPresentIdea> getPresentIdeas();
    
}
