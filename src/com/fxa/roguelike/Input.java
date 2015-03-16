package com.fxa.roguelike;

import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

public class Input
{
	public static Gestures actions = new Input.Gestures();
	
	private static Vector2 pos = Vector2.Zero;
	
	public static boolean isTouched = false;
	
	public static Vector2 touchLocation()
	{
//		Log.i("GestureEvent", "Touch status: " + isTouched);
		return pos;
	}
	
	public static void consumeTouch()
	{
		isTouched = false;
	}
	
	public static class Gestures implements OnGestureListener
	{
		@Override
		public boolean onDown(MotionEvent event) 
		{
			pos.x = (int)event.getX();
			pos.y = (int)event.getY();
			isTouched = true;
//			Log.i("GestureEvent", "Action Touch");
			return isTouched;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) 
		{
			Log.i("GestureEvent", "Action Fling");
			return true;
		}

		@Override
		public void onLongPress(MotionEvent event) 
		{
			
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) 
		{
			return true;
		}

		@Override
		public void onShowPress(MotionEvent event) 
		{
			
		}

		@Override
		public boolean onSingleTapUp(MotionEvent event) 
		{
			isTouched = false;
//			Log.i("GestureEvent", "Action Touch up");
			return isTouched;
		}
	}
}
