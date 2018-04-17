package cqupt.service.orders.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import cqupt.domain.Goods;
import cqupt.domain.Orders;
import cqupt.repository.OrdersDao;
import cqupt.service.orders.OrdersService;
@Component
public class OrdersServiceImp implements OrdersService{
	@Autowired
	private OrdersDao ordersDao;
	
	@Override
	public void deleteOrders(int orderid) {
		ordersDao.delete(orderid);
	}
	
	@Override
	public Orders insertOrders(Orders order){
		return ordersDao.saveAndFlush(order);
	}

	@Override
	public Page<Orders> selectOrders(Pageable p) {
		return ordersDao.findAll(p);
	}

	@Override
	public List<Orders> selectOrders(String time) {
		return ordersDao.selectOrders(time);
	}

	@Override
	public Orders selectOrder(int orderid) {
		return ordersDao.findOne(orderid);
	}

	@Override
	public List<Orders> selectOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> selectAllOrder() {
		return ordersDao.findAll();
	}

}
