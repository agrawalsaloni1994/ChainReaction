package com.android.games;
import java.util.*;
public class ChainReaction {
	public enum Color {
	    GREEN,
	    RED,
	    NONE
	}
	public int gridValue;
	public int atomCount;
	public int x;
	public int y;
	public int N;
	public Color color;
	public ChainReaction(){
	}
	public ChainReaction(int N){
		this.N = N;
	}
	public void setCellLocation(int x, int y){
		 this.x = x;
		 this.y = y;
	}
	public int getX(ChainReaction cell){
		return cell.x;
	}
	public int getY(ChainReaction cell){
		return cell.y;
	}
	public void setCellColor(Color color){
		this.color = color;
	}
	public Color getCellColor(){
		return this.color;
	}
	public int getGridValue(int x, int y){
		if((x==0 || x==(N-1)) && (y==0 || y==(N-1))){
			this.gridValue = 1;
		}
		else if ((x > 0 && x < (N-1)) && (y == 0 || y == (N-1)) || (y > 0 && y < (N-1)) && (x == 0 || x == (N-1))){
			this.gridValue = 2;
		}
		else{
			this.gridValue = 3;
		}
		return this.gridValue;
	}
	public void insertAtom(ArrayList<ArrayList<ChainReaction>> cell,Color color){
		if(this.getCellColor() == Color.NONE){
			this.setCellColor(color);
			(this.atomCount)++;
		}
		else if(this.getCellColor() == color){
			if(this.atomCount < this.getGridValue( this.x, this.y)){
				(this.atomCount)++;
			}
			else if(this.atomCount >= this.getGridValue( this.x, this.y)){
				startExplosion(cell,this.x,this.y, this.color);
			}
		}
		else{
			System.out.println("The cell has already been punched by another player.");
		}
		
	}
	public void startExplosion(ArrayList<ArrayList<ChainReaction>> cell,int x, int y , Color color){
		if((x == 0) && (y == 0)){
			if(cell.get(x).get(y).atomCount < getGridValue(x, y)){
				(cell.get(x).get(y).atomCount)++;
				cell.get(x).get(y).setCellColor(color);
				return;
			}
			else if(cell.get(x).get(y).atomCount >= getGridValue(x, y)){
				cell.get(x).get(y).atomCount = 0;
				cell.get(x).get(y).setCellColor(Color.NONE);
				startExplosion(cell, x+1, y, color);
				startExplosion(cell, x, y+1, color);
			}
		}else if(x == (N - 1) && y == (N - 1)){
			if(cell.get(x).get(y).atomCount < getGridValue(x, y)){
				(cell.get(x).get(y).atomCount)++;
				cell.get(x).get(y).setCellColor(color);
				return;
			}
			else if(cell.get(x).get(y).atomCount >= getGridValue(x, y)){
				cell.get(x).get(y).atomCount = 0;
				cell.get(x).get(y).setCellColor(Color.NONE);
				startExplosion(cell, x-1, y, color);
				startExplosion(cell, x, y-1, color);
			}
		}else if((x > 0 && x < (N - 1)) && (y == 0)){
			if(cell.get(x).get(y).atomCount < getGridValue(x, y)){
				(cell.get(x).get(y).atomCount)++;
				cell.get(x).get(y).setCellColor(color);
				return;
			}
			else if(cell.get(x).get(y).atomCount >= getGridValue(x, y)){
				cell.get(x).get(y).atomCount = 0;
				cell.get(x).get(y).setCellColor(Color.NONE);
				startExplosion(cell, x+1, y, color);
				startExplosion(cell, x, y+1, color);
				startExplosion(cell, x-1, y, color);
			}
		}else if((x > 0 && x < (N - 1)) && (y == N - 1)){
			if(cell.get(x).get(y).atomCount < getGridValue(x, y)){
				(cell.get(x).get(y).atomCount)++;
				cell.get(x).get(y).setCellColor(color);
				return;
			}
			else if(cell.get(x).get(y).atomCount >= getGridValue(x, y)){
				cell.get(x).get(y).atomCount = 0;
				cell.get(x).get(y).setCellColor(Color.NONE);
				startExplosion(cell, x+1, y, color);
				startExplosion(cell, x, y-1, color);
				startExplosion(cell, x-1, y, color);
			}
		}else{
			if(cell.get(x).get(y).atomCount < getGridValue(x, y)){
				(cell.get(x).get(y).atomCount)++;
				cell.get(x).get(y).setCellColor(color);
				return;
			}
			else if(cell.get(x).get(y).atomCount >= getGridValue(x, y)){
				cell.get(x).get(y).atomCount = 0;
				cell.get(x).get(y).setCellColor(Color.NONE);
				startExplosion(cell, x+1, y, color);
				startExplosion(cell, x, y+1, color);
				startExplosion(cell, x, y-1, color);
				startExplosion(cell, x-1, y, color);
			}
		}
	}
	public static boolean checkState(ArrayList<ArrayList<ChainReaction>> cell, int N){
		Color color = cell.get(0).get(0).getCellColor();
		for(int i = 0; i < N ; i++){
			for(int j = 0; j < N; j++){
				if(cell.get(i).get(j).getCellColor() != color || cell.get(i).get(j).atomCount == 0){
					return false;
				}
			}
		}
		System.out.println("Game over!");
		return true;
	}
	public static void main(String args[]){
		Scanner reader = new Scanner(System.in);  
		System.out.println("Enter the size of Chain Reaction Grid : ");
		int n = reader.nextInt();
		ArrayList<ArrayList<ChainReaction>> cell = new ArrayList<ArrayList<ChainReaction>>(n);
		for(int i = 0; i < n; i++){
	        cell.add(new ArrayList<ChainReaction>(n));
		
	        for(int j = 0; j < n; j++){
				//System.out.println("bye "+ i + j);
				cell.get(i).add(j,new ChainReaction());
				cell.get(i).get(j).setCellColor(Color.NONE);
				cell.get(i).get(j).setCellLocation(i, j);
				cell.get(i).get(j).atomCount = 0;
			}
		}

		for(;;)
		{
			System.out.println("Player 1: Enter the x & y co-ordinates : ");
			int Player1x = reader.nextInt();
			int Player1y = reader.nextInt();
			cell.get(Player1x).get(Player1y).insertAtom(cell,Color.GREEN);
			int ac = cell.get(Player1x).get(Player1y).atomCount;
			System.out.println("The cell count is "+ac);
			if(checkState(cell,n)){
				reader.close();
				return;
			}
			System.out.println("Player 2: Enter the x & y co-ordinates : ");
			int Player2x = reader.nextInt();
			int Player2y = reader.nextInt();
			cell.get(Player2x).get(Player2y).insertAtom(cell,Color.RED);
			ac = cell.get(Player2x).get(Player2y).atomCount;
			System.out.println("The cell count is "+ac);
			if(checkState(cell,n)){
				reader.close();
				return;
			}
		}
	}
}
