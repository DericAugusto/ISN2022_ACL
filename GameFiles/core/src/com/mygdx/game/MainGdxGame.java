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
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;
import com.badlogic.gdx.math.MathUtils;
import java.lang.Math;

public class MainGdxGame extends ApplicationAdapter {

  // Defining game's elements
  ShapeRenderer shapeRenderer;

  enum Screen {
    TITLE, MAIN_MainGdxGame, MainGdxGame_OVER;
  }

  SpriteBatch batch;
  BitmapFont font;
  Texture currentTexture;

  Music introMusic;
  Music gameplayMusic;
  Music walkingSound;
  Sound potionSound;
  Music gameOverSound;

  Screen currentScreen = Screen.TITLE;
  Fighter player;

  int itempop = 0;
  int itemsnumber = 0;
  ArrayList<Item> Litems = new ArrayList<Item>();
  ArrayList<Item> Luseditems = new ArrayList<Item>();
  String[] randomItem = new String[] { "healthPotion", "strengthPotion", 
  "speedPotion", "bomb", "knife", "monster", "stone" };
      

  @Override
  public void create() {
    shapeRenderer = new ShapeRenderer();
    batch = new SpriteBatch();
    player = new Fighter("Chad"); 
    // player has been created here because create section textures have
    // to be created in the...
    font = new BitmapFont();

    Gdx.input.setInputProcessor(new InputAdapter() {
      @Override
      public boolean keyDown(int keyCode) {

        if (currentScreen == Screen.TITLE && keyCode == Input.Keys.SPACE) {
          currentScreen = Screen.MAIN_MainGdxGame;
        } else if (currentScreen == Screen.MainGdxGame_OVER && keyCode == Input.Keys.ENTER) {
          currentScreen = Screen.TITLE;
        } else if (currentScreen == Screen.MAIN_MainGdxGame && keyCode == Input.Keys.E)
          currentScreen = Screen.MainGdxGame_OVER;
        return true;
      }
    });

    // Defining game's musics
    gameplayMusic = Gdx.audio.newMusic(Gdx.files.internal("CrystalightDelver.mp3"));
    gameplayMusic.setVolume(0.2f); // max volume in 1.0f
    gameplayMusic.setLooping(true);
    introMusic = Gdx.audio.newMusic(Gdx.files.internal("welcomsound.wav"));
    introMusic.setVolume(0.2f); // max volume in 1.0f
    introMusic.setLooping(true);

    // Defining game's sounds
    walkingSound = Gdx.audio.newMusic(Gdx.files.internal("mixkit-footsteps-in-woods-loop-533.wav"));
    walkingSound.setVolume(0.2f); // max volume in 1.0f
    walkingSound.setLooping(true);
    potionSound = Gdx.audio.newSound(Gdx.files.internal("arcade-bleep-sound-6071.mp3"));
    gameOverSound = Gdx.audio.newMusic(Gdx.files.internal("sacred-garden-10377.mp3"));
  }

  @Override
  public void render() {

    // Defining opening screen characteristics
    if (currentScreen == Screen.TITLE) {
      Gdx.gl.glClearColor(0, .25f, 0, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      batch.begin();
      font.draw(batch, "V 1.0 !", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
      font.draw(batch, "Beat the monsters to win", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f);
      font.draw(batch, "Press space to play.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
      font.draw(batch, "<--Q D-->", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .30f);
      batch.end();

      gameOverSound.stop();
      introMusic.play();
    }

    // Game's screen
    else if (currentScreen == Screen.MAIN_MainGdxGame) {

      // Gameplay musics
      introMusic.stop();
      gameOverSound.stop();
      gameplayMusic.play();

      // Moving player commands
      if (Gdx.input.isKeyPressed(Input.Keys.A)) {
        currentTexture = player.left();
        walkingSound.play();
      } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
        currentTexture = player.right(); // --> depile une walking frame
        walkingSound.play();
      } else {
        currentTexture = player.stand();
        player.comp = 0;
        walkingSound.pause();
      }

      // Itens controller
      itempop++;

      if (itempop == 30) {
        itempop = 0;
        itemsnumber += 1;
        Litems.add(new Item(randomItem[MathUtils.random(randomItem.length - 1)]));
      }

      for (int i = 0; i < Litems.size(); i++) {

        Item currentItem = Litems.get(i);

        if ((currentItem).move()) {
          Litems.remove(i);
        }

        // Adding items effect to the player
        if (Math.sqrt((player.xCoord - currentItem.xCoord) * 
        (player.xCoord - currentItem.xCoord) +
        (player.yCoord - currentItem.yCoord) *
        (player.yCoord - currentItem.yCoord)) < 
        (player.textureWidth + currentItem.textureWidth) / 2) {

          currentItem.effect(player);
          Litems.remove(i);
          Luseditems.add(currentItem);
          long potionSoundId = potionSound.play(0.5f);
          potionSound.setPitch(potionSoundId, 1);
        }
      }

      // Defining effect time
      for (int i = 0; i < Luseditems.size(); i++) {

        Item currentItem = Luseditems.get(i);

        if (currentItem.effectCountdown >= 1) {
          currentItem.effectCountdown += 1;

          if (currentItem.effectCountdown >= 300) {
            currentItem.clearEffect(player);
            Luseditems.remove(i);
          }
        }
      }

      // ?
      Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      batch.begin();
      font.draw(batch, "quit -> E", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);

      batch.draw(currentTexture, player.locate()[0], player.locate()[1], player.textureWidth, player.textureHeight);

      for (int i = 0; i < Litems.size(); i++) {
        batch.draw(Litems.get(i).ItemSkins[0], Litems.get(i).locate()[0], Litems.get(i).locate()[1],
        Litems.get(i).textureWidth, Litems.get(i).textureHeight);
      }

      batch.end();

    }

    // Defining GameOver screen characteristics
    else if (currentScreen == Screen.MainGdxGame_OVER) {
      Gdx.gl.glClearColor(.25f, 0, 0, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      batch.begin();
      font.draw(batch, "END SCREEN", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
      font.draw(batch, "Press enter to restart.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
      batch.end();

      // executes game over sound once
      gameplayMusic.stop();
      gameOverSound.play();
    }
  }

  // Dispose Method
  @Override
  public void dispose() {
    batch.dispose();
    currentTexture.dispose();
    gameplayMusic.dispose();
    walkingSound.dispose();
    potionSound.dispose();
    introMusic.dispose();
    gameOverSound.dispose();
  }
}
