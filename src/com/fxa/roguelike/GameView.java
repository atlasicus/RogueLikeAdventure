package com.fxa.roguelike;

import android.content.Context;
import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class GameView extends GLSurfaceView
{
	private Thread renderThread, updateThread;
	private GameRenderer renderer;
	
	private AssetManager assetManager;
	
	GestureDetector gestures;

	Game game;
	
	public GameView(Context context)
	{
		super(context);
		
		renderer = new GameRenderer();;
		
		assetManager = context.getAssets();
		game = new Game();
		
		gestures = new GestureDetector(this.getContext(), Input.actions);
		
		
	}
	
	public void stop()
	{
		
		game.setRunning(false);
		
		boolean complete = false;
		while(!complete)
		{
			try 
			{
				updateThread.join();
				complete = true;
			} 
			catch (Exception e) 
			{
				
			}
		}
	}

	public void resume()
	{
		setRenderer(renderer);
		updateThread = new Thread(game);
		updateThread.start();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		return gestures.onTouchEvent(event);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
}
