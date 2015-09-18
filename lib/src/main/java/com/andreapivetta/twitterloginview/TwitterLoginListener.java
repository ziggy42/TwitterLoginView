package com.andreapivetta.twitterloginview;

import twitter4j.auth.AccessToken;

public interface TwitterLoginListener {

    void onSuccess(TwitterLoginView view, AccessToken accessToken);

    void onFailure(TwitterLoginView view, int result);

}
