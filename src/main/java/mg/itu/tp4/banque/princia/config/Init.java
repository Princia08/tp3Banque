/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tp4.banque.princia.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import mg.itu.tp4.banque.princia.entities.CompteBancaire;
import mg.itu.tp4.banque.princia.services.GestionnaireCompte;

/**
 *
 * @author princ
 */
@ApplicationScoped
public class Init {

    @Inject
    GestionnaireCompte gestionnaireCompte;

    public void init(@Observes @Initialized(ApplicationScoped.class) ServletContext context) {
        if (gestionnaireCompte.nbComptes() == 0) {
            CompteBancaire cb1 = new CompteBancaire("John Lennon", 150000);
            CompteBancaire cb2 = new CompteBancaire("Paul McCartney", 950000);
            CompteBancaire cb3 = new CompteBancaire("Ringo Starr", 20000);
            CompteBancaire cb4 = new CompteBancaire("Georges Harrisson", 100000);
            gestionnaireCompte.creerCompte(cb1);
            gestionnaireCompte.creerCompte(cb2);
            gestionnaireCompte.creerCompte(cb3);
            gestionnaireCompte.creerCompte(cb4);

        }
    }
}
