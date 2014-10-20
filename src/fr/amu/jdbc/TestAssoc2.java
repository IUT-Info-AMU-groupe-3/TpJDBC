package fr.amu.jdbc;

import fr.amu.jdbc.assoc.AssociationNotation;
import fr.amu.jdbc.assoc.Lien;
import fr.amu.jdbc.model.Etudiant;
import fr.amu.jdbc.model.Module;
import fr.amu.jdbc.model.Notation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestAssoc2 {
    final static private String REQ_MODULES = "SELECT * FROM MODULE";
    final static private String REQ_ETUDIANTS = "SELECT * FROM ETUDIANT";
    final static private String REQ_NOTATIONS = "SELECT * FROM NOTATION";
    
    final static private Map<String, Module> modules = new HashMap<>();
    final static private Map<Integer, Etudiant> etudiants = new HashMap<>();
    
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        
        try {
            connection = ConnexionUnique.getInstance().getConnection();
            
            loadModules(connection);
            loadEtudiants(connection);
            
            Statement stmt = connection.createStatement();
            ResultSet RS = stmt.executeQuery(REQ_NOTATIONS);
            
            while(RS.next()){
                Etudiant etudiant = etudiants.get(RS.getInt("NUM_ET"));
                Module module = modules.get(RS.getString("CODE"));
                
                if(etudiant == null || module == null)
                    continue;
                
                Notation notation = new Notation();
                
                notation.setMoyCc(RS.getFloat("MOY_CC"));
                notation.setMoyTest(RS.getFloat("MOY_TEST"));
                
                AssociationNotation.getInstance().creerLien(module, etudiant, notation);
            }
            
            Module acsi = modules.get("ACSI");
            Set<Lien> liens = AssociationNotation.getInstance().getLiens(acsi);
            
            for(Lien lien : liens){
                System.out.println(lien.getEtudiant());
                System.out.println(lien.getNote());
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
    }
}
