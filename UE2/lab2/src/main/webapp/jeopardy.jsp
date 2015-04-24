<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="jeopardyBean" scope="session" type="at.ac.tuwien.big.we15.lab2.api.impl.JeopardyBean" class="at.ac.tuwien.big.we15.lab2.api.impl.JeopardyBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Business Informatics Group Jeopardy! - Fragenauswahl</title>
<link rel="stylesheet" type="text/css" href="style/base.css" />
<link rel="stylesheet" type="text/css" href="style/screen.css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/framework.js" type="text/javascript"></script>
</head>
<body id="selection-page">
	<a class="accessibility" href="#question-selection">Zur
		Fragenauswahl springen</a>
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
				<td class="playerpoints"><%=jeopardyBean.getHumanScore()%> Eur</td>
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
				<td class="playerpoints"><%=jeopardyBean.getAiScore()%> Eur</td>
			</tr>
		</table>
		</section>
		<p id="round">Fragen: <%=jeopardyBean.getAskedQuestionCount()%> / 10</p>
		</section>

		<!-- Question -->
		<section id="question-selection" aria-labelledby="questionheading">
		<h2 id="questionheading" class="black accessibility">Jeopardy</h2>
		<%if(jeopardyBean.getAskedQuestionCount() > 0) {%>
			<p class="user-info <%=jeopardyBean.getUserCorrectStatus()%>-change">Du hast <%=jeopardyBean.getUserCorrectStatusText()%> geantwortet:
				<%=jeopardyBean.getUserEuroChangeStatus()%> Eur</p>
			<p class="user-info <%=jeopardyBean.getAiCorrectStatus()%>-change">Deadpool hat <%=jeopardyBean.getAiCorrectStatusText()%>
				geantwortet: <%=jeopardyBean.getAiEuroChangeStatus()%> Eur</p>
		<%}%>
		<p class="user-info">Deadpool hat TUWIEN für Eur 1000 gewählt.</p>
		<form action="BigJeopardyServlet" method="post">
			<fieldset>
				<legend class="accessibility">Fragenauswahl</legend>
				<section class="questioncategory" aria-labelledby="tvheading">
				<h3 id="tvheading" class="tile category-title">
					<span class="accessibility">Kategorie: </span>Web Eng.
				</h3>
				<ol class="category_questions">
					<li><input name="question_selection" id="question_1" value="1"
						type="radio" <%=jeopardyBean.isClickedButton(1) %> /><label class="tile clickable"
						for="question_1">Eur 100</label></li>
					<li><input name="question_selection" id="question_2" value="2"
						type="radio" <%=jeopardyBean.isClickedButton(2) %> /><label class="tile clickable" for="question_2">Eur
							200</label></li>
					<li><input name="question_selection" id="question_3" value="3"
						type="radio" <%=jeopardyBean.isClickedButton(3) %> /><label class="tile clickable" for="question_3">Eur
							500</label></li>
					<li><input name="question_selection" id="question_4" value="4"
						type="radio" <%=jeopardyBean.isClickedButton(4) %>/><label class="tile clickable" for="question_4">Eur
							750</label></li>
				</ol>
				</section>
				<section class="questioncategory" aria-labelledby="ssdheading">
				<h3 id="ssdheading" class="tile category-title">
					<span class="accessibility">Kategorie: </span>SSD
				</h3>
				<ol class="category_questions">
					<li><input name="question_selection" id="question_5" value="5"
						type="radio" <%=jeopardyBean.isClickedButton(5) %>/><label class="tile clickable" for="question_5">Eur
							100</label></li>
					<li><input name="question_selection" id="question_6" value="6"
						type="radio" <%=jeopardyBean.isClickedButton(6) %>/><label class="tile clickable" for="question_6">Eur
							200</label></li>
					<li><input name="question_selection" id="question_7" value="7"
						type="radio" <%=jeopardyBean.isClickedButton(7) %>/><label class="tile clickable" for="question_7">Eur
							500</label></li>
					<li><input name="question_selection" id="question_8" value="8"
						type="radio" <%=jeopardyBean.isClickedButton(8) %>/><label class="tile clickable" for="question_8">Eur
							750</label></li>
					<li><input name="question_selection" id="question_9" value="9"
						type="radio" <%=jeopardyBean.isClickedButton(9) %>/><label class="tile clickable" for="question_9">Eur
							1000</label></li>
				</ol>
				</section>
				<section class="questioncategory" aria-labelledby="webheading">
				<h3 id="webheading" class="tile category-title">
					<span class="accessibility">Kategorie: </span>Web Tech.
				</h3>
				<ol class="category_questions">
					<li><input name="question_selection" id="question_10"
						value="10" type="radio" <%=jeopardyBean.isClickedButton(10) %>/><label class="tile clickable"
						for="question_10">Eur 100</label></li>
					<li><input name="question_selection" id="question_11"
						value="11" type="radio" <%=jeopardyBean.isClickedButton(11) %>/><label class="tile clickable"
						for="question_11">Eur 200</label></li>
					<li><input name="question_selection" id="question_12"
						value="12" type="radio" <%=jeopardyBean.isClickedButton(12) %> /><label
						class="tile clickable" for="question_12">Eur 500</label></li>
					<li><input name="question_selection" id="question_13"
						value="13" type="radio" <%=jeopardyBean.isClickedButton(13) %>/><label class="tile clickable"
						for="question_13">Eur 750</label></li>
					<li><input name="question_selection" id="question_14"
						value="14" type="radio" <%=jeopardyBean.isClickedButton(14) %>/><label class="tile clickable"
						for="question_14">Eur 1000</label></li>
				</ol>
				</section>
				<section class="questioncategory" aria-labelledby="sportheading">
				<h3 id="sportheading" class="tile category-title">
					<span class="accessibility">Kategorie: </span>Internet
				</h3>
				<ol class="category_questions">
					<li><input name="question_selection" id="question_15"
						value="15" type="radio" <%=jeopardyBean.isClickedButton(15) %>/><label class="tile clickable"
						for="question_15">Eur 100</label></li>
					<li><input name="question_selection" id="question_16"
						value="16" type="radio" <%=jeopardyBean.isClickedButton(16) %> /><label
						class="tile clickable" for="question_16">Eur 200</label></li>
					<li><input name="question_selection" id="question_17"
						value="17" type="radio" <%=jeopardyBean.isClickedButton(17) %>/><label class="tile clickable"
						for="question_17">Eur 500</label></li>
					<li><input name="question_selection" id="question_18"
						value="18" type="radio" <%=jeopardyBean.isClickedButton(18) %>/><label class="tile clickable"
						for="question_18">Eur 750</label></li>
				</ol>
				</section>
				<section class="questioncategory" aria-labelledby="tuwienheading">
				<h3 id="tuwienheading" class="tile category-title">
					<span class="accessibility">Kategorie: </span>TUWIEN
				</h3>
				<ol class="category_questions">
					<li><input name="question_selection" id="question_19"
						value="19" type="radio" <%=jeopardyBean.isClickedButton(19) %>/><label class="tile clickable"
						for="question_19">Eur 100</label></li>
					<li><input name="question_selection" id="question_20"
						value="20" type="radio" <%=jeopardyBean.isClickedButton(20) %>/><label class="tile clickable"
						for="question_20">Eur 200</label></li>
					<li><input name="question_selection" id="question_21"
						value="21" type="radio" <%=jeopardyBean.isClickedButton(21) %>/><label class="tile clickable"
						for="question_21">Eur 500</label></li>
					<li><input name="question_selection" id="question_22"
						value="22" type="radio" <%=jeopardyBean.isClickedButton(22) %>/><label class="tile clickable"
						for="question_22">Eur 750</label></li>
					<li><input name="question_selection" id="question_23"
						value="23" type="radio" <%=jeopardyBean.isClickedButton(23) %> /><label
						class="tile clickable" for="question_23">Eur 1000</label></li>
				</ol>
				</section>
			</fieldset>

			<input class="greenlink formlink clickable" type="submit"
				accesskey="s" value="wählen" /> <input type="hidden"
				value="questionSubmitButtonClicked" name="action" />
		</form>
		</section>

		<section id="lastgame" aria-labelledby="lastgameheading">
		<h2 id="lastgameheading" class="accessibility">Letztes Spielinfo</h2>
		<p>Letztes Spiel: Nie</p>
		</section>
	</div>

	<!-- footer -->
	<footer role="contentinfo">C 2015 BIG Jeopardy!</footer>

	<script type="text/javascript">
            //<![CDATA[
            
            // initialize time
            $(document).ready(function() {
                // set last game
                if(supportsLocalStorage()) {
	                var lastGameMillis = parseInt(localStorage['lastGame'])
	                if(!isNaN(parseInt(localStorage['lastGame']))){
	                    var lastGame = new Date(lastGameMillis);
	                	$("#lastgame p").replaceWith('<p>Letztes Spiel: <time datetime="'
	                			+ lastGame.toUTCString()
	                			+ '">'
	                			+ lastGame.toLocaleString()
	                			+ '</time></p>')
	                }
            	}
            });            
            //]]>
        </script>
</body>
</html>