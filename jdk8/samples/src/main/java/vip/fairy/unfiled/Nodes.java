package vip.fairy.unfiled;

public class Nodes {

  Object dataObj;
  Nodes nextNode;

  public Nodes(final Object data) {
    dataObj = data;
  }

  public static void main(String[] args) {
    LinkedList listObj = new LinkedList();
    System.out.println("测试未排序的链表");
    System.out.println();
    System.out.println("添加值");
    listObj.addtoFront(new String("二"));
    listObj.addtoBack(new String("三"));
    listObj.addtoFront(new String("一"));
    listObj.addtoBack(new String("四"));
    listObj.display();
    System.out.println("******************");
    System.out.println("从链表中删除");
    System.out.println("******************");
    try {
      System.out.println("已删除 " + listObj.fetchFromFront());
      System.out.println("已删除 " + listObj.fetchFromBack());
    } catch (Exception e) {
      System.out.println("异常：" + e);
    }
  }

}

class LinkedList {

  /**
   * 声明 Node 类的对象.
   */
  private Nodes firstNode;

  /**
   * 声明 Node 类的对象.
   */
  private Nodes lastNode;

  LinkedList() {
  }

  private Nodes createNode(final Object dataObj) {
    return new Nodes(dataObj);
  }

  boolean isEmpty() {
    return firstNode == null;
  }

  void addtoFront(Object data) {
    Nodes nNode = createNode(data);
    /* 将节点附加到链表的前面 */
    if (isEmpty()) {
      firstNode = nNode;
      lastNode = nNode;
    } else {
      nNode.nextNode = firstNode;
      firstNode = nNode;
    }
  }

  void addtoBack(final Object data) {
    Nodes nNode = createNode(data);

    /* 将节点附加到链表的末尾 */
    if (isEmpty()) {
      firstNode = nNode;
      lastNode = nNode;
    } else {
      lastNode.nextNode = nNode;
      lastNode = nNode;
    }
  }

  Object fetchFromFront() throws Exception {
    if (isEmpty()) {
      throw new Exception("Empty list in Fetch From Front");
    } else {
      Nodes tempNode = firstNode; // declare a localnode

      if (firstNode == lastNode) {
        firstNode = null;
        lastNode = null;
      } else {
        firstNode = firstNode.nextNode;
      }
      return tempNode.dataObj;
    }
  }

  Object fetchFromBack() throws Exception {
    if (this.isEmpty()) {
      throw new Exception("Empty list in Fetch From Back");
    } else {
      Nodes tempNode = lastNode;

      if (firstNode == lastNode) {
        firstNode = null;
        lastNode = null;
      } else {
        Nodes currentNode = firstNode;

        while (currentNode.nextNode != lastNode) {
          currentNode = currentNode.nextNode;
        }

        lastNode = currentNode;
        currentNode.nextNode = null;
      }
      return tempNode.dataObj;
    }
  }

  void display() {
    System.out.println("\n**********************************");
    System.out.println("显示链表的值");
    System.out.println("**********************************");
    if (isEmpty()) {
      System.out.println("空");
      return;
    }

    Nodes current = firstNode;
    while (current != null) {
      System.out.println("值： " + current.dataObj);
      current = current.nextNode;
    }
  }
}
