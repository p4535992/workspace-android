package com.github.p4535992.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * This is a simple component that adds an arrow to the left of the text
 */
public class DestinationTextView extends TextView {

  /**
   * The offset we apply to the text
   */
  private static float sOffset;

  /**
   * The Paint we use to draw the image
   */
  private static Paint sPaint;

  /**
   * The Path for the arrow
   */
  private static Path sPath;

  public DestinationTextView(Context context) {
    super(context);
    init(context);
  }

  public DestinationTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public DestinationTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }


  @Override
  protected void onDraw(Canvas canvas) {
    if (sPath == null) {
      final float size = getResources().getDimension(R.dimen.bus_stop_destination_icon_size);
      // We create the Path for the arrow
      final float offset = (getHeight() - size) / 2;
      sPath = new Path();
      sPath.moveTo(0, offset);
      sPath.lineTo(0, size + offset);
      sPath.lineTo(size, offset + size / 2);
      sPath.close();
    }
    canvas.save();
    canvas.translate(sOffset, 0);
    super.onDraw(canvas);
    canvas.translate(-sOffset, 0);
    canvas.drawPath(sPath, sPaint);
    canvas.restore();
  }

  private void init(final Context context) {
    if (sPaint == null) {
      sOffset = getResources().getDimension(R.dimen.bus_stop_destination_offset);
      sPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
      sPaint.setStyle(Paint.Style.FILL);
      sPaint.setColor(Color.BLUE);
    }
  }
}
