package cqupt.service.orders.imp;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
	public Page<Orders> selectOrders(Pageable p,final String username) {
		if(username.equals("hq")) {
			return ordersDao.findAll(p);
		}
		Specification<Orders> specification = new Specification<Orders>() {
			@Override
			public javax.persistence.criteria.Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
			    Predicate condition  = (Predicate) criteriaBuilder.equal(root.get("username"), username);
				return criteriaBuilder.and(condition);
			}
		};
		return ordersDao.findAll(specification,p);
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
		return null;
	}

	@Override
	public List<Orders> selectAllOrder() {
		return ordersDao.findAll();
	}

	@Override
	public List<Orders> selectOrdersByusername(String username) {
		return ordersDao.selectOrdersByusername(username);
	}

}
