package com.fxa.roguelike;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;

public class Starter extends Activity
{
	GameView gameView;
	int buildVersion;

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        buildVersion = Build.VERSION.SDK_INT;
        
        Log.i("MainActivity", "System start");
        if(buildVersion > Build.VERSION_CODES.HONEYCOMB)
        {
        	this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_FULLSCREEN);
        	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        }
        gameView = new GameView(getApplication());
        getScreenSize();
        
        Log.i("Screen Dimensions", "Screen X: " + SystemParams.screenWidth + " Screen Y: " + SystemParams.screenHeight);
        setContentView(gameView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_starter, menu);
        return true;
    }
    
    @Override
    protected void onResume()
    {
    	super.onResume();
    	gameView.onResume();
    }
    @Override
    protected void onPause()
    {
    	super.onPause();
    	gameView.onPause();
    }
    
    private void getScreenSize()
    {
    	Display display = getWindowManager().getDefaultDisplay();
    	Point size = new Point();
    	if(buildVersion > Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    		display.getSize(size);
    	else
    	{
    		size.x = display.getWidth();
    		size.y = display.getHeight();
    	}
    			
    	
    	SystemParams.setScreenDimensions(size.x, size.y);
    }
}
