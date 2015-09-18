package com.andreapivetta.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOGIN = 0;

    private SharedPreferences mSharedPreferences;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        textView = (TextView) findViewById(R.id.helloTextView);

        if (true || !isTwitterLoggedInAlready()) {
            startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), REQUEST_LOGIN);
        } else {
            textView.setText("Logged");
        }
    }

    private boolean isTwitterLoggedInAlready() {
        return mSharedPreferences.getBoolean(getString(R.string.pref_key_login), false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {
                textView.setText("Logged");
            } else {
                finish();
            }
        }
    }
}
