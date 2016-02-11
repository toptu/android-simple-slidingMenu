package com.toptu.example;

import com.toptu.slidingMenu.activity.BaseSlidMenuAcitivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends BaseSlidMenuAcitivity {

	/**
	 * Do not call the "setContentView" method, to call the "setMainView" method
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 
		setMainView(R.layout.activity_main) ;
	}

	/**
	 * Must override the "initMenuView" method
	 * Set your menu
	 */
	@Override
	protected View initMenuView(LayoutInflater inflater) {
		View menuView = inflater.inflate(R.layout.activity_menu,null ) ;
		setMenuwidth(220,menuView) ;// Set menu width
		return menuView;
	}
}
