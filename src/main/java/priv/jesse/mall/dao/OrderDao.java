package priv.jesse.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import priv.jesse.mall.entity.Order;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderDao extends JpaRepository<Order, Integer> {

    /**
     * 更改订单状态
     * @param state
     * @param id
     */
    @Modifying
    @Transactional
    @Query(value = "update `order` o set o.state=?1 where o.id=?2",nativeQuery = true)
    void updateState(int state,int id);

    @Modifying
    @Transactional
    @Query(value = "insert into date_count(date,count) values(?,1)" +
            "ON DUPLICATE KEY UPDATE date=CURDATE(),count=count+1",nativeQuery = true)
    void updateCount(String data);

    /**
     * 查找用户的订单
     * @param userId
     * @return
     */
    List<Order> findByUserId(int userId);

    List<Order> findAll();

    @Query(value = "select p.title , count(p.title) as `count`  \n" +
            "FROM `order` o INNER JOIN order_item ot on ot.order_id=o.id\n" +
            "INNER JOIN product p on p.id=ot.product_id\n" +
            "WHERE date(o.order_time)=date(now()) and o.state!=1\n" +
            "GROUP BY p.title\n",nativeQuery = true)
    List<Object[]> findOrderToday();

    @Query(value = "select SUBSTRING_INDEX(`order`.addr,'省',1) as '地区',count('地区') AS '数量'\n" +
            "from `order`\n" +
            "GROUP BY SUBSTRING_INDEX(`order`.addr,'省',1)",nativeQuery = true)
    List<Object[]> findOrderCountByArea();

    @Query(value = "select dayname(o.order_time),p.title,sum(ot.sub_total)\n" +
            "FROM `order` o INNER JOIN order_item ot on ot.order_id=o.id\n" +
            "INNER JOIN product p on p.id=ot.product_id\n" +
            "where p.id in(\n" +
            "\tselect t.id from(\n" +
            "\t\tselect p.* \n" +
            "\t\tfrom `order` o inner join order_item ot on o.id=ot.order_id\n" +
            "\t\tINNER JOIN product p on p.id=ot.product_id\n" +
            "\t\tWHERE week(now())=week(o.order_time)\n" +
            "\t\tGROUP BY p.id\n" +
            "\t\tORDER BY count(p.id) desc \n" +
            "\t\tlimit 3\n" +
            "\t) as t\n" +
            ")\n" +
            " GROUP BY dayname(o.order_time),p.title\n" +
            "\n",nativeQuery = true)
    List<Object[]> findItemWeek();


    @Query(value = "select week(o.order_time),p.title,sum(ot.sub_total),week(LAST_DAY(NOW()))\n" +
            "FROM `order` o INNER JOIN order_item ot on ot.order_id=o.id\n" +
            "INNER JOIN product p on p.id=ot.product_id\n" +
            "where p.id in(\n" +
            "\tselect t.id from(\n" +
            "\t\tselect p.* \n" +
            "\t\tfrom `order` o inner join order_item ot on o.id=ot.order_id\n" +
            "\t\tINNER JOIN product p on p.id=ot.product_id\n" +
            "\t\tWHERE month(now())=month(o.order_time)\n" +
            "\t\tGROUP BY p.id\n" +
            "\t\tORDER BY count(p.id) desc \n" +
            "\t\tlimit 3\n" +
            "\t) as t\n" +
            ")\n" +
            " GROUP BY week(o.order_time),p.title\n" +
            "\n" ,nativeQuery = true)
    List<Object[]> findItemMonth();
}
