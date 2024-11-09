package com.mycompany.webkatalogvideoigara.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.time.Year;
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
public class VideoIgra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToMany
    private Set<KategorijaVideoIgre> igre_u_kategoriji;
    
    @Column
    private String naziv;
    
    @Column
    private String izdavacka_kuca;
    
    @Column
    private String opis;
    
    @Column
    private Year godina_izdanja;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<KategorijaVideoIgre> getIgre_u_kategoriji() {
        return igre_u_kategoriji;
    }

    public void setIgre_u_kategoriji(Set<KategorijaVideoIgre> igre_u_kategoriji) {
        this.igre_u_kategoriji = igre_u_kategoriji;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getIzdavacka_kuca() {
        return izdavacka_kuca;
    }

    public void setIzdavacka_kuca(String izdavacka_kuca) {
        this.izdavacka_kuca = izdavacka_kuca;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Year getGodina_izdanja() {
        return godina_izdanja;
    }

    public void setGodina_izdanja(Year godina_izdanja) {
        this.godina_izdanja = godina_izdanja;
    }
    
    
    
}
