package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Floor {
	
	int floor;
	
	
	Texture background;
	
	Texture floorTexture;
	
	Texture startMenu;
	Texture gameOverMenu;
	Texture pauseMenu;
	
	
	Item reward;
	Texture chestT;
	Sprite chestS;
	boolean chestB;
	
	
	
	Floor(int floor){
		
		this.floor = floor;
		
		this.chestB = true;
		
		if (floor == 0) { // Start menu
			this.startMenu = new Texture(Gdx.files.internal("Jeu-Logo.png"));
			this.gameOverMenu = new Texture(Gdx.files.internal("gameover.png"));
			this.pauseMenu = new Texture(Gdx.files.internal("pause.png"));
		}
		
		else if(floor == 1) {
			
			this.background = new Texture(Gdx.files.internal("Roadmountain-B1.png"));
			
			this.floorTexture = new Texture(Gdx.files.internal("floor-1.png"));
			
			this.chestT = new Texture(Gdx.files.internal("Bag ColorD.png"));
		}
		
		else if(floor == 2) {
			
			this.background = new Texture(Gdx.files.internal("Mountains.png"));
			
			this.floorTexture = new Texture(Gdx.files.internal("floor-2.png"));
			
			this.chestT = new Texture(Gdx.files.internal("Bucket ColorD.png"));
		}
		else if(floor == 3) {
			
			this.background = new Texture(Gdx.files.internal("Road.png"));
			
			this.floorTexture = new Texture(Gdx.files.internal("floor-3.png"));
			
			this.chestT = new Texture(Gdx.files.internal("Chest ColorD.png"));
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


	public Sprite getChestS() {
		return chestS;
	}


	public void setChestS(Sprite chest) {
		this.chestS = chest;
	}
	
	
	public Texture getChestT() {
		return chestT;
	}


	public void setChestT(Texture chestT) {
		this.chestT = chestT;
	}
	
	
	public boolean isChestB() {
		return chestB;
	}


	public void setChestB(boolean chestB) {
		this.chestB = chestB;
	}


	void reward(Fighter player) {
		
		if(floor == 1) {
			
			player.setHP(player.getHP() + 250);
			player.setBonusHP(250);
		}
		
		else if(floor == 2) {
			
			player.setShieldAbilities(true);
		}
	}
	
	void rewardAppear(float xcoord) {
		
		this.chestS = new Sprite(this.chestT);
		this.chestS.setSize(Gdx.graphics.getWidth()/38, Gdx.graphics.getHeight()/15);
		this.chestS.setPosition(xcoord, 85);
	}
	
}
