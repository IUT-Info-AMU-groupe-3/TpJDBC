/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.amu.jdbc.dao;

import fr.amu.jdbc.assoc.Enseignement;
import fr.amu.jdbc.dao.impl.DAOEnseignementI;
import fr.amu.jdbc.model.Etudiant;
import fr.amu.jdbc.model.Module;
import fr.amu.jdbc.model.Prof;
import java.util.List;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public abstract class DAOEnseignement implements DAO<Enseignement>{
    final static private DAOEnseignement instance = new DAOEnseignementI();
    
    abstract public List<Enseignement> findByEtudiant(Etudiant et);
    abstract public List<Enseignement> findByMatiere(Module matiere);
    abstract public List<Enseignement> findByProf(Prof prof);

    public static DAOEnseignement getInstance() {
        return instance;
    }
}
