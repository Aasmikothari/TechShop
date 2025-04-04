package com.techshop.dao;

import com.techshop.entity.OrderDetail;
import java.util.List;

public interface OrderDetailDAO {
    void addOrderDetail(OrderDetail orderDetail);
    List<OrderDetail> getOrderDetailsByOrderID(int orderID);
    void deleteOrderDetail(int orderDetailID);
}