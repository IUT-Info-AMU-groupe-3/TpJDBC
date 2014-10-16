package fr.amu.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnexion {

    // La requête de test

    static final String req = "SELECT NUM_ET, NOM_ET, PRENOM_ET " + "FROM ETUDIANT "
            + "WHERE VILLE_ET = 'AIX-EN-PROVENCE'";

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

            // Affichage du résultat
            while (rset.next()) {
                System.out.print(rset.getInt("NUM_ET") + " ");
                System.out.print(rset.getString("NOM_ET") + " ");
                System.out.println(rset.getString("PRENOM_ET"));
            }
            // Fermeture de l'instruction (libération des ressources)
            stmt.close();
            System.out.println("\nOk.\n");

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
