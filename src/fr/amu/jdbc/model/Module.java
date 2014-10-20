/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.amu.jdbc.model;

import fr.amu.jdbc.assoc.Enseignement;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author q13000412
 */
public class Module {
    private String code;
    private String libelle;
    private int hCoursPrev;
    private int hCoursRea;
    private int hTpPrev;
    private int hTpRea;
    private String discipline;
    private int coefTest;
    private int coefCc;
    /*private Prof responsable;
    private Module pere;*/
    
    final private Set<Enseignement> enseignements = new HashSet<>(); //A1

    public Set<Enseignement> getEnseignements() {
        return enseignements;
    }
    
    public void addEnseignement(Enseignement e){
        enseignements.add(e);
    }


    /*public Module getPere() {
        return pere;
    }

    public void setPere(Module pere) {
        this.pere = pere;
    }

    public Prof getResponsable() {
        return responsable;
    }

    public void setResponsable(Prof responsable) {
        this.responsable = responsable;
    }*/

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int gethCoursPrev() {
        return hCoursPrev;
    }

    public void sethCoursPrev(int hCoursPrev) {
        this.hCoursPrev = hCoursPrev;
    }

    public int gethCoursRea() {
        return hCoursRea;
    }

    public void sethCoursRea(int hCoursRea) {
        this.hCoursRea = hCoursRea;
    }

    public int gethTpPrev() {
        return hTpPrev;
    }

    public void sethTpPrev(int hTpPrev) {
        this.hTpPrev = hTpPrev;
    }

    public int gethTpRea() {
        return hTpRea;
    }

    public void sethTpRea(int hTpRea) {
        this.hTpRea = hTpRea;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public int getCoefTest() {
        return coefTest;
    }

    public void setCoefTest(int coefTest) {
        this.coefTest = coefTest;
    }

    public int getCoefCc() {
        return coefCc;
    }

    public void setCoefCc(int coefCc) {
        this.coefCc = coefCc;
    }

    @Override
    public String toString() {
        return "Module{" + "code=" + code + ", libelle=" + libelle + ", hCoursPrev=" + hCoursPrev + ", hCoursRea=" + hCoursRea + ", hTpPrev=" + hTpPrev + ", hTpRea=" + hTpRea + ", discipline=" + discipline + ", coefTest=" + coefTest + ", coefCc=" + coefCc + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.code);
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
        final Module other = (Module) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }
}
