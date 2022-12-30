package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Floor {
	
	int floor;
	
	
	Texture background;
	
	Texture floorTexture;
	
	Texture startMenu;
	Texture gameOverMenu;
	Texture pauseMenu;
	
	
	Item reward;
	
	
	
	Floor(int floor){
		
		this.floor = floor;
		
		if (floor == 0) { // Start menu
			this.startMenu = new Texture(Gdx.files.internal("Jeu-Logo.png"));
			this.gameOverMenu = new Texture(Gdx.files.internal("gameover.png"));
			this.pauseMenu = new Texture(Gdx.files.internal("pause.png"));
		}
		
		else if(floor == 1) {
			
			this.background = new Texture(Gdx.files.internal("Roadmountain-B1.png"));
			
			this.floorTexture = new Texture(Gdx.files.internal("floor-1.png"));
		}
		
		else if(floor == 2) {
			
			this.background = new Texture(Gdx.files.internal("Mountains.png"));
			
			this.floorTexture = new Texture(Gdx.files.internal("floor-2.png"));
		}
	}


	public int getFloor() {
		return floor;
	}


	public void setFloor(int floor) {
		this.floor = floor;
	}


	


	public Texture getBackground() {
		return background;
	}


	public void setBackground(Texture background) {
		this.background = background;
	}


	public Item getReward() {
		return reward;
	}


	public void setReward(Item reward) {
		this.reward = reward;
	}


	public Texture getFloorTexture() {
		return floorTexture;
	}


	public void setFloorTexture(Texture floorTexture) {
		this.floorTexture = floorTexture;
	}


	public Texture getStartMenu() {
		return startMenu;
	}


	public void setStartMenu(Texture startMenu) {
		this.startMenu = startMenu;
	}


	public Texture getGameOverMenu() {
		return gameOverMenu;
	}


	public void setGameOverMenu(Texture gameOverMenu) {
		this.gameOverMenu = gameOverMenu;
	}


	public Texture getPauseMenu() {
		return pauseMenu;
	}


	public void setPauseMenu(Texture pauseMenu) {
		this.pauseMenu = pauseMenu;
	}
	
	

	

}
