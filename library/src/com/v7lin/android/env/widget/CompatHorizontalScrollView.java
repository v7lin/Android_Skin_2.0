package com.v7lin.android.env.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import com.v7lin.android.env.EnvCallback;

/**
 * 
 * 
 * @author v7lin E-mail:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class CompatHorizontalScrollView extends HorizontalScrollView implements XFrameLayoutCall, EnvCallback {

	private static final boolean ALLOW_SYSRES = false;

	private EnvUIChanger<FrameLayout, XFrameLayoutCall> mEnvUIChanger;

	public CompatHorizontalScrollView(Context context) {
		this(context, null);
	}

	public CompatHorizontalScrollView(Context context, AttributeSet attrs) {
//		this(context, attrs, com.android.internal.R.attr.horizontalScrollViewStyle);
		this(context, attrs, InternalTransfer.transferAttr(context, "horizontalScrollViewStyle"));
	}

	public CompatHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		mEnvUIChanger = new EnvFrameLayoutChanger<FrameLayout, XFrameLayoutCall>(context);
		mEnvUIChanger.applyStyle(context, attrs, defStyle, 0, ALLOW_SYSRES, isInEditMode());
	}

	@Override
	public void setForeground(Drawable d) {
		super.setForeground(d);

		applyAttrForeground(0);
	}

	private void applyAttrForeground(int resid) {
		applyAttr(getContext(), android.R.attr.foreground, resid);
	}

	@Override
	public void setBackgroundColor(int color) {
		super.setBackgroundColor(color);

		applyAttrBackground(0);
	}

	@Override
	public void setBackgroundResource(int resid) {
		super.setBackgroundResource(resid);

		applyAttrBackground(resid);
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void setBackground(Drawable background) {
		super.setBackground(background);

		applyAttrBackground(0);
	}

	@Override
	public void setBackgroundDrawable(Drawable background) {
		super.setBackgroundDrawable(background);

		applyAttrBackground(0);
	}

	private void applyAttrBackground(int resid) {
		applyAttr(getContext(), android.R.attr.background, resid);
	}

	private void applyAttr(Context context, int attr, int resid) {
		if (mEnvUIChanger != null) {
			mEnvUIChanger.applyAttr(context, attr, resid, ALLOW_SYSRES, isInEditMode());
		}
	}

	@Override
	public void scheduleForeground(Drawable d) {
		super.setForeground(d);
	}

	@Override
	public void scheduleBackgroundDrawable(Drawable background) {
		super.setBackgroundDrawable(background);
	}

	@Override
	public void scheduleSkin() {
		if (mEnvUIChanger != null) {
			mEnvUIChanger.scheduleSkin(this, this, isInEditMode());
		}
	}

	@Override
	public void scheduleFont() {
		if (mEnvUIChanger != null) {
			mEnvUIChanger.scheduleFont(this, this, isInEditMode());
		}
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		if (mEnvUIChanger != null) {
			mEnvUIChanger.scheduleSkin(this, this, isInEditMode());
			mEnvUIChanger.scheduleFont(this, this, isInEditMode());
		}
	}
}
