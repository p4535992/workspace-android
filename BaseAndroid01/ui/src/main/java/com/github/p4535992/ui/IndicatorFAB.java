package com.github.p4535992.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * Custom FAB with the text inside
 */
public class IndicatorFAB extends FloatingActionButton {

  /**
   * The Tag for this class
   */
  private static final String TAG = IndicatorFAB.class.getSimpleName();

  /**
   * The Text to show in this class
   */
  private String mText;

  /**
   * The Paint to use for text
   */
  private static Paint sTextPaint;

  public IndicatorFAB(Context context) {
    super(context);
    init(context);
  }

  public IndicatorFAB(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public IndicatorFAB(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  public void setText(final String text) {
    this.mText = text;
    invalidate();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (!TextUtils.isEmpty(mText)) {
      Rect result = new Rect();
      sTextPaint.getTextBounds(mText, 0, mText.length(), result);
      final float textX = -result.left + (getWidth() - result.width()) / 2;
      final float textY = -result.top + (getHeight() - result.height()) / 2;
      canvas.drawText(mText, textX, textY, sTextPaint);
    }
  }


  /**
   * Initialization of the component
   *
   * @param context The Context
   */
  private void init(final Context context) {
    if (sTextPaint == null) {
      sTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
      sTextPaint.setTextSize(getResources().getDimension(R.dimen.indicator_text_size));
      sTextPaint.setColor(Color.WHITE);
      sTextPaint.setFakeBoldText(true);
    }
    if (isInEditMode()) {
      mText = "AB";
    }
  }
}
