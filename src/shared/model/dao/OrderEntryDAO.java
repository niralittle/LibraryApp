package shared.model.dao;

import shared.model.vo.OrderEntry;

public interface OrderEntryDAO extends DAO<OrderEntry> {

    void createOrder(OrderEntry orderEntry);
}
