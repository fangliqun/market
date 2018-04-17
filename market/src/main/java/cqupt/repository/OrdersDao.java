package cqupt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cqupt.domain.Orders;


public interface OrdersDao	extends JpaRepository<Orders, Integer> {
	@Modifying
	@Transactional
	@Query("delete from Orders o where o.orderid=?1 ")
	public int deleteOrders(int orderid);
	
	
	@Query("from Orders o where o.time=?1 ")//?
	public List<Orders> selectOrders(String time);
	
}
