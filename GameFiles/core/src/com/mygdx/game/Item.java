package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Item {
	
	float xCoord;
	float yCoord;
	enum itemType {healthPotion, strengthPotion, speedPotion, stone, monster,bomb,knife}
	itemType iT;
	
	Texture[] ItemSkins;
	float textureHeight;
	float textureWidth;
	int deathCountdown;
	int effectCountdown;
	float resistance;
	
	Item(String itemName){
		if(itemName == "healthPotion") {
			this.iT = itemType.healthPotion;
			this.ItemSkins = new Texture[]{new Texture(Gdx.files.internal("HealthPotion.png"))};
		}
		else if(itemName == "strengthPotion") {
			this.iT = itemType.strengthPotion;
			this.ItemSkins = new Texture[]{new Texture(Gdx.files.internal("StrengthPotion.png"))};
		}
		else if(itemName == "speedPotion") {
			this.iT = itemType.speedPotion;
			this.ItemSkins = new Texture[]{new Texture(Gdx.files.internal("SpeedPotion.png"))};
		}

		else if(itemName == "stone"){
			this.iT = itemType.stone;
			this.ItemSkins = new Texture[]{new Texture(Gdx.files.internal("stone.png"))};
			this.resistance = 1;
		}

		else if(itemName == "bomb"){
			this.iT = itemType.bomb;
			this.ItemSkins = new Texture[]{new Texture(Gdx.files.internal("bomb.png"))};
			this.resistance = 5;
		}

		else if(itemName == "knife"){
			this.iT = itemType.knife;
			this.ItemSkins = new Texture[]{new Texture(Gdx.files.internal("knife.png"))};
			this.resistance = 2;
		}
		
		else if(itemName == "monster"){
			this.iT = itemType.monster;
			this.ItemSkins = new Texture[]{new Texture(Gdx.files.internal("monster.png"))};
			this.resistance = 10;

		}
		this.xCoord = MathUtils.random(Gdx.graphics.getWidth());
		this.yCoord = Gdx.graphics.getHeight();
		this.deathCountdown = 0;
		
		textureHeight = Gdx.graphics.getHeight()/30;
		textureWidth = Gdx.graphics.getWidth()/120;
	}
	
	float[] locate() {
		
		float[] location = new float[]{this.xCoord, this.yCoord};
		return location;
		
	}
	
	void effect(Fighter player) {
		
		if (this.iT == itemType.healthPotion) {
			player.HP += player.HP/2; 	// x 1,5
			effectCountdown = 1;
		}
		
		else if (this.iT == itemType.strengthPotion) { // +1 
			if(player.currentStrength < 4) {
				player.currentStrength += 1;
			}
			effectCountdown = 1;
		}
		else if (this.iT == itemType.speedPotion) { // +1
			if(player.currentSpeed < 4) {
				player.currentSpeed += 1;
			}
			effectCountdown = 1;
		}
	}
	
	void clearEffect(Fighter player) {
		
		if (this.iT == itemType.healthPotion) {
			player.HP -= player.HP/2; 	// x 1,5
		}
		
		else if (this.iT == itemType.strengthPotion) {
			player.currentStrength = player.currentStrength/2; // x2
		}
		else if (this.iT == itemType.speedPotion) {
			player.currentSpeed = 1;
		}
	}
	
	
	Boolean move() {
		
		if (this.yCoord > 100) {
			
			this.yCoord -= 60*Gdx.graphics.getDeltaTime();
			return false;
		}
		
		else {
			this.deathCountdown+=1;
			
			if(this.deathCountdown == 300) {
				return true;
			}
			else {
				return false;
			}
		}
	}
}
