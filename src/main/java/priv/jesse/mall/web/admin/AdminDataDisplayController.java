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
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/admin/data_display")
public class AdminDataDisplayController {
    @Autowired
    private DataService dataService;


    @RequestMapping("/product_order_line")
    public ResultBean<List<DateCount>> getOrder_line() {
        List<Object[]> orders = this.dataService.findOrderCountByDay();
        Collections.reverse(orders);
        System.out.println(orders);
        ResultBean<List<DateCount>> resultBean = new ResultBean(orders);
        return resultBean;
    }

    @RequestMapping("/product_order_bar")
    public ResultBean<List<HashMap>> get_bar() {
        List<HashMap> products = this.dataService.findCount();
        return new ResultBean<>(products);
    }

    @RequestMapping("/product_order_pie")
    public ResultBean<List<NameValue>> get_pie() {
        List<Object[]> products = this.dataService.findPie();
        System.out.println(products);
        List<NameValue> nameValues = new ArrayList<>();
        for (Object[] obj : products) {
            NameValue nameValue = new NameValue(obj[0].toString(), obj[1].toString());
            nameValues.add(nameValue);
        }
        return new ResultBean<>(nameValues);
    }

    @RequestMapping("/product_order_today")
    public ResultBean<Map<String, String>> countToday() {
        List<Object[]> title_count = dataService.findOrderToday();
        Map<String, String> res = new HashMap<>();
        for (Object[] objects : title_count) {
            res.put(objects[0].toString(), objects[1].toString());
        }
        return new ResultBean<>(res);
    }

    @RequestMapping("/product_order_Area")
    public ResultBean<Map<String, String>> countArea() {
        List<Object[]> orderCountByArea = dataService.findOrderCountByArea();
        Map<String, String> res = new HashMap<>();
        for (Object[] objects : orderCountByArea) {
            res.put(objects[0].toString(), objects[1].toString());
        }
        return new ResultBean<>(res);
    }

    @RequestMapping("/product_order_day")
    public ResultBean<Map<String, Object>> dayCount() {
        List<Object[]> dayCount = dataService.findItemWeek();
        //周列表
        String[] weekdays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        //商品列表
        List<String> legends = new ArrayList<>();
        for (Object[] o : dayCount) {
            legends.add(o[1].toString());
        }
        Stream<String> stream = legends.stream();
        legends = stream.distinct().collect(Collectors.toList());
        //构建一周的数据
        List<Map<String, Object>> title_count = new ArrayList<>();
        for (int i = 0; i < legends.size(); i++) {
            Double[] count = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            for (Object[] o : dayCount) {
                if (legends.get(i).toString().equals(o[1].toString())) {
                    switch (o[0].toString()) {
                        case "Sunday":
                            count[0] = (Double) o[2];
                            break;
                        case "Monday":
                            count[1] = (Double) o[2];
                            break;
                        case "Tuesday":
                            count[2] = (Double) o[2];
                            break;
                        case "Wednesday":
                            count[3] = (Double) o[2];
                            break;
                        case "Thursday":
                            count[4] = (Double) o[2];
                            break;
                        case "Friday":
                            count[5] = (Double) o[2];
                            break;
                        case "Saturday":
                            count[6] = (Double) o[2];
                            break;
                    }
                }
            }
            Map<String,Object> temp=new HashMap<>();
            temp.put("name",legends.get(i).toString());
            temp.put("data",count);
            temp.put("type","line");
            title_count.add(temp);
        }
        Map<String,Object> res= new HashMap<>();
        res.put("legend",legends);
        res.put("xAxis",weekdays);
        res.put("series",title_count);
        return new ResultBean<>(res);
    }

    @RequestMapping("/product_order_week")
    public ResultBean<Map<String, Object>> weekCount() {
        List<Object[]> weekCount = dataService.findItemMonth();
        //周列表
        String[] weekdays = {Integer.parseInt(weekCount.get(0)[3].toString())-4+"",
                Integer.parseInt(weekCount.get(0)[3].toString())-3+"",
                Integer.parseInt(weekCount.get(0)[3].toString())-2+"",
                Integer.parseInt(weekCount.get(0)[3].toString())-1+"" };
        //商品列表
        Map<String, Object> res = getDateCount(weekdays,weekCount);
        return new ResultBean<>(res);
    }

    public Map<String, Object> getDateCount(String[] xData,List<Object[]> queryResult){
        //商品列表
        List<String> legends = new ArrayList<>();
        for (Object[] o : queryResult) {
            legends.add(o[1].toString());
        }
        Stream<String> stream = legends.stream();
        legends = stream.distinct().collect(Collectors.toList());
        //构建一周的数据
        List<Map<String, Object>> title_count = new ArrayList<>();
        for (int i = 0; i < legends.size(); i++) {
            Double[] count = new Double[xData.length];
            for (Object[] o : queryResult) {
                if (legends.get(i).toString().equals(o[1].toString())) {
                    String xdata_tmep=o[0].toString();
                    for (int j=0;j<xData.length;j++){
                        if (xData[j].equals(xdata_tmep)){
                            count[j]=(Double)o[2];
                            break;
                        }
                    }
                }
            }
            Map<String,Object> temp=new HashMap<>();
            temp.put("name",legends.get(i).toString());
            temp.put("data",nullToZero(count));
            temp.put("type","line");
            temp.put("stack","总量");
            title_count.add(temp);
        }
        Map<String,Object> res= new HashMap<>();
        res.put("legend",legends);
        res.put("xAxis",xData);
        res.put("series",title_count);
        return res;
    }

    public Double[] nullToZero(Double[] array){
        for (int i=0;i<array.length;i++){
            if (array[i]==null){
                array[i]=0.0;
            }
        }
        return array;
    }
}
