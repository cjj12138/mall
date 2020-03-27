package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.jesse.mall.dao.OrderItemDao;
import priv.jesse.mall.entity.OrderItem;
import priv.jesse.mall.service.DataService;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    private OrderItemDao orderItemDao;


    @Override
    public List<OrderItem> findByProductId(int productId) {
        return orderItemDao.findByProductId(productId);
    }
}
