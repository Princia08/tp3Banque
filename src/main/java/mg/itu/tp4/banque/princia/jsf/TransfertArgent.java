/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tp4.banque.princia.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import mg.itu.tp4.banque.princia.entities.CompteBancaire;
import mg.itu.tp4.banque.princia.jsf.utilitaire.Util;
import mg.itu.tp4.banque.princia.services.GestionnaireCompte;

/**
 *
 * @author princ
 */
@Named(value = "transfertArgent")
@ViewScoped
public class TransfertArgent implements Serializable {

    private Long idSource;
    private Long idDestinataire;
    
    @PositiveOrZero
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of TransfertArgent
     */
    public TransfertArgent() {
    }

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestinataire() {
        return idDestinataire;
    }

    public void setIdDestinataire(Long idDestinataire) {
        this.idDestinataire = idDestinataire;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String transfererArgent() {
        CompteBancaire destinataire = gestionnaireCompte.getCompte(idDestinataire);
        CompteBancaire source = gestionnaireCompte.getCompte(idSource);

        boolean erreur = false;
        if (destinataire == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destinataire");
            erreur = true;
        }

        if (source == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < montant) {
                Util.messageErreur("Le solde du compte source de " + source.getNom() + " est insuffisant !", "Le solde du compte source de " + source.getNom() + " est insuffisant !", "form:montant");
                erreur = true;
            }
        }

        if (erreur) {
            return null;
        } else {
            gestionnaireCompte.transfert(source, destinataire, montant);
            Util.addFlashInfoMessage("Transfert de " + montant + " EUR depuis le compte de " + source.getNom() + " vers le compte de " + destinataire.getNom() + " correctement effectué.");
            return "listeComptes?faces-redirect=true";
        }
    }

}
