package com.nhom2.sharingblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.autoconfigure.jdbc.*;

@SpringBootTest(exclude = {DataSourceAutoConfiguration.class })
class SharingblogApplicationTests {

	@Test
	void contextLoads() {
	}

}
