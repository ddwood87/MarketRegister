package dmacc.controller;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dmacc.beans.Item;
import dmacc.beans.Order;
import dmacc.beans.Transaction;
import dmacc.repository.ItemRepository;
import dmacc.repository.OrderRepository;
import dmacc.repository.TransactionRepository;

/**
 * @author dominicwood - ddwood2@dmacc.edu CIS175 - Fall 2022 Oct 22, 2022
 */
@Controller
public class WebController {
	@Autowired
	ItemRepository itemRepo;
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	TransactionRepository transactionRepo;

	@GetMapping({ "/", "/viewAllItems" })
	public String viewAllItems(Model model) {
		if (itemRepo.findAll().isEmpty()) {
			starterData();
			// return addItem(model);
		}
		model.addAttribute("items", itemRepo.findAll());
		return "/listItems.html";
	}

	@GetMapping("/addItem")
	public String addItem(Model model) {
		Item i = new Item();
		model.addAttribute("item", i);
		model.addAttribute("addOrEdit", "Add");
		return "/input.html";
	}

	@PostMapping("/addItem")
	public String addItem(@ModelAttribute Item i, Model model) {
		itemRepo.save(i);
		return viewAllItems(model);
	}

	@GetMapping("/editItem/{id}")
	public String editItem(@PathVariable("id") int id, Model model) {
		Item i = itemRepo.findById(id).orElse(null);
		model.addAttribute("item", i);
		model.addAttribute("addOrEdit", "Edit");
		return "/input.html";
	}

	@GetMapping("/deleteItem/{id}")
	public String deleteItem(@PathVariable("id") int id, Model model) {
		Item i = itemRepo.findById(id).orElse(null);
		List<Order> lo = orderRepo.findAll();
		for (Order o : lo) {
			if (o.getItems().contains(i)) {
				o.removeItem(i.getId());
			}
		}
		itemRepo.delete(i);
		return viewAllItems(model);
	}

	@GetMapping("removeFromOrder/{order_id}/{item_id}")
	public String removeFromOrder(@PathVariable("order_id") int orderId, @PathVariable("item_id") int itemId,
			Model model) {
		Order o = orderRepo.findById(orderId).orElse(null);
		o.removeItem(itemId);
		orderRepo.save(o);
		return orderDetail(o.getId(), model);
	}

	@GetMapping("/viewAllOrders")
	public String viewAllOrders(Model model) {
		List<Order> l = orderRepo.findAll();
		// Map<Integer, Boolean> paid = new HashMap<Integer, Boolean>();
		// Map<Integer, Double> total = new HashMap<Integer, Double>();
		/*
		 * int length = l.size(); for(int i = 0; i < length; i++) {
		 * paid.put(l.get(i).getId(), l.get(i).isPaid()); total.put(l.get(i).getId(),
		 * l.get(i).total()); }
		 */
		// model.addAttribute("total", total);
		model.addAttribute("orders", l);
		return "listOrders";
	}

	@PostMapping("/createOrder")
	public String createOrder(@RequestParam(value = "active", required = false) String[] active, Model model) { // @ModelAttribute("active")
																												// String[]
																												// active
		Order o = new Order();
		System.out.println(model.toString());
		List<Integer> ids = new ArrayList<Integer>();
		for (String s : active) {
			ids.add(Integer.parseInt(s));
		}
		o.setItems(itemRepo.findAllById(ids));
		o = orderRepo.save(o);
		return orderDetail(o.getId(), model);
	}

	@GetMapping("/orderDetail/{id}")
	public String orderDetail(@PathVariable("id") int id, Model model) {
		Order o = orderRepo.findById(id).orElse(null);
		model.addAttribute("order", o);
		model.addAttribute("orderTotal", o.total());
		return "/orderDetail.html";
	}
	
	@GetMapping("/createTransaction/{id}")
	public String createTransaction(@PathVariable("id") int orderId, Model model) {
		Order o = orderRepo.findById(orderId).orElse(null);
		Transaction t = new Transaction();
		t.setOrder(o);
		model.addAttribute("transaction", t);
		return "signTransaction.html";
	}
	
	@PostMapping("/createTransaction")
	public String createTransaction(@ModelAttribute Transaction t, Model model) {
		t = transactionRepo.save(t);
		model.addAttribute("transaction", t);
		return "/transactionDetail.html";
	}

	@GetMapping("/viewAllTransactions")
	public String viewAllTransactions(Model model) {
		List<Transaction> l = transactionRepo.findAll();
		model.addAttribute("transactions", l);
		return "/listTransactions";
	}

	public void starterData() {
		for (int i = 0; i < 3; i++) {
			Item item = new Item();
			item.setName("Name" + i);
			item.setPrice(3.50 + i);
			itemRepo.save(item);
		}
	}
}
