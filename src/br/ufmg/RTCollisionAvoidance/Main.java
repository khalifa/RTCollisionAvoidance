package br.ufmg.RTCollisionAvoidance;

import br.ufmg.RTCollisionAvoidance.Vehicle.VehicleType;

public class Main {

	public static void main(String[] args) {
		
		Map map = new Map(args[0]);
		//map.print();
		//map.print2();
		int numberCarrs = 25;
		int numberAmbulances = 2;
		
		for(int i =0; i < numberCarrs; i ++){
			map.addVehicle(VehicleType.CARR);
		}
		for(int i =0; i < numberAmbulances; i ++){
			map.addVehicle(VehicleType.AMBULANCE);
		}
		map.print();
		
		map.nextCicle();
		map.nextCicle();
		map.nextCicle();
		
		System.out.println();
		
		map.print();
		System.out.println(5%5);

	}

}
