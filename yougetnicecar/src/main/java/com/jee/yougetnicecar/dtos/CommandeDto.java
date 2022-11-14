package com.jee.yougetnicecar.dtos;

import com.jee.yougetnicecar.models.Produit;

import java.time.LocalDate;
import java.util.Map;

public class CommandeDto {

    private Long panier_id;
    private LocalDate date;
    private Map<Produit, Integer> produits;
    private Integer total;

    public Long getPanierId() {
        return panier_id;
    }

    public void setPanierId(Long panierId) {
        this.panier_id = panierId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<Produit, Integer> getProduits() {
        return produits;
    }

    public void setProduits(Map<Produit, Integer> produits) {
        this.produits = produits;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
