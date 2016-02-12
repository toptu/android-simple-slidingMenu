package com.toptu.example;

import com.toptu.slidingMenu.activity.BaseSlidMenuAcitivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends BaseSlidMenuAcitivity implements
		OnClickListener {

	/**
	 * Do not call the "setContentView" method, to call the "setMainView" method
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setMainView(R.layout.activity_main) ;
		
		TextView hello = (TextView) findViewById(R.id.hello) ;
		TextView world = (TextView) findViewById(R.id.world) ;
		
		hello.setOnClickListener(this) ;
		world.setOnClickListener(this) ;
	}

	/**
	 * Must override the "initMenuView" method Set your menu
	 */
	@Override
	protected View initMenuView(LayoutInflater inflater) {
		View menuView = inflater.inflate(R.layout.activity_menu, null);
		setMenuwidth(220, menuView);// Set menu width
		return menuView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.hello:

			toggleSlideMenu();// Switch menu
			break;

		case R.id.world:

			setSlidingMenuEnable(false);// Set menu to slide 
			break;
		}
	}
}
