package com.toptu.slidingMenu.activity;

import com.toptu.slidingMenu.R;
import com.toptu.slidingMenu.view.SlideMenu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public abstract class BaseSlideMenuFragmentActivity extends FragmentActivity {

	private SlideMenu slideMenu;
	private FrameLayout mainView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initView();
	}

	private void initView() {
		setContentView(R.layout.activity_sliding_menu);
		slideMenu = (SlideMenu) findViewById(R.id.slideMenu);
		mainView = (FrameLayout) findViewById(R.id.mainView);
		LayoutInflater inflater = getLayoutInflater();
		slideMenu.addView(initMenuView(inflater));
	}

	protected abstract View initMenuView(LayoutInflater inflater);

	protected void setMainView(int resource) {
		View homeView = View.inflate(getApplication(), resource, null);
		mainView.addView(homeView);
		View view = new View(getApplication()) ;//覆盖homeView
//		view.setBackgroundColor(Color.GRAY) ;
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slideMenu.hideMenu() ;
			}
		}) ;
		view.setVisibility(View.INVISIBLE) ;
		mainView.addView(view) ;
	}

	public void toggleSlideMenu() {
		if (slideMenu.isShowMenu()) {
			slideMenu.hideMenu();
		} else {
			slideMenu.showMenu();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if(slideMenu.isShowMenu() && keyCode == KeyEvent.KEYCODE_BACK){
			slideMenu.hideMenu() ;
			return true ;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	protected void setMenuwidth(int width ,View menuView){
		LayoutParams params = new LayoutParams(width, LayoutParams.MATCH_PARENT);
		menuView.setLayoutParams(params);
	}
}
