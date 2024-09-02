package vip.fairy.sample_commond_2;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class App {

  public interface Order {

    void execute();
  }

  static class Stock {

    private String name = "ABC";
    private int quantity = 10;

    public void buy() {
      System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] bought");
    }

    public void sell() {
      System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] sold");
    }
  }

  static class BuyStock implements Order {

    private Stock buyStock = new Stock();

    public BuyStock(Stock abcStock) {
      this.buyStock = abcStock;
    }

    public void execute() {
      buyStock.buy();
    }
  }

  static class SellStock implements Order {

    private Stock sellStock = new Stock();

    public SellStock(Stock abcStock) {
      this.sellStock = abcStock;
    }

    public void execute() {
      sellStock.sell();
    }
  }

  public static class Broker {

    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order) {
      orderList.add(order);
    }

    public void placeOrders() {
      for (Order order : orderList) {
        order.execute();
      }
      orderList.clear();
    }
  }

  @Test
  void test( ) {
    Stock abcStock = new Stock();

    BuyStock buyStockOrder = new BuyStock(abcStock);
    SellStock sellStockOrder = new SellStock(abcStock);

    Broker broker = new Broker();
    broker.takeOrder(buyStockOrder);
    broker.takeOrder(sellStockOrder);

    broker.placeOrders();
  }
}
