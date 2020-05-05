package priv.jesse.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import priv.jesse.mall.entity.DateCount;
import priv.jesse.mall.entity.NameValue;
import priv.jesse.mall.entity.Product;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DateCountDao extends JpaRepository<DateCount,Integer> {

    @Modifying
    @Transactional
    @Query(value = " SELECT date(`order`.order_time )as'日期',count(1) as'销售量' \n" +
            "FROM `order`\n" +
            "GROUP BY date(`order`.order_time)\n" +
            "ORDER BY date(`order`.order_time)  desc\n" +
            "limit 7\n" ,nativeQuery = true)
    List<Object[]> findOrderCountByDay();

    @Modifying
    @Transactional
    @Query(value = "select p.*,COUNT(p.id) as'count'   FROM product p INNER JOIN order_item ot on ot.product_id=p.id INNER JOIN `order` o on o.id=ot.order_id GROUP BY p.id ORDER BY count(p.id) desc",nativeQuery = true)
    List<HashMap> findCount();

    @Modifying
    @Transactional
    @Query(value = "SELECT count(b.product_id) AS value ,a.`desc` AS name  FROM product a\n" +
            "inner JOIN order_item b ON a.id=b.product_id\n" +
            "GROUP BY b.product_id ",nativeQuery = true)
    List<Object[]> findPie();


}
