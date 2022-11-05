package com.jee.yougetnicecar.models;

import javax.persistence.*;

@Entity
@Table(name = "produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "modele")
    private String modele;

    @Column(name = "prix")
    private Integer prix;

    @Column(name = "annee")
    private Integer annee;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "motorisation")
    private Motorisation motorisation;

    @ManyToOne
    @JoinColumn(name = "marque_id")
    private Marque marque;

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Motorisation getMotorisation() {
        return motorisation;
    }

    public void setMotorisation(Motorisation motorisation) {
        this.motorisation = motorisation;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}