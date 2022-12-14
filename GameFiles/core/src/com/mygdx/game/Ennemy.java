package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Ennemy {
	
	float xCoord;
	float yCoord;
	
	boolean sideIndicator;
	
	int textureTick;
	Texture texture;
	Sprite sprite;
	
	
	Texture[] Idle;
	Texture[] Walk;
	Texture[] deathTexture;
	Texture[][] attackTexture;
	
	float textureHeight;
	float textureWidth;
	
	int currentAttack;
	boolean attack = false;
	boolean ennemyDeath = false;
	int attackDuration;
	
	float HP;
	int currentSpeed;
	float currentStrength;
	
	
	Ennemy(int ennemyClass){
		
		this.xCoord = 600;
		this.yCoord = 120;
		
		textureHeight = Gdx.graphics.getHeight()/4;
		textureWidth = Gdx.graphics.getWidth()/6;
		
		
		
		if(ennemyClass == 1) {
			
			HP = 1000;
			currentSpeed = 1;
		
			Idle = new Texture[]{new Texture(Gdx.files.internal("idle-1R-E1.png")),new Texture(Gdx.files.internal("idle-2R-E1.png")),
					new Texture(Gdx.files.internal("idle-3R-E1.png")),new Texture(Gdx.files.internal("idle-4R-E1.png"))};
			
			Walk = new Texture[]{new Texture(Gdx.files.internal("walk-1R.png")),new Texture(Gdx.files.internal("walk-2R.png")),
					new Texture(Gdx.files.internal("walk-3R.png")),new Texture(Gdx.files.internal("walk-4R.png")),
					new Texture(Gdx.files.internal("walk-5R.png")),new Texture(Gdx.files.internal("walk-6R.png"))};
			
			attackTexture = new Texture[][]{new Texture[]{new Texture(Gdx.files.internal("attack-A1-E1.png")),new Texture(Gdx.files.internal("attack-A2-E1.png")),
					new Texture(Gdx.files.internal("attack-A3-E1.png")),new Texture(Gdx.files.internal("attack-A4-E1.png")),
					new Texture(Gdx.files.internal("attack-A5-E1.png")),new Texture(Gdx.files.internal("attack-A6-E1.png")),
					new Texture(Gdx.files.internal("attack-A7-E1.png")),new Texture(Gdx.files.internal("attack-A8-E1.png"))},
				new Texture[]{new Texture(Gdx.files.internal("attack-B1-E1.png")),new Texture(Gdx.files.internal("attack-B2-E1.png")),
						new Texture(Gdx.files.internal("attack-B3-E1.png")),new Texture(Gdx.files.internal("attack-B4-E1.png")),
						new Texture(Gdx.files.internal("attack-B5-E1.png")),new Texture(Gdx.files.internal("attack-B6-E1.png")),
						new Texture(Gdx.files.internal("attack-B7-E1.png")),new Texture(Gdx.files.internal("attack-B8-E1.png")),
						new Texture(Gdx.files.internal("attack-B9-E1.png")),new Texture(Gdx.files.internal("attack-B10-E1.png")),
						new Texture(Gdx.files.internal("attack-B11-E1.png"))}};
						
			deathTexture = new Texture[]{new Texture(Gdx.files.internal("dead-1-E1.png")),new Texture(Gdx.files.internal("dead-2-E2.png")),
					new Texture(Gdx.files.internal("dead-3-E3.png")),new Texture(Gdx.files.internal("dead-4-E4.png"))};
			
			
		}
		
		else if(ennemyClass == 2) {
			
			HP = 1000;
			currentSpeed = 1;
		
			Idle = new Texture[]{new Texture(Gdx.files.internal("idle-1-E2.png")),new Texture(Gdx.files.internal("idle-2-E2.png")),
					new Texture(Gdx.files.internal("idle-3-E2.png")),new Texture(Gdx.files.internal("idle-4-E2.png"))};
			
			Walk = new Texture[]{new Texture(Gdx.files.internal("walk-1-E2.png")),new Texture(Gdx.files.internal("walk-2-E2.png")),
					new Texture(Gdx.files.internal("walk-3-E2.png")),new Texture(Gdx.files.internal("walk-4-E2.png")),
					new Texture(Gdx.files.internal("walk-5-E2.png")),new Texture(Gdx.files.internal("walk-6-E2.png"))};
			
			attackTexture = new Texture[][]{new Texture[]{new Texture(Gdx.files.internal("attack-A1-E2.png")),new Texture(Gdx.files.internal("attack-A2-E2.png")),
					new Texture(Gdx.files.internal("attack-A3-E2.png")),new Texture(Gdx.files.internal("attack-A4-E2.png")),
					new Texture(Gdx.files.internal("attack-A5-E2.png")),new Texture(Gdx.files.internal("attack-A6-E2.png")),
					new Texture(Gdx.files.internal("attack-A7-E2.png")),new Texture(Gdx.files.internal("attack-A8-E2.png"))},
				new Texture[]{new Texture(Gdx.files.internal("attack-B1-E2.png")),new Texture(Gdx.files.internal("attack-B2-E2.png")),
						new Texture(Gdx.files.internal("attack-B3-E2.png")),new Texture(Gdx.files.internal("attack-B4-E2.png")),
						new Texture(Gdx.files.internal("attack-B5-E2.png")),new Texture(Gdx.files.internal("attack-B6-E2.png")),
						new Texture(Gdx.files.internal("attack-B7-E2.png"))}};
						
			deathTexture = new Texture[]{new Texture(Gdx.files.internal("dead-1-E2.png")),new Texture(Gdx.files.internal("dead-2-E2.png")),
					new Texture(Gdx.files.internal("dead-3-E3.png")),new Texture(Gdx.files.internal("dead-4-E4.png"))};
			
			
		}
		
		else if(ennemyClass == 4) {
			
			HP = 1000;
			currentSpeed = 1;
		
			Idle = new Texture[]{new Texture(Gdx.files.internal("idle-1-E4.png")),new Texture(Gdx.files.internal("idle-2-E4.png"))};
			
			Walk = new Texture[]{new Texture(Gdx.files.internal("walk-1-E4.png")),new Texture(Gdx.files.internal("walk-2-E4.png")),
					new Texture(Gdx.files.internal("walk-3-E4.png")),new Texture(Gdx.files.internal("walk-4-E4.png")),
					new Texture(Gdx.files.internal("walk-5-E4.png")),new Texture(Gdx.files.internal("walk-6-E4.png"))};
			
			attackTexture = new Texture[][]{new Texture[]{new Texture(Gdx.files.internal("attack-A1-E4.png")),new Texture(Gdx.files.internal("attack-A2-E4.png")),
					new Texture(Gdx.files.internal("attack-A3-E4.png")),new Texture(Gdx.files.internal("attack-A4-E4.png")),
					new Texture(Gdx.files.internal("attack-A5-E4.png")),new Texture(Gdx.files.internal("attack-A6-E4.png"))},
				new Texture[]{new Texture(Gdx.files.internal("attack-B1-E4.png")),new Texture(Gdx.files.internal("attack-B2-E4.png")),
						new Texture(Gdx.files.internal("attack-B3-E4.png")),new Texture(Gdx.files.internal("attack-B4-E4.png")),
						new Texture(Gdx.files.internal("attack-B5-E4.png")),new Texture(Gdx.files.internal("attack-B6-E4.png")),
						new Texture(Gdx.files.internal("attack-B7-E4.png")),new Texture(Gdx.files.internal("attack-B8-E4.png")),
						new Texture(Gdx.files.internal("attack-B9-E4.png"))}};
						
			deathTexture = new Texture[]{new Texture(Gdx.files.internal("dead-1-E4.png")),new Texture(Gdx.files.internal("dead-2-E4.png")),new Texture(Gdx.files.internal("dead-3-E4.png")),new Texture(Gdx.files.internal("dead-4-E4.png"))};
		}
		
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
	
	public Sprite getSprite() {
		return sprite;
	}


	void right() {
		xCoord+=this.currentSpeed;
		textureTick++;
		
		this.texture = Walk[((textureTick-1)/(2*this.currentSpeed))%6];
		this.sprite = new Sprite(this.texture);
		this.sprite.flip(this.sideIndicator, false);
		this.sprite.setPosition(xCoord, yCoord);
		this.sprite.setSize(this.textureWidth, this.textureHeight);
		
		// this.texture = 
		// else if textureTick != 0 num frame textureTickt+1%7 else num frame = 1
	}
	void left() {
		xCoord-=this.currentSpeed;
		textureTick++;
		
		this.texture = Walk[((textureTick-1)/(2*this.currentSpeed))%6];
		this.sprite = new Sprite(this.texture);
		this.sprite.flip(this.sideIndicator, false);
		this.sprite.setPosition(xCoord, yCoord);
		this.sprite.setSize(this.textureWidth, this.textureHeight);
	}
	
	void idle() {
		textureTick++;
		
		this.texture = Idle[((textureTick-1)/42)%4];
		this.sprite = new Sprite(this.texture);
		this.sprite.flip(this.sideIndicator, false);
		this.sprite.setPosition(xCoord, yCoord);
		this.sprite.setSize(this.textureWidth, this.textureHeight);
	}
	
	boolean pattern(Fighter player) {
		
		if(this.HP == 0) {
			
			ennemyDeath = true;
			textureTick = 0;
			this.HP = -1;
		}
		if(ennemyDeath) {
			
			textureTick++;
			this.texture = deathTexture[((textureTick-1)/(10*this.currentSpeed))%deathTexture.length];
			this.sprite = new Sprite(this.texture);
			this.sprite.flip(this.sideIndicator, false);
			this.sprite.setPosition(xCoord, yCoord);
			this.sprite.setSize(this.textureWidth, this.textureHeight);
			
			if(textureTick == 41) {
				return true;
			}
			return false;
			
		}
		
		if(attack == false) {
		
			if(Math.sqrt((player.getxCoord()-xCoord)*(player.getxCoord()-xCoord)+
					(player.getyCoord()-yCoord)*(player.getyCoord()-yCoord)) > (this.textureWidth)/2 -20 ){
        		
				if(playerDirection(player) == -1) {
					this.sideIndicator = true;
					left();
					return false;
				}
				else {
					this.sideIndicator = false;
					right();
					return false;
				}
        		
			}
			else {
				
				attack = true;
				textureTick = 0;
				if(playerDirection(player) == -1) {
					this.sideIndicator = true;
				}
				else {
					this.sideIndicator = false;
				}
				idle();
				return false;
			}
		}
		
		else {
			
			if(playerDirection(player) == -1) {
				this.attackDuration = 40;
				fight(2,player);
				return false;
			}
			else {
				this.attackDuration = 40;
				fight(1,player);
				return false;
			}
			
		}
		
	}
	
	
	int playerDirection(Fighter player) {
		
		if(player.getxCoord() - xCoord <= 0) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
	void fight(int currentAttack, Fighter player){
		
		if(textureTick == attackDuration-1) {
			
			attack = false;
			textureTick = 0;
			player.setHP(player.getHP()-50);
			
		}
		textureTick ++;
		
		this.texture = attackTexture[currentAttack-1][((textureTick-1)*(attackTexture[currentAttack-1].length-1)/(attackDuration-1))];
		this.sprite = new Sprite(this.texture);
		this.sprite.flip(this.sideIndicator, false);
		this.sprite.setPosition(xCoord, yCoord);
		this.sprite.setSize(this.textureWidth, this.textureHeight);
		
	}
	
	void reset() {
		
		this.HP = 1000; // initial HP
		this.currentSpeed = 1; // initial speed
		this.currentStrength = 1; // initial strength
		this.xCoord = 600;
		this.yCoord = 120;
		this.textureTick = 0;
		
	}
	
	
}



