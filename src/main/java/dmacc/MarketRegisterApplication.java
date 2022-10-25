package dmacc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dmacc.beans.Item;
import dmacc.controller.WebController;
import dmacc.repository.ItemRepository;

@SpringBootApplication
public class MarketRegisterApplication {
	@Autowired
	ItemRepository itemRepo;
	public static void main(String[] args) {
		SpringApplication.run(MarketRegisterApplication.class, args);		
	}

}
