package br.ufmg.RTCollisionAvoidance;

public class Main {

	public static void main(String[] args) {

		Map map = new Map(args[0]);

		int numberCicles = Integer.parseInt(args[1]);
		boolean printMap;
		if(args[2].equals("m")){
			printMap = true;
		}else {
			printMap = false;
		}
		
		RTSystem system = new RTSystem(map, numberCicles, printMap);
		system.run();
	}

}
