package com.mycompany.webkatalogvideoigara.model.dto;



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

public class VideoIgraDTO {
    

    private Integer id;

    private Set<KategorijaVideoIgreDTO> igre_u_kategoriji;

    private String naziv;

    private String izdavacka_kuca;

    private String opis;

    private Year godina_izdanja;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<KategorijaVideoIgreDTO> getIgre_u_kategoriji() {
        return igre_u_kategoriji;
    }

    public void setIgre_u_kategoriji(Set<KategorijaVideoIgreDTO> igre_u_kategoriji) {
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
