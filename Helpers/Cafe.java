package Helpers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.*;
import java.lang.Thread;   

public class Cafe{
    private final Map<Integer, Order> orders = new TreeMap<>();
    private final List<Integer> Waitlist = new ArrayList<Integer>();
    private final List<Integer> BrewlistT = new ArrayList<Integer>();
    private final List<Integer> BrewlistC = new ArrayList<Integer>();
    private final List<Integer> Traylist = new ArrayList<Integer>();

    public void creatOrder( String name, String beverage, int orderID){
        Order order = new Order( name, beverage, orderID );
        order.setStatus( "Waiting Area" );
        Waitlist.add(orderID); 
//        System.out.println(Waitlist.size());
//        System.out.println("BrewlistC: "+ BrewlistC.size());
//        System.out.println("BrewlistT: "+ BrewlistT.size());
        orders.put( orderID, order );
        sortOrder();
    }
     public void createOrder(String name, String beverage) {
        synchronized (orders) {
            int maxOrderID = 0;
            for (Integer orderID : orders.keySet())
                if (maxOrderID < orderID)
                    maxOrderID = orderID;
            int newOrderID = maxOrderID + 1;
            creatOrder(name, beverage, newOrderID);
        }
    }
    public void sortOrder(){
     synchronized (Waitlist) {
      if(Waitlist.size() != 0){
         for(int i=0;i<Waitlist.size();i++){
            Order order = orders.get(Waitlist.get(i));
            String type = order.getBeverage();
            String[] substrings = type.split("");

            try{
               switch (substrings[0].toLowerCase()){
                   case "t":
                      int size = BrewlistT.size();
                      if(size < 2){
                         orders.get(Waitlist.get(i)).setStatus( "Brewing Area" );
                         Order order1 = orders.get(Waitlist.get(i));
                         String answer1 =  ("Order Number: "+order1.getOrderID()+ " Beverage:  "+order1.getBeverage()+" for "+order1.getName()+" has now been moved to  "+order1.getStatus());
                         BrewlistT.add(Waitlist.get(i));
                         Waitlist.remove(i);
                         System.out.println(answer1);
                      }
                      break;
                   case "c":
                     int size2 = BrewlistC.size();
                     if(size2 < 2){
                        orders.get(Waitlist.get(i)).setStatus( "Brewing Area" );
                        Order order1 = orders.get(Waitlist.get(i));
                        String answer1 =  ("Order Number: "+order1.getOrderID()+ " Beverage:  "+order1.getBeverage()+" for "+order1.getName()+" has now been moved to  "+order1.getStatus());
                        BrewlistC.add(Waitlist.get(i));
                        Waitlist.remove(i);
                        System.out.println(answer1);
                     }
                     break;
              }
           }catch(Exception e) { System.out.println("Waitlist is finished"); }
        }
      }
     }
    }

     public void makeOrder(){
     try{
      synchronized (BrewlistT) {

        if(BrewlistT.size() != 0){
           Order order = orders.get(BrewlistT.get(0));
           String type = order.getBeverage();
           String[] substrings = type.split("");
           int size = Waitlist.size();
           Thread.sleep(30000);
           order.setStatus( "Tray Area" );
           String answer5 =  ("Order Number: "+order.getOrderID()+ " Beverage:  "+order.getBeverage()+" for "+order.getName()+" has now been moved to  "+order.getStatus());
           System.out.println(answer5);
           Traylist.add(BrewlistT.get(0));
           BrewlistT.remove(0);
           sortOrder();
           makeOrder();
        }
      }
      synchronized (BrewlistC) {

        if(BrewlistC.size() != 0){
           Order order = orders.get(BrewlistC.get(0));
           String type = order.getBeverage();
           String[] substrings = type.split("");
           int size = Waitlist.size();
           Thread.sleep(40000);
           order.setStatus( "Tray Area" );
           String answer6 =  ("Order Number: "+order.getOrderID()+ " Beverage:  "+order.getBeverage()+" for "+order.getName()+" has now been moved to  "+order.getStatus());
           System.out.println(answer6);
           Traylist.add(BrewlistC.get(0));
           BrewlistC.remove(0);
           sortOrder();
           makeOrder();
        }
     }

     }catch(Exception e) { System.out.println("Brewing Area is empty"); }
    }

        public void makeTOrder(){
     try{
      synchronized (BrewlistT) {
        if(BrewlistT.size() != 0){
           Order order = orders.get(BrewlistT.get(0));
           String type = order.getBeverage();
           String[] substrings = type.split("");
           int size = Waitlist.size();
           Thread.sleep(30000);
           order.setStatus( "Tray Area" );
           String answer5 =  ("Order Number: "+order.getOrderID()+ " Beverage:  "+order.getBeverage()+" for "+order.getName()+" has now been moved to  "+order.getStatus());
           System.out.println(answer5);
           Traylist.add(BrewlistT.get(0));
           BrewlistT.remove(0);
           sortOrder();
           makeTOrder();
        }
      }

     }catch(Exception e) { e.printStackTrace(); }
 }
        public void makeCOrder(){
     try{
      synchronized (BrewlistC) {
        if(BrewlistC.size() != 0){
           Order order = orders.get(BrewlistC.get(0));
           String type = order.getBeverage();
           String[] substrings = type.split("");
           int size = Waitlist.size();
           Thread.sleep(40000);
           order.setStatus( "Tray Area" );
           String answer6 =  ("Order Number: "+order.getOrderID()+ " Beverage:  "+order.getBeverage()+" for "+order.getName()+" has now been moved to  "+order.getStatus());
           System.out.println(answer6);
           Traylist.add(BrewlistC.get(0));
           BrewlistC.remove(0);
           sortOrder();
           makeCOrder();
        }
       }

     }catch(Exception e) { e.printStackTrace(); }
    }

    public List<String> getListOfOrders( String name ){
        List<String> result = new ArrayList<String>();
        for (Order order : orders.values()){
          if (order.getName() == name){
              String str1 = Integer.toString(order.getOrderID());
              String answer =  ("Order Number: "+str1+ " Beverage:  "+order.getBeverage()+"  Status: "+order.getStatus()); 
              result.add( answer );
           }
        }
        return result;
    }
     public void removeOrders( String name ){
        int size = orders.size();
        for (int i=0;i<size;i++){
            int Key = i + 1;
            Order order = orders.get(Key);
             System.out.println(i);
             System.out.println(size);
             int Wsize = Waitlist.size();
             int BTsize = BrewlistT.size();
             int BCsize = BrewlistC.size();
             for (int j=0;j<Wsize;j++){
                 Order worder = orders.get(Waitlist.get(j));
                 if(Waitlist.get(j)== Key && worder.getName().equals(name)){
                     Waitlist.remove(j);
                     System.out.println("Order Number: "+Key+" has been removed from the Waiting Area");
                 }
             }
    //         for (int k=0;k<BTsize;k++){
    //             if(BrewlistT.get(k)== Key){
    //                 Thread.interrupt();
    //                 BrewlistT.remove(k);
    //                 System.out.println("Order Number: "+Key+" has been removed from the Brewing Area");
   //              }
    //         }
    //         for (int f=0;f<BCsize;f++){
     //            if(BrewlistC.get(f)== Key){
     ///                Thread.interrupt();
      //               BrewlistC.remove(f);
      //               System.out.println("Order Number: "+Key+" has been removed from the Brewing Area");
      //           }
      //       }
            if (order.getName().equals(name)){
               String str2 = Integer.toString(order.getOrderID());
               String answer =  ("Order Number: "+str2+ " Beverage:  "+order.getBeverage()+" has been removed"); 
               orders.remove(order.getOrderID());
               System.out.println(answer);
            }

         }
     sortOrder();
   //  makeTOrder();
  //   makeTOrder();
     System.out.println("All orders for "+name+" have been removed");
    } 
   public void finish(String name){
     synchronized (Traylist){
       int size = Traylist.size();
       for(int i = 0;i<size;i++){
           Order order = orders.get(Traylist.get(i));
           int orderID = order.getOrderID();
           if(order.getName().equals(name)){
           orders.remove(orderID);
           }
       }
       System.out.println("Orders for "+name+" has been delivered");
     }

   }
   public String getOrderStatus( String name, int orderID ) throws Exception{
        if (orders.get(orderID).getName() != name){
            throw new Exception( "Order " +orderID+
                                 " belongs to a different customer; customer " +name+
                                 "This is not your oder." );

          }
           return orders.get(orderID).getStatus();
    }
   public void changeOrderStatusBrew( String name, int orderID ) throws Exception{
        if (orders.get(orderID).getName() != name){
            throw new Exception( "Order " +orderID+
                                 " belongs to a different customer; customer " +name+
                                 "This is not your oder." );

          }
          orders.get(orderID).setStatus("Brewing Area");
    }
   public void changeOrderStatusTray( String name, int orderID ) throws Exception{
        if (orders.get(orderID).getName() != name){
            throw new Exception( "Order " +orderID+
                                 " belongs to a different customer; customer " +name+
                                 "This is not your oder." );
          }
          orders.get(orderID).setStatus("Tray Area");
    }


}

