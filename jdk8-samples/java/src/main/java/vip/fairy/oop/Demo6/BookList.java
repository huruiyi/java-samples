package vip.fairy.oop.Demo6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookList {

  ArrayList<String> bookArray;
  List<String> bookCopyObj;

  BookList() {
    bookArray = new ArrayList<String>();
  }

  public static void main(String[] args) {
    BookList bookObj = new BookList();
    bookObj.add();
    bookObj.display();
    bookObj.reverse();
    bookObj.sort();
    bookObj.copy();
  }

  void add() {
    bookArray.add("西游记");
    bookArray.add("三国演义");
    bookArray.add("水浒传");
    bookArray.add("红楼梦");
    bookArray.add("东周列国志");
    bookArray.add("围城");
    System.out.println();
  }

  void display() {
    System.out.println("**********************" + "****************");
    System.out.println("从 ArrayList 检索对象");
    System.out.println("*******************" + "*******************");
    System.out.println();
    for (int ctr = 0; ctr < bookArray.size(); ctr++) {
      System.out.print("\n" + bookArray.get(ctr));
    }
    System.out.println();
  }

  void reverse() {

    System.out.println();
    System.out.println("*********************************");
    System.out.println("反转 List 的内容");
    System.out.println("*********************************");
    System.out.println();

    System.out.println();
    System.out.println("书名列表   （反转前）： " + bookArray);

    System.out.println();
    Collections.reverse(bookArray);
    System.out.println("书名列表   （反转后）： " + bookArray);
  }

  void sort() {
    System.out.println("********************");
    System.out.println("对 List 进行排序");
    System.out.println("********************");
    System.out.println();
    System.out.println("书名列表   （排序前）： " + bookArray);

    System.out.println();
    Collections.sort(bookArray);
    System.out.println("书名列表   （排序后）： " + bookArray);
  }

  void copy() {

    System.out.println("***************************************");
    System.out.println("将内容复制到另一个 Array");
    System.out.println("***************************************");
    System.out.println();
    System.out.println("bookArray 是否为空？   " + bookArray.isEmpty());
    System.out.println("bookArray   （之前）： " + bookArray);
    System.out.println();
    bookCopyObj = new ArrayList<String>(bookArray);
    System.out.println("bookCopyObj   （之后）： " + bookCopyObj);
  }

}
