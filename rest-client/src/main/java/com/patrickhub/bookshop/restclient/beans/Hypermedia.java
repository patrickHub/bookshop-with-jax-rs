/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restclient.beans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * this class will be extends by any entity that wan to have
 * HATEOAS capability.
 *
 * @author PatrickHub
 */
@XmlRootElement
public class Hypermedia {
    
    private List<LinkResource> links = new ArrayList<>();

    public void addLink(LinkResource linkResource){
        links.add(linkResource);
    }
    
    /**
     * @return the links
     */
    public List<LinkResource> getLinks() {
        return links;
    }

    /**
     * @param links the links to set
     */
    public void setLinks(List<LinkResource> links) {
        this.links = links;
    }
    
    
}
