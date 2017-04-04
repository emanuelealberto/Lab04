package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				// Crea un nuovo JAVA Bean Corso
				Corso temp = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				// Aggiungi il nuovo Corso alla lista
				corsi.add(temp);
			}

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	/*public List<Studente> getStudenti (){
		List<Studente> studenti= new ArrayList<Studente>();
		final String sql = "SELECT * FROM studente";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				// Crea un nuovo JAVA Bean Corso
				Studente temp = new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("cds"));
				// Aggiungi il nuovo Corso alla lista
				studenti.add(temp);
			}

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}*/
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(String corso) {
		final String sql = "SELECT * "+
							"FROM studente s "+
							"WHERE s.matricola IN (SELECT DISTINCT matricola FROM iscrizione WHERE codins IN (SELECT DISTINCT codins FROM corso WHERE nome=?))";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				// Crea un nuovo JAVA Bean Corso
				Studente temp = new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("cds"));
				// Aggiungi il nuovo Corso alla lista
				studenti.add(temp);
			}

			return studenti;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
			}
	}

	/*
	 * Data una matricola ed il codice insegnamento,
	 * iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(int  matricola, String corso) {
	final String sql = "INSERT INTO `iscritticorsi`.`iscrizione` VALUES (?, (SELECT codins FROM corso WHERE nome= ?))";
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, matricola);
		st.setString(2, corso);
		ResultSet rs = st.executeQuery();
	}
	catch(SQLException e) {
		e.printStackTrace();
		throw new RuntimeException("Errore Db");
		}
	return false;
	}
}
