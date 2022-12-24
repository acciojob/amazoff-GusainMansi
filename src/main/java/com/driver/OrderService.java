package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    //1.
    public void addOrder(Order order){
        orderRepository.saveOrder(order);
    }
    //2.
    public void addPartner(String deliveryPartner){
        orderRepository.saveDeliveryPartner(deliveryPartner);
    }
    //3.
    public void saveOrderPartner(String order,String partner){
        orderRepository.saveOrderPartnerPair(order,partner);
    }

//5.
public DeliveryPartner findDeliveryPartner(String id){
        return orderRepository.findPartner(id);
}
//7.
public List<String> findOrderstoPartner(String partner){
        return orderRepository.findOrderFromPartner(partner);
}
//8.
public List<String> allOrders(){
        return orderRepository.findAllOrders();
}
//4.
public Order findOrder(String id){
        return orderRepository.findOrder(id);
}
//11
public int getLastDelivery(String partnerId){
        return orderRepository.lastDelivery(partnerId);
}
//10.
public int undelivered(String time,String partnerId){
        return orderRepository.undelivered(partnerId,time);
}
//9
public int countsOfUnassignedOrders(){
        return orderRepository.countOfordersUnassigned();
}
//13
public void deleteOrder(String order){
         orderRepository.deleteOrder(order);
}
//12
public void deletePartner(String partner){
         orderRepository.deletePartner(partner);
}
//6.
public int countOfOrders(String partner){
        return orderRepository.countOrdersForPartner(partner);
}

}
