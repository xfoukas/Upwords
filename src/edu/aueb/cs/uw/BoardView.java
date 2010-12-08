package edu.aueb.cs.uw;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BoardView extends View{

	private static int BOARD_SIZE=10;
	private static final float MIN_FONT_DIPS = 14.0f;
	
	private Bitmap bmp;
	private Canvas m_canvas;
	private Paint drawPaint;
    private Paint fillPaint;
    private Paint strokePaint;
    private Paint tileStrokePaint;
	private Rect tileRect;
	private int defaultFontSize;
	private Dimensions dimensions;
	private Context mContext;
	private boolean alreadyDrawn;
	
	
	public BoardView( Context context, AttributeSet attrs ) 
    {
        super( context, attrs );
        alreadyDrawn=false;
        initialise( context );
        mContext=context;
    }
	
	@Override
	protected void onDraw(android.graphics.Canvas canvas) 
	{
		createLayout();
		
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int evt=event.getAction();
		switch(evt){
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			break;
		default:
			break;	
		}
		return true;
	}
	
	private void initialise(Context context) {
		final float scale = getResources().getDisplayMetrics().density;
		defaultFontSize = (int)(MIN_FONT_DIPS * scale + 0.5f);
		drawPaint=new Paint();
		fillPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
		strokePaint=new Paint();
		strokePaint.setStyle(Paint.Style.STROKE);
		tileStrokePaint=new Paint();
		tileStrokePaint.setStyle(Paint.Style.STROKE);
		float curWidth = tileStrokePaint.getStrokeWidth();
        curWidth *= 2;
        if ( curWidth < 2 ) {
            curWidth = 2;
        }
        tileStrokePaint.setStrokeWidth( curWidth );
		setFocusable(true);
		
	}
	
	
	private void createLayout()
	{
		if(!alreadyDrawn){
			int width=getWidth();
			int height=getHeight();
			dimensions=calculateDimensions(width, height);
			bmp=Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			m_canvas=new Canvas(bmp);
			Resources res = getResources();
			Drawable d = res.getDrawable(R.drawable.icon);
			d.setBounds(0, 0, width-1, height-1);
			d.draw(m_canvas);
			drawBoard();
		}
	}
	
	private void drawBoard() {
		
	}

	private Dimensions calculateDimensions(int width,int height){
		int cellSize,padding,top,bWidth,bHeight,scHeight,tHeight;
		int maxCellSize,scrollCells;
		
		Dimensions dims=new Dimensions();
		dims.setTotalWidth(width);
		dims.setTotalHeight(height);		
		cellSize=width/BOARD_SIZE;
		maxCellSize=3*defaultFontSize;
		if(maxCellSize<cellSize)
			cellSize=maxCellSize;
		bWidth=BOARD_SIZE*cellSize;
		bHeight=bWidth;
		padding=(BOARD_SIZE*maxCellSize-bWidth)/2;
		scHeight=defaultFontSize*2;
		tHeight=cellSize*3;
		if(height>bWidth+scHeight+tHeight)
			top=(height-bWidth-scHeight-tHeight)/2;
		else {
			top=0;
			tHeight=3*defaultFontSize;
			int remHeight=height-tHeight-scHeight;
			scrollCells=BOARD_SIZE-(remHeight/cellSize);
			tHeight=height-scHeight-((BOARD_SIZE-scrollCells)*cellSize);
		}
		dims.setCellSize(cellSize);
		dims.setBoardheight(bHeight);
		dims.setPadding(padding);
		dims.setScoreHeight(scHeight);
		dims.setTop(top);
		dims.setTrayHeight(tHeight);
		return dims;
	}
	
}
