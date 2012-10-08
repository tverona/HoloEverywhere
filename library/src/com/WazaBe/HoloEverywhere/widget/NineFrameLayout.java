package com.WazaBe.HoloEverywhere.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.nineoldandroids.view.animation.AnimatorProxy;

public class NineFrameLayout extends FrameLayout {
	private final AnimatorProxy proxy;

	public NineFrameLayout(Context context) {
		super(context);
		proxy = AnimatorProxy.NEEDS_PROXY ? AnimatorProxy.wrap(this) : null;
	}

	public NineFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		proxy = AnimatorProxy.NEEDS_PROXY ? AnimatorProxy.wrap(this) : null;
	}

	public NineFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		proxy = AnimatorProxy.NEEDS_PROXY ? AnimatorProxy.wrap(this) : null;
	}

	@SuppressLint("NewApi")
	@Override
	public float getAlpha() {
		if (proxy != null) {
			return proxy.getAlpha();
		}
		return super.getAlpha();
	}

	@SuppressLint("NewApi")
	@Override
	public float getTranslationX() {
		if (proxy != null) {
			return proxy.getTranslationX();
		}
		return super.getTranslationX();
	}

	@SuppressLint("NewApi")
	@Override
	public float getTranslationY() {
		if (proxy != null) {
			return proxy.getTranslationY();
		}
		return super.getTranslationY();
	}

	@SuppressLint("NewApi")
	public void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);
	}

	@SuppressLint("NewApi")
	@Override
	public void setAlpha(float alpha) {
		if (proxy != null) {
			proxy.setAlpha(alpha);
		}
		super.setAlpha(alpha);
	}

	@SuppressLint("NewApi")
	@Override
	public void setTranslationX(float translationX) {
		if (proxy != null) {
			proxy.setTranslationX(translationX);
		}
		super.setTranslationX(translationX);
	}

	@SuppressLint("NewApi")
	@Override
	public void setTranslationY(float translationY) {
		if (proxy != null) {
			proxy.setTranslationY(translationY);
		}
		super.setTranslationY(translationY);
	}

	@Override
	public void setVisibility(int visibility) {
		if (proxy != null) {
			if (visibility == GONE) {
				clearAnimation();
			} else if (visibility == VISIBLE) {
				setAnimation(proxy);
			}
		}
		super.setVisibility(visibility);
	}
}
