package fr.amu.jdbc;

import fr.amu.jdbc.model.Etudiant;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestConnexion {

    // La requête de test

    static final String req = "SELECT * FROM ETUDIANT";

    public static void main(String[] args) throws SQLException {
        // Objet materialisant la connexion à la base de données
        Connection conn = null;

        try {
            conn = ConnexionUnique.getInstance().getConnection();
            System.out.println("Connecté\n");

            // Creation d'une instruction
            Statement stmt = conn.createStatement();

            // Execution de la requete
            System.out.println("Exécution de la requête : " + req + "\n");
            ResultSet rset = stmt.executeQuery(req);
            
            List<Etudiant> etudiants = new ArrayList<>();

            // Affichage du résultat
            while (rset.next()) {
                Etudiant et = new Etudiant();
                
                et.setNumEt(rset.getInt("NUM_ET"));
                et.setNomEt(rset.getString("NOM_ET"));
                et.setPrenomEt(rset.getString("PRENOM_ET"));
                et.setGroupe(rset.getInt("GROUPE"));
                et.setCpEt(rset.getString("CP_ET"));
                et.setAnnee(rset.getInt("ANNEE"));
                et.setVilleEt(rset.getString("VILLE_ET"));
                
                etudiants.add(et);
            }
            // Fermeture de l'instruction (libération des ressources)
            stmt.close();
            System.out.println("\nOk.\n");
            
            System.out.println(etudiants);

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
}
