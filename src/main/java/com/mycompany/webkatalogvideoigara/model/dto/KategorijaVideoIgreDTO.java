package com.mycompany.webkatalogvideoigara.model.dto;


import jakarta.persistence.Entity;
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
public class KategorijaVideoIgreDTO {
    

    private Integer id;

    private String naziv;

    private Set<VideoIgraDTO> igre_u_kategoriji;

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

    public Set<VideoIgraDTO> getIgre_u_kategoriji() {
        return igre_u_kategoriji;
    }

    public void setIgre_u_kategoriji(Set<VideoIgraDTO> igre_u_kategoriji) {
        this.igre_u_kategoriji = igre_u_kategoriji;
    }
    
    
}
