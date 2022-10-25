package dmacc.repository;

import org.springframework.stereotype.Repository;

import dmacc.beans.Order;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 22, 2022
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
