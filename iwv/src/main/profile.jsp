<!DOCTYPE html>
<html>
    <head>
        <title>Internetowa Wypożyczalnia Video - Nowy film</title>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-2 ">
    </head>
    <body>
        <h1>Nowy film</h1>
		<form action="AddNewFIlm" method="post"> 
			<table>
				<tr><td>Login:</td><td><input type="text" name="username" size="20px"></td></tr>
				<tr><td>E-Mail:</td><td><input type="text" name="email" size="20px"></td></tr>
				<tr><td>Hasło:</td><td><input type="password" name="password" size="20px"></td></tr>
				<tr><td>Ponownie Hasło:</td><td><input type="password" name="password2" size="20px"></td></tr>
				<tr><td>Telefon:</td><td><input type="text" name="phone" size="20px"></td></tr>
			</table>
		<input type="submit" value="Dodaj nowy film">
		</form>
    </body>
</html>