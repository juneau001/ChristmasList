/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cjug.christmaslist.service.exception;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

/**
 *
 * @author Juneau
 */
@Provider
public class ChristmasListExceptionMapper implements
    ResponseExceptionMapper<Exception> {

    @Override
    public boolean handles(int statusCode, MultivaluedMap<String, Object> headers) {
        return statusCode == 404  // Not Found
            || statusCode == 409; // Conflict
    }

    @Override
    public Exception toThrowable(Response response) {
        switch(response.getStatus()) {
        case 404: return new Exception();
        case 409: return new Exception();
        }
        return null;
    }

}