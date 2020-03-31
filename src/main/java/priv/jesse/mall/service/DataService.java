package priv.jesse.mall.service;

import priv.jesse.mall.entity.DateCount;
import priv.jesse.mall.entity.Order;
import priv.jesse.mall.entity.OrderItem;

import java.util.List;

public interface DataService {
    //根据商品ID对每种商品的出售统计
    List<OrderItem> findByProductId(int productId);

    List<DateCount> findAll();
}
