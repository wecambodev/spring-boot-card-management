package com.mbanq.banksystem;

import com.mbanq.banksystem.service.ConsumerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankSystemApplicationTests {

	@Autowired
	private ConsumerService consumerService;
	@Test
	public void contextLoads() {

	}

}
