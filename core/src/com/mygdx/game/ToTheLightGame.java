package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.helpers.ImageManager;
import com.mygdx.helpers.InputHandler;
import com.mygdx.helpers.SoundManager;

public class ToTheLightGame extends Game {
	public SpriteBatch batch;



	ImageManager iM;
	SoundManager sM;
	InputHandler iH;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		iM = new ImageManager();
		iH = new InputHandler();
		this.setScreen(new MenuScreen(this));

	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

	}

	@Override
	public void dispose () {
		batch.dispose();
		Gdx.app.exit();

	}
}
