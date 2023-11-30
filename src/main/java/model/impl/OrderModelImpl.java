package model.impl;

import db.DBConnection;
import dto.ItemDto;
import dto.OrderDto;
import model.OrderModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderModelImpl implements OrderModel {
    @Override
    public boolean saveOrder() {
        return false;
    }

    @Override
    public List<OrderDto> allOrders() throws SQLException, ClassNotFoundException {
        List<OrderDto> list = new ArrayList<>();

        String sql = "SELECT * FROM orders";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            list.add(new OrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }

        return list;
    }

}
