package com.v7lin.android.env.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.v7lin.android.env.EnvCallback;

/**
 * 
 * 
 * @author v7lin Email:v7lin@qq.com
 */
public class CompatRadioButton extends RadioButton implements EnvCallback {

	private EnvUIChanger<RadioButton> mEnvUIChanger;

	public CompatRadioButton(Context context) {
		this(context, null);
	}

	public CompatRadioButton(Context context, AttributeSet attrs) {
		this(context, attrs, com.android.internal.R.attr.radioButtonStyle);
	}

	public CompatRadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		mEnvUIChanger = new EnvCompoundButtonChanger<RadioButton>();
		mEnvUIChanger.applyStyle(context, attrs, defStyle, 0, false);
	}

	@Override
	public void scheduleSkin() {
		mEnvUIChanger.scheduleSkin(this);
	}

	@Override
	public void scheduleFont() {
		mEnvUIChanger.scheduleFont(this);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		mEnvUIChanger.scheduleSkin(this);
		mEnvUIChanger.scheduleFont(this);
	}
}