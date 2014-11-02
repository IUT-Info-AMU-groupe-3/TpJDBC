/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.amu.jdbc;

import fr.amu.jdbc.dao.DAOEtudiant;
import fr.amu.jdbc.dao.DAOModule;
import fr.amu.jdbc.dao.DAONotation;
import fr.amu.jdbc.model.Etudiant;
import fr.amu.jdbc.model.Module;
import fr.amu.jdbc.model.Notation;
import java.util.List;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TestDAO {
    public static void main(String[] args) {
        Module module = DAOModule.getInstance().getByCode("ACSI");
        assert module != null;
        
        DAONotation daoNotation = DAONotation.getInstance();
        
        List<Etudiant> etudiants = DAOEtudiant.getInstance().findByAnnee(2);
        
        System.out.println("avant : ");
        for(Etudiant etudiant : etudiants){
            System.out.println(etudiant);
            Notation notation = daoNotation.getByEtudiantAndMatiere(etudiant, module);
            
            if(notation == null){
                System.err.println("Non noté pour le module " + module);
                continue;
            }
            
            System.out.println("Note=" + notation);
            
            notation.setMoyCc(notation.getMoyCc() + 1);
            notation.setMoyTest(notation.getMoyTest() + 1);
            daoNotation.update(notation);
        }
        
        System.out.println("\naprès : ");
        for(Etudiant etudiant : etudiants){
            System.out.println(etudiant);
            Notation notation = daoNotation.getByEtudiantAndMatiere(etudiant, module);
            
            if(notation == null){
                System.err.println("Non noté pour le module " + module);
                continue;
            }
            
            System.out.println("Note=" + notation);
        }
    }
}
