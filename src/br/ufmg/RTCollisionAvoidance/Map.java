package br.ufmg.RTCollisionAvoidance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import br.ufmg.RTCollisionAvoidance.Cell.CellType;
import br.ufmg.RTCollisionAvoidance.Vehicle.Direction;
import br.ufmg.RTCollisionAvoidance.Vehicle.VehicleType;

public class Map {

	private Cell[][] grid;
	private int columns;
	private int lines;
	private ArrayList<Vehicle> vehicles;
	int collisions, leisure;

	public Map(String fileName) {
		this.vehicles = new ArrayList<Vehicle>();
		this.read(fileName);
		this.collisions = 0;
		this.leisure = 0;
	}

	private void read(String fileName) {
		try {
			Scanner s;
			s = new Scanner(new File(fileName));
			try {
				int numberCarrs = s.nextInt();
				int numberAmbulances = s.nextInt();
				int w = s.nextInt();
				int h = s.nextInt();
				System.out.println(w + " " + h);
				int[] blocsWidth;
				blocsWidth = new int[h];
				// Read width of the blocs
				for (int i = 0; i < w; i++) {
					blocsWidth[i] = s.nextInt();
					System.out.println(blocsWidth[i]);
				}
				int[] blocsHeight;
				blocsHeight = new int[h];
				// Read height of the blocs
				for (int i = 0; i < h; i++) {
					blocsHeight[i] = s.nextInt();
					System.out.println(blocsHeight[i]);
				}
				this.create(blocsHeight, blocsWidth);
				for(int i =0; i < numberCarrs; i ++){
					this.addVehicle(VehicleType.CARR);
				}
				for(int i =0; i < numberAmbulances; i ++){
					this.addVehicle(VehicleType.AMBULANCE);
				}
			} catch (Exception e) {
				System.out.println("Wrong input file format");
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void create(int[] blocsHeight, int[] blocsWidth) {
		this.columns = blocsWidth.length - 1;
		for (int i : blocsWidth) {
			this.columns += i;
		}
		this.lines = blocsHeight.length - 1;
		for (int i : blocsHeight) {
			this.lines += i;
		}
		System.out.println(this.columns + " " + this.lines);
		this.grid = new Cell[this.lines][this.columns];

		int countBlocV = 0;
		int blocV = blocsHeight[countBlocV];
		for (int i = 0; i < this.lines; i++) {
			int countBlocH = 0;
			int blocH = blocsWidth[countBlocH];
			if (blocV > 0) {
				for (int j = 0; j < this.columns; j++) {
					if (blocH > 0) {
						// Pavement
						grid[i][j] = new Cell(CellType.PAVEMENT);
						blocH--;
					} else {
						// Street
						grid[i][j] = new Cell(CellType.STREET_VERTICAL);
						countBlocH++;
						blocH = blocsWidth[countBlocH];
					}
				}
				blocV--;
			} else {
				for (int j = 0; j < this.columns; j++) {
					if (blocH > 0) {
						// Street
						grid[i][j] = new Cell(CellType.STREET_HORIZONTAL);
						blocH--;
					} else {
						// Crossroad
						grid[i][j] = new Cell(CellType.CROSSROAD);
						countBlocH++;
						blocH = blocsWidth[countBlocH];
					}
				}
				blocV--;
				countBlocV++;
				blocV = blocsHeight[countBlocV];
			}
		}

		for (int i = 0; i < this.lines; i++) {
			for (int j = 0; j < this.columns; j++) {
				int r = (j - 1) % this.columns;
				if (r < 0) {
					r += this.columns;
				}
				System.out.println(i + " " + r + "|" + i + " " + j + "|" + i
						+ " " + (j + 1) % this.columns);
				this.grid[i][j].neighW = this.grid[i][r];
				this.grid[i][j].neighE = this.grid[i][(j + 1) % this.columns];
				r = (i - 1) % this.lines;
				if (r < 0) {
					r += this.lines;
				}
				System.out.println(r + " " + j + "|" + i + " " + j + "|"
						+ (i + 1) % this.lines + " " + j);
				this.grid[i][j].neighN = this.grid[r][j];
				this.grid[i][j].neighS = this.grid[(i + 1) % this.lines][j];
			}
		}

	}

	public void detectColision() {
		for (int i = 0; i < this.lines; i++) {
			for (int j = 0; j < this.columns; j++) {
				if(this.grid[i][j].type == CellType.CROSSROAD){
					int count = 0;
					for (Vehicle v : this.vehicles) {
						if (v.cell == this.grid[i][j]) {
							count++;
						}
					}
					if(count > 1){
						this.collisions++;
						System.out.print("c");
					}
				}
			}
		}
		System.out.println();
	}
	
	public void detectLeisure() {
		for (Vehicle v : this.vehicles) {
			if(v.waiting){
				this.leisure++;
			}
		}
		System.out.println(this.leisure);
	}

	public void print() {
		for (int i = 0; i < this.vehicles.size(); i++) {
			System.out.print("%");
		}

		System.out.println();
		System.out.println();
		for (int i = 0; i < this.lines; i++) {
			for (int j = 0; j < this.columns; j++) {
				boolean f = true;
				for (Vehicle v : this.vehicles) {
					if (v.cell == this.grid[i][j]) {
						if (v.direction == Direction.SOUTH || v.direction == Direction.WEAST) {
							v.print();
							f = false;
						}
						break;
					}
				}
				if (f) {
					System.out.print(" ");
				}

				this.grid[i][j].print();

				f = true;
				for (Vehicle v : this.vehicles) {
					if (v.cell == this.grid[i][j]) {
						if (v.direction == Direction.NORTH || v.direction == Direction.EAST) {
							v.print();
							f = false;
						}
						break;
					}
				}
				if (f) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	public void addVehicle(VehicleType type) {
		ArrayList<Cell> freeStreets;
		freeStreets = new ArrayList<Cell>();
		for (int i = 0; i < this.lines; i++) {
			for (int j = 0; j < this.columns; j++) {
				if (this.grid[i][j].type == CellType.STREET_HORIZONTAL
						|| this.grid[i][j].type == CellType.STREET_VERTICAL) {
					freeStreets.add(this.grid[i][j]);
				}
			}
		}
		Random rand = new Random();
		Cell c = freeStreets.get(rand.nextInt(freeStreets.size()));
		// c.print();
		Random rand2 = new Random();
		if (c.type == CellType.STREET_HORIZONTAL) {
			if (rand2.nextInt(2) == 0) {
				vehicles.add(new Vehicle(type, Direction.WEAST, c, vehicles
						.size()));
			} else {
				vehicles.add(new Vehicle(type, Direction.EAST, c, vehicles
						.size()));
			}
		} else {
			if (rand2.nextInt(2) == 0) {
				vehicles.add(new Vehicle(type, Direction.SOUTH, c, vehicles
						.size()));
			} else {
				vehicles.add(new Vehicle(type, Direction.NORTH, c, vehicles
						.size()));
			}
		}

	}

	public void nextCicle() {
		for (Vehicle v : this.vehicles) {
			v.nextCicle();

		}
		this.detectColision();
		this.detectLeisure();
	}
}
