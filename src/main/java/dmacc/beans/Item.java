package dmacc.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name="ITEM")
public class Item {
	@Id
	@GeneratedValue
	int id;
	@Column(unique=true)
	String name;
	double price;
	boolean active;
}
