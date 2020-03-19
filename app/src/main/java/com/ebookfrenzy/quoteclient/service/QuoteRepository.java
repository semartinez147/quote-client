package com.ebookfrenzy.quoteclient.service;

import com.ebookfrenzy.quoteclient.model.Content;
import com.ebookfrenzy.quoteclient.model.Quote;
import com.ebookfrenzy.quoteclient.model.Source;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class QuoteRepository {

  private static final int NETWORK_POOL_SIZE = 10;
  private static final String OAUTH_HEADER_FORMAT = "Bearer %s";

  private final QodService proxy;
  private final Executor networkPool;

  public static QuoteRepository getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private QuoteRepository() {
    proxy = QodService.getInstance();
    networkPool = Executors.newFixedThreadPool(NETWORK_POOL_SIZE);
  }

  public Single<Quote> getRandom(String token) {
    return proxy.getRandom(String.format(OAUTH_HEADER_FORMAT, token))
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<List<Quote>> getAllQuotes (String token) {
    return proxy.getAll(String.format(OAUTH_HEADER_FORMAT, token))
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<List<Source>> getAllSources (String token, boolean includeNull) {
    return proxy.getAllSources(String.format(OAUTH_HEADER_FORMAT, token), includeNull)
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<Quote> add (String token, Quote quote) {
    return proxy.post(String.format(OAUTH_HEADER_FORMAT, token), quote)
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<List<Content>> getAllContent(String token) {
    return getAllSources(token, true)
        .subscribeOn(Schedulers.io())
        .map((sources) -> {
          List<Content> combined = new ArrayList<>();
          for (Source source : sources) {
            combined.add(source);
            Collections.addAll(combined, source.getQuotes());
          }
          return combined;
        });
  }

  private static class InstanceHolder {

    private static final QuoteRepository INSTANCE = new QuoteRepository();

  }

}
