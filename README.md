#SpringSecurity

#一、环境：
1.Eclipse

2.JDK1.5.x

3.Tomcat6.0以上

4.Maven3.3.3

#二、项目配置
1.pom.xml配置
```
	<dependency>
		<groupId>org.springframework.security</groupId>
		<artifactId>spring-security-web</artifactId>
		<version>3.0.3.RELEASE</version>
	</dependency>
	<dependency>
		<groupId>org.springframework.security</groupId>
		<artifactId>spring-security-config</artifactId>
		<version>3.0.3.RELEASE</version>
	</dependency>
	<dependency>
		<groupId>org.springframework.security</groupId>
		<artifactId>spring-security-taglibs</artifactId>
		<version>3.0.3.RELEASE</version>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-webmvc</artifactId>
		<version>3.0.3.RELEASE</version>
	</dependency>
```
	
2.web.xml配置
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns="http://java.sun.com/xml/ns/javaee"
xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
id="WebApp_ID" version="2.5">
  <display-name>SpringSecurityPrj</display-name>
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring/*.xml</param-value>
  </context-param>
  <filter>
    <filter-name>springSecurityFilterChain</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>springSecurityFilterChain</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  <welcome-file-list>
    <welcome-file>index.jsp</welcome-file>
  </welcome-file-list>
</web-app>
```
3.applicationContext-security.xml配置
```
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/security"
    xmlns:beans="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans 
        http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
        http://www.springframework.org/schema/security
        http://www.springframework.org/schema/security/spring-security-3.0.xsd">
    <!-- 自动配置模式，拦截所有请求，有ROLE_USER才可以通过 -->
    <http auto-config="true">
        <intercept-url pattern="/**" access="ROLE_USER"/>
    </http>
    <!-- 认证管理器。用户名密码都集成在配置文件中 --> 
    <authentication-manager>
        <authentication-provider>
            <user-service>
                <user name="liangw" password="liangw" authorities="ROLE_USER"/>
            </user-service>
        </authentication-provider>
    </authentication-manager>
</beans:beans>
```
4.登录成功后返回到index.jsp页面
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>登录首页</title>
</head>
<body>
<h2>登陆成功！</h2>
</body>
</html>
```
5.配置完成。


#三、Test

在浏览器地址栏里输入下面的url：
http://localhost:8080/SpringSecurityPrj/

会跳转到SpringSecurity内置的登录页面，输入用户名liangw和密码liangw，页面跳转到index.jsp，登录成功
(注意：此处的用户名和密码在applicationContext-security.xml配置文件里面有配置，
<user name="liangw" password="liangw" authorities="ROLE_USER"/>)

如果用户名名或密码输入错误，直接显示错误信息，不会跳转到index.jsp页面
