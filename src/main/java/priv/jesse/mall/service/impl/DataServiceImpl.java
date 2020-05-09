package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.jesse.mall.dao.DateCountDao;
import priv.jesse.mall.dao.OrderDao;
import priv.jesse.mall.dao.OrderItemDao;
import priv.jesse.mall.entity.*;
import priv.jesse.mall.service.DataService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private DateCountDao dateCountDao;


    @Override
    public List<OrderItem> findByProductId(int productId) {
        return orderItemDao.findByProductId(productId);
    }

    @Override
    public List<DateCount> findAll() {
        return this.dateCountDao.findAll();
    }

    @Override
    public List<HashMap> findCount() {
        return this.dateCountDao.findCount();
    }

    @Override
    public List<Object[]> findPie() {
        return this.dateCountDao.findPie();
    }

    @Override
    public List<Object[]> findOrderToday() {
        return orderDao.findOrderToday();
    }

    @Override
    public List<Object[]> findOrderCountByArea() {
        return orderDao.findOrderCountByArea();
    }

    @Override
    public List<Object[]> findOrderCountByDay() {
        return dateCountDao.findOrderCountByDay();
    }

    @Override
    public List<Object[]> findItemWeek() {
        return orderDao.findItemWeek();
    }

    @Override
    public List<Object[]> findItemMonth() {
        return orderDao.findItemMonth();
    }
}
