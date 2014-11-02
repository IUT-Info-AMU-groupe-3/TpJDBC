package fr.amu.jdbc.dao;

import fr.amu.jdbc.dao.impl.DAONotationI;
import fr.amu.jdbc.model.Etudiant;
import fr.amu.jdbc.model.Module;
import fr.amu.jdbc.model.Notation;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public abstract class DAONotation implements DAO<Notation>{
    final static private DAONotation instance = new DAONotationI();
    
    abstract public Notation getByEtudiantAndMatiere(Etudiant etudiant, Module matiere);

    static public DAONotation getInstance() {
        return instance;
    }
    
}
