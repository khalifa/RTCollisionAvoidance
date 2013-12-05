package br.ufmg.RTCollisionAvoidance;

public class Main {

	public static void main(String[] args) {

		

		int numberCicles = Integer.parseInt(args[1]);
		boolean printMap;
		if(args[2].equals("m")){
			printMap = true;
		}else {
			printMap = false;
		}
		
		int numberExecutions = Integer.parseInt(args[3]);
		int collisions = 0;
		int leisure = 0;
		for(int i = 0; i < numberExecutions; i++){
			Map map = new Map(args[0]);
			RTSystem system = new RTSystem(map, numberCicles, printMap);
			system.run();
			collisions += system.map.collisions;
			leisure += system.map.leisure;
		}
		System.out.println(/*"Collisions: " + */collisions/numberExecutions);
		System.out.println(/*"Leisure Time: " + */leisure/numberExecutions);
	}

}
