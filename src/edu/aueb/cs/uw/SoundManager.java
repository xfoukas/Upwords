package edu.aueb.cs.uw;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager 
{
	static private SoundManager i;
	
	public static SoundPool sp;
	private static HashMap<Integer,Integer> sm;
	private static AudioManager am;
	private static Context c;
	private static boolean mute;

	private SoundManager()
	{
		mute=false;
	}
	/*
	public static void soundOn()
	{
		mute=false;
	}*/
	
	public static void soundOff()
	{		
		mute=true;
	}
	
	static synchronized public SoundManager getInstance()
	{
		if(i==null)
		{
			i=new SoundManager();
		}
		
		return i;
	}
	
	public static void initSounds(Context cb)
	{
	    c=cb;
		sp=new SoundPool(4,AudioManager.STREAM_MUSIC,0);
		sm=new HashMap<Integer,Integer>();
		am=(AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
	}
	
	public static void addSound(int i,int sid)
	{
		sm.put(i,sp.load(c,sid,1));
	}
	
	public static void loadSounds()
	{
		sm.put(1,sp.load(c,R.raw.wwwsoundbytercomelectroniccminorarpeggio,1));
		sm.put(2,sp.load(c,R.raw.clickon, 1));
		sm.put(3,sp.load(c,R.raw.clickoff, 1));
	}
	
	public static void playSound(int i,float s,int loop)
	{
		if(!mute)
		{
			float sv=am.getStreamVolume(AudioManager.STREAM_MUSIC);
	    	sv=sv/am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	    	sp.play((int)((Integer)(sm.get(i))),sv,sv,1,loop,s);
		}			
	}
	
	public static void stopSound(int i)
	{
		if(!mute)
		{
			sp.stop(sm.get(i));
		}		
	}
	
	public static void cleanup()
	{
		sp.release();
		sp=null;
		sm.clear();
		am.unloadSoundEffects();
		i=null;
	}
}
