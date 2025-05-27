package vip.fairy.Inheritance.monsters;

/**
 * @author Arek
 */
public class Spider extends Monster {

  void whatever() {
    this.getHitPoints();
    this.setHitPoints(60);
  }

  @Override
  protected void description() {
    System.out.println("Spider...........");
  }
}
