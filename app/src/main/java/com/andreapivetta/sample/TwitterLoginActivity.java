package com.andreapivetta.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.andreapivetta.twitterloginview.TwitterLoginListener;
import com.andreapivetta.twitterloginview.TwitterLoginView;

import twitter4j.auth.AccessToken;

public class TwitterLoginActivity extends Activity implements TwitterLoginListener {

    public static final String CALLBACK_URL = "http://andreapivetta.altervista.org/";
    public static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
    public static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
    public static final String TWITTER_CONSUMER_KEY = "mGeER2Qoo0qDrqEnXz8z5uDI0";
    public static final String TWITTER_CONSUMER_SECRET = "vDhLRffzrNG348IShbsMeI74KD6Wi6tNzvqbPGA4vQTv7xGio5";

    private TwitterLoginView view;
    private boolean oauthStarted;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new TwitterLoginView(this);

        setContentView(view);

        oauthStarted = false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (oauthStarted)
            return;

        oauthStarted = true;

        view.start(TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET, CALLBACK_URL, this);
    }

    public void onSuccess(TwitterLoginView view, AccessToken accessToken) {
        PreferenceManager.getDefaultSharedPreferences(TwitterLoginActivity.this).edit().
                putString(PREF_KEY_OAUTH_TOKEN, accessToken.getToken()).
                putString(PREF_KEY_OAUTH_SECRET, accessToken.getTokenSecret()).
                putBoolean(getString(R.string.pref_key_login), true).
                apply();

        showMessage(getString(R.string.authorized_by, accessToken.getScreenName()));

        setResult(RESULT_OK, new Intent());
        finish();
    }

    public void onFailure(TwitterLoginView view, int result) {
        showMessage((getString(R.string.failed_due, result)));
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
