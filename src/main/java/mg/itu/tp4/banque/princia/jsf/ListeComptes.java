/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tp4.banque.princia.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.tp4.banque.princia.entities.CompteBancaire;
import mg.itu.tp4.banque.princia.jsf.utilitaire.Util;
import mg.itu.tp4.banque.princia.services.GestionnaireCompte;

/**
 *
 * @author princ
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private List<CompteBancaire> allComptes;

    public ListeComptes() {
    }
    
    public List<CompteBancaire> getAllComptes() {
        if (allComptes == null) {
            allComptes = gestionnaireCompte.getAllComptes();
        }
        return allComptes;
    }
    
    public String supprimerCompte(CompteBancaire compte) {
        gestionnaireCompte.supprimer(compte);
        Util.addFlashInfoMessage("Compte de " + compte.getNom() + " supprimé avec succès.");
        return "listeComptes?faces-redirect=true";
    }
}
