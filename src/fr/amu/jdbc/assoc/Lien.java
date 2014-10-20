/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.amu.jdbc.assoc;

import fr.amu.jdbc.model.Etudiant;
import fr.amu.jdbc.model.Module;
import fr.amu.jdbc.model.Notation;
import java.util.Objects;

/**
 *
 * @author q13000412
 */
public class Lien {
    final private Module module;
    final private Etudiant etudiant;
    private Notation note;

    public Lien(Module module, Etudiant etudiant) {
        this.module = module;
        this.etudiant = etudiant;    
    }

    public Module getModule() {
        return module;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Notation getNote() {
        return note;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.module);
        hash = 61 * hash + Objects.hashCode(this.etudiant);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lien other = (Lien) obj;
        if (!Objects.equals(this.module, other.module)) {
            return false;
        }
        if (!Objects.equals(this.etudiant, other.etudiant)) {
            return false;
        }
        return true;
    }

    public void setNote(Notation note) {
        this.note = note;
    }
    
}
