package Helpers;

public class Order {
    private final String name;
    private final String beverage;
    private String status;
    private final int orderID;
    

    public Order(String name, String beverage, int orderID)
    {   this.name = name;
        this.beverage  = beverage;
        this.orderID = orderID;
    }

    public String  getName()           { return name; }
    public String  getBeverage()              { return beverage;    }
    public int  getOrderID()              { return orderID;    }
    public String  getStatus()                 { return status;       }
    public String  setStatus( String newStatus ) { status = newStatus; return status; }
}

