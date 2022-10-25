package dmacc.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 22, 2022
 */
@Getter
@Setter
@Entity
@Table(name="ORDER_1")
public class Order {
	@Id
	@GeneratedValue
	int id;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinTable(name="ORDER_ITEM")
	List<Item> items;
	
	@OneToOne
	@JoinColumn(name="transaction_id")
	Transaction transaction;
	
	public Order() {
		super();
		items = new ArrayList<Item>();
	}
	
	public double total() {
		double sum = 0;
		for(Item i : items) {
			sum += i.price;
		}
		return sum;
	}
	public boolean isPaid() {
		if(transaction == null) {
			return false;
		}else {return true;}
	}
	
	public void removeItem(int itemId) {
		for(Item i : items) {
			if(i.getId() == itemId) {
				items.remove(i);
				break;
			}
		}
	}
}
