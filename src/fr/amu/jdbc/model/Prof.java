/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.amu.jdbc.model;

import fr.amu.jdbc.assoc.Enseignement;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author q13000412
 */
public class Prof {
    private int numProf;
    private String nomProf;
    private String prenomProf;
    private String adrProf;
    private String cpProf;
    private String villeProf;
    private Module specialite;
    
    final private Set<Enseignement> enseignements = new HashSet<>(); //A2

    public Set<Enseignement> getEnseignements() {
        return enseignements;
    }
    
    public void addEnseignement(Enseignement e){
        enseignements.add(e);
    }

    public Module getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Module specialite) {
        this.specialite = specialite;
    }

    public int getNumProf() {
        return numProf;
    }

    public void setNumProf(int numProf) {
        this.numProf = numProf;
    }

    public String getNomProf() {
        return nomProf;
    }

    public void setNomProf(String nomProf) {
        this.nomProf = nomProf;
    }

    public String getPrenomProf() {
        return prenomProf;
    }

    public void setPrenomProf(String prenomProf) {
        this.prenomProf = prenomProf;
    }

    public String getAdrProf() {
        return adrProf;
    }

    public void setAdrProf(String adrProf) {
        this.adrProf = adrProf;
    }

    public String getCpProf() {
        return cpProf;
    }

    public void setCpProf(String cpProf) {
        this.cpProf = cpProf;
    }

    public String getVilleProf() {
        return villeProf;
    }

    public void setVilleProf(String villeProf) {
        this.villeProf = villeProf;
    }

    @Override
    public String toString() {
        return "Prof{" + "numProf=" + numProf + ", nomProf=" + nomProf + ", prenomProf=" + prenomProf + ", adrProf=" + adrProf + ", cpProf=" + cpProf + ", villeProf=" + villeProf + ", specialite=" + specialite + '}';
    }
    
}
