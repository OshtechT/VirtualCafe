package Helpers;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Coffeethread extends Thread{
    private  Cafe cafe;

     public Coffeethread(Cafe cafe) { 
          this.cafe = cafe;
     }
      @Override
    public void run(){
          System.out.println("Coffethread has started");
          cafe.makeCOrder();
    }
}
