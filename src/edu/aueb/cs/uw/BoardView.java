package edu.aueb.cs.uw;


import edu.aueb.cs.uw.core.GameEngine;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BoardView extends View{

	private static int BOARD_SIZE=10;
	private static final float MIN_FONT_DIPS = 14.0f;

	private Paint fillScorePaint;
    private Paint fillTrayPaint;
    private Paint strokePaint;
    private Paint tileStrokePaint;
    private Paint centralSquarePaint;
    private Paint scorePaint;
	private int defaultFontSize;
	private Dimensions dimensions;
	private Context mContext;
	private boolean isInitialized;
	private GameEngine ge;

	public BoardView( Context context, AttributeSet attrs ) 
    {
        super(context, attrs);
        isInitialized=false;
        mContext=context;
    }

	@Override
	protected void onDraw(android.graphics.Canvas canvas) 
	{
		initialise(mContext);
		drawTemplate(canvas);
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
		if(!isInitialized){			
			final float scale = getResources().getDisplayMetrics().density;
			int width=getWidth();
			int height=getHeight();
			defaultFontSize = (int)(MIN_FONT_DIPS * scale + 0.5f);
			fillScorePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
			fillScorePaint.setColor(Color.GRAY);
			fillTrayPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
			fillTrayPaint.setColor(Color.DKGRAY<<3);
			strokePaint=new Paint();
			strokePaint.setStyle(Paint.Style.STROKE);
			strokePaint.setColor(Color.WHITE);
			tileStrokePaint=new Paint();
			tileStrokePaint.setStyle(Paint.Style.STROKE);
			centralSquarePaint=new Paint();
			centralSquarePaint.setColor(Color.RED);
			scorePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
	   
			float curWidth = tileStrokePaint.getStrokeWidth();
			curWidth *= 2;
			if ( curWidth < 2 ) {
				curWidth = 2;
			}
			tileStrokePaint.setStrokeWidth(curWidth);
			dimensions=calculateDimensions(width, height);
			isInitialized=true;
			setFocusable(true);
		}
		
	}
	
	private void drawTemplate(Canvas canvas){
		canvas.drawColor(Color.DKGRAY);
		Rect scRect=new Rect(0,0, dimensions.getTotalWidth(),dimensions.getTotalHeight()-dimensions.getBoardheight()-dimensions.getTrayHeight());
		canvas.drawRect(scRect, fillScorePaint);
		Rect tRect=new Rect(0,dimensions.getScoreHeight()+dimensions.getBoardheight(), dimensions.getTotalWidth(),dimensions.getTotalHeight());
		canvas.drawRect(tRect, fillTrayPaint);
		canvas.drawRect(scRect, fillScorePaint);
		drawBoard(canvas);
		drawScore(canvas);
	}
	
	private void drawScore(Canvas canvas)
	{		
		if(ge==null) return;
		
		int turn=ge.getPlayerTurn();
		int NumOfPlayers=ge.getNumPlayers();
		
		int maxHeight=dimensions.getScoreHeight();
		float scoreWidth=dimensions.getTotalWidth()/NumOfPlayers;
		
		scorePaint.setTextAlign(Align.CENTER);
		
		for(int i=0;i<NumOfPlayers;i++){
			if(i==turn)
				scorePaint.setTextSize(1.3f*defaultFontSize);
			scorePaint.setColor(ge.getPlayer(i).getColor());
			canvas.drawText(ge.getPlayer(i).getNickname()+":"+ge.getPlayer(i).getScore(),i*scoreWidth+(scoreWidth/2) ,3*maxHeight/4, scorePaint);
			scorePaint.setTextSize(defaultFontSize);
		}

	}
	
	private void drawBoard(Canvas canvas)
	{
		int bHeight=dimensions.getBoardheight();
		
		int bWidth=bHeight;
		int sHeight=dimensions.getScoreHeight();
		int padding=dimensions.getPadding();
		
		int left=dimensions.getPadding()+(BOARD_SIZE/2-1)*dimensions.getCellSize();
		int top=dimensions.getScoreHeight()+(BOARD_SIZE/2-1)*dimensions.getCellSize();
		int bottom= 2*dimensions.getCellSize()+top;
		int right= 2*dimensions.getCellSize()+left;
		
		Rect centralRect=new Rect(left, top, right, bottom);
		canvas.drawRect(centralRect, centralSquarePaint);
		
		for(int i=0;i<11;i++)
		{
			int x=i*dimensions.getCellSize();
			canvas.drawLine(padding+x,sHeight,padding+x,sHeight+bHeight,strokePaint);
		}
		
		for(int i=0;i<11;i++)
		{
			int y=i*dimensions.getCellSize();
			canvas.drawLine(padding,sHeight+y,padding+bWidth,sHeight+y,strokePaint);
		}
		
	}

	private Dimensions calculateDimensions(int width,int height){
		int cellSize,padding,top,bWidth,bHeight,scHeight,tHeight;
		int maxCellSize;

		Dimensions dims=new Dimensions();
		dims.setTotalWidth(width);
		dims.setTotalHeight(height);		
		cellSize=width/BOARD_SIZE;
		maxCellSize=3*defaultFontSize;
		if(maxCellSize<cellSize)
			cellSize=maxCellSize;
		bWidth=BOARD_SIZE*cellSize;
		bHeight=bWidth;
		padding=(width-bWidth)/2;
		scHeight=defaultFontSize*2;
		tHeight=cellSize*3;

		if(height>=bWidth+scHeight+tHeight) {
			top=(height-bWidth-scHeight-tHeight)/2;
		}
		else {
			top=0;
			tHeight=4*defaultFontSize;
			if(height-bHeight-tHeight>tHeight){
				tHeight=height-bHeight-tHeight;
				scHeight=height-bHeight-tHeight;
			} else{
				scHeight=height-bHeight-tHeight;				
			}
		}
		dims.setCellSize(cellSize);
		dims.setBoardheight(bHeight);
		dims.setPadding(padding);
		dims.setScoreHeight(scHeight);
		dims.setTop(top);
		dims.setTrayHeight(tHeight);
		return dims;
	}

	public void setGameEngine(GameEngine ge) {
		this.ge=ge;
	}	
	
}