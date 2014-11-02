/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.amu.jdbc.dao.impl;

import fr.amu.jdbc.ConnexionUnique;
import fr.amu.jdbc.dao.DAOEtudiant;
import fr.amu.jdbc.model.Etudiant;
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
public class DAOEtudiantI extends DAOEtudiant{

    @Override
    public Etudiant insert(Etudiant obj) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO ETUDIANT(NOM_ET, PRENOM_ET, CP_ET, VILLE_ET, ANNEE, GROUPE) VALUES(?,?,?,?,?,?)", 
                    Statement.RETURN_GENERATED_KEYS
            );
            
            stmt.setString(1, obj.getNomEt());
            stmt.setString(2, obj.getPrenomEt());
            stmt.setString(3, obj.getCpEt());
            stmt.setString(4, obj.getVilleEt());
            stmt.setInt(5, obj.getAnnee());
            stmt.setInt(6, obj.getGroupe());
            
            stmt.executeUpdate();
            ResultSet RS = stmt.getGeneratedKeys();
            
            if(RS.next())
                obj.setNumEt(RS.getInt(1));
            
            RS.close();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return obj;
    }

    @Override
    public boolean delete(Etudiant obj) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM ETUDIANT WHERE NUM_ET = ?");
            stmt.setInt(1, obj.getNumEt());
            
            boolean r = stmt.executeUpdate() > 0;
            
            stmt.close();
            return r;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Etudiant obj) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement("UPDATE ETUDIANT SET NOM_ET = ?, PRENOM_ET = ?, CP_ET = ?, VILLE_ET = ?, ANNEE = ?, GROUPE = ? WHERE NUM_ET = ?");
            
            stmt.setString(1, obj.getNomEt());
            stmt.setString(2, obj.getPrenomEt());
            stmt.setString(3, obj.getCpEt());
            stmt.setString(4, obj.getVilleEt());
            stmt.setInt(5, obj.getAnnee());
            stmt.setInt(6, obj.getGroupe());
            stmt.setInt(7, obj.getNumEt());
            
            boolean r = stmt.executeUpdate() > 0;
            
            stmt.close();
            return r;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    private Etudiant getEtudiantByResultSet(ResultSet RS) throws SQLException{
        Etudiant et = new Etudiant();
        
        et.setNumEt(RS.getInt("NUM_ET"));
        et.setNomEt(RS.getString("NOM_ET"));
        et.setPrenomEt(RS.getString("PRENOM_ET"));
        et.setCpEt(RS.getString("CP_ET"));
        et.setVilleEt(RS.getString("VILLE_ET"));
        et.setAnnee(RS.getInt("ANNEE"));
        et.setGroupe(RS.getInt("GROUPE"));
        
        return et;
    }

    @Override
    public Etudiant getById(int id) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        Etudiant et = null;
        
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ETUDIANT WHERE NUM_ET = ?");
            stmt.setInt(1, id);
            
            ResultSet RS = stmt.executeQuery();
            
            if(RS.next())
                et = getEtudiantByResultSet(RS);
            
            RS.close();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return et;
    }

    @Override
    public List<Etudiant> findAll() {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        List<Etudiant> etudiants = new ArrayList<>();
        
        try{
            Statement stmt = connection.createStatement();
            ResultSet RS = stmt.executeQuery("SELECT * FROM ETUDIANT");
            
            while(RS.next()){
                etudiants.add(getEtudiantByResultSet(RS));
            }
            
            RS.close();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return etudiants;
    }

    @Override
    public List<Etudiant> findByNom(String nom) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        List<Etudiant> etudiants = new ArrayList<>();
        
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ETUDIANT WHERE NOM_ET = ?");
            stmt.setString(1, nom);
            
            ResultSet RS = stmt.executeQuery();
            
            while(RS.next()){
                etudiants.add(getEtudiantByResultSet(RS));
            }
            
            RS.close();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return etudiants;
    }

    @Override
    public List<Etudiant> findByGroupe(int groupe) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        List<Etudiant> etudiants = new ArrayList<>();
        
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ETUDIANT WHERE GROUPE = ?");
            stmt.setInt(1, groupe);
            
            ResultSet RS = stmt.executeQuery();
            
            while(RS.next()){
                etudiants.add(getEtudiantByResultSet(RS));
            }
            
            RS.close();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return etudiants;
    }

    @Override
    public List<Etudiant> findByAnnee(int annee) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        List<Etudiant> etudiants = new ArrayList<>();
        
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ETUDIANT WHERE ANNEE = ?");
            stmt.setInt(1, annee);
            
            ResultSet RS = stmt.executeQuery();
            
            while(RS.next()){
                etudiants.add(getEtudiantByResultSet(RS));
            }
            
            RS.close();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return etudiants;
    }

    @Override
    public float computeMoyennePonderee(Etudiant etudiant) {
        final String REQ = "SELECT AVG("
                            + "(IFNULL(MOY_CC, 0) * IFNULL(COEFF_CC, 0) + IFNULL(MOY_TEST, 0) * IFNULL(COEFF_TEST, 0)) / 100"
                        + ") AS MOY "
                        + "FROM ETUDIANT ET "
                        + "JOIN NOTATION N ON N.NUM_ET = ET.NUM_ET "
                        + "JOIN MODULE M ON M.CODE = N.CODE "
                        + "WHERE ET.NUM_ET = ?";
        
        Connection connection = ConnexionUnique.getInstance().getConnection();
        
        float moy = -1;
        
        try{
            PreparedStatement stmt = connection.prepareStatement(REQ);
            stmt.setInt(1, etudiant.getNumEt());
            
            ResultSet RS = stmt.executeQuery();
            
            if(RS.next())
                moy = RS.getFloat("MOY");
            
            RS.close();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return moy;
    }

    @Override
    public int computeNbEtudiant() {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        
        int count = -1;
        
        try{
            Statement stmt = connection.createStatement();
            
            ResultSet RS = stmt.executeQuery("SELECT COUNT(*) FROM ETUDIANT");
            
            if(RS.next())
                count = RS.getInt("COUNT(*)");
            
            RS.close();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return count;
    }
    
}
