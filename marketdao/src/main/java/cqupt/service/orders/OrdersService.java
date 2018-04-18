package cqupt.service.orders;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cqupt.domain.Goods;
import cqupt.domain.Orders;

public interface OrdersService {
	void deleteOrders(int orderid);
	
	Orders insertOrders(Orders order);//保存,更新时覆盖??
	
	List<Orders> selectOrders();
	
	List<Orders> selectOrders(String time);
	
	List<Orders> selectOrdersByusername(String username);
	
	Orders selectOrder(int orderid);
	
	Page<Orders> selectOrders(Pageable p);
	
	List<Orders> selectAllOrder();
	
}
