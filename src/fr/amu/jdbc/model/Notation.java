/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.amu.jdbc.model;

/**
 *
 * @author q13000412
 */
public class Notation {
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

    @Override
    public String toString() {
        return "Notation{" + "moyCc=" + moyCc + ", moyTest=" + moyTest + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Float.floatToIntBits(this.moyCc);
        hash = 41 * hash + Float.floatToIntBits(this.moyTest);
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
        if (Float.floatToIntBits(this.moyCc) != Float.floatToIntBits(other.moyCc)) {
            return false;
        }
        if (Float.floatToIntBits(this.moyTest) != Float.floatToIntBits(other.moyTest)) {
            return false;
        }
        return true;
    }
    
}
