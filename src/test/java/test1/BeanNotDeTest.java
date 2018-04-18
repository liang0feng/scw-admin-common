package test1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.scw.common.service.PermissionService;


//"classpath:spring-mybatis.xml",
//"classpath:spring-tx.xml"

//<!-- 引入外部属性文件 -->
//<context:property-placeholder location="classpath:dbconfig.properties,classpath:email.properties" ignore-unresolvable="true"/>
//<!--1、配置数据源  -->
//<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
//	<property name="username" value="${jdbc.username}"></property>
//	<property name="password" value="${jdbc.password}"></property>
//	<property name="url" value="${jdbc.url}"></property>
//	<property name="driverClassName" value="${jdbc.driver}"></property>
//</bean>
@ContextConfiguration(locations= {
		"classpath:spring-beans.xml",
		"classpath:spring-mybatis.xml",
		"classpath:spring-tx.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class BeanNotDeTest {
	
//	@Autowired
//	PermissionService permissionService;

	@Test
	public void Beantest() {
//		System.out.println(permissionService);
	}
}
