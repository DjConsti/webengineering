<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="jeopardyBean" scope="session" type="at.ac.tuwien.big.we15.lab2.api.impl.JeopardyBean" class="at.ac.tuwien.big.we15.lab2.api.impl.JeopardyBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Business Informatics Group Jeopardy! - Gewinnanzeige</title>
<link rel="stylesheet" type="text/css" href="style/base.css" />
<link rel="stylesheet" type="text/css" href="style/screen.css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/framework.js" type="text/javascript"></script>
</head>
<body id="winner-page">
	<a class="accessibility" href="#winner">Zur Gewinnanzeige springen</a>
	<!-- Header -->
	<header role="banner" aria-labelledby="bannerheading">
	<h1 id="bannerheading">
		<span class="accessibility">Business Informatics Group </span><span
			class="gametitle">Jeopardy!</span>
	</h1>
	</header>

	<!-- Navigation -->
	<nav role="navigation" aria-labelledby="navheading">
	<h2 id="navheading" class="accessibility">Navigation</h2>
	<form action="BigJeopardyServlet" method="get">
		<input class="orangelink navigationlink"
			title="Klicke hier um dich abzumelden" type="submit" value="Abmelden" />
		<input type="hidden" value="logoutlinkButtonClicked" name="action" />
	</form>
	</nav>

	<!-- Content -->
	
	<div role="main">
		<section id="gameinfo" aria-labelledby="winnerinfoheading">
		<h2 id="winnerinfoheading" class="accessibility">Gewinnerinformationen</h2>
		<p class="user-info <%=jeopardyBean.getUserCorrectStatus()%>-change">Du hast <%=jeopardyBean.getUserCorrectStatusText()%> geantwortet:
				<%=jeopardyBean.getUserEuroChangeStatus()%> Eur</p>
			<p class="user-info <%=jeopardyBean.getAiCorrectStatus()%>-change">Deadpool hat <%=jeopardyBean.getAiCorrectStatusText()%>
				geantwortet: <%=jeopardyBean.getAiEuroChangeStatus()%> Eur</p>
		<section class="playerinfo leader"
			aria-labelledby="winnerannouncement">
		<h3 id="winnerannouncement">Gewinner: Black Widow</h3>
		<img class="avatar" src="img/avatar/black-widow.png"
			alt="Spieler-Avatar Black Widow" />
		<table>
			<tr>
				<th class="accessibility">Spielername</th>
				<td class="playername">Black Widow</td>
			</tr>
			<tr>
				<th class="accessibility">Spielerpunkte</th>
				<td class="playerpoints"><%=jeopardyBean.getHumanScore()%> Eur</td>
			</tr>
		</table>
		</section> <section class="playerinfo" aria-labelledby="loserheading">
		<h3 id="loserheading" class="accessibility">Verlierer: Deadpool</h3>
		<img class="avatar" src="img/avatar/deadpool_head.png"
			alt="Spieler-Avatar Deadpool" />
		<table>
			<tr>
				<th class="accessibility">Spielername</th>
				<td class="playername">Deadpool</td>
			</tr>
			<tr>
				<th class="accessibility">Spielerpunkte</th>
				<td class="playerpoints"><%=jeopardyBean.getAiScore()%> Eur</td>
			</tr>
		</table>
		</section> </section>
		<section id="newgame" aria-labelledby="newgameheading">
		<h2 id="newgameheading" class="accessibility">Neues Spiel</h2>

		<form action="BigJeopardyServlet" method="get">
			<input class="clickable orangelink contentlink" type="submit"
				value="Neues Spiel" /> <input type="hidden"
				value="restartButtonClicked" name="action" />
		</form>
		</section>
	</div>
	<!-- footer -->
	<footer role="contentinfo">© 2015 BIG Jeopardy</footer>
	<script type="text/javascript">
        //<![CDATA[
           $(document).ready(function(){
         	   if(supportsLocalStorage()){
         		   localStorage["lastGame"] = new Date().getTime();
         	   }
           });
        //]]>
        </script>
</body>
</html>