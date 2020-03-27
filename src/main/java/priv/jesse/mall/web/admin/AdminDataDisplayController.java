package priv.jesse.mall.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import priv.jesse.mall.entity.OrderItem;
import priv.jesse.mall.entity.pojo.ResultBean;


import java.util.List;

@Controller
@RequestMapping("/admin/data_display")
public class AdminDataDisplayController {
    @RequestMapping("/toList.html")
    public String toList() {
        return "admin/data_display/list";
    }

//    @RequestMapping("/product_data.do")
//    public ResultBean<List<OrderItem>> getData(){
//        System.out.println(dataService.findByProductId(9));
//        List<OrderItem> orderItem=dataService.findByProductId(9);
//        return new ResultBean<List<OrderItem>>(orderItem);
//    }
}
