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
import mg.itu.tp4.banque.princia.entities.OperationBancaire;
import mg.itu.tp4.banque.princia.services.GestionnaireCompte;

/**
 *
 * @author princ
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable{
    private Long id;
    private CompteBancaire compte;
    /**
     * Creates a new instance of Operations
     */
    public Operations() {
    }
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }
    
    public void loadCompteBancaire() {
        compte = gestionnaireCompte.getCompte(id);
    }
            
    public List<OperationBancaire> getOperations() {
        return compte.getOperations();
    }
}
