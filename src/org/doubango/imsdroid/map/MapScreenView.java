package org.doubango.imsdroid.map;

import org.doubango.imsdroid.XMPPSetting;
import org.doubango.imsdroid.Screens.BaseScreen;
import org.doubango.imsdroid.map.Game;
import org.doubango.imsdroid.map.GameView;
import org.doubango.imsdroid.map.MapList;
import org.doubango.imsdroid.map.NetworkStatus;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.doubango.imsdroid.IMSDroid;
import org.doubango.imsdroid.R;
import org.doubango.imsdroid.UartReceive;
import org.doubango.ngn.services.INgnSipService;

public class MapScreenView{
	
	private static String TAG = "Shinhua";
	
	private UartReceive uartRec;

	public static GameView gameView;		
	public static Game game;
	SendCmdToBoardAlgorithm sendCmdToBoardAlgorithm;
	

	//Encoder encoder;
	
	
	private NetworkStatus loggin;
	
	private String mName;	//For XMPP thread user name
	private String mPass;	//For XMPP thread user password
	
	private XMPPSetting XMPPSet;

	private ArrayAdapter<String> adapter;
	private ArrayAdapter<String> adapter2;
	
	
	Button navigationBtn, jsRunBtn, threadSTOP;
	
	
	
	public void MapScreenView(Activity v) {

		gameView = (GameView) v.findViewById(R.id.gameView1);
		sendCmdToBoardAlgorithm = new SendCmdToBoardAlgorithm();
		game = new Game();
		game.reloadMap(0,gameView);
		game.rec(v);
		
		threadSTOP = (Button) v.findViewById(R.id.ThreadSTOP);
		threadSTOP.setOnClickListener(
				new Button.OnClickListener(){
					public void onClick(View v) {
//						game.stopAlgorithm();
						Log.i("jamesdebug","0000000000000000000stop00000000000000000");
						sendCmdToBoardAlgorithm.RobotStop();
					}
			}
				
		);
		
		/* Navigation way display */
		navigationBtn = (Button) v.findViewById(R.id.navigation);
		navigationBtn.setOnClickListener(
        	new Button.OnClickListener(){
				public void onClick(View v) {
//					game.runAlgorithm();
					game.suckccc();
					//navigationBtn.setEnabled(false);
					//sendCmdToBoardAlgorithm.RobotStart(gameView, game, XMPPSet1);
					
//					synchronized (sendCmdToBoardAlgorithm) {
//						try {
//							//navigationBtn.setEnabled(false);
//							//setEnabled(false);
//							
//							sendCmdToBoardAlgorithm.RobotStart(gameView, game, XMPPSet);
//						
//							
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
					
				}
	        }
        );
		
		jsRunBtn = (Button) v.findViewById(R.id.runjs);
		jsRunBtn.setEnabled(false);

		
		initIoc();
	}
	

	

	public void signin(String name) {
		mName = name;
		mPass = "0000";

		loggin = NetworkStatus.getInstance();
		XMPPSet = new XMPPSetting();
		
	}
    
    public void initIoc(){
    	gameView.game = this.game;
    	game.gameView = this.gameView;
    	game.runButton = this.jsRunBtn;
    	game.goButton = this.navigationBtn;
    	sendCmdToBoardAlgorithm._gameView = this.gameView;
    	sendCmdToBoardAlgorithm._game = this.game;
    }
}
