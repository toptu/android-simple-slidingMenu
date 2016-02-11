package com.toptu.slidingMenu.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ScrollAnimation extends Animation{
	
	private View view ;
	private int distanceX ;
	private int startScrollX ;
	public ScrollAnimation(View view, int targetScrollX) {
		this.view = view ;
		startScrollX = view.getScrollX() ;
		distanceX = targetScrollX-startScrollX ;
		setDuration(Math.abs(distanceX)*2) ;
	}

	@Override
	protected void applyTransformation(float interpolatedTime,
			Transformation t) {
		super.applyTransformation(interpolatedTime, t);
		
		view.scrollTo(startScrollX+(int) (distanceX*interpolatedTime), 0) ;//根据相对(自己)位置移动
	}
}
