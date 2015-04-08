<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Login</title>
<link rel="stylesheet" type="text/css" href="style/screen.css" />
<link rel="stylesheet" type="text/css" href="style/base.css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/framework.js" type="text/javascript"></script>
</head>
<body id="login-page">
	<a class="accessibility" href="#login">Zum Login springen</a>
	<a class="accessibility" title="Klicke hier um dich zu registrieren"
		href="register.xhtml" accesskey="r">Registrieren</a>
	<!-- Header -->
	<header role="banner" aria-labelledby="bannerheading">
	<h1 id="bannerheading">
		<span class="accessibility">Business Informatics Group </span><span
			class="gametitle">Jeopardy!</span>
	</h1>
	</header>


	<!-- Content -->
	<div role="main">
		<section id="login" aria-labelledby="loginheading">
		<h2 id="loginheading" class="accessibility">Login</h2>
		<fieldset>
			<legend id="logindata">Login</legend>
			<label for="username">Benutzername:</label> <input name="username"
				id="username" type="text" required="required" /> <label
				for="password">Passwort:</label> <input name="password"
				id="password" type="password" required="required" />
			<form action="BigJeopardyServlet" method="post">
				<input class="greenlink formlink clickable" type="submit"
					value="Anmelden" /> <input type="hidden"
					value="signInButtonClicked" name="action" />
			</form>
		</fieldset>
		</section>
		<!-- Register section -->
		<section id="registerforward" aria-labelledby="registerheading">
		<h2 id="registerheading" class="accessibility">Registrierung</h2>
		<p id="registerhint">Nicht registriert?</p>
		<form action="BigJeopardyServlet" method="get">
			<input class="contentlink orangelink"
				title="Klicke hier um dich zu registrieren" type="submit" accesskey="r"
				value="Zur Registrierung" /> <input type="hidden" 
				value="registerButtonClicked" name="action" />
		</form>
		</section>
	</div>

	<!-- footer -->
	<footer role="contentinfo">© 2015 BIG Jeopardy</footer>
</body>
</html>