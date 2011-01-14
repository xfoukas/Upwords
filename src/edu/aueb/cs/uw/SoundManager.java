package edu.aueb.cs.uw;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager 
{
	static private SoundManager i;
	
	public static SoundPool sp;
	private static HashMap sm;
	private static AudioManager am;
	private static Context c;
	private static boolean paused=false;
	private static int sId;

	private SoundManager()
	{
		
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
		sm=new HashMap();
		am=(AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
	}
	
	public static void addSound(int i,int sid)
	{
		sm.put(i,sp.load(c,sid,1));
	}
	
	public static void loadSounds()
	{
		sm.put(1,sp.load(c,/*name of sound file*/,1));
	}
	
	public static void playSound(int i,float s,int loop)
	{
		if(!paused)
		{
			float sv=am.getStreamVolume(AudioManager.STREAM_MUSIC);
	    	sv=sv/am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	    	sId=sp.play((int)((Integer)(sm.get(i))),sv,sv,1,loop,s);
		}
		else
		{
			sp.resume(sId);
		}
	}
	
	public static void pauseSound(int i)
	{
		//sp.pause((int)((Integer)(sm.get(i))));
		sp.pause(sId);
		paused=true;
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
