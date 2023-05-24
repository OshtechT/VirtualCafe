package Helpers;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Shopthread implements Runnable{
    private final String[] substrings;
    private final String Name;
    private  Cafe cafe;

     public Shopthread( String Name, Cafe cafe, String[] substrings ) { 
          this.substrings = substrings;
          this.cafe = cafe;
          this.Name = Name;
     }
      @Override
    public void run(){
                         int ordersize = substrings.length; 
                               if(ordersize == 3){
                                   int quantity =Integer.parseInt( substrings[1]);
                                   String beverage = substrings[2];
                                   System.out.println("Order recieved from "+Name+" : "+quantity+" "+beverage);
                                   for (int i = 0; i < quantity; i++) {
                                      cafe.createOrder(Name,beverage);
//                                      new Thread( new Teathread(cafe)).start();
  //                                    new Thread( new Coffeethread(cafe)).start();
                                   }
                                    cafe.sortOrder();
                                   Thread c1 = new Thread( new Teathread(cafe));
                                   Thread t1 = new Thread( new Coffeethread(cafe));
                                   c1.start();
                                   t1.start();
				 
                                      try{
                                 	  c1.join();
                                  	 t1.join();
                                     }catch(Exception e){ System.out.println("Brewing Area is empty");}
                  //                  cafe.makeOrder();
                                    System.out.println("Order:( "+quantity+" "+beverage+" ) for "+Name+" has been finished");
                                    cafe.finish(Name);

                               }
                               if(ordersize == 5){
                                  int quantity1 = Integer.parseInt(substrings[1]);
                                  int quantity2 = Integer.parseInt(substrings[3]);
                                  String beverage1 = substrings[2];
                                  String beverage2 = substrings[4];
                                   System.out.println("Order recieved from "+Name+" : "+quantity1+" "+beverage1+" and "+quantity2+" "+beverage2);
                                  for (int i = 0; i < quantity1; i++) {
                                     cafe.createOrder(Name,beverage1);
   //                                 new Thread( new Teathread(cafe)).start();
   //                                new Thread( new Coffeethread(cafe)).start();

                                  }
                                  for (int i = 0; i < quantity2; i++) {
//                                   new Thread( new Teathread(cafe)).start();
 //                                  new Thread( new Coffeethread(cafe)).start();

                                     cafe.createOrder(Name,beverage2);
                                  }
                                    cafe.sortOrder();
                                   Thread c1 = new Thread( new Teathread(cafe));
                                   Thread t1 = new Thread( new Coffeethread(cafe));
                                   c1.start();
                                   t1.start();


                                      try{
                                          c1.join();
                                         t1.join();
                                     }catch(Exception e){ System.out.println("Brewing area is empty");}


  //                                  cafe.makeOrder();
                                     System.out.println("Order(: "+quantity1+" "+beverage1+" and "+quantity2+" "+beverage2+" ) for "+Name+" has been finished");
                                     cafe.finish(Name);


                               }
     }
}


