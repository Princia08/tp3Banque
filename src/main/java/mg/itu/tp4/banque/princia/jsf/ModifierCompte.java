/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tp4.banque.princia.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.tp4.banque.princia.entities.CompteBancaire;
import mg.itu.tp4.banque.princia.jsf.utilitaire.Util;
import mg.itu.tp4.banque.princia.services.GestionnaireCompte;

/**
 *
 * @author princ
 */
@Named(value = "modifierCompte")
@ViewScoped
public class ModifierCompte implements Serializable {

    private Long idCompte;
    private CompteBancaire compte;
    private String nomInitial;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of ModifierCompte
     */
    public ModifierCompte() {
    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public String getNomInitial() {
        return nomInitial;
    }

    public void setNomInitial(String nomInitial) {
        this.nomInitial = nomInitial;
    }

    public void chargerCompte() {
        this.compte = gestionnaireCompte.getCompte(idCompte);
        this.nomInitial = this.compte.getNom();
    }

    public String modifierCompte() {
        gestionnaireCompte.update(compte);
        Util.addFlashInfoMessage("Le nom " + this.nomInitial + " a été changé en " + compte.getNom());
        return "listeComptes?faces-redirect=true";
    }
}
