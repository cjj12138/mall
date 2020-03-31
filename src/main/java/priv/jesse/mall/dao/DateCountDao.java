package priv.jesse.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import priv.jesse.mall.entity.DateCount;
import priv.jesse.mall.entity.Product;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DateCountDao extends JpaRepository<DateCount,Integer> {

    @Modifying
    @Transactional
    @Query(value = " SELECT * FROM date_count ORDER BY date" ,nativeQuery = true)
    List<DateCount> findAll();

    @Modifying
    @Transactional
    @Query(value = "select p.*,COUNT(p.id) as'count'   FROM product p INNER JOIN order_item ot on ot.product_id=p.id INNER JOIN `order` o on o.id=ot.order_id GROUP BY p.id ORDER BY count(p.id) desc",nativeQuery = true)
    List<HashMap> findCount();
}
