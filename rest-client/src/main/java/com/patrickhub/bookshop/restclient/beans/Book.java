/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restclient.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PatrickHub
 */
@XmlRootElement
public class Book extends Hypermedia  implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private static final String IMAGE_LOCATION = "/images/covers/";
    
    private int id;
    @NotNull
    @Size(min=10, max=50)
    private String title;
    @NotNull
    @Size(min=20, max=500)
    private String description;
    @NotNull
    private List<Author> authors;
    @NotNull
    @DecimalMin("0.00")
    private Float price;
    @NotNull
    @Size(max=50)
    private String imgPath;
    @NotNull
    @Pattern(regexp="^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$")
    private String link;
    @NotNull
    private String published;

    public Book() {
    }
    
    public Book(int id, String title, String description, List<Author> authors, Float price, String imgPath, String link, String published) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.price = price;
        this.imgPath = imgPath;
        this.link = link;
        this.published = published;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", description=" + description + ", authors=" + authors + ", price=" + price + ", imgPath=" + imgPath + ", link=" + link + ", published=" + published + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.title);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.authors);
        hash = 59 * hash + Objects.hashCode(this.price);
        hash = 59 * hash + Objects.hashCode(this.imgPath);
        hash = 59 * hash + Objects.hashCode(this.link);
        hash = 59 * hash + Objects.hashCode(this.published);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.imgPath, other.imgPath)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        if (!Objects.equals(this.authors, other.authors)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.published, other.published)) {
            return false;
        }
        return true;
    }
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the authors
     */
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * @return the imgPath
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * @param imgPath the imgPath to set
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the published
     */
    public String getPublished() {
        return published;
    }

    /**
     * @param published the published to set
     */
    public void setPublished(String published) {
        this.published = published;
    }
    
    
}
