package com.pvr.crackit;

import java.util.ArrayList;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

@SuppressWarnings("deprecation")
public class ExamTab_GroupActivity extends ActivityGroup {

	// Keep this in a static variable to make it accessible for all the nested
	// activities, lets them manipulate the view
	public static ExamTab_GroupActivity group;

	// Need to keep track of the history if you want the back-button to work
	// properly, don't use this if your activities requires a lot of memory.
	public static ArrayList<View> history;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// save context of activity so in future is used to load 
		// other activity in same tab
		group = this;
		ExamTab_GroupActivity.history = new ArrayList<View>();
		// Start the root activity within the group and get its view
		
		View view = getLocalActivityManager().startActivity("FristActivity",new Intent(this, Exam_Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();

		// Replace the view of this ActivityGroup
		replaceView(view);

	}
	/**
	 * @author vishal
	 * @param View
	 * @return void 
	 * @use To maintain the history for directed view
	 */
	public void replaceView(View v) {
		// Adds the old one to history
		history.add(v);
		// Changes this Groups View to the new View.
		setContentView(v);
	}


	private void back() 
	{
		// if this is not last view in tab then remove
		// view from history and load previous view
		try {
			if (history.size() > 1) 
			{
				history.remove(history.size() - 1);
				setContentView(history.get(history.size() - 1));
			} 
			// if this is last view in tab then finish activity 
			// and exit
			else 
			{
				finish();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("EXCEPTION",e.toString());
		}
	}
	
	public void onBackPressed() {
		
		// when back button of device is pressed below function will call
		try {
			ExamTab_GroupActivity.group.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("EXCEPTION_FISRT",e.toString());
		}
		return;
	}
	
	// reset tabs by clear all views
	public void resetExamTab_GroupActivity(){
		//history.clear();
		
		//View view = getLocalActivityManager().startActivity("FristActivity",new Intent(this, Exam_Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		
		//replaceView(view);
		
		replaceView(history.get(0));
		
		for(int i=1; i < history.size(); i++ ){
			history.remove(i);
		}
	}
}