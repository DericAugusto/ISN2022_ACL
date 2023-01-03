package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.audio.Sound;

public class Item {
	
	float xCoord;
	float yCoord;
	enum itemType {healthPotion, strengthPotion, speedPotion}
	itemType iT;
	
	Texture[] ItemSkins;
	float textureHeight;
	float textureWidth;
	int deathCountdown;
	int effectCountdown;
	Sound sound;
	
	Item(String itemName){
		if(itemName == "healthPotion") {
			this.iT = itemType.healthPotion;
			this.ItemSkins = new Texture[]{new Texture(Gdx.files.internal("HealthPotion.png"))};
			this.sound = Gdx.audio.newSound(Gdx.files.internal("02_Heal_02.mp3"));
        	
		}
		else if(itemName == "strengthPotion") {
			this.iT = itemType.strengthPotion;
			this.ItemSkins = new Texture[]{new Texture(Gdx.files.internal("StrengthPotion.png"))};
			this.sound = Gdx.audio.newSound(Gdx.files.internal("16_Atk_buff_5.mp3"));
		}
		else if(itemName == "speedPotion") {
			this.iT = itemType.speedPotion;
			this.ItemSkins = new Texture[]{new Texture(Gdx.files.internal("SpeedPotion.png"))};
			this.sound = Gdx.audio.newSound(Gdx.files.internal("48_Speed_up_3.mp3"));
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
		
		if (this.iT == itemType.healthPotion ) { // +300 HP 
			if(player.HP < 700 + player.getBonusHP()) {
				player.HP += 300;
			}
			else {
				player.HP = 1000 + player.getBonusHP();
			}
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
		
		if (this.iT == itemType.strengthPotion) {
			player.currentStrength = 1; 
		}
		else if (this.iT == itemType.speedPotion) {
			player.currentSpeed = 1;
		}
	}
	
	
	Boolean move() {
		
		if (this.yCoord > 80) {
			
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

	public Sound getSound() {
		return sound;
	}
	
	
}


