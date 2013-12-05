package br.ufmg.RTCollisionAvoidance;

public class Cell {
	
	public static enum CellType{
		STREET_HORIZONTAL, STREET_VERTICAL, CROSSROAD, PAVEMENT, RADAR
	}
	
	public CellType type;
	public Vehicle vehicle1, vehicle2;
	public Cell neighN, neighS, neighE, neighW;
	
	public Cell (CellType type){
		this.type = type;
		vehicle1 = null;
		vehicle2 = null;
	}
	
	public void print(){
		
		switch (type) {
		case STREET_HORIZONTAL:
			System.out.print("−");
			break;
		case STREET_VERTICAL:
			System.out.print("|");
			break;
		case CROSSROAD:
			System.out.print("+");
			break;
		case PAVEMENT:
			System.out.print("□");
			break;
		case RADAR:
			System.out.print("#");
			break;
		}
		
		
	}

}
