package fr.amu.jdbc;

import fr.amu.jdbc.model.Module;
import fr.amu.jdbc.model.Prof;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestAssoc1 {

    // La requête de test
    static final String req = "SELECT * FROM PROF";
    static final String REQ_MODULE = "SELECT * FROM MODULE WHERE CODE = ?";
    static private PreparedStatement modStmt;

    public static void main(String[] args) throws SQLException {
        // Objet materialisant la connexion à la base de données
        Connection conn = null;

        try {
            conn = ConnexionUnique.getInstance().getConnection();
            System.out.println("Connecté\n");

            // Creation d'une instruction
            Statement stmt = conn.createStatement();

            modStmt = conn.prepareStatement(REQ_MODULE);

            // Execution de la requete
            System.out.println("Exécution de la requête : " + req + "\n");
            ResultSet rset = stmt.executeQuery(req);

            List<Prof> profs = new ArrayList<>();

            while (rset.next()) {
                Prof prof = new Prof();
                prof.setNumProf(rset.getInt("NUM_PROF"));
                prof.setNomProf(rset.getString("NOM_PROF"));
                prof.setPrenomProf(rset.getString("PRENOM_PROF"));
                prof.setAdrProf(rset.getString("ADR_PROF"));
                prof.setCpProf(rset.getString("CP_PROF"));
                prof.setVilleProf(rset.getString("VILLE_PROF"));
                prof.setSpecialite(getModuleByCode(rset.getString("MAT_SPEC")));
                profs.add(prof);
            }
            
            // Fermeture de l'instruction (libération des ressources)
            stmt.close();
            modStmt.close();
            
            System.out.println("\nOk.\n");
            
            for(Prof p : profs){
                System.out.println(p);
                System.out.println(p.getSpecialite());
            }
            
        } catch (Exception e) {
            e.printStackTrace();// Arggg!!!
            System.out.println(e.getMessage() + "\n");
        } finally {
            if (conn != null) {
                // Déconnexion de la base de données
                conn.close();
            }
        }
    }

    static private Module getModuleByCode(String code) throws SQLException {
        Module module = null;

        modStmt.setString(1, code);
        ResultSet RS = modStmt.executeQuery();

        if (RS.next()) {
            module = new Module();
            module.setCode(RS.getString("CODE"));
            module.setLibelle(RS.getString("LIBELLE"));
            module.sethCoursPrev(RS.getInt("H_COURS_PREV"));
            module.sethCoursRea(RS.getInt("H_COURS_REA"));
            module.sethTpPrev(RS.getInt("H_TP_PREV"));
            module.setDiscipline(RS.getString("DISCIPLINE"));
            module.setCoefTest(RS.getInt("COEFF_TEST"));
            module.setCoefCc(RS.getInt("COEFF_CC"));
            //module.setPere(getModuleByCode(RS.getString("CODEPERE")));
        }
        
        RS.close();

        return module;
    }
}
