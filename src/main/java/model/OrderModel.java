package model;

import dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderModel {
     boolean saveOrder();
     List<OrderDto> allOrders() throws SQLException, ClassNotFoundException;
}
