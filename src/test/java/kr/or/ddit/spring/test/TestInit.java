package kr.or.ddit.spring.test;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/spring/application-context.xml"
								, "classpath:kr/or/ddit/spring/context-datasource.xml"
								, "classpath:kr/or/ddit/spring/context-transaction.xml"
								})
public class TestInit {
	
	@Before
	public void setup() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("kr/or/ddit/db/initDb.sql"));
		
		//에러발생시 정지시키면서 데이터 롤백
		populator.setContinueOnError(false);
		
		BasicDataSource datasource = new BasicDataSource();
		//url, driverClassname, username, password
		datasource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		/*
		 * 테스트 계정인지 확인 할것~!
		 */
		datasource.setUsername("boardtest");
		datasource.setPassword("java");
		
		DatabasePopulatorUtils.execute(populator, datasource);
	}
	
}
