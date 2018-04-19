package cqupt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cqupt.domain.Orders;

	
public interface OrdersDao	extends JpaRepository<Orders, Integer>,JpaSpecificationExecutor<Orders> {//JpaSpecificationExecutor<Orders>
	@Modifying
	//@Transactional?是否会影响之前的事务
	@Query("delete from Orders o where o.orderid=?1 ")
	public int deleteOrders(int orderid);
	
	
	@Query("from Orders o where o.time=?1 ")//?
	public List<Orders> selectOrders(String time);
	
	@Query("from Orders o where o.username=?1 ")//?
	public List<Orders> selectOrdersByusername(String username);
	
}
