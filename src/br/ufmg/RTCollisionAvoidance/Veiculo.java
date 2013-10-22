package br.ufmg.RTCollisionAvoidance;

import java.util.Queue;

public class Veiculo {
	
	private boolean status; // true => em movimento; false => parado
	
	private int prioridade;
	
	private Queue <Radar> menorCaminho;
	
	private Radar origem;
	
	private Radar destino;
	
	private Radar currentRadar;
	

}
