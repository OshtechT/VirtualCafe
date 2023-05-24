package Helpers;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Server implements Runnable{
    private final Socket socket;
    private Cafe cafe;

    public Server( Socket socket, Cafe cafe ) { 
          this.socket = socket;
          this.cafe = cafe;
     }

    @Override
    public void run(){
        int orderID = 0;
        String Name = "";
        try (Scanner scanner    = new Scanner( socket.getInputStream() );
             PrintWriter writer = new PrintWriter( socket.getOutputStream(), true ) ){
           try{
                Name =  scanner.nextLine();
                System.out.println( "New connection; Name:  " + Name );
                writer.println( "SUCCESS" );

                while (true){
                    String line = scanner.nextLine();
                    String[] substrings = line.split(" ");
                    switch (substrings[0].toLowerCase()){
                        case "make":
                                 new Thread( new Shopthread(Name,cafe,  substrings) ).start();


                               break;


                         case "orders":
                            List<String> ordersList = new ArrayList<String>();
                              ordersList = cafe.getListOfOrders(Name);
                              writer.println( ordersList.size() );
                              for(String fields : ordersList){
 				writer.println(fields);
                              }
                            break;
                         case "exit":
                                 cafe.removeOrders(Name);


                               break;


			default:   throw new Exception( "Unknown command: " + substrings[ 0 ] );
                    }
                }
            }
            catch (Exception e) { writer.println("ERROR " + e.getMessage()); socket.close(); }
        }
        catch (Exception e) { }
        finally { System.out.println("Customer " +Name+"  left the shop."); }
    } // end of 'run' method
}


     

