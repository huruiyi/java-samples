package com.example.search.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Page<T> extends SimplePage implements Serializable, IPage {

  /**
   * 当前页的数据
   */
  private List<T> list;

  public Page() {
  }

  public Page(int pageNo, int pageSize, int totalCount) {
    super(pageNo, pageSize, totalCount);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public Page(int pageNo, int pageSize, int totalCount, List list) {
    super(pageNo, pageSize, totalCount);
    this.list = list;
  }

  public int getFirstResult() {
    return (pageNo - 1) * pageSize;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }
}
