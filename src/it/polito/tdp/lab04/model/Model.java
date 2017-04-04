package it.polito.tdp.lab04.model;
import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;
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
	
	public String cercaNomeStudente(int matricola){
		StudenteDAO sd = new StudenteDAO();
		Studente temp = sd.cercaStudente(matricola);
		if(temp == null)
			return null;
		return temp.getNome();
		
	}
	public String cercaCognomeStudente(int matricola){
		StudenteDAO sd = new StudenteDAO();
		Studente temp = null;
		temp=sd.cercaStudente(matricola);
		return temp.getCognome();
		
	}
	public String studentiIscritti (String corso){
		CorsoDAO cd = new CorsoDAO();
		String ris = "";
		List<Studente> temp = new LinkedList<Studente>();
		temp.addAll(cd.getStudentiIscrittiAlCorso(corso));
		if(!temp.isEmpty()){
		for (Studente s: temp) {
			ris+=s.getMatricola()+" "+s.getNome()+" "+s.getCognome()+" "+s.getCds()+"\n";
		}
		}
		else{
			return null;
		}
		return ris;
		
	}
	public String cercaCorsiStudente(int matricola){
		StudenteDAO sd = new StudenteDAO();
		Studente temp = sd.cercaStudente(matricola);
		String ris="";
		if(temp == null)
			return null;
		List<Corso> corsi = new LinkedList<Corso>();
		corsi.addAll(sd.cercaCorsiIscritto(matricola));
		if (!corsi.isEmpty()){
			for(Corso c: corsi){
				ris+=c.getCodice()+" "+c.getCrediti()+" "+c.getNome()+" "+c.getPd()+"\n";
			}
		}
		else{
			return null;
		}
		return ris;
		
	}

	public boolean controllaCorsoStudente(String corso, int matricola) {
		StudenteDAO sd = new StudenteDAO();
		if(sd.cercaSeIscritto(corso,matricola)==1){
			return true;
		}
		return false;
	}



	public int iscrivi(int matricola, String codins) {
		if(this.cercaNomeStudente(matricola)==null){
			return 1;
		}
		else if (this.cercaCorsiStudente(matricola).contains(codins)){
			return 2;
			}
		CorsoDAO cd = new CorsoDAO();
		cd.iscriviStudenteACorso(matricola, codins);
		return 0;
			
		}
		
	}
	

