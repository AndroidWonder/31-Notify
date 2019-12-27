/*
 * Enter dinner bill and a 20% tip is calculated.
 * This example uses a listener implemented by the Activity class.
 * Another alternative is using an anonymous class.
 */
package com.course.example.notify;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Widgets extends Activity implements OnClickListener {

	private EditText mealpricefield;
	private TextView answerfield;
	private Button button;
	private static final String tag = "Widgets";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.widget);

		mealpricefield = (EditText) findViewById(R.id.mealprice);
		answerfield = (TextView) findViewById(R.id.answer);

		button = (Button) findViewById(R.id.calculate);
		button.setOnClickListener(this);

	}

	// Perform action on click
	public void onClick(View v) {
		try {

			Log.i(tag, "onClick invoked.");

			// grab the meal price from the UI
			String mealprice = mealpricefield.getText().toString();
			Log.i(tag, "mealprice is $" + mealprice);
			
			String answer;

			// check to see if the meal price includes a "$"
			if (mealprice.contains("$"))
					mealprice = mealprice.substring(1);


			float fmp = Float.parseFloat(mealprice);

			// let's give a nice tip -> 20%
			fmp = fmp * 1.2f;
			Log.i(tag, "Total Meal price is $" + fmp);
			
			// format our result
			answer = String.format("Full Price including tip is $%.2f", fmp);

			// display the answer
			answerfield.setText(answer);
			Log.i(tag, "onClick complete.");
		
		} catch (Exception e) {
			Log.e(tag, "Failed to Calculate Tip:" + e.getMessage());
			answerfield.setText("Failed to Calculate Tip:" + e.getMessage());
		}

	}
}
