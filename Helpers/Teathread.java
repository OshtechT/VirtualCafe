package Helpers;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Teathread extends Thread{
    private  Cafe cafe;

     public Teathread(Cafe cafe) { 
          this.cafe = cafe;
     }
      @Override
    public void run(){
          System.out.println("Tea thread has started");

          cafe.makeTOrder();
    }
}
