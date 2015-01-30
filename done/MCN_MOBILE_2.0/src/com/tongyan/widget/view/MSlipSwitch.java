package com.tongyan.widget.view;

import com.tongyan.activity.R;
import com.tongyan.common.entities._HolefaceSetting;
import com.tongyan.common.entities._HolefaceSettingRecord;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MSlipSwitch extends View implements OnTouchListener {
	
	//my object
	private _HolefaceSettingRecord mRecord;
	private _HolefaceSetting mSetting;
	
	//开关开启时的背景，关闭时的背景，滑动按钮
	private Bitmap switch_on_Bkg, switch_off_Bkg, slip_Btn;
	private Rect on_Rect, off_Rect;
	
	//是否正在滑动
	private boolean isSlipping = false;
	//当前开关状态，true为开启，false为关闭
	private boolean isSwitchOn = false;
	
	//手指按下时的水平坐标X，当前的水平坐标X
	private float previousX, currentX;
	
	//开关监听器
	private OnSwitchListener onSwitchListener;
	//是否设置了开关监听器
	private boolean isSwitchListenerOn = false;
	
	private String mTureText;
	private String mFalseText;
	private int mTextSize;
	
	public MSlipSwitch(Context context) {
		super(context);
		init();
	}
	
	
	public MSlipSwitch(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	
	private void init() {
		switch_on_Bkg = BitmapFactory.decodeResource(getResources(), R.drawable.p00_widget_switch_bluebround);
		switch_off_Bkg = BitmapFactory.decodeResource(getResources(), R.drawable.p00_widget_switch_bluebround);
		slip_Btn = BitmapFactory.decodeResource(getResources(), R.drawable.p00_widget_switch_off_on_btn);	
		
		//右半边Rect，即滑动按钮在右半边时表示开关开启
		on_Rect = new Rect(switch_off_Bkg.getWidth() - slip_Btn.getWidth(), 0, switch_off_Bkg.getWidth(), slip_Btn.getHeight());
		//左半边Rect，即滑动按钮在左半边时表示开关关闭
		off_Rect = new Rect(0, 0, slip_Btn.getWidth(), slip_Btn.getHeight());
		
		setOnTouchListener(this);
	}
	
	
	/*public void setImageResource(int switchOnBkg, int switchOffBkg, int slipBtn) {
		switch_on_Bkg = BitmapFactory.decodeResource(getResources(), switchOnBkg);
		switch_off_Bkg = BitmapFactory.decodeResource(getResources(), switchOffBkg);
		slip_Btn = BitmapFactory.decodeResource(getResources(), slipBtn);	
		
		//右半边Rect，即滑动按钮在右半边时表示开关开启
		on_Rect = new Rect(switch_off_Bkg.getWidth() - slip_Btn.getWidth(), 0, switch_off_Bkg.getWidth(), slip_Btn.getHeight());
		//左半边Rect，即滑动按钮在左半边时表示开关关闭
		off_Rect = new Rect(0, 0, slip_Btn.getWidth(), slip_Btn.getHeight());
	}*/
	
	
	public void setSwitchState(boolean switchState) {
		isSwitchOn = switchState;
	}
	
	
	protected boolean getSwitchState() {
		return isSwitchOn;
	}
	
	
	protected void updateSwitchState(boolean switchState) {
		isSwitchOn = switchState;
		invalidate();
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Matrix matrix = new Matrix();
		Paint paint = new Paint();
		//滑动按钮的左边坐标
		float left_SlipBtn;
		
		//手指滑动到左半边的时候表示开关为关闭状态，滑动到右半边的时候表示开关为开启状态
		if(currentX < (switch_on_Bkg.getWidth() / 2)) {
			canvas.drawBitmap(switch_off_Bkg, matrix, paint);
		} else {
			canvas.drawBitmap(switch_on_Bkg, matrix, paint);
		}
		if(mTextSize == 0) {
			mTextSize = 20;
		}
		paint.setTextSize(mTextSize);
		paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
		paint.setColor(Color.parseColor("#ffffff"));
		canvas.drawText(mTureText, switch_on_Bkg.getWidth()/5-2, (switch_on_Bkg.getHeight()/3)*2 - 2, paint);
		
		canvas.drawText(mFalseText, (switch_on_Bkg.getWidth()/4)*3-5, (switch_on_Bkg.getHeight()/3)*2 - 2, paint);
		//判断当前是否正在滑动
		if(isSlipping) {
			if(currentX > switch_on_Bkg.getWidth()) {
				left_SlipBtn = switch_on_Bkg.getWidth() - slip_Btn.getWidth();
			} else {
				left_SlipBtn = currentX - slip_Btn.getWidth() / 2;
			}
		} else {
			//根据当前的开关状态设置滑动按钮的位置
			if(isSwitchOn) {
				left_SlipBtn = on_Rect.left;
			} else {
				left_SlipBtn = off_Rect.left;
			}
		}
		
		//对滑动按钮的位置进行异常判断
		if(left_SlipBtn < 0) {
			left_SlipBtn = 0;
		} else if(left_SlipBtn > switch_on_Bkg.getWidth() - slip_Btn.getWidth()) {
			left_SlipBtn = switch_on_Bkg.getWidth() - slip_Btn.getWidth();
		}
		int top = (switch_off_Bkg.getHeight() - slip_Btn.getHeight())/2;
		canvas.drawBitmap(slip_Btn, left_SlipBtn, top-2, paint);
	}
	
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(switch_on_Bkg == null) {
			switch_on_Bkg = BitmapFactory.decodeResource(getResources(), R.drawable.p00_widget_switch_bluebround);
		}
		setMeasuredDimension(switch_on_Bkg.getWidth(), switch_on_Bkg.getHeight());
	}


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction()) {
		//滑动
		case MotionEvent.ACTION_MOVE:
			currentX = event.getX();
			break;
			
		//按下
		case MotionEvent.ACTION_DOWN:
			if(event.getX() > switch_on_Bkg.getWidth() || event.getY() > switch_on_Bkg.getHeight()) {
				return false;
			}
			
			isSlipping = true;
			previousX = event.getX();
			currentX = previousX;
			break;
		
		//松开
		case MotionEvent.ACTION_UP:
			isSlipping = false;
			//松开前开关的状态
			boolean previousSwitchState  = isSwitchOn;
			
			if(event.getX() >= (switch_on_Bkg.getWidth() / 2)) {
				isSwitchOn = true;
			} else {
				isSwitchOn = false;
			}
			
			//如果设置了监听器，则调用此方法
			if(isSwitchListenerOn && (previousSwitchState != isSwitchOn)) {
				onSwitchListener.onSwitched(this,isSwitchOn);
			}
			break;
		
		default:
			break;
		}
		
		//重新绘制控件
		invalidate();
		return true;
	}

 
	public void setOnSwitchListener(OnSwitchListener listener) {
		onSwitchListener = listener;
		isSwitchListenerOn = true;
	}
	
	
	public interface OnSwitchListener {
		abstract void onSwitched(View switchView,boolean isSwitchOn);
	}
	
	
	public String getmTureText() {
		return mTureText;
	}

	public void setmTureText(String mTureText) {
		this.mTureText = mTureText;
	}


	public void setmFalseText(String mFalseText) {
		this.mFalseText = mFalseText;
	}


	public String getmFalseText() {
		return mFalseText;
	}


	public void setmRecord(_HolefaceSettingRecord mRecord) {
		this.mRecord = mRecord;
	}


	public _HolefaceSettingRecord getmRecord() {
		return mRecord;
	}


	public void setmSetting(_HolefaceSetting mSetting) {
		this.mSetting = mSetting;
	}


	public _HolefaceSetting getmSetting() {
		return mSetting;
	}


	public void setmTextSize(int mTextSize) {
		this.mTextSize = mTextSize;
	}


	public int getmTextSize() {
		return mTextSize;
	}
}
