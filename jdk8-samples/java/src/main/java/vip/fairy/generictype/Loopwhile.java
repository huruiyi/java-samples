package vip.fairy.generictype;

public class Loopwhile {

  public static void main(String[] args) {
    String[] videoCourses =
        {
            "C++",
            "C++ operating on files",
            "Java",
            "Java Android",
            "Java Database",
            "JavaScript",
            "Grunt.js",
            "SASS"
        };

    for (int i = 0; i < videoCourses.length; i++) {
      System.out.println(videoCourses[i]);
    }

    System.out.println("***********************************************");

    int i = 0;

    for (String myValue : videoCourses) {
      i++;

      if (i == 5) {
        System.out.println("lalal");
      }
      System.out.println(myValue);
    }
    System.out.println("***********************************************");

    int j = 0;
    do {
      System.out.println(videoCourses[j]);
      j++;

    } while (j < videoCourses.length);

    System.out.println("***********************************************");
  }

}
