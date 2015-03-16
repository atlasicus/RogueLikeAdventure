package com.fxa.roguelike;

import android.os.SystemClock;
import android.util.Log;

public class SystemParams 
{
	public static int screenHeight;
	public static int screenWidth;
	
	public static int tileSize = 128;
	
	public static long framesPerSecond = 0;
	public static long timeDelta = 0;

    private static long lastTime   = SystemClock.elapsedRealtime();
    private static long currentTime = SystemClock.elapsedRealtime();
	
    private static long timerCur = SystemClock.elapsedRealtime();
    private static long timerLast = SystemClock.elapsedRealtime();
    
    private static boolean resizeCalled = false;
    
	public static void CalcFramesPerSecond()
	{
		timerCur = SystemClock.elapsedRealtime();
		
		lastTime = currentTime;
        currentTime = SystemClock.elapsedRealtime();
        
        timeDelta = currentTime - lastTime;
        
        if(timeDelta > 0)
        	framesPerSecond = 1000 / timeDelta;

        if( timerCur - timerLast > 3000 )
        {
        	timerLast = timerCur;
            Log.i("FPS", "FPS: " + framesPerSecond);
        }
	}
	
	static void resizeTiles()
	{
		if(screenWidth < 800 && screenWidth > 0 && !resizeCalled)
		{
			Log.i("Game Info", "Game Screen Size X: " + SystemParams.screenWidth + "\nGame Screen Size Y: " + SystemParams.screenHeight);
			tileSize = 32;
			resizeCalled = true;
		}
		else
		{
			tileSize = 128;
		}
	}
	
	static void setScreenDimensions(int x, int y)
	{
		screenHeight = y;
		screenWidth = x;
	}
}
