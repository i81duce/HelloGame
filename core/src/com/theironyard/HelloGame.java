package com.theironyard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelloGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	float x, y, xv, yv;

	static final float MAX_VELOCITY = 100; // this is a constant
	
	@Override
	public void create () { // used to initialize. main method in a sense. This is run once
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () { // where the magic happens!! This is run many many times
		move(); // calls the move() method

		Gdx.gl.glClearColor(1, 0, 0, 1); // clears last frame
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clears last frame
		batch.begin(); // prepares frame
		batch.draw(img, x, y); // passes frame to it
		batch.end(); // ends frame
	}

	float decelerate(float velocity) { //
		float deceleration = 0.99f; // the closer to 1, the slower the deceleration;
		velocity *= deceleration;
		if (Math.abs(yv) < 1) {
			velocity = 0;
		}
		return velocity;
	}

	void move() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) { // This takes input
			yv = MAX_VELOCITY;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = MAX_VELOCITY * -1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = MAX_VELOCITY * -1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_VELOCITY;
		}

		y += yv * Gdx.graphics.getDeltaTime(); // getting a sliver of Max Velocity
		x += xv * Gdx.graphics.getDeltaTime(); // getting a sliver of Max Velocity

		yv = decelerate(yv);
		xv = decelerate(xv);
	}
}