/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tp4.banque.princia.services;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.itu.tp4.banque.princia.entities.CompteBancaire;

/**
 *
 * @author princ
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root", // nom et
        password = "root", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    @Transactional
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        TypedQuery<CompteBancaire> query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        return query.getResultList();
    }

    public Long nbComptes() {
        TypedQuery<Long> query = em.createQuery("select count(c) from CompteBancaire c", Long.class);
        return query.getSingleResult();
    }

    @Transactional
    public CompteBancaire update(CompteBancaire compte) {
        return em.merge(compte);
    }

    @Transactional
    public void transfert(CompteBancaire source, CompteBancaire destination, int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }
    
    @Transactional
    public void retirer(CompteBancaire c, int montant) {
        c.retirer(montant);
        update(c);
    }
    
    @Transactional
    public void verser(CompteBancaire c, int montant) {
        c.deposer(montant);
        update(c);
    }

    public CompteBancaire getCompte(Long id) {
        return em.find(CompteBancaire.class, id);
    }

    @Transactional
    public void supprimer(CompteBancaire compte) {
        em.remove(em.merge(compte));
    }
}
