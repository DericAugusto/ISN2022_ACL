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



public class MainGdxGame extends ApplicationAdapter {
    
	ShapeRenderer shapeRenderer;
	float statsxCoord;
	OrthographicCamera camera;
	
	enum Screen{
        TITLE, MAIN_MainGdxGame,Next_Floor, MainGdxGame_OVER;
    }
	
	SpriteBatch batch;
	BitmapFont font;

	
	Screen currentScreen = Screen.TITLE;
	
	int floor = 1;

    Fighter player;
    
    int itempop = 0;
    int itemsnumber = 0;
    ArrayList<Item> Litems = new ArrayList<Item>();
    ArrayList<Item> Luseditems = new ArrayList<Item>();
    String[] randomItem = new String[] {"healthPotion","strengthPotion", "speedPotion"};
    
    Ennemy[] ennemies = new Ennemy[3];
    ArrayList<Ennemy> Lennemies = new ArrayList<Ennemy>();
    
    Floor[] Lfloor = new Floor[2];
    
    
    @Override
    public void create() {
        
    	camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        camera.update();
    	
    	shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        
        player = new Fighter("Chad"); // player has been created here because textures have to be created in the create section
        
        Lfloor[0] = new Floor(1);
        Lfloor[1] = new Floor(2);
        
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
                }
                else if(currentScreen == Screen.Next_Floor && keyCode == Input.Keys.SPACE) {
                	
                	if(floor < ennemies.length) {
                		floor++;
                		Lennemies.add(ennemies[floor-1]);
                		player.setxCoord(200);
                		player.setyCoord(100);
                		currentScreen = Screen.MAIN_MainGdxGame;
                	}
                }
                else if(currentScreen == Screen.MAIN_MainGdxGame && keyCode == Input.Keys.E)
                	currentScreen = Screen.MainGdxGame_OVER;
                return true;
            }
        });
    }

    @Override
    public void render() {
    	
    	if(currentScreen == Screen.TITLE){

            Gdx.gl.glClearColor(0, .25f, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            font.draw(batch, "V 1.0 !", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .75f);
            font.draw(batch, "Beat the monsters to win", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .5f);
            font.draw(batch, "Press space to play.", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .25f);
            font.draw(batch, "<--Q D-->", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .30f);
            batch.end();
    	}
    	
        else if(currentScreen == Screen.MAIN_MainGdxGame) {
        	
        	if(player.getHP() ==  0) {
        		
        		Lennemies.clear();
        		currentScreen = Screen.MainGdxGame_OVER;
        		
        	}
        	
        	else if(Gdx.input.isKeyPressed(Input.Keys.A) & player.getxCoord() > -600 & player.isAttack() == false){
        		player.setSideIndicator(true);
        		player.walkL();
        		camera.translate(-1,0);
        	}
        	else if(Gdx.input.isKeyPressed(Input.Keys.D) & player.getxCoord() < 600 & player.isAttack() == false){
        		player.setSideIndicator(false);
        		player.walkR();
        		camera.translate(1,0);
        	}
        	else if(Gdx.input.isKeyPressed(Input.Keys.Q) & player.isAttack() == false) {
        		player.setCurrentAttack(1);
        		player.setAttack(true);
        		player.setTextureTick(0);
        		player.setAttackDuration(26);
        	}
        	else if(Gdx.input.isKeyPressed(Input.Keys.P) & player.isAttack() == false) {
        		player.setCurrentAttack(2);
        		player.setAttack(true);
        		player.setTextureTick(0);
        		player.setAttackDuration(31);
        	}
        	else if(player.isAttack()) {
        		player.fight();
        		player.setTextureTick(player.getTextureTick()+1);
        		if (player.getTextureTick() == player.getAttackDuration()) {
        			player.setAttack(false);
        			player.setyCoord(100); // after jump attack
        			
        			for(int i = 0; i < Lennemies.size(); i++) {
        				
        				if(player.reach(Lennemies.get(i))) {
        					player.hit(Lennemies.get(i));
        				}
        			}
        		}
        	}
        	else if(player.isAttack() == false) {
        		player.idle();
        		
        	}
        	
        	
        	itempop ++;
        	
        	if (itempop == 30) {
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
        		
        		camera.position.set(player.getxCoord(), player.getyCoord()+150,0);	
        		camera.update();
        		shapeRenderer.setProjectionMatrix(camera.combined);
        		batch.setProjectionMatrix(camera.combined);
        		
        		statsxCoord = player.getxCoord();
        	}
        	else {
        		
        		if(player.getxCoord() >= statsxCoord + 300) { // just a fonctionnality when kill an ennemy
        			currentScreen = Screen.Next_Floor;
        		}
        	}
        	
        	Gdx.gl.glClearColor(0,0,0, 1);
        	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        	
        	batch.begin();
        	//batch.disableBlending();
        	
        	batch.draw(Lfloor[floor-1].getBackground1(),-1000,20,640+1400,480+100);
        	//batch.draw(Lfloor[floor-1].getBackground2(),-1000,20,640+1400,480+100);
        	batch.draw(Lfloor[floor-1].getBackground3(),-1000,20,640+1400,480+100);
        	
        	font.draw(batch, "quit -> E", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .75f);
        	
        	
        	
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
        	shapeRenderer.rect(statsxCoord-300,470, Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/200);
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
            font.draw(batch, "FLOOR ", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .75f);
            font.draw(batch, "press space to continue.", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .25f);
            batch.end();
        	
        }
    	
        else if(currentScreen == Screen.MainGdxGame_OVER){
            Gdx.gl.glClearColor(.25f, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            font.draw(batch, "END SCREEN", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .75f);
            font.draw(batch, "Press enter to restart.", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .25f);
            batch.end();
        }
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
    }
}
