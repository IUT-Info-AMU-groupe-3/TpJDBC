package fr.amu.jdbc;

import fr.amu.jdbc.assoc.Enseignement;
import fr.amu.jdbc.model.Etudiant;
import fr.amu.jdbc.model.Module;
import fr.amu.jdbc.model.Prof;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestAssoc3 {
    final static private String REQ_MODULES = "SELECT * FROM MODULE";
    final static private String REQ_ETUDIANTS = "SELECT * FROM ETUDIANT";
    final static private String REQ_PROF = "SELECT * FROM PROF";
    final static private String REQ_ENSEIGNEMENT = "SELECT * FROM ENSEIGNT";
    
    final static private Map<String, Module> modules = new HashMap<>();
    final static private Map<Integer, Etudiant> etudiants = new HashMap<>();
    final static private Map<Integer, Prof> profs = new HashMap<>();
    
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        
        try {
            connection = ConnexionUnique.getInstance().getConnection();
            
            loadModules(connection);
            loadEtudiants(connection);
            loadProfs(connection);
            
            List<Enseignement> enseignements = getEnseignements(connection);
            
            for(Enseignement e : enseignements){
                if(e.getEtudiant().getGroupe() != 1)
                    continue;
                
                System.out.println(e);
            }
            
        } catch (Exception e) {
            e.printStackTrace();// Arggg!!!
            System.out.println(e.getMessage() + "\n");
        } finally {
            if (connection != null) {
                // Déconnexion de la base de données
                connection.close();
            }
        }
    }
    
    private static void loadModules(Connection connection) throws SQLException{
        Statement stmt = connection.createStatement();
        ResultSet RS = stmt.executeQuery(REQ_MODULES);
        
        while (RS.next()) {
            Module module = new Module();
            
            module.setCode(RS.getString("CODE"));
            module.setLibelle(RS.getString("LIBELLE"));
            module.sethCoursPrev(RS.getInt("H_COURS_PREV"));
            module.sethCoursRea(RS.getInt("H_COURS_REA"));
            module.sethTpPrev(RS.getInt("H_TP_PREV"));
            module.setDiscipline(RS.getString("DISCIPLINE"));
            module.setCoefTest(RS.getInt("COEFF_TEST"));
            module.setCoefCc(RS.getInt("COEFF_CC"));
            
            modules.put(module.getCode(), module);
        }
        
        RS.close();
        stmt.close();
    }
    
    private static void loadEtudiants(Connection connection) throws SQLException{
        Statement stmt = connection.createStatement();
        ResultSet rset = stmt.executeQuery(REQ_ETUDIANTS);
        
        while (rset.next()) {
            Etudiant et = new Etudiant();
            
            et.setNumEt(rset.getInt("NUM_ET"));
            et.setNomEt(rset.getString("NOM_ET"));
            et.setPrenomEt(rset.getString("PRENOM_ET"));
            et.setGroupe(rset.getInt("GROUPE"));
            et.setCpEt(rset.getString("CP_ET"));
            et.setAnnee(rset.getInt("ANNEE"));
            et.setVilleEt(rset.getString("VILLE_ET"));
            
            etudiants.put(et.getNumEt(), et);
        }
        
        rset.close();
        stmt.close();
    }
    
    private static void loadProfs(Connection connection) throws SQLException{
        Statement stmt = connection.createStatement();
        ResultSet RS = stmt.executeQuery(REQ_PROF);
        
        while(RS.next()){
            Prof prof = new Prof();
            
            prof.setNumProf(RS.getInt("NUM_PROF"));
            prof.setNomProf(RS.getString("NOM_PROF"));
            prof.setPrenomProf(RS.getString("PRENOM_PROF"));
            prof.setAdrProf(RS.getString("ADR_PROF"));
            prof.setCpProf(RS.getString("CP_PROF"));
            prof.setVilleProf(RS.getString("VILLE_PROF"));
            
            profs.put(prof.getNumProf(), prof);
        }
        
        RS.close();
        stmt.close();
    }
    
    private static List<Enseignement> getEnseignements(Connection connection) throws SQLException{
        List<Enseignement> enseignements = new ArrayList<>();
        
        Statement stmt = connection.createStatement();
        ResultSet RS = stmt.executeQuery(REQ_ENSEIGNEMENT);
        
        while(RS.next()){
            Module module = modules.get(RS.getString("CODE"));
            Etudiant etudiant = etudiants.get(RS.getInt("NUM_ET"));
            Prof prof = profs.get(RS.getInt("NUM_PROF"));
            
            Enseignement e = new Enseignement(module, etudiant, prof);
            
            module.addEnseignement(e);
            etudiant.addEnseignement(e);
            prof.addEnseignement(e);
            enseignements.add(e);
        }
        
        RS.close();
        stmt.close();
        
        return enseignements;
    }
}
