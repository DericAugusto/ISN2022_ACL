package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import java.util.ArrayList;
import com.badlogic.gdx.math.MathUtils;
import java.lang.Math;




public class MainGdxGame extends ApplicationAdapter {

	// Defining the game's elements   
	ShapeRenderer shapeRenderer;
	
	enum Screen{
    TITLE, MAIN_MainGdxGame, MainGdxGame_OVER, MAIN_GdxPause;
  }
	
	SpriteBatch batch;
	BitmapFont font;
	Texture currentTexture;
  Texture backgroundMenu;
  Texture backgroundGame;
  Texture backgroundPause;
  Texture backgroundEnd;
	Music music;
	
	Screen currentScreen = Screen.TITLE;
  Fighter player;
  
  int itempop = 0;
  int itemsnumber = 0;
  ArrayList<Item> Litems = new ArrayList<Item>();
  ArrayList<Item> Luseditems = new ArrayList<Item>();
  String[] randomItem = new String[] {"healthPotion","strengthPotion", "speedPotion","bomb","knife","monster","stone"};
    

  @Override
  public void create() {
    shapeRenderer = new ShapeRenderer();
    batch = new SpriteBatch();
    player = new Fighter("Chad"); // player has been created here because textures have to be created in the create section
    font = new BitmapFont();

    Gdx.input.setInputProcessor(new InputAdapter() {
      @Override
      public boolean keyDown (int keyCode) {

        if(currentScreen == Screen.TITLE && keyCode == Input.Keys.SPACE){
          currentScreen = Screen.MAIN_MainGdxGame;
        }
        else if(currentScreen == Screen.MAIN_MainGdxGame && keyCode == Input.Keys.S){
          currentScreen = Screen.MAIN_GdxPause;
        }

        else if (currentScreen == Screen.MAIN_GdxPause && keyCode == Input.Keys.R){
          currentScreen = Screen.MAIN_MainGdxGame;
        }
        else if(currentScreen == Screen.MainGdxGame_OVER && keyCode == Input.Keys.ENTER){
          currentScreen = Screen.TITLE;
        }
        else if(currentScreen == Screen.MAIN_MainGdxGame && keyCode == Input.Keys.E)
          currentScreen = Screen.MainGdxGame_OVER;
        return true;
      }
    });

    // Creating the game's sound
    music = Gdx.audio.newMusic(Gdx.files.internal("CrystalightDelver.mp3"));    
    music.setVolume(0.2f); // max volume in 1.0f
    music.setLooping(true);
    music.play();
    // Creating different background 
    backgroundMenu = new Texture(Gdx.files.internal("JeuLogo.png"));
    backgroundGame = new Texture(Gdx.files.internal("ground1.png"));
    backgroundPause = new Texture(Gdx.files.internal("pause.png"));
    backgroundEnd = new Texture(Gdx.files.internal("gameOver.png"));

  

  }

@Override
public void render() {
  
  // Defining opening screen characteristics
  if(currentScreen == Screen.TITLE){
    Gdx.gl.glClearColor(0, .25f, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    batch.begin();
    batch.draw(backgroundMenu, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    font.draw(batch, "V 1.0 !", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .75f);
    font.draw(batch, "Beat the monsters to win", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .5f);
    font.draw(batch, "Press space to play.", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .25f);
    font.draw(batch, "Press S to pause.", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .15f);
    font.draw(batch, "<--Q D-->", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .30f);
    batch.end();
  }
    	
  // Game's screen
  else if(currentScreen == Screen.MAIN_MainGdxGame) { 
    // Moving player commands
    if(Gdx.input.isKeyPressed(Input.Keys.A) & player.xCoord > 0){
      currentTexture = player.left();
    }
    else if(Gdx.input.isKeyPressed(Input.Keys.D) & player.xCoord < 600){
      currentTexture = player.right(); // --> depile une walking frame
    }
    else {
      currentTexture = player.stand();
      player.comp = 0;
    }
  if (player.HP <=0){
    currentScreen = Screen.MainGdxGame_OVER;
  }
    
    // Itens controller 
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
        		
      // Adding items effect to the player
      if(Math.sqrt((player.xCoord-currentItem.xCoord)*(player.xCoord-currentItem.xCoord)+
      (player.yCoord-currentItem.yCoord)*(player.yCoord-currentItem.yCoord)) < (player.textureWidth+currentItem.textureWidth)/2) {
        
        currentItem.effect(player);
        Litems.remove(i);
        Luseditems.add(currentItem);
      }
    }
        	
    // Defining effect time
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
        	
        		
    // ? 
    Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
    batch.begin();
    //batch.disableBlending();
    batch.draw(backgroundGame, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    font.draw(batch, "quit -> E", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .75f);
    font.draw(batch, "PAUSE -> S", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .80f);
    
    batch.draw(currentTexture, player.locate()[0], player.locate()[1],player.textureWidth, player.textureHeight);
    
    for (int i = 0; i < Litems.size(); i++) {
      batch.draw(Litems.get(i).ItemSkins[0], Litems.get(i).locate()[0], Litems.get(i).locate()[1],Litems.get(i).textureWidth, Litems.get(i).textureHeight); 
    }

          float HP = player.HP;
          int strength = player.currentStrength * 100;
          float speed = player.currentSpeed * 100;
          
          String hp_in_Text = Integer.toString((int) HP);
          String strength_in_Text = Integer.toString((int) strength);
          String speed_in_Text = Integer.toString((int) speed);

          font.draw(batch, "Heartpoint: " + hp_in_Text , Gdx.graphics.getWidth()*.30f, Gdx.graphics.getHeight() * .95f );
          font.draw(batch, "Strength: " + strength_in_Text + "%", Gdx.graphics.getWidth()*.30f, Gdx.graphics.getHeight() * .92f );
          font.draw(batch, "Speed: " + speed_in_Text + "%", Gdx.graphics.getWidth()*.30f, Gdx.graphics.getHeight() * .89f );
        	
    batch.end();
        	
    // HP bar
        	
        	shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        	shapeRenderer.setColor(255, 255, 255, 1);
        	shapeRenderer.rect(10,450, Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/200);
        	shapeRenderer.end();
        	
        	shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        	shapeRenderer.setColor(1, 0, 0, 1);
        	shapeRenderer.rect(10,451, ((player.HP)*(Gdx.graphics.getWidth()/4))/1000,Gdx.graphics.getHeight()/200-2);
        	shapeRenderer.end();
        	
        	// Stength bar
        	
        	shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(255, 255, 255, 1);
    shapeRenderer.rect(10,435, Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/200);
        	shapeRenderer.end();
        	
        	shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        	shapeRenderer.setColor(0, 1, 0, 1);
        	shapeRenderer.rect(10,435, ((player.currentStrength)*(Gdx.graphics.getWidth()/4))/4,Gdx.graphics.getHeight()/200-2);
    shapeRenderer.end();
        	        
  }

  else if(currentScreen == Screen.MAIN_GdxPause){

    batch.begin();
    batch.draw(backgroundPause, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    font.draw(batch, "Press R to restart.", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .25f);
    batch.end();
  }

  // Defining GameOver screen characteristics
  else if(currentScreen == Screen.MainGdxGame_OVER){
    Gdx.gl.glClearColor(.25f, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    batch.draw(backgroundEnd, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    font.draw(batch, "Press enter", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .25f);
    batch.end();
  }
}

  // ?
  @Override
  public void dispose() {
    shapeRenderer.dispose();
    batch.dispose();
    currentTexture.dispose();
    music.dispose();  
  }
}
