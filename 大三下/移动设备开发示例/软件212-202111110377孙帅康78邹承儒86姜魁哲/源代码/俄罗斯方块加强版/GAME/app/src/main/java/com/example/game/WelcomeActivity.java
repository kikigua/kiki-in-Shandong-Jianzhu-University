package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
	Button play_now;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_welcome);
		play_now = (Button) findViewById(R.id.play_now);

		play_now.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(WelcomeActivity.this, SelectActivity.class);

		startActivity(intent);
}}

