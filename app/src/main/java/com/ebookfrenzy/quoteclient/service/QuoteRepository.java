package com.ebookfrenzy.quoteclient.service;

import com.ebookfrenzy.quoteclient.model.Quote;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class QuoteRepository {

  private static final int NETWORK_POOL_SIZE = 10;
  private static final String OAUTH_HEADER_FORMAT = "Bearer %s";

  private final QodService proxy;
  private final Executor networkPool;

  private QuoteRepository() {
    proxy = QodService.getInstance();
    networkPool = Executors.newFixedThreadPool(NETWORK_POOL_SIZE);
  }

  public Single<Quote> getRandom(String token) {
    return proxy.getRandom(String.format(OAUTH_HEADER_FORMAT, token))
        .subscribeOn(Schedulers.from(networkPool));
  }

  public static QuoteRepository getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {

    private static final QuoteRepository INSTANCE = new QuoteRepository();
  }
}
