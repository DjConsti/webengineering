package controllers;

import java.io.Serializable;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import models.JeopardyGame;

public class HighscoreService implements Serializable {
	
	private SOAPConnectionFactory soapConnectionFactory;
    private MessageFactory messageFactory;

	public HighscoreService() {
		try {
			soapConnectionFactory = SOAPConnectionFactory.newInstance();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			messageFactory = MessageFactory.newInstance();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String postHighscore(JeopardyGame game) {
		SOAPConnection soap = null;
		try {
			soap = soapConnectionFactory.createConnection();
			SOAPMessage message = messageFactory.createMessage();
			//SOAPEnvelope soapEnvelope = message.getSOAPPart().getEnvelope();
			SOAPBody soapBody = message.getSOAPPart().getEnvelope().getBody();
			SOAPElement highScoreRequest = soapBody.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "HighScoreRequest", "data"));
	        SOAPElement userKey = highScoreRequest.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "UserKey", "data"));
	        userKey.setValue("3ke93-gue34-dkeu9");
	        SOAPElement userData = highScoreRequest.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "UserData", "data"));
	        
	        SOAPElement loser = userData.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "Loser", "data"));
	        loser.setAttribute("Gender", game.getLoser().getUser().getGender().toString());
	        loser.setAttribute("BirthDate", game.getLoser().getUser().getBirthDate().toString());
	        SOAPElement firstname = loser.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "FirstName", "data"));
	        firstname.setValue(game.getLoser().getUser().getFirstName());
	        SOAPElement lastname = loser.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "LastName", "data"));
	        lastname.setValue(game.getLoser().getUser().getLastName());
	        SOAPElement password = loser.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "Password", "data"));
	        //password.setValue(game.getLoser().getUser().getPassword());
	        SOAPElement points = loser.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "Points", "data"));
	        points.setValue(String.valueOf(game.getLoser().getProfit()));
	        
	        SOAPElement winner = userData.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "Winner", "data"));
	        winner.setAttribute("Gender", game.getWinner().getUser().getGender().toString());
	        winner.setAttribute("BirthDate", game.getWinner().getUser().getBirthDate().toString());
	        firstname = winner.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "FirstName", "data"));
	        firstname.setValue(game.getWinner().getUser().getFirstName());
	        lastname = winner.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "LastName", "data"));
	        lastname.setValue(game.getWinner().getUser().getLastName());
	        password = winner.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "Password", "data"));
	        //password.setValue(game.getWinner().getUser().getPassword());
	        points = winner.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "Points", "data"));
	        points.setValue(String.valueOf(game.getWinner().getProfit()));
	        
	        SOAPMessage reply = soap.call(message, "http://playground.big.tuwien.ac.at:8080/highscoreservice/PublishHighScoreService?wsdl");
		
	        String uuid = ((SOAPElement)(reply.getSOAPBody().getChildElements(new QName("http://big.tuwien.ac.at/we/highscore/data", "HighScoreResponse")).next())).getValue();
			System.out.println(uuid);
			//TODO play.logger
			return uuid;
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Could not connect";
	}

}
