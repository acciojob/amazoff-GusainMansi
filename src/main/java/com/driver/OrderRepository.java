package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
@Component

@Repository
public class OrderRepository {
    @Autowired
    Order order;
   private HashMap<String,Order> orderMap;
   private HashSet<String> deliveryMap;
    private  HashMap<String, List<String>> orderDeliveryMap;
     public OrderRepository(){
         this.orderMap=new HashMap<String,Order>();
         this.deliveryMap=new HashSet<String>();
         this.orderDeliveryMap=new HashMap<String, List<String>>();
     }

     //1.
    public void saveOrder(Order order){
         orderMap.put(order.getId(),order);

    }
    //2.
    public void saveDeliveryPartner(String deliveryPartner){
   deliveryMap.add(deliveryPartner);

    }
    //3.
    public void saveOrderPartnerPair(String order,String partner){
        if(orderMap.containsKey(order) && deliveryMap.contains(partner)){
            orderMap.put(order, orderMap.get(order));
            deliveryMap.add(partner);
            List<String> currentDelivery = new ArrayList<String>();
            if(orderDeliveryMap.containsKey(partner))
                currentDelivery = orderDeliveryMap.get(partner);
            currentDelivery.add(order);
            orderDeliveryMap.put(partner, currentDelivery);
        }
    }
    //4.
    public Order findOrder(String order){
        return orderMap.get(order);
    }
//5.
    public DeliveryPartner findPartner(String partner){
        return new DeliveryPartner(partner);
    }
    //7.
    public List<String> findOrderFromPartner(String partner){
        List<String> ordersList = new ArrayList<String>();
        if(orderDeliveryMap.containsKey(partner)) ordersList = orderDeliveryMap.get(partner);
        return ordersList;
    }
    //6.
    public int countOrdersForPartner(String partner){
        List<String> ordersList = new ArrayList<String>();
        if(orderDeliveryMap.containsKey(partner)) ordersList = orderDeliveryMap.get(partner);
        return  ordersList.size();
    }
    //8.
    public List<String> findAllOrders(){

        return new ArrayList<>(orderMap.keySet());
    }
    //12.
    public void deletePartner(String partner){
        List<String> orders = new ArrayList<String>();
        if(orderDeliveryMap.containsKey(partner)){
            orderDeliveryMap.remove(partner);
        }

        if(deliveryMap.contains(partner)){
            deliveryMap.remove(partner);
        }
    }
    //13.
    public void deleteOrder(String order){
         for(String x: orderDeliveryMap.keySet()){
             List<String> ans=new ArrayList<>();
             ans=orderDeliveryMap.get(x);
             for(String y: ans){
                 if(y==order)
                     ans.remove(y);
                 if(ans==null) orderDeliveryMap.remove(x);
             }


         }
         if(orderMap.containsKey(order))
             orderMap.remove(order);

    }
//9.
public int countOfordersUnassigned(){
         return orderMap.size()-orderDeliveryMap.size();
}
//10.
public int undelivered(String time,String partnerId){
         int c=0;
          int t=Integer.parseInt(time.substring(0,2))*60 + Integer.parseInt(time.substring(2));
    for(String x: orderDeliveryMap.keySet()) {
        if (x == partnerId){
            List<String> ans = new ArrayList<>();
        ans = orderDeliveryMap.get(x);
        for (String y : orderMap.keySet()) {
            if(orderMap.containsKey(y)){
                if(order.getDeliveryTime()>t) c++;
            }

        }


    }

    } return c;
}
//11.
public int lastDelivery(String partnerId){
         List<String> ans=new ArrayList<>();
         ans=orderDeliveryMap.get(partnerId);
         int n=ans.size()-1;
         String s=ans.get(n);
          if(orderMap.containsKey(s))
             return order.getDeliveryTime();
         else return 0;
}




}
