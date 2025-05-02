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

    private Set<KategorijaVideoIgreDTO> kategorije_igre;

    private Set<PlatformaDTO> platforme_igre;

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

    public Set<KategorijaVideoIgreDTO> getKategorije_igre() {
        return kategorije_igre;
    }

    public void setKategorije_igre(Set<KategorijaVideoIgreDTO> igre_u_kategoriji) {
        this.kategorije_igre = igre_u_kategoriji;
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

    public Set<PlatformaDTO> getPlatforme_igre() {
        return platforme_igre;
    }

    public void setPlatforme_igre(Set<PlatformaDTO> platforme_igre) {
        this.platforme_igre = platforme_igre;
    }

    
}
