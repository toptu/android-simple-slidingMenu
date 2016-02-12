package com.toptu.slidingMenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class SlideMenu extends FrameLayout {

	private ViewGroup mainView;
	private View menuView;
	private int downX;
	private int menuViewWidth;
	private boolean isShowMenu = false;
	private Scroller scroller;
	private View view;
	private boolean enable = true;

	public SlideMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SlideMenu(Context context) {
		super(context);
		init();
	}

	private void init() {
		// 使用scroller做平滑
		scroller = new Scroller(getContext());
	}

	// @Override
	// protected void onFinishInflate() {
	// 一级子类系统填充完成时调用（自己动态填充一级子类不可以）
	// super.onFinishInflate();
	// mainView = getChildAt(0);
	// menuView = getChildAt(1);
	// }

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		mainView = (ViewGroup) getChildAt(0);
		menuView = getChildAt(1);
		view = mainView.getChildAt(1);// 覆盖homeView的view
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// mainView.measure(widthMeasureSpec, heightMeasureSpec);
		// int measureSpec = MeasureSpec.makeMeasureSpec(
		// menuView.getLayoutParams().width, MeasureSpec.EXACTLY);
		// menuView.measure(measureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		mainView.layout(0, 0, r, b);
		menuViewWidth = menuView.getMeasuredWidth();
		menuView.layout(-menuViewWidth, 0, 0, b);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (enable) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:

				downX = (int) event.getRawX();

				break;
			case MotionEvent.ACTION_MOVE:

				int moveX = (int) event.getRawX();// 当前移动到的点
				int differenceX = moveX - downX;// 此次移动的差值

				int newScrollX = getScrollX() - differenceX;
				// 设置移动的边界
				if (newScrollX < -menuViewWidth) {
					newScrollX = -menuViewWidth;
				} else if (newScrollX > 0) {
					newScrollX = 0;
				}
				scrollTo(newScrollX, 0);
				downX = moveX;

				break;
			case MotionEvent.ACTION_UP:

				if (getScrollX() < -menuViewWidth / 2) {
					// 显示
					showMenu();
				} else {
					// 不显示
					hideMenu();
				}
				break;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if (isShowMenu) {
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				downX = (int) ev.getRawX();
				break;
			case MotionEvent.ACTION_MOVE:
				int distanceX = (int) (ev.getRawX() - downX);
				if (Math.abs(distanceX) > 11) {
					downX = downX + distanceX;
					return true;
				}
				break;
			case MotionEvent.ACTION_UP:

				break;
			}
		}
		return false;
	}

	public void hideMenu() {
		isShowMenu = false;
		view.setVisibility(View.INVISIBLE);
		// 使用自定义动画做平滑(太卡)
		// ScrollAnimation hideAnimation = new ScrollAnimation(this, 0) ;
		// startAnimation(hideAnimation) ;

		// 使用scroller做平滑
		int distanceX = 0 - getScrollX();
		scroller.startScroll(getScrollX(), 0, distanceX, 0,
				Math.abs(distanceX) * 2);
		invalidate();
	}

	public void showMenu() {
		isShowMenu = true;
		view.setVisibility(View.VISIBLE);
		// 使用自定义动画做平滑(太卡)
		// ScrollAnimation hideAnimation = new ScrollAnimation(this,
		// -menuViewWidth) ;
		// startAnimation(hideAnimation) ;

		// 使用scroller做平滑
		int distanceX = -menuViewWidth - getScrollX();
		scroller.startScroll(getScrollX(), 0, distanceX, 0,
				Math.abs(distanceX) * 2);
		invalidate();
	}

	public boolean isShowMenu() {
		return isShowMenu;
	}

	// 使用scroller做平滑
	@Override
	public void computeScroll() {
		super.computeScroll();
		if (scroller.computeScrollOffset()) {// 返回true,表示动画没结束
			scrollTo(scroller.getCurrX(), 0);
			invalidate();
		}
	}

	public void notEnable() {
		enable = false;
		if(isShowMenu)
			hideMenu() ;
	}
	public void openEnable() {
		enable = true ;
	}

}
