package com.mycompany.webkatalogvideoigara.model.dto;


import jakarta.persistence.Entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author uros
 */
@Entity
public class KomentarDTO {
    

    private Integer id;

    private Integer igra_id;

    private Integer user_id;

    private String tekst;

    private Integer ocena;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }
    
    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getIgra_id() {
        return igra_id;
    }

    public void setIgra_id(Integer igra_id) {
        this.igra_id = igra_id;
    }
    
    
}