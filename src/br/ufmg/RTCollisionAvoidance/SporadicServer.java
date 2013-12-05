package br.ufmg.RTCollisionAvoidance;

import java.util.ArrayList;
import java.util.List;

public class SporadicServer {
	List<Task> lowPriority;
	List<Task> highPriority;
	
	public SporadicServer(){
		this.lowPriority = new ArrayList<Task>();
		this.highPriority = new ArrayList<Task>();
	}
	
	public void run(){
		int time = 20;
		
		//acceptance
	}

}
