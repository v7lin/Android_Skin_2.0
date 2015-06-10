package com.v7lin.android.env.checked;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;

import com.v7lin.android.env.widget.CompatLinearLayout;

/**
 * 
 * 
 * @author v7lin Email:v7lin@qq.com
 */
public class CheckedLinearLayout extends CompatLinearLayout implements Checkable, CheckedConst {

	private boolean mChecked;

	public CheckedLinearLayout(Context context) {
		super(context);

		setup();
	}

	public CheckedLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);

		setup();
	}

	private void setup() {
	}

	@Override
	protected int[] onCreateDrawableState(int extraSpace) {
		final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
		if (isChecked()) {
			mergeDrawableStates(drawableState, CHECKED_STATE_SET);
		}
		return drawableState;
	}

	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();
	}

	@Override
	public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
		boolean populated = super.dispatchPopulateAccessibilityEvent(event);
		if (!populated) {
			event.setChecked(mChecked);
		}
		return populated;
	}

	@Override
	public void setChecked(boolean checked) {
		if (mChecked != checked) {
			mChecked = checked;
			refreshDrawableState();
		}
	}

	@Override
	public boolean isChecked() {
		return mChecked;
	}

	@Override
	public void toggle() {
		setChecked(!mChecked);
	}
}
