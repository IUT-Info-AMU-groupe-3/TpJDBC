/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.amu.jdbc.model;

import java.util.Objects;

/**
 *
 * @author q13000412
 */
public class Notation {
    private int numEt;
    private String code;
    private float moyCc;
    private float moyTest;

    public float getMoyCc() {
        return moyCc;
    }

    public void setMoyCc(float moyCc) {
        this.moyCc = moyCc;
    }

    public float getMoyTest() {
        return moyTest;
    }

    public void setMoyTest(float moyTest) {
        this.moyTest = moyTest;
    }

    public int getNumEt() {
        return numEt;
    }

    public void setNumEt(int numEt) {
        this.numEt = numEt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.numEt;
        hash = 97 * hash + Objects.hashCode(this.code);
        hash = 97 * hash + Float.floatToIntBits(this.moyCc);
        hash = 97 * hash + Float.floatToIntBits(this.moyTest);
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
        final Notation other = (Notation) obj;
        if (this.numEt != other.numEt) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (Float.floatToIntBits(this.moyCc) != Float.floatToIntBits(other.moyCc)) {
            return false;
        }
        if (Float.floatToIntBits(this.moyTest) != Float.floatToIntBits(other.moyTest)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Notation{" + "numEt=" + numEt + ", code=" + code + ", moyCc=" + moyCc + ", moyTest=" + moyTest + '}';
    }
    
}
