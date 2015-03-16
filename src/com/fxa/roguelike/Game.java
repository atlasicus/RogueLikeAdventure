package com.fxa.roguelike;

import android.graphics.Canvas;

public class Game implements Runnable
{
	Player player;
	
	private boolean running = true;
	
	public Game()
	{
		player = new Player(GameAssets.playerSpriteLocation);
	}
	
	public void draw(Canvas canvas)
	{
		player.draw(canvas);
	}
	
	public void update()
	{	
		player.update();
	}

	@Override
	public void run() 
	{
		while(running)
			this.update();
	}
	
	public void setRunning(boolean runningState)
	{
		this.running = runningState;
	}
}
