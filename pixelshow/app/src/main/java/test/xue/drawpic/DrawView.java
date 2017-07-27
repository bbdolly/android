package test.xue.drawpic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/21.
 */
public class DrawView extends View {
    Paint mPaint1 = new Paint();
    Paint mPaint2 = new Paint();

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public DrawView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint1.setColor(Color.BLACK);
        mPaint1.setAntiAlias(false);
        mPaint1.setFilterBitmap(false);
        mPaint2.setColor(Color.WHITE);
        mPaint2.setAntiAlias(false);
        mPaint2.setFilterBitmap(false);

        int x,y;
        for(y=0;y<1000;y++)
            for(x=0;x<1000;x++)
            {
                if((x+y)%2 == 0)
                {
                    canvas.drawPoint(x,y,mPaint1);
                }
                else
                {
                    canvas.drawPoint(x,y,mPaint2);
                }
            }
    }
}
