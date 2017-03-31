package it.polito.tdp.lab04.model;
import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
public class Model {
	public List<String> corsi = new ArrayList<String>();
	public List<Studente> studenti = new ArrayList<Studente>();
	
	public List<String> getListaCorsi(){
		CorsoDAO cd = new CorsoDAO();
		for(Corso c : cd.getTuttiICorsi()){
			corsi.add(c.getNome());
		}
		
		return corsi;
	}
	
	public Studente cercaStudente(int matricola){
		CorsoDAO cd = new CorsoDAO();
		List<Studente> temp = new ArrayList<Studente>();
		temp=cd.getStudenti();
		Studente ris=temp.get(matricola);
		return ris;
		
	}
	
}
