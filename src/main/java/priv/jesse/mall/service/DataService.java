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
    /**
     * 查询当天各商品的销售量
     * @return
     */
    List<Object[]> findOrderToday();

    /**
     * 获取订单不同省份的个数
     * @return
     */
    List<Object[]> findOrderCountByArea();

    /**
     * 获取近七天 每天的销售量
     * @return
     */
    List<Object[]> findOrderCountByDay();

    /**
     * 获取一周内热销热三的商品个天的销量
     * @return
     */
    List<Object[]> findItemWeek();

    /**
     * 获取一月内热销热三的商品每周的销量
     */
    List<Object[]> findItemMonth();
}
