package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import com.badlogic.gdx.math.MathUtils;
import java.lang.Math;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;



public class MainGdxGame extends ApplicationAdapter {
    
	ShapeRenderer shapeRenderer;
	Sound sound;
	Music music;
	boolean audio = true;
	float statsxCoord;
	OrthographicCamera camera;
	
	enum Screen{
        TITLE, MAIN_MainGdxGame,Next_Floor,Pause, MainGdxGame_OVER;
    }
	
	SpriteBatch batch;
	BitmapFont font;

	
	Screen currentScreen = Screen.TITLE;
	
	int floor = 1;
	Floor Menus;
    Fighter player;
    
    int itempop = 0;
    int itemsnumber = 0;
    ArrayList<Item> Litems = new ArrayList<Item>();
    ArrayList<Item> Luseditems = new ArrayList<Item>();
    String[] randomItem = new String[] {"healthPotion","strengthPotion", "speedPotion"};
    
    Ennemy[] ennemies = new Ennemy[3];
    ArrayList<Ennemy> Lennemies = new ArrayList<Ennemy>();
    
    Floor[] Lfloor = new Floor[3];
    
    
    @Override
    public void create() {
        
    	camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        camera.update();
    	
    	shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        
        player = new Fighter("Chad"); // player has been created here because textures have to be created in the create section
        Menus = new Floor(0);
        
        Lfloor[0] = new Floor(1);
        Lfloor[1] = new Floor(2);
        Lfloor[2] = new Floor(3);
        
        // all enemies have been created
        
        ennemies[0] = new Ennemy(1);  // contain all existing enemies x1
        ennemies[1] = new Ennemy(2);
        ennemies[2] = new Ennemy(4);
        
        font = new BitmapFont();
        
        
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown (int keyCode) {

                if(currentScreen == Screen.TITLE && keyCode == Input.Keys.SPACE){
                	
            		ennemies[0].reset();
            		Lennemies.add(ennemies[0]);
            		floor = 1;
            		player.reset();
                	
                    currentScreen = Screen.MAIN_MainGdxGame;
                }
                else if(currentScreen == Screen.MainGdxGame_OVER && keyCode == Input.Keys.ENTER){
                    currentScreen = Screen.TITLE;
                    audio = true;
                }
                else if(currentScreen == Screen.Next_Floor && keyCode == Input.Keys.SPACE) {
                	
                	if(floor < ennemies.length) {
                		floor++;
                		Lennemies.add(ennemies[floor-1]);
                		player.setxCoord(200);
                		player.setyCoord(80);
                		currentScreen = Screen.MAIN_MainGdxGame;
                	}
                }
                else if(currentScreen == Screen.Pause && keyCode == Input.Keys.R){
                    currentScreen = Screen.MAIN_MainGdxGame;
                }
                else if(currentScreen == Screen.MAIN_MainGdxGame && keyCode == Input.Keys.E) {
                	currentScreen = Screen.Pause;
                	sound = Gdx.audio.newSound(Gdx.files.internal("DM-CGS-16.mp3"));
                	sound.play();
                }
                return true;
            }
        });
    }

    @Override
    public void render() {
    	
    	if(currentScreen == Screen.TITLE){

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(Menus.getStartMenu(),camera.position.x-200,camera.position.y-240,400,480);
            batch.end();
            
            if(audio) {
            	audio = false;
            	music = Gdx.audio.newMusic(Gdx.files.internal("Final Struggle (Boss Theme).mp3"));
            	music.play();
            }
    	}
    	
        else if(currentScreen == Screen.MAIN_MainGdxGame) {
        	
        	music.stop();
        	
        	if(player.getHP() ==  0) {
        		
        		Lennemies.clear();
        		currentScreen = Screen.MainGdxGame_OVER;
        		
        	}
        	
        	else if(Gdx.input.isKeyPressed(Input.Keys.A) & player.isAttack() == false){
        		player.setSideIndicator(true);
        		player.walkL();
        		camera.translate(-1,0);
        	}
        	else if(Gdx.input.isKeyPressed(Input.Keys.D) & player.isAttack() == false){
        		player.setSideIndicator(false);
        		player.walkR();
        		camera.translate(1,0);
        	}
        	else if(Gdx.input.isKeyPressed(Input.Keys.Q) & player.isAttack() == false) {
        		player.setCurrentAttack(1);
        		player.setAttack(true);
        		player.setTextureTick(0);
        		player.setAttackDuration(26);
        		player.getSound().play();
        	}
        	else if(Gdx.input.isKeyPressed(Input.Keys.P) & player.isAttack() == false) {
        		player.setCurrentAttack(2);
        		player.setAttack(true);
        		player.setTextureTick(0);
        		player.setAttackDuration(31);
        	}
        	else if(Gdx.input.isKeyPressed(Input.Keys.X) & player.isAttack() == false & player.isShieldAbilities()) {// & floor == 2 
        		player.setCurrentAttack(3);
        		player.setAttack(true);
        		player.setShield(true);
        		player.setTextureTick(0);
        		player.setAttackDuration(15);
        	}
        	else if(player.isAttack()) {
        		player.fight();
        		player.setTextureTick(player.getTextureTick()+1);
        		if (player.getTextureTick() == player.getAttackDuration()) {
        			player.setAttack(false);
        			player.setyCoord(80); // after jump attack
        			
        			for(int i = 0; i < Lennemies.size(); i++) {
        				
        				if(player.reach(Lennemies.get(i)) & player.shield == false) {
        					player.hit(Lennemies.get(i));
        				}
        			}
        			player.setShield(false);
        		}
        	}
        	else if(player.isAttack() == false) {
        		player.idle();
        		
        	}
        	
        	
        	itempop ++;
        	
        	if (itempop == 90) { // spawn rate potions
        		itempop = 0;
        		itemsnumber +=1;
        		
        		Litems.add(new Item(randomItem[MathUtils.random(randomItem.length-1)]));
        	}
        		
        	for(int i = 0; i < Litems.size(); i++) {
        		
        		Item currentItem = Litems.get(i);
        		
        		if((currentItem).move()) {
        			Litems.remove(i);
        		}
        		
        		
        		if(Math.sqrt((player.xCoord-currentItem.xCoord)*(player.xCoord-currentItem.xCoord)+
        				(player.yCoord-currentItem.yCoord)*(player.yCoord-currentItem.yCoord)) < (player.textureWidth+currentItem.textureWidth)/2) {
        			
        			currentItem.effect(player);
        			currentItem.getSound().play();
        			
        			Litems.remove(i);
        			Luseditems.add(currentItem);
        		}
        	}
        	
        	for(int i = 0; i < Luseditems.size(); i++) {
        		
        		Item currentItem = Luseditems.get(i);
        		
        		if(currentItem.effectCountdown >= 1) {
        			currentItem.effectCountdown+=1;
        			
        			if(currentItem.effectCountdown >= 600) {
        				
        				currentItem.clearEffect(player);
        				Luseditems.remove(i);
        			}
        		}
        	}
        	
        	if(Lennemies.size() > 0) {
        		
        		camera.position.set(player.getxCoord(), player.getyCoord()+170,0);	
        		camera.update();
        		shapeRenderer.setProjectionMatrix(camera.combined);
        		batch.setProjectionMatrix(camera.combined);
        		
        		statsxCoord = player.getxCoord();
        	}
        	else {
        		
        		Lfloor[floor-1].rewardAppear(statsxCoord + 180);
        		
        		if(player.getxCoord() >= statsxCoord + 170 & Lfloor[floor-1].isChestB()) { 
        			
        			Lfloor[floor-1].setChestB(false);
        			sound = Gdx.audio.newSound(Gdx.files.internal("DM-CGS-18.mp3"));
                	sound.play();
                	Lfloor[floor-1].reward(player);
        		}
        		
        		if(player.getxCoord() >= statsxCoord + 300) { // just a functionality when kill an enemy
        			currentScreen = Screen.Next_Floor;
        			sound = Gdx.audio.newSound(Gdx.files.internal("DM-CGS-50.mp3"));
                	sound.play();
        		}
        	}
        	
        	Gdx.gl.glClearColor(0,0,0, 1);
        	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        	
        	batch.begin();
        	//batch.disableBlending();
        	
        	batch.draw(Lfloor[floor-1].getBackground(),-1000,20,640+1400,480+100);
        	
        	if(Lfloor[floor-1].isChestB() & Lennemies.size() == 0) {
        		
        		Lfloor[floor-1].getChestS().draw(batch); // si le joueur gagne le floor, reward visible
        	}
        	
        	player.getSprite().draw(batch);
        	
        	for(int i = 0; i < Lennemies.size(); i++) {
        		
        		if((Lennemies.get(i)).pattern(player)) {
        			Lennemies.remove(i);
        		}
        		else {
        			(Lennemies.get(i)).getSprite().draw(batch);
        		}
        		
        	}
        	
        	for (int i = 0; i < Litems.size(); i++) {
        		
            	batch.draw(Litems.get(i).ItemSkins[0], Litems.get(i).locate()[0], Litems.get(i).locate()[1],Litems.get(i).textureWidth, Litems.get(i).textureHeight);
            	
        	}
        	
        	batch.end();
        	
        	// HP bar
        	
        	shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        	shapeRenderer.setColor(255, 255, 255, 1);
        	shapeRenderer.rect(statsxCoord-300,470, Gdx.graphics.getWidth()/4 + (player.getBonusHP()/250)*Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/200);
        	shapeRenderer.end();
        	
        	shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        	shapeRenderer.setColor(0, 1, 0, 1);
        	shapeRenderer.rect(statsxCoord-300,471, ((player.getHP())*(Gdx.graphics.getWidth()/4))/1000,Gdx.graphics.getHeight()/200-2);
        	shapeRenderer.end();
        	
        	for(int i = 0; i < Lennemies.size(); i++) {
        		
        	
        		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        		shapeRenderer.setColor(255, 255, 255, 1);
        		shapeRenderer.rect((Lennemies.get(i)).getxCoord()+50,(Lennemies.get(i)).getyCoord()+85, Gdx.graphics.getWidth()/64,Gdx.graphics.getHeight()/200);
        		shapeRenderer.end();
        	
        		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        		shapeRenderer.setColor(1, 0, 0, 1);
        		shapeRenderer.rect((Lennemies.get(i)).getxCoord()+50,(Lennemies.get(i)).getyCoord()+86, (((Lennemies.get(i)).getHP())*(Gdx.graphics.getWidth()/64))/1000,Gdx.graphics.getHeight()/200-2);
        		shapeRenderer.end();
        	}
        	
        	// Stength bar
        	
        	shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        	shapeRenderer.setColor(255, 255, 255, 1);
        	shapeRenderer.rect(statsxCoord-300,460, Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/200);
        	shapeRenderer.end();
        	
        	shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        	shapeRenderer.setColor(0, 0, 1, 1);
        	shapeRenderer.rect(statsxCoord-300,461, ((player.getCurrentStrength())*(Gdx.graphics.getWidth()/4))/4,Gdx.graphics.getHeight()/200-2);
        	shapeRenderer.end();
        	
        	
        	
        	
        }
    	
        else if(currentScreen == Screen.Next_Floor) {
        	Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            batch.draw(Lfloor[floor-1].getFloorTexture(),camera.position.x-340,camera.position.y-100,500,200);
            batch.end();
        	
        }
    	
        else if(currentScreen == Screen.MainGdxGame_OVER){
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            batch.draw(Menus.getGameOverMenu(),camera.position.x-200,camera.position.y-220,400,480);
            batch.end();
        }
    	
        else if(currentScreen == Screen.Pause){
        	
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            batch.draw(Menus.getPauseMenu(),camera.position.x-200,camera.position.y-240,400,480);
            batch.end();
        }
    }

    @Override
    public void dispose() {
    	music.dispose();
        sound.dispose();
        shapeRenderer.dispose();
        batch.dispose();
    }
}
