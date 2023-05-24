import Helpers.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Customer implements AutoCloseable{  // i.e. can use in a try-with-resources block
    final int port = 8888;
    private final Scanner reader;
    private final PrintWriter writer;

    public Customer( String name ) throws Exception {
        Socket socket = new Socket( "localhost", port );            // Connecting to the server,
        reader = new Scanner( socket.getInputStream() );            //   and create objects for communications;
        writer = new PrintWriter( socket.getOutputStream(), true ); // Provides nice 'println' functions (which flush automatically!)
        writer.println(name);        // Send  Name
        String line = reader.nextLine();   // Parse the response
        if (line.trim().compareToIgnoreCase( "success" ) != 0) throw new Exception( line );
    }
    public String[] getOrders(){
        writer.println("ORDERS");
        String line = reader.nextLine();
        int numberOfOrders = Integer.parseInt( line ); // Parse response
        String [] orders = new String[ numberOfOrders ];   // Create array to orders
        for (int i = 0; i < numberOfOrders; i++) { 
              line = reader.nextLine();
              orders[ i ] = line;
         } // Populate orders list
        return orders;   // Return accounts array (or, to be exact, a reference to the array)
    }
    public void makeOrder(String input){
        String[] substrings = input.split(" ");
        if (substrings.length == 3){
          int quantity = Integer.parseInt(substrings[1]);
          String beverage = substrings[2]; 
          writer.println("MAKE " + quantity +" "+ beverage);
          System.out.println("Order Made");
        }if (substrings.length == 6){
          int quantity1 = Integer.parseInt(substrings[1]); 
          int quantity2 = Integer.parseInt(substrings[4]); 
          String beverage1 = substrings[2]; 
          String beverage2 = substrings[5]; 
          writer.println("MAKE " + quantity1 +" "+ beverage1 +" "+ quantity2 +" "+ beverage2);
          System.out.println("Order Made");
         }
          
    
    }
    @Override
    public void close() throws Exception {  // Implement AutoCloseable interface!
        writer.println("EXIT");
        reader.close();
        writer.close();
    }


     public static void main(String[] args){   
     System.out.println("Enter your Name:");
        try
        {   Scanner in = new Scanner(System.in);
            String Name = in.nextLine();
            try (Customer customer = new Customer( Name ))
            {   System.out.println("Hello " + Name + " Welcome to the Virtual Cafe what would you like to order?" );
                 System.out.println("We serve only Tea and Coffee");
                 System.out.println("Tea will take 30 seconds");
                 System.out.println("Coffee will take 45 seconds");
                 System.out.println("Enter How many Teas or Coffees you want");
                while (true){
                    String Query = in.nextLine();
                    String[] substrings = Query.split(" ");
                    String exit = "exit";
                    if( substrings[0].equals("exit")){
                       customer.close();
                    }
                    if(substrings[1].equals("status")){
                      String[] orderList = customer.getOrders();
                      if(orderList.length == 0){
                          System.out.println("There are currently no orders for "+Name);
 
                      }else{
                        for (String fields : orderList){
                        System.out.println(fields);
                        }
                     }
                    }
                   else{
                     customer.makeOrder(Query);
                    System.out.println(" To check status of order type in ORDERS STATUS");
                  }
                }
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }

}
