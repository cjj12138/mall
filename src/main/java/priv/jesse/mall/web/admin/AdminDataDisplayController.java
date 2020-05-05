package priv.jesse.mall.web.admin;

import org.codehaus.jackson.map.util.JSONPObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.jesse.mall.entity.*;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.DataService;

import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/admin/data_display")
public class AdminDataDisplayController {
    @Autowired
    private DataService dataService;


    @RequestMapping("/product_order_line")
    public ResultBean<List<DateCount>> getOrder_line(){
        List<Object[]> orders=this.dataService.findOrderCountByDay();
        Collections.reverse(orders);
        System.out.println(orders);
        ResultBean<List<DateCount>> resultBean=new ResultBean(orders);
        return resultBean;
    }

    @RequestMapping("/product_order_bar")
    public ResultBean<List<HashMap>> get_bar(){
        List<HashMap> products=this.dataService.findCount();
        return  new ResultBean<>(products);
    }

    @RequestMapping("/product_order_pie")
    public ResultBean<List<NameValue>> get_pie(){
        List<Object[]> products=this.dataService.findPie();
        System.out.println(products);
        List<NameValue> nameValues=new ArrayList<>();
        for (Object[] obj:products){
            NameValue nameValue=new NameValue(obj[0].toString(),obj[1].toString());
            nameValues.add(nameValue);
        }
        return new ResultBean<>(nameValues);
    }

    @RequestMapping("/product_order_today")
    public ResultBean<Map<String,String>> countToday(){
        List<Object[]> title_count=dataService.findOrderToday();
        Map<String,String> res=new HashMap<>();
        for(Object[] objects:title_count){
            res.put(objects[0].toString(),objects[1].toString());
        }
        return new ResultBean<>(res);
    }

    @RequestMapping("/product_order_Area")
    public ResultBean<Map<String,String>> countArea(){
        List<Object[]> orderCountByArea = dataService.findOrderCountByArea();
        Map<String,String> res=new HashMap<>();
        for(Object[] objects:orderCountByArea){
            res.put(objects[0].toString(),objects[1].toString());
        }
        return new ResultBean<>(res);
    }
}
