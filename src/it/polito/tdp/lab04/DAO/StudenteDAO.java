package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
public Studente cercaStudente(int matricola){
		
		final String sql = "SELECT * FROM studente WHERE matricola=? ";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();
			
			Studente s = null;

			while (rs.next()) {
				
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("cds");
				
				s = new Studente (matricola,cognome,nome,cds);
			}
			
			//conn.close();
			

			return s;
		}	catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
public List<Corso> cercaCorsiIscritto(int matricola){
	
	final String sql = "SELECT * FROM corso "+
						"WHERE codins IN (SELECT DISTINCT codins FROM iscrizione WHERE matricola IN "+
						"(SELECT DISTINCT matricola FROM studente WHERE matricola=?))";

	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, matricola);

		ResultSet rs = st.executeQuery();
		List<Corso> corsi = new ArrayList<Corso>();

		while (rs.next()) {
			
			String codins = rs.getString("codins");
			int crediti = rs.getInt("crediti");
			String nome = rs.getString("nome");
			int pd = rs.getInt("pd");
			Corso temp;
			temp = new Corso (codins,crediti,nome,pd);
			corsi.add(temp);
		}
		
		//conn.close();
		return corsi;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
public 	int cercaSeIscritto(String corso, int matricola){
	
	final String sql = "SELECT * "+
						"FROM iscrizione "+
						"WHERE matricola = ? AND codins IN (SELECT codins "+
														"FROM corso "+
														"WHERE nome= ?)";

	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, matricola);
		st.setString(2, corso);

		ResultSet rs = st.executeQuery();
		List<Corso> corsi = new ArrayList<Corso>();
		int contatore =0;

		while (rs.next()) {
			
			contatore++;
		}
		
		//conn.close();
		return contatore;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

}
