package com.jee.yougetnicecar.models;

import javax.persistence.*;

@Entity
@Table(name = "carte_bleue")
public class CarteBleue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "dateExpiration", nullable = false)
    private String dateExpiration;

    @Column(name = "cryptogramme", nullable = false)
    private String cryptogramme;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "solde")
    private Double solde;

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getCryptogramme() {
        return cryptogramme;
    }

    public void setCryptogramme(String cryptogramme) {
        this.cryptogramme = cryptogramme;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
