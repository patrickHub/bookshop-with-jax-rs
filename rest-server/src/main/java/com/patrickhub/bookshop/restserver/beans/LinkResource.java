/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.beans;

import java.net.URI;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author PatrickHub
 */

@XmlRootElement
public class LinkResource {
    private String rel;
    private String type;
    private URI uri;

    public LinkResource() {}
    
    public LinkResource(Link link) {
        this.rel = link.getRel();
        this.type = link.getType();
        this.uri = link.getUri();
    }
    
    

    /**
     * @return the rel
     */
    public String getRel() {
        return rel;
    }

    /**
     * @param rel the rel to set
     */
    public void setRel(String rel) {
        this.rel = rel;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the uri
     */
    public URI getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(URI uri) {
        this.uri = uri;
    }
    
    public static LinkResource createLinkRResource(UriInfo uriInfo, Class classPath , String resourceMethod, int id, String rel, String type){
        Link link = Link.fromUri(uriInfo.getBaseUriBuilder()
                  .path(classPath)
                  .path(classPath, resourceMethod)
                  .build(id))
                  .rel(rel)
                  .type(type)
                  .build();
       
        return new  LinkResource(link);
    }
    
    
}
