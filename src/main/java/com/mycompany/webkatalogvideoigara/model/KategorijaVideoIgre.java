package com.mycompany.webkatalogvideoigara.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Set;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author uros
 */
@Entity
public class KategorijaVideoIgre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private String naziv;
    
    @ManyToMany
    private Set<VideoIgra> igre_u_kategoriji;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<VideoIgra> getIgre_u_kategoriji() {
        return igre_u_kategoriji;
    }

    public void setIgre_u_kategoriji(Set<VideoIgra> igre_u_kategoriji) {
        this.igre_u_kategoriji = igre_u_kategoriji;
    }
    
    
}
