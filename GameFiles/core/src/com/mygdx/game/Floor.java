package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Floor {
	
	int floor;
	
	
	Texture background1;
	Texture background2;
	Texture background3;
	Item reward;
	
	
	
	Floor(int floor){
		
		this.floor = floor;
		
		if(floor == 1) {
			
			this.background1 = new Texture(Gdx.files.internal("Background-B1.png"));
			this.background2 = new Texture(Gdx.files.internal("Mountains-B1.png"));
			this.background3 = new Texture(Gdx.files.internal("Roadmountain-B1.png"));
			
		}
		
		else if(floor == 2) {
			
			//this.background1 = new Texture(Gdx.files.internal("Mountains.png"));
		}
	}


	public int getFloor() {
		return floor;
	}


	public void setFloor(int floor) {
		this.floor = floor;
	}


	public Texture getBackground1() {
		return background1;
	}

	public Texture getBackground2() {
		return background2;
	}


	public void setBackground2(Texture background2) {
		this.background2 = background2;
	}


	public Texture getBackground3() {
		return background3;
	}


	public void setBackground3(Texture background3) {
		this.background3 = background3;
	}


	public void setBackground1(Texture background) {
		this.background1 = background;
	}


	public Item getReward() {
		return reward;
	}


	public void setReward(Item reward) {
		this.reward = reward;
	}

	

}
