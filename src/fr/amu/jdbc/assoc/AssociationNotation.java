/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.amu.jdbc.assoc;

import fr.amu.jdbc.model.Etudiant;
import fr.amu.jdbc.model.Module;
import fr.amu.jdbc.model.Notation;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author q13000412
 */
public class AssociationNotation {
    final static private AssociationNotation instance = new AssociationNotation();
    final private Set<Lien> liens = new HashSet<>();
    

    private AssociationNotation() {
    }

    public static AssociationNotation getInstance() {
        return instance;
    }

    public void creerLien(Module module, Etudiant etudiant, Notation notation){
        Lien lien = new Lien(module, etudiant);
        lien.setNote(notation);
        liens.add(lien);
    }
    
    public void supprimerLien(Module module, Etudiant etudiant){
        liens.remove(new Lien(module, etudiant));
    }
    
    public Lien getLien(Module module, Etudiant etudiant){
        Lien lien = new Lien(module, etudiant);
        
        for(Lien l2 : liens){
            if(l2.equals(lien))
                return l2;
        }
        
        return null;
    }
    
    public Set<Lien> getLiens(Module module){
        Set<Lien> liens2 = new HashSet<>();
        
        for(Lien lien : liens){
            if(lien.getModule().equals(module))
                liens2.add(lien);
        }
        
        return liens2;
    }
    
    public Set<Lien> getLiens(Etudiant etudiant){
        Set<Lien> liens2 = new HashSet<>();
        
        for(Lien lien : liens){
            if(lien.getEtudiant().equals(etudiant))
                liens2.add(lien);
        }
        
        return liens2;
    }
    
    public Set<Module> getModules(Etudiant etudiant){
        Set<Module> mods = new HashSet<Module>();
        
        for(Lien lien : liens){
            if(lien.getEtudiant().equals(etudiant))
                mods.add(lien.getModule());
        }
        
        return mods;
    }
    
    public Set<Etudiant> getEtudiants(Module module){
        Set<Etudiant> ets = new HashSet<Etudiant>();
        
        for(Lien lien : liens){
            if(lien.getModule().equals(module))
                ets.add(lien.getEtudiant());
        }
        
        return ets;
    }
    
    public void supprimerLien(Lien lien){
        liens.remove(lien);
    }
}
