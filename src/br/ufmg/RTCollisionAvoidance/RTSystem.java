package br.ufmg.RTCollisionAvoidance;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.RTCollisionAvoidance.Vehicle.VehicleType;

public class RTSystem {

	int clock;
	Map map;
	boolean printMap;
	int numberCicles;
	List<Task> lowPriority;
	List<Task> highPriority;

	public RTSystem(Map map, int numberCicles, boolean printMap) {
		clock = 0;
		this.map = map;
		this.printMap = printMap;
		this.numberCicles = numberCicles;
		this.lowPriority = new ArrayList<Task>();
		this.highPriority = new ArrayList<Task>();
	}

	public void run() {
		for (int i = 0; i < this.numberCicles; i++) {
			for (Vehicle v : map.vehicles) {
				if (v.signal) {
					if (v.type == VehicleType.CARR) {
						lowPriority.add(v.sendSignal());
					} else {
						highPriority.add(v.sendSignal());
					}
				}
			}
			int time = 50;
			while (time > 0) {
				if (highPriority.isEmpty()) {
					if (!lowPriority.isEmpty()) {
						Task t = lowPriority.get(lowPriority.size() - 1);
						lowPriority.remove(lowPriority.size() - 1);
						for (Vehicle v : map.vehicles) {
							if (v.id == t.vehicleId) {
								v.stop = false;
							}
						}
						time -=50;
					}
				} else {
					Task t = highPriority.get(highPriority.size() - 1);
					highPriority.remove(t);
				}
				clock++;
				time -= 5;
			}
			if (this.printMap) {
				this.map.print();
			}
			this.map.nextCicle();
		}
	}

}
