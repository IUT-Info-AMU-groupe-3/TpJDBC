/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.amu.jdbc.assoc;

import fr.amu.jdbc.model.Etudiant;
import fr.amu.jdbc.model.Module;
import fr.amu.jdbc.model.Prof;

/**
 *
 * @author q13000412
 */
public class Enseignement {
    final private Module module;
    final private Etudiant etudiant;
    final private Prof prof;

    public Enseignement(Module module, Etudiant etudiant, Prof prof) {
        this.module = module;
        this.etudiant = etudiant;
        this.prof = prof;
    }

    public Module getModule() {
        return module;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Prof getProf() {
        return prof;
    }

    @Override
    public String toString() {
        return "Enseignement{" + "module=" + module + ", etudiant=" + etudiant + ", prof=" + prof + '}';
    }
}
