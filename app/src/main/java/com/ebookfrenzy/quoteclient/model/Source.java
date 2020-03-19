package com.ebookfrenzy.quoteclient.model;

import com.google.gson.annotations.Expose;
import java.util.Date;
import java.util.UUID;
import retrofit2.http.Url;

public class Source implements Content {

  @Expose
  private UUID id;

  @Expose
  private Date created;

  @Expose
  private Date updated;

  @Expose
  private String name;

  @Expose
  private Quote[] quotes;

  @Expose
  private Url href;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Quote[] getQuotes() {
    return quotes;
  }

  public void setQuotes(Quote[] quotes) {
    this.quotes = quotes;
  }

  public Url getHref() {
    return href;
  }

  public void setHref(Url href) {
    this.href = href;
  }
}
