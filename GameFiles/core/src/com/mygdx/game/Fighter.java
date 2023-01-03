package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.audio.Sound;

public class Fighter {
	
	float xCoord;
	float yCoord;
	boolean sideIndicator;
	
	String pseudo;
	
	int textureTick;
	Texture texture;
	Sprite sprite;
	
	Sound sound;
	
	
	Texture[] Idle;
	Texture[] Walk;
	Texture[][] attackTexture;
	
	float textureHeight;
	float textureWidth;
	
	int currentAttack;
	boolean attack = false;
	boolean shield = false;
    int attackDuration;
	
	float HP;
	float bonusHP;
	int currentSpeed;
	float currentStrength;
	boolean shieldAbilities;
	
	Fighter(String name){
		
		pseudo = name;
		xCoord = 200;
		yCoord = 80;
		
		
		
		Idle = new Texture[]{new Texture(Gdx.files.internal("idleR-1.png")),new Texture(Gdx.files.internal("idleR-2.png")),
				new Texture(Gdx.files.internal("idleR-3.png")),new Texture(Gdx.files.internal("idleR-4.png")),
				new Texture(Gdx.files.internal("idleR-5.png")),new Texture(Gdx.files.internal("idleR-6.png"))};
		
		Walk = new Texture[]{new Texture(Gdx.files.internal("runR-1.png")),new Texture(Gdx.files.internal("runR-2.png")),
				new Texture(Gdx.files.internal("runR-3.png")),new Texture(Gdx.files.internal("runR-4.png")),
				new Texture(Gdx.files.internal("runR-5.png")),new Texture(Gdx.files.internal("runR-6.png")),
				new Texture(Gdx.files.internal("runR-7.png")),new Texture(Gdx.files.internal("runR-8.png")),
				new Texture(Gdx.files.internal("runR-9.png")),new Texture(Gdx.files.internal("runR-10.png")),
				new Texture(Gdx.files.internal("runR-11.png")),new Texture(Gdx.files.internal("runR-12.png")),};
		
		attackTexture = new Texture[][]{new Texture[]{new Texture(Gdx.files.internal("attack-A1-P.png")),new Texture(Gdx.files.internal("attack-A2-P.png")),
				new Texture(Gdx.files.internal("attack-A3-P.png")),new Texture(Gdx.files.internal("attack-A4-P.png")),
				new Texture(Gdx.files.internal("attack-A5-P.png")),new Texture(Gdx.files.internal("attack-A6-P.png")),
				new Texture(Gdx.files.internal("attack-A7-P.png")),new Texture(Gdx.files.internal("attack-A8-P.png"))},
			new Texture[]{new Texture(Gdx.files.internal("jump-attack-1.png")),new Texture(Gdx.files.internal("jump-attack-2.png")),
					new Texture(Gdx.files.internal("jump-attack-3.png")),new Texture(Gdx.files.internal("jump-attack-4.png")),
					new Texture(Gdx.files.internal("jump-attack-5.png")),new Texture(Gdx.files.internal("jump-attack-6.png")),
					new Texture(Gdx.files.internal("jump-attack-7.png")),new Texture(Gdx.files.internal("jump-attack-8.png"))},
			new Texture[]{new Texture(Gdx.files.internal("shield-block-center-1.png")), new Texture(Gdx.files.internal("shield-block-center-2.png"))}};
		
				
		textureHeight = Gdx.graphics.getHeight()/4;
		textureWidth = Gdx.graphics.getWidth()/6;
		
		
		HP = 1000; // initial HP
		bonusHP = 0;
		currentSpeed = 2; // initial speed
		currentStrength = 3; // initial strength
		shieldAbilities = false;
		
		sound = Gdx.audio.newSound(Gdx.files.internal("SWORD_03.mp3"));
	}
	
	
	public float getxCoord() {
		return xCoord;
	}
	public float getyCoord() {
		return yCoord;
	}
	
	public void setxCoord(float xCoord) {
		this.xCoord = xCoord;
	}
	public void setyCoord(float yCoord) {
		this.yCoord = yCoord;
	}
	
	
	public float getHP() {
		return HP;
	}


	public void setHP(float hP) {
		HP = hP;
	}


	public int getCurrentSpeed() {
		return currentSpeed;
	}


	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}


	public float getCurrentStrength() {
		return currentStrength;
	}


	public void setCurrentStrength(float currentStrength) {
		this.currentStrength = currentStrength;
	}


	public void setSideIndicator(boolean sideIndicator) {
		this.sideIndicator = sideIndicator;
	}
	
	public int getTextureTick() {
		return textureTick;
	}

	public void setTextureTick(int textureTick) {
		this.textureTick = textureTick;
	}

	public int getCurrentAttack() {
		return currentAttack;
	}
	public void setCurrentAttack(int currentAttack) {
		this.currentAttack = currentAttack;
	}
	public boolean isAttack() {
		return attack;
	}
	public void setAttack(boolean attack) {
		this.attack = attack;
	}
	
	public boolean isShield() {
		return shield;
	}


	public void setShield(boolean shield) {
		this.shield = shield;
	}


	public int getAttackDuration() {
		return attackDuration;
	}
	public void setAttackDuration(int attackDuration) {
		this.attackDuration = attackDuration;
	}
	
	
	public Sprite getSprite() {
		return sprite;
	}

	public Sound getSound() {
		return sound;
	}
	

	public void setShieldAbilities(boolean shieldAbilities) {
		this.shieldAbilities = shieldAbilities;
	}
	public boolean isShieldAbilities() {
		return shieldAbilities;
	}
	
	public float getBonusHP() {
		return bonusHP;
	}


	public void setBonusHP(float bonusHP) {
		this.bonusHP = bonusHP;
	}


	// this.texture = 
		// else if textureTickt != 0 num frame textureTickt+1%7 else num frame = 1
	void walkL() {
		this.xCoord-=this.currentSpeed;
		textureTick++;
		
		this.texture = this.Walk[((textureTick-1)/(2*this.currentSpeed))%12];
		this.sprite = new Sprite(this.texture);
		this.sprite.flip(this.sideIndicator, false);
		this.sprite.setPosition(this.xCoord, this.yCoord);
		this.sprite.setSize(this.textureWidth, this.textureHeight);
		
	}
	void walkR() {
		this.xCoord+=this.currentSpeed;
		textureTick++;
		
		this.texture = this.Walk[((textureTick-1)/(2*this.currentSpeed))%12];
		this.sprite = new Sprite(this.texture);
		this.sprite.flip(this.sideIndicator, false);
		this.sprite.setPosition(this.xCoord, this.yCoord);
		this.sprite.setSize(this.textureWidth, this.textureHeight);
		
	}
	
	void idle() {
		textureTick++;
		this.texture = this.Idle[((textureTick-1)/42)%6];
		this.sprite = new Sprite(this.texture);
		this.sprite.flip(this.sideIndicator, false);
		this.sprite.setSize(this.textureWidth, this.textureHeight);
		this.sprite.setPosition(this.xCoord, this.yCoord);
	}
	
	void fight(){
		if(this.currentAttack == 2) {
			if(textureTick*7/(attackDuration-1) < 4) {
				this.yCoord += 8 ;
			}
			else if(textureTick*7/(attackDuration-1) >= 4 & this.yCoord >= 180) {
				this.yCoord =  this.yCoord/2;
			}
			
		}
		this.texture = this.attackTexture[this.currentAttack-1][((textureTick-1)*(attackTexture[currentAttack-1].length-1)/(attackDuration-1))];
		this.sprite = new Sprite(this.texture);
		this.sprite.flip(this.sideIndicator, false);
		this.sprite.setSize(this.textureWidth, this.textureHeight);
		this.sprite.setPosition(this.xCoord, this.yCoord);
		
	}
	
	void hit(Ennemy ennemy) {
		if(ennemy.getHP() >= 50*this.currentStrength) {
			ennemy.setHP(ennemy.getHP()-50*this.currentStrength);
		}
		
	}
	
	boolean reach(Ennemy ennemy) {
		return Math.sqrt((this.xCoord-ennemy.getxCoord())*(this.xCoord-ennemy.getxCoord())+
				(this.yCoord-ennemy.getyCoord())*(this.yCoord-ennemy.getyCoord())) < (this.textureWidth)/2;
	}
	
	void reset() {
		
		this.HP = 1000; // initial HP
		this.currentSpeed = 2; // initial speed
		this.currentStrength = 1; // initial strength
		this.xCoord = 200;
		this.yCoord = 80;
		this.textureTick = 0;
		
	}

}









