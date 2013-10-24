package br.ufmg.RTCollisionAvoidance;

import java.util.Random;

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
	int posX, posY;
	int id;
	Cell cell;
	
	public Vehicle(VehicleType type, Direction direction, Cell cell, int id){
		this.type = type;
		this.direction = direction;
		this.cell = cell;
		this.id = id;
		sendSignal = true;
		if(type == VehicleType.AMBULANCE){
			stop = false;
		} else if (type == VehicleType.CARR){
			stop = true;
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
			
		} else {
			this.cell = nextCell;
		}
	}
	
	public void print(){
		System.out.print(this.id);
		/*
		switch(this.direction){
		case NORTH:
			System.out.print("N");
			break;
		case SOUTH:
			System.out.print("S");
			break;
		case EAST:
			System.out.print("E");
			break;
		case WEAST:
			System.out.print("W");
			break;
		}*/
	}
	
}
