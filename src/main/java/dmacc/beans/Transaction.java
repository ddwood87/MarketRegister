package dmacc.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 22, 2022
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="TRANSACTION")
public class Transaction {
	@Id
	@GeneratedValue
	int id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="order_id")
	Order order;
	String customer;
}
