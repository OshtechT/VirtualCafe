import Helpers.*;
import java.io.IOException;   
import java.net.ServerSocket;
import java.net.Socket;

public class Barista{
    private final static int port = 8888;
    private static final Cafe cafe = new Cafe();

    public static void main(String[] args){   
        RunServer();
    }

    private static void RunServer()
    {   ServerSocket serverSocket = null;   // passive socket, used for 'listening'
        try
        {   serverSocket = new ServerSocket( port );  // bind port and start listening
            System.out.println( "Waiting for incoming connections..." );
            while (true)
            {   Socket socket = serverSocket.accept();  // accept incoming connections (blocks until it does!)
                new Thread( new Server( socket, cafe) ).start();
            }
        }
        catch (IOException e) { e.printStackTrace(); }
    }
    
}
