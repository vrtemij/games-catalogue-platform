package com.game.store.platform.shared;

import java.util.List;

public class PagedListGenericRestDto<T> {

  private Long totalCount;

  private List<T> items;

  public PagedListGenericRestDto() {
  }

  public PagedListGenericRestDto(List<T> items) {
    this.setItemsAndTotalCount(items);
  }

  public Long getTotalCount() {
    return this.totalCount;
  }

  public void setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
  }

  public List<T> getItems() {
    return this.items;
  }

  public void setItems(List<T> items) {
    this.items = items;
  }

  public void setItemsAndTotalCount(List<T> items) {
    this.items = items;
    this.totalCount = (long) (items != null ? items.size() : 0);
  }
}
