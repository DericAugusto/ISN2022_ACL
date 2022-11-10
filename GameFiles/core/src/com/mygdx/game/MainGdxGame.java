package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class MainGdxGame extends ApplicationAdapter {
    
	ShapeRenderer shapeRenderer;
	
	enum Screen{
        TITLE, MAIN_MainGdxGame, MainGdxGame_OVER;
    }
	
	SpriteBatch batch;
	BitmapFont font;
	
	Screen currentScreen = Screen.TITLE;

    Fighter player = new Fighter("Chad");

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown (int keyCode) {

                if(currentScreen == Screen.TITLE && keyCode == Input.Keys.SPACE){
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
        	
        	if(Gdx.input.isKeyPressed(Input.Keys.A)){
        		player.left();
        	}
        	else if(Gdx.input.isKeyPressed(Input.Keys.D)){
        		player.right();
        	}

        	Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        	
        	batch.begin();
        	font.draw(batch, "quit -> E", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .75f);
        	batch.end();

        	shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        	shapeRenderer.setColor(255, 255, 255, 1);
        	shapeRenderer.circle(player.locate()[0],player.locate()[1] , 10);
        	shapeRenderer.end();
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
    }
}
