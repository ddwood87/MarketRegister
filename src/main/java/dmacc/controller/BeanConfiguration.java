package dmacc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dmacc.beans.Item;
import dmacc.beans.Order;
import dmacc.beans.Transaction;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 22, 2022
 */
@Configuration
public class BeanConfiguration {
	@Bean
	public Item item() {
		Item i = new Item();
		return i;
	}
	@Bean
	public Order order() {
		Order o = new Order();
		return o;
	}
	@Bean
	public Transaction transaction() {
		Transaction t = new Transaction();
		return t;
	}
}
