/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.amu.jdbc.dao.impl;

import fr.amu.jdbc.assoc.Enseignement;
import fr.amu.jdbc.dao.DAOEnseignement;
import fr.amu.jdbc.model.Etudiant;
import fr.amu.jdbc.model.Module;
import fr.amu.jdbc.model.Prof;
import java.util.List;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class DAOEnseignementI extends DAOEnseignement{

    @Override
    public List<Enseignement> findByEtudiant(Etudiant et) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<Enseignement> findByMatiere(Module matiere) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<Enseignement> findByProf(Prof prof) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Enseignement insert(Enseignement obj) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean delete(Enseignement obj) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean update(Enseignement obj) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Enseignement getById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Enseignement> findAll() {
        throw new UnsupportedOperationException("TODO");
    }
    
}
