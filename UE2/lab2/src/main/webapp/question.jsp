<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="jeopardyBean" scope="session" type="at.ac.tuwien.big.we15.lab2.api.impl.JeopardyBean" class="at.ac.tuwien.big.we15.lab2.api.impl.JeopardyBean" />
<%@page import="at.ac.tuwien.big.we15.lab2.api.Question"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Business Informatics Group Jeopardy! - Fragenbeantwortung</title>
<link rel="stylesheet" type="text/css" href="style/base.css" />
<link rel="stylesheet" type="text/css" href="style/screen.css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/framework.js" type="text/javascript"></script>
</head>
<body id="questionpage">
	<a class="accessibility" href="#questions">Zur Fragenauswahl
		springen</a>
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
		<!-- info -->
		<section id="gameinfo" aria-labelledby="gameinfoinfoheading">
		<h2 id="gameinfoinfoheading" class="accessibility">Spielinformationen</h2>
		<section id="firstplayer" class="playerinfo leader"
			aria-labelledby="firstplayerheading">
		<h3 id="firstplayerheading" class="accessibility">Führender
			Spieler</h3>
		<img class="avatar" src="img/avatar/black-widow_head.png"
			alt="Spieler-Avatar Black Widow" />
		<table>
			<tr>
				<th class="accessibility">Spielername</th>
				<td class="playername">Black Widow (Du)</td>
			</tr>
			<tr>
				<th class="accessibility">Spielerpunkte</th>
				<td class="playerpoints">2000 Eur</td>
			</tr>
		</table>
		</section> <section id="secondplayer" class="playerinfo"
			aria-labelledby="secondplayerheading">
		<h3 id="secondplayerheading" class="accessibility">Zweiter
			Spieler</h3>
		<img class="avatar" src="img/avatar/deadpool_head.png"
			alt="Spieler-Avatar Deadpool" />
		<table>
			<tr>
				<th class="accessibility">Spielername</th>
				<td class="playername">Deadpool</td>
			</tr>
			<tr>
				<th class="accessibility">Spielerpunkte</th>
				<td class="playerpoints">400 Eur</td>
			</tr>
		</table>
		</section>
		<p id="round">Frage: <%=jeopardyBean.getAskedQuestionCount()%> / 10</p>
		</section>
		<% 	// neue Frage holen
			Question q = jeopardyBean.getQuestion();
			jeopardyBean.getAllAnswers();
		%>
		<!-- Question -->
		<section id="question" aria-labelledby="questionheading">
		<form action="BigJeopardyServlet" method="post">
			<h2 id="questionheading" class="accessibility">Frage</h2>
			<p id="questiontype"><%=q.getCategory().getName()%></p>
			<p id="questiontext"><%=q.getText()%></p>
			<ul id="answers">
				
				<li><input name="answers" id="answer_1" value="<%=jeopardyBean.getCurrentAnswers().get(0).getId()%>"
					type="checkbox" /><label class="tile clickable" for="answer_1">
					<%=jeopardyBean.getCurrentAnswers().get(0).getText()%>
					</label></li>
				<li><input name="answers" id="answer_2" value="<%=jeopardyBean.getCurrentAnswers().get(1).getId()%>"
					type="checkbox" /><label class="tile clickable" for="answer_2">
					<%=jeopardyBean.getCurrentAnswers().get(1).getText()%></label></li>
				<li><input name="answers" id="answer_3" value="<%=jeopardyBean.getCurrentAnswers().get(2).getId()%>"
					type="checkbox" /><label class="tile clickable" for="answer_3">
					<%=jeopardyBean.getCurrentAnswers().get(2).getText()%></label></li>
				<li><input name="answers" id="answer_4" value="<%=jeopardyBean.getCurrentAnswers().get(3).getId()%>"
					type="checkbox" /><label class="tile clickable" for="answer_4">
					<%=jeopardyBean.getCurrentAnswers().get(3).getText()%></label></li>
			</ul>
			<input id="timeleftvalue" type="hidden" value="100" /> <input
				class="greenlink formlink clickable" type="submit" accesskey="s"
				value="wählen" /> <input type="hidden" value="submitButtonClicked"
				name="action" />
		</form>
		</section>

		<section id="timer" aria-labelledby="timerheading">
		<h2 id="timerheading" class="accessibility">Timer</h2>
		<p>
			<span id="timeleftlabel">Verbleibende Zeit:</span>
			<time id="timeleft">00:30</time>
		</p>
		<meter id="timermeter" min="0" low="20" value="100" max="100" /> </section>
	</div>

	<!-- footer -->
	<footer role="contentinfo">© 2015 BIG Jeopardy!</footer>

	<script type="text/javascript">
            //<![CDATA[
            
            // initialize time
            $(document).ready(function() {
                var maxtime = 30;
                var hiddenInput = $("#timeleftvalue");
                var meter = $("#timer meter");
                var timeleft = $("#timeleft");
                
                hiddenInput.val(maxtime);
                meter.val(maxtime);
                meter.attr('max', maxtime);
                meter.attr('low', maxtime/100*20);
                timeleft.text(secToMMSS(maxtime));
            });
            
            // update time
            function timeStep() {
                var hiddenInput = $("#timeleftvalue");
                var meter = $("#timer meter");
                var timeleft = $("#timeleft");
                
                var value = $("#timeleftvalue").val();
                if(value > 0) {
                    value = value - 1;   
                }
                
                hiddenInput.val(value);
                meter.val(value);
                timeleft.text(secToMMSS(value));
                
                if(value <= 0) {
                    $('#questionform').submit();
                }
            }
            
            window.setInterval(timeStep, 1000);
            
            //]]>
        </script>
</body>
</html>