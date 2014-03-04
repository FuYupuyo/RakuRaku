package own.example.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class PenView extends View {

	ArrayList<Path> draw_list = new ArrayList<Path>();
	private float posx = 0f;
	private float posy = 0f;
	private Path path = null;

	public PenView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(6);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeJoin(Paint.Join.ROUND);
		for (int i = 0; i < draw_list.size(); i++) {
			Path pt = draw_list.get(i);
			canvas.drawPath(pt, paint);
		}
		if (path != null)
			canvas.drawPath(path, paint);
	}

	public boolean onTouchEvent(MotionEvent e) {
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path = new Path();
			posx = e.getX();
			posy = e.getY();
			path.moveTo(e.getX(), e.getY());
			break;
		case MotionEvent.ACTION_MOVE:
			posx += (e.getX() - posx) / 1.4;
			posy += (e.getY() - posy) / 1.4;
			path.lineTo(posx, posy);
			break;
		case MotionEvent.ACTION_UP:
			path.lineTo(e.getX(), e.getY());
			draw_list.add(path);
			invalidate();
			break;
		default:
			break;
		}
		return true;
	}

}
