/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.amu.jdbc.dao.impl;

import fr.amu.jdbc.ConnexionUnique;
import fr.amu.jdbc.dao.DAONotation;
import fr.amu.jdbc.model.Etudiant;
import fr.amu.jdbc.model.Module;
import fr.amu.jdbc.model.Notation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class DAONotationI extends DAONotation{
    private Notation getNotationByResultSet(ResultSet RS) throws SQLException{
        Notation notation = new Notation();
        
        notation.setMoyCc(RS.getFloat("MOY_CC"));
        notation.setMoyTest(RS.getFloat("MOY_TEST"));
        notation.setCode(RS.getString("CODE"));
        notation.setNumEt(RS.getInt("NUM_ET"));
        
        return notation;
    }

    @Override
    public Notation getByEtudiantAndMatiere(Etudiant etudiant, Module matiere) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        Notation notation = null;
        
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM NOTATION WHERE NUM_ET = ? AND CODE = ?");
            
            stmt.setInt(1, etudiant.getNumEt());
            stmt.setString(2, matiere.getCode());
            
            ResultSet RS = stmt.executeQuery();
            
            if(RS.next())
                notation = getNotationByResultSet(RS);
            
            RS.close();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return notation;
    }

    @Override
    public Notation insert(Notation obj) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO NOTATION(...) VALUES(...)");
            
            //...
            
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return obj;
    }

    @Override
    public boolean delete(Notation obj) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM NOTATION WHERE CODE = ? AND NUM_ET = ?");
            
            stmt.setString(1, obj.getCode());
            stmt.setInt(2, obj.getNumEt());
            
            boolean r = stmt.executeUpdate() > 0;
            stmt.close();
            return r;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Notation obj) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement("UPDATE NOTATION SET MOY_TEST = ?, MOY_CC = ? WHERE CODE = ? AND NUM_ET = ?");
            
            stmt.setFloat(1, obj.getMoyTest());
            stmt.setFloat(2, obj.getMoyCc());
            stmt.setString(3, obj.getCode());
            stmt.setInt(4, obj.getNumEt());
            
            boolean r = stmt.executeUpdate() > 0;
            stmt.close();
            return r;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Notation getById(int id) {
        throw new UnsupportedOperationException("Clé primaire non unique. Utilisez getByEtudiantAndMatiere à la place."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Notation> findAll() {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        List<Notation> notations = new ArrayList<>();
        
        try{
            Statement stmt = connection.createStatement();
            ResultSet RS = stmt.executeQuery("SELECT * FROM NOTATION");
            
            while(RS.next())
                notations.add(getNotationByResultSet(RS));
            
            RS.close();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return notations;
    }
    
}
