package com.jpa.multi.thread.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_details")
public class StockDetails {

  @Id
  @Column(name = "stock_id")
  private int stockId;

  @Column(name = "stock_name")
  private String stockName;

  public int getStockId() {
    return stockId;
  }

  public void setStockId(int stockId) {
    this.stockId = stockId;
  }

  public String getStockName() {
    return stockName;
  }

  public void setStockName(String stockName) {
    this.stockName = stockName;
  }

  @Override
  public String toString() {
    return "StockDetails [stockId=" + stockId + ", stockName=" + stockName + "]";
  }

}
