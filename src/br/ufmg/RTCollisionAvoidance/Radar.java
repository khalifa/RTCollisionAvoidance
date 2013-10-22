package br.ufmg.RTCollisionAvoidance;

import java.util.ArrayList;
import java.util.List;

public class Radar implements Comparable <Radar>{

	private String 	name;
	private Radar 	parent;
	private boolean status; // true => ativo; false => inativo
	private boolean isVisited;
	private int distancia;


	private List <Via> 		incidentList;
	private List <Via>	 	emanatingList;
	private List <Radar>	predecessorsList;
	private List <Radar> 	sucessorsList;
	
	public Radar (String strName){
		
		this.name 		= strName;
		this.parent		= null;
		this.setVisited(false);
		
		this.incidentList 	= new ArrayList <Via>();
		this.emanatingList = new ArrayList <Via>();
		this.predecessorsList 	= new ArrayList <Radar>();
		this.sucessorsList 		= new ArrayList <Radar>();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Radar getParent() {
		return parent;
	}

	public void setParent(Radar parent) {
		this.parent = parent;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Via> getIncidentList() {
		return incidentList;
	}

	public void setIncidentList(List<Via> incidentList) {
		this.incidentList = incidentList;
	}

	public List<Via> getEmanatingList() {
		return emanatingList;
	}

	public void setEmanatingList(List<Via> emanatingList) {
		this.emanatingList = emanatingList;
	}

	public List<Radar> getPredecessorsList() {
		return predecessorsList;
	}

	public void setPredecessorsList(List<Radar> predecessorsList) {
		this.predecessorsList = predecessorsList;
	}

	public List <Radar> getSucessorsList() {
		return sucessorsList;
	}

	public void setSucessorsList(List<Radar> sucessorsList) {
		this.sucessorsList = sucessorsList;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	//================================================================
	//================================================================
    void addIncidentVia(Via v) {
    	incidentList.add(v);
    }

	//================================================================
	//================================================================
    void addEmanatingVia(Via v) {
    	emanatingList.add(v);
    }
    
	//================================================================
	//================================================================
    void addPredecessors(Radar r) {
    	predecessorsList.add(r);
    }
    
	//================================================================
	//================================================================
    void addSuccessors(Radar r) {
    	sucessorsList.add(r);
    }
    
	//================================================================
	//================================================================
	public int getWeight() {
		
		return 0;
	}

	//================================================================
	//================================================================
	public boolean isVisited() {
		return isVisited;
	}

	//================================================================
	//================================================================
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	//================================================================
	//================================================================
	@Override
	public int compareTo(Radar r) {
		
		return this.name.compareToIgnoreCase(r.getName());
	}
	
}
