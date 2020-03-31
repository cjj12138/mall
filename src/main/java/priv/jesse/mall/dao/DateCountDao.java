package priv.jesse.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import priv.jesse.mall.entity.DateCount;

import javax.transaction.Transactional;
import java.util.List;

public interface DateCountDao extends JpaRepository<DateCount,Integer> {

    @Modifying
    @Transactional
    @Query(value = " SELECT * FROM date_count ORDER BY date" ,nativeQuery = true)
    List<DateCount> findAll();
}
