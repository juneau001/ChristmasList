/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cjug.christmaslist.entity;

import java.io.Serializable;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juneau
 */
//@Entity
//@Table(name = "CHRISTMAS_PRESENT_IDEA")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "ChristmasPresentIdea.findAll", query = "SELECT c FROM ChristmasPresentIdea c"),
 //   @NamedQuery(name = "ChristmasPresentIdea.findById", query = "SELECT c FROM ChristmasPresentIdea c WHERE c.id = :id"),
 //   @NamedQuery(name = "ChristmasPresentIdea.findByItem", query = "SELECT c FROM ChristmasPresentIdea c WHERE c.item = :item"),
 //   @NamedQuery(name = "ChristmasPresentIdea.findByPrice", query = "SELECT c FROM ChristmasPresentIdea c WHERE c.price = :price")})
public class ChristmasPresentIdea implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @Column(name = "ID")
    private Integer id;
//    @Column(name = "ITEM")
    private String item;
//    @Column(name = "PRICE")
    private Integer price;

    public ChristmasPresentIdea() {
    }

    public ChristmasPresentIdea(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChristmasPresentIdea)) {
            return false;
        }
        ChristmasPresentIdea other = (ChristmasPresentIdea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cjug.christmaslist.entity.ChristmasPresentIdea[ id=" + id + " ]";
    }
    
}
