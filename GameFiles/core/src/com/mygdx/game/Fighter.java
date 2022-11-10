package com.mygdx.game;

public class Fighter {
	
	float xCoord;
	float yCoord;
	String pseudo;
	
	Fighter(String name){
		
		pseudo = name;
		xCoord = 200;
		yCoord = 100;
	}
	
	float[] locate() {
		
		float[] location = new float[]{this.xCoord, this.yCoord};
		return location;
		
	}
	void right() {
		this.xCoord++;
	}
	void left() {
		this.xCoord--;
	}

}
