package priv.jesse.mall.service;

import priv.jesse.mall.entity.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DataService {
    //根据商品ID对每种商品的出售统计
    List<OrderItem> findByProductId(int productId);

    List<DateCount> findAll();

    List<HashMap> findCount();

    List<Object[]> findPie();
}
