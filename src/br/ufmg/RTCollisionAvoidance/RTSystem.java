package br.ufmg.RTCollisionAvoidance;

public class RTSystem {
	
	int clock;
	Map map;
	SporadicServer server;
	boolean printMap;
	int numberCicles;
	
	public RTSystem(Map map, int numberCicles, boolean printMap){
		clock = 0;
		this.map = map;
		server = new SporadicServer();
		this.printMap = printMap;
		this.numberCicles = numberCicles;
	}
	
	public void run(){
		for (int i = 0; i < this.numberCicles; i++) {
			if(this.printMap){
				this.map.print();
			}
			this.map.nextCicle();
		}
		System.out.println(this.map.collisions);
		System.out.println(this.map.leisure);
	}

}
