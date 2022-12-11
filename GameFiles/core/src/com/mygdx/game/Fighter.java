package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Fighter {
	
	float xCoord;
	float yCoord;
	String pseudo;
	int comp;
	Texture Stand;
	Texture[] WalkR;
	Texture[] WalkL;
	float textureHeight;
	float textureWidth;
	
	float HP;
	int currentSpeed;
	float currentStrength;
	
	Fighter(String name){
		
		// Setting player's initial characteristics 
		pseudo = name;
		xCoord = 200;
		yCoord = 100;
		// Character sprites
		WalkR = new Texture[]{new Texture(Gdx.files.internal("WalkingPositionR1.png")),new Texture(Gdx.files.internal("WalkingPositionR2.png")),
				new Texture(Gdx.files.internal("WalkingPositionR3.png")),new Texture(Gdx.files.internal("WalkingPositionR4.png")),
				new Texture(Gdx.files.internal("WalkingPositionR5.png")),new Texture(Gdx.files.internal("WalkingPositionR6.png")),
				new Texture(Gdx.files.internal("WalkingPositionR7.png")),new Texture(Gdx.files.internal("WalkingPositionR8.png")),
				new Texture(Gdx.files.internal("WalkingPositionR9.png")),new Texture(Gdx.files.internal("WalkingPositionR10.png")),
				new Texture(Gdx.files.internal("WalkingPositionR11.png")),new Texture(Gdx.files.internal("WalkingPositionR12.png")),};
		Stand = new Texture(Gdx.files.internal("StandingPosition.png"));
		WalkL = new Texture[]{new Texture(Gdx.files.internal("WalkingPositionL1.png")),new Texture(Gdx.files.internal("WalkingPositionL2.png")),
				new Texture(Gdx.files.internal("WalkingPositionL3.png")),new Texture(Gdx.files.internal("WalkingPositionL4.png")),
				new Texture(Gdx.files.internal("WalkingPositionL5.png")),new Texture(Gdx.files.internal("WalkingPositionL6.png")),
				new Texture(Gdx.files.internal("WalkingPositionL7.png")),new Texture(Gdx.files.internal("WalkingPositionL8.png")),
				new Texture(Gdx.files.internal("WalkingPositionL9.png")),new Texture(Gdx.files.internal("WalkingPositionL10.png")),
				new Texture(Gdx.files.internal("WalkingPositionL11.png")),new Texture(Gdx.files.internal("WalkingPositionL12.png")),};
		
		textureHeight = Gdx.graphics.getHeight()/7;
		textureWidth = Gdx.graphics.getWidth()/16;
		
		HP = 1000; // initial HP
		currentSpeed = 1; // initial speed
		currentStrength = 1; // initial strength
	}
	
	float[] locate() {
		
		float[] location = new float[]{this.xCoord, this.yCoord};
		return location;
		
	}

	// Walking
	Texture right() {
		this.xCoord+=this.currentSpeed;
		comp++;
		return this.WalkR[((comp-1)/(4*this.currentSpeed))%12];
	}
	Texture left() {
		this.xCoord-=this.currentSpeed;
		comp++;
		return this.WalkL[((comp-1)/(4*this.currentSpeed))%12];
	}
	
	Texture stand() {
		return this.Stand;
	}

}









