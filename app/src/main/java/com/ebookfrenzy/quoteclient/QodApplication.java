package com.ebookfrenzy.quoteclient;

import android.app.Application;
import com.ebookfrenzy.quoteclient.service.GoogleSignInService;

public class QodApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setContext(this);
  }
}
