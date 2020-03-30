package priv.jesse.mall.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.jesse.mall.entity.Order;
import priv.jesse.mall.entity.OrderItem;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.DataService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin/data_display")
public class AdminDataDisplayController {
    @Autowired
    private DataService dataService;

    @RequestMapping("/product_data.do")
    public ResultBean<List<OrderItem>> getData(){
        System.out.println("hello");
        System.out.println(dataService.findByProductId(9));
        List<OrderItem> orderItem=dataService.findByProductId(9);
        return new ResultBean<>(orderItem);
    }

    @RequestMapping("/product_order")
    public ResultBean<List<Order>> getOrder(){
        List<Order> orders=this.dataService.findAll();
        System.out.println(orders);
        return new ResultBean<>(orders);
    }
}
