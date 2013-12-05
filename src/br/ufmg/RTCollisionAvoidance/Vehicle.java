package br.ufmg.RTCollisionAvoidance;

import br.ufmg.RTCollisionAvoidance.Cell.CellType;



public class Vehicle {
	
	public static enum VehicleType {
		CARR, AMBULANCE
	}
	
	public static enum Direction {
		NORTH, SOUTH, EAST, WEAST
	}
	
	public VehicleType type;
	Direction direction;
	boolean stop;
	boolean sendSignal;
	boolean waiting;
	int posX, posY;
	int id;
	Cell cell;
	
	public Vehicle(VehicleType type, Direction direction, Cell cell, int id){
		this.type = type;
		this.direction = direction;
		this.cell = cell;
		this.id = id;
		this.waiting = false;
		this.sendSignal = true;
		if(this.type == VehicleType.AMBULANCE){
			this.stop = false;
		} else if (this.type == VehicleType.CARR){
			this.stop = true;
		}
		
	}
	
	public void nextCicle(){
		Cell nextCell;
		switch(this.direction){
		case NORTH:
			nextCell = this.cell.neighN;
			break;
		case SOUTH:
			nextCell = this.cell.neighS;
			break;
		case EAST:
			nextCell = this.cell.neighE;
			break;
		case WEAST:
			nextCell = this.cell.neighW;
			break;
		default:
			nextCell = this.cell.neighW;
		}
		if(nextCell.type == CellType.CROSSROAD && stop == true){
			this.waiting = true;
		} else {
			this.waiting = false;
			this.cell = nextCell;
		}
	}
	
	public void print(){
		//System.out.print("[" + this.id + "]");
		
		switch(this.direction){
		case NORTH:
			System.out.print("▲");
			break;
		case SOUTH:
			System.out.print("▼");
			break;
		case EAST:
			System.out.print("▶");
			break;
		case WEAST:
			System.out.print("◀");
			break;
		}
	}
	
}
