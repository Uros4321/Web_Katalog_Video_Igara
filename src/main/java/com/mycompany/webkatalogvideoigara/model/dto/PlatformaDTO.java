/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webkatalogvideoigara.model.dto;
/**
 *
 * @author uros
 */
public class PlatformaDTO {

    private Integer id;

    private String naziv;

    private String tip_platforme;

    private String proizvodjac;

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

    public String getTip_platforme() {
        return tip_platforme;
    }

    public void setTip_platforme(String tip_platforme) {
        this.tip_platforme = tip_platforme;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }
}
