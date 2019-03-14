<html>
	<head>
		<link href="../css/startPageStyle.css" rel="stylesheet">
	</head>
	
	<body>
		<div class="mainblock">
			<form action="Enter" name="login" method="POST">
				<div class="imgLogo">
					<img src="../images/logo.svg" alt="">
				</div>
				<div class="inputCenter">
					<input type="text" name="email" placeholder="login" required autofocus>
					<input type="password" name="password" placeholder="password" required>
					<input class="btn" type="submit" name="submit" value="login">
				</div>
			</form>
			<div class="createAcc">
					Not registered? <a href="createAccount.jsp">Create an accaunt</a>
			</div>
		</div>
	</body>
	
</html>