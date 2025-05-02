package com.mycompany.webkatalogvideoigara.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
    @JoinTable(name="kategorije_igara",
            joinColumns=@JoinColumn(name="igra_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="kategorija_id", referencedColumnName="id"))
    private Set<KategorijaVideoIgre> kategorije_igre;
    
    @ManyToMany
    @JoinTable(name="platforme_za_igru",
            joinColumns=@JoinColumn(name="igra_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="platforma_id", referencedColumnName="id"))
    private Set<Platforma> platforme_igre;
    
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

    public Set<KategorijaVideoIgre> getKategorije_igre() {
        return kategorije_igre;
    }

    public void setKategorije_igre(Set<KategorijaVideoIgre> igre_u_kategoriji) {
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

    public Set<Platforma> getPlatforme_igre() {
        return platforme_igre;
    }

    public void setPlatforme_igre(Set<Platforma> platforme_igre) {
        this.platforme_igre = platforme_igre;
    }
    
    
    
}
