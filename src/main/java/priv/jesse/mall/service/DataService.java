package priv.jesse.mall.service;

import priv.jesse.mall.entity.Order;
import priv.jesse.mall.entity.OrderItem;

import java.util.List;

public interface DataService {
    //根据商品ID对每种商品的出售统计
    List<OrderItem> findByProductId(int productId);
    //查询所有商品的订单信息，对日期进行统计
    List<Order>findAll();
}
