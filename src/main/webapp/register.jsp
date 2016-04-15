<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
</head>
<body>
	<form action="reg" method="post">
		<div>
			<label for="email">Account Name: </label>
			<input type="email" name="email" placeholder="Input your email">
		</div>
		<div>
			<label for="password">Password: </label>
			<input type="password" name="password">
		</div>
		<div>
			<label for="confirmPwd">Confirm Password: </label>
			<input type="password" name="confirmPwd">
		</div>
		<div>
			<label for="firstName">First Name: </label>
			<input type="text" name="firstName">
		</div>
		<div>
			<label for="lastName">Last Name: </label>
			<input name="lastName">
		</div>
		<input type="submit" value="Register">
	</form>
</body>
</html>