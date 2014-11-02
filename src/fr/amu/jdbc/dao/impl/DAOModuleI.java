/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.amu.jdbc.dao.impl;

import fr.amu.jdbc.ConnexionUnique;
import fr.amu.jdbc.dao.DAOModule;
import fr.amu.jdbc.model.Module;
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
public class DAOModuleI extends DAOModule{
    private Module getModuleByResultSet(ResultSet RS) throws SQLException{
        Module module = new Module();
        
        module.setCode(RS.getString("CODE"));
        module.setCoefCc(RS.getInt("COEFF_CC"));
        module.setCoefTest(RS.getInt("COEFF_TEST"));
        module.setDiscipline(RS.getString("DISCIPLINE"));
        module.setLibelle(RS.getString("LIBELLE"));
        module.sethCoursPrev(RS.getInt("H_COURS_PREV"));
        module.sethCoursRea(RS.getInt("H_COURS_REA"));
        module.sethTpPrev(RS.getInt("H_TP_PREV"));
        module.sethTpRea(RS.getInt("H_TP_REA"));
        
        return module;
    }

    @Override
    public Module getByCode(String code) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        Module module = null;
        
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM MODULE WHERE CODE = ?");
            stmt.setString(1, code);
            
            ResultSet RS = stmt.executeQuery();
            
            if(RS.next())
                module = getModuleByResultSet(RS);
            
            RS.close();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return module;
    }

    @Override
    public Module insert(Module obj) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO MODULE(CODE, LIBELLE, H_COURS_PREV, H_COURS_REA, H_TP_PREV, H_TP_REA, DISCIPLINE, COEFF_TEST, COEFF_CC) VALUES(?,?,?,?,?,?,?,?,?)");
            
            stmt.setString(1, obj.getCode());
            stmt.setString(2, obj.getLibelle());
            stmt.setInt(3, obj.gethCoursPrev());
            stmt.setInt(4, obj.gethCoursRea());
            stmt.setInt(5, obj.gethTpPrev());
            stmt.setInt(6, obj.gethTpRea());
            stmt.setString(7, obj.getDiscipline());
            stmt.setInt(8, obj.getCoefTest());
            stmt.setInt(9, obj.getCoefCc());
            
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return obj;
    }

    @Override
    public boolean delete(Module obj) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM MODULE WHERE CODE = ?");
            stmt.setString(1, obj.getCode());
            boolean r = stmt.executeUpdate() > 0;
            stmt.close();
            return r;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Module obj) {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement("UPDATE MODULE SET ...");
            
            //...
            
            boolean r = stmt.executeUpdate() > 0;
            stmt.close();
            return r;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Module getById(int id) {
        throw new UnsupportedOperationException("Module n'a pas de int comme clé primaire. Utilisez getByCode à la place."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Module> findAll() {
        Connection connection = ConnexionUnique.getInstance().getConnection();
        List<Module> modules = new ArrayList<>();
        
        try{
            Statement stmt = connection.createStatement();
            ResultSet RS = stmt.executeQuery("SELECT * FROM MODULE");
            
            while(RS.next())
                modules.add(getModuleByResultSet(RS));
            
            RS.close();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return modules;
    }
    
}
