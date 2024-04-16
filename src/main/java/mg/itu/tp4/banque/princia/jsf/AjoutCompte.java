/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tp4.banque.princia.jsf;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import mg.itu.tp4.banque.princia.entities.CompteBancaire;
import mg.itu.tp4.banque.princia.jsf.utilitaire.Util;
import mg.itu.tp4.banque.princia.services.GestionnaireCompte;

/**
 *
 * @author princ
 */
@Named(value = "ajoutCompte")
@ViewScoped
public class AjoutCompte implements Serializable {

    private String nom;
    
    @PositiveOrZero
    private int solde;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String ajouterCompte() {
        gestionnaireCompte.creerCompte(new CompteBancaire(nom, solde));
        Util.addFlashInfoMessage("Compte de " + nom + " crée avec succès");
        return "listeComptes?faces-redirect=true";
    }
}
