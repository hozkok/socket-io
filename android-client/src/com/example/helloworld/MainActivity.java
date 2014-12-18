package com.example.helloworld;

import java.net.URISyntaxException;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.*;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
        
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        
    	int id = item.getItemId();
        
    	try {
    		System.out.println("in try...");
			final Socket socket = IO.socket("http://10.0.2.2:3000");
			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
				@Override
				public void call(Object... args) {
					Log.d("client", "Connected to server");
					socket.emit("client message", "Hello Android!");
				}
			}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
				
				@Override
				public void call(Object... args) {
					Log.d("client", "Disconnected from server");
				}
			}).on(Socket.EVENT_ERROR, new Emitter.Listener() {

				@Override
				public void call(Object... arg0) {
					Log.d("client", "An error is occured.");
					// TODO Auto-generated method stub
					
				}
				
			});
			socket.connect();
			Log.d("Socket", "Attempting to connect...");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    	
    	if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
