/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.amu.jdbc.dao;

import fr.amu.jdbc.dao.impl.DAOEtudiantI;
import fr.amu.jdbc.model.Etudiant;
import java.util.List;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public abstract class DAOEtudiant implements DAO<Etudiant>{
    final static private DAOEtudiant instance = new DAOEtudiantI();
    
    abstract public List<Etudiant> findByNom(String nom);
    abstract public List<Etudiant> findByGroupe(int groupe);
    abstract public List<Etudiant> findByAnnee(int annee);
    abstract public float computeMoyennePonderee(Etudiant etudiant);
    abstract public int computeNbEtudiant();
    
    static public DAOEtudiant getInstance(){
        return instance;
    }
}
