package cqupt.service.orders.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import cqupt.client.OrdersClient;
import cqupt.domain.Goods;
import cqupt.domain.Orders;
import cqupt.repository.OrdersDao;
import cqupt.service.orders.OrdersService;
@Component
public class OrdersServiceImp implements OrdersService{
	@Autowired
	private OrdersClient ordersClient;
	
	@Override
	public void deleteOrders(int orderid) {
		ordersClient.deleteOrders(orderid);
	}
	
	@Override
	public Orders insertOrders(Orders order){
		return ordersClient.insertOrders(order.getGoodsname(),order.getTime(),order.getUsername(),order.getNumber());
	}


	@Override
	public String selectOrders(Integer start, Integer limit) {
		return ordersClient.selectOrders(start,limit);
	}

	@Override
	public Orders selectOrder(int orderid) {
		return ordersClient.selectOrder(orderid);
	}

	@Override
	public List<Orders> selectOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectAllOrder() {
		return ordersClient.selectAllOrder();
	}

	@Override
	public String selectAllOrderGoodsname() {
		return ordersClient.selectAllOrderGoodsname();
	}

}
