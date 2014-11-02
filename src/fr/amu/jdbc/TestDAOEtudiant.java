package fr.amu.jdbc;

import fr.amu.jdbc.dao.DAOEtudiant;
import fr.amu.jdbc.model.Etudiant;
import java.sql.SQLException;
import java.util.List;

public class TestDAOEtudiant {
    
    public static void main(String[] args) throws SQLException {
        DAOEtudiant dao = DAOEtudiant.getInstance();
        
        List<Etudiant> etudiants = dao.findAll();
        
        System.out.println("findAll()");
        for(Etudiant etudiant : etudiants)
            System.out.println(etudiant);
        
        System.out.println("\ncomputeNbEtudiant()");
        int nb = dao.computeNbEtudiant();
        System.out.println("size=" + etudiants.size() + ", nb=" + nb);
        assert etudiants.size() == nb;
        
        System.out.println("\nfindById()");
        
        for(Etudiant etudiant : etudiants){
            Etudiant other = dao.getById(etudiant.getNumEt());
            System.out.println("etudiant=" + etudiant + ", other=" + other);
            assert etudiant.equals(other);
        }
        
        System.out.println("\ninsert()");
        Etudiant etudiant = new Etudiant();
        etudiant.setPrenomEt("a");
        etudiant.setNomEt("b");
        etudiant.setVilleEt("c");
        etudiant.setAnnee(12);
        etudiant.setGroupe(3);
        System.out.println("Avant : " + etudiant);
        
        dao.insert(etudiant);
        System.out.println("Après : " + etudiant);
        
        System.out.println("\nupdate()");
        etudiant.setPrenomEt("abcd");
        dao.update(etudiant);
        Etudiant other = dao.getById(etudiant.getNumEt());
        assert etudiant.getPrenomEt().equals(other.getPrenomEt());
        other = null;
        
        System.out.println("\ndelete()");
        dao.delete(etudiant);
        assert dao.getById(etudiant.getNumEt()) == null;
        etudiant = null;
        
        System.out.println("\nfindByNom()");
        String nom = etudiants.get(0).getNomEt();
        System.out.println("nom : " + nom);
        for(Etudiant et : dao.findByNom(nom))
            System.out.println(et);
        
        System.out.println("\nfindByGroupe()");
        int groupe = 2;
        System.out.println("groupe : " + groupe);
        for(Etudiant et : dao.findByGroupe(groupe))
            System.out.println(et);
        
        System.out.println("\nfindByAnnee()");
        int annee = 1;
        System.out.println("annee : " + annee);
        for(Etudiant et : dao.findByAnnee(annee))
            System.out.println(et);
        
        System.out.println("\ncomputeMoyennePonderee()");
        for(Etudiant et : etudiants){
            float moy = dao.computeMoyennePonderee(et);
            System.out.println(et + " : moy = " + moy);
        }
    }
}
