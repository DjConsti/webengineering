package controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Attr;

import play.Logger;
import twitter.TwitterStatusMessage;
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
			SOAPPart sp = message.getSOAPPart();
			sp.getEnvelope().setAttribute("xmlns:data","http://big.tuwien.ac.at/we/highscore/data");
			//SOAPEnvelope soapEnvelope = message.getSOAPPart().getEnvelope();
			SOAPBody soapBody = message.getSOAPPart().getEnvelope().getBody();
			SOAPElement highScoreRequest = soapBody.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "HighScoreRequest", "data"));
	        SOAPElement userKey = highScoreRequest.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "UserKey", "data"));
	        userKey.setValue("3ke93-gue34-dkeu9");
	        SOAPElement userData = highScoreRequest.addChildElement(new QName("http://big.tuwien.ac.at/we/highscore/data", "UserData", "data"));
	        QName tmp = new QName("UserData");
	        SOAPElement loser = userData.addChildElement(new QName("Loser"));
	        if(game.getLoser().getUser().getGender()!=null)loser.setAttribute("Gender", game.getLoser().getUser().getGender().toString());
	        
	        if(game.getLoser().getUser().getBirthDate() != null){
	        	loser.setAttribute("BirthDate", new SimpleDateFormat("yyyy-MM-dd").format(game.getLoser().getUser().getBirthDate()));
	        }else
	        {
	        	loser.setAttribute("BirthDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	        }
	        
	        SOAPElement firstname = loser.addChildElement(new QName("FirstName"));
	        if(game.getLoser().getUser().getFirstName()!=null && !game.getLoser().getUser().getFirstName().equals(""))
	        	firstname.setValue(game.getLoser().getUser().getFirstName());
	        else
	        	firstname.setValue("Max");
	        
	        SOAPElement lastname = loser.addChildElement(new QName("LastName"));
	        if(game.getLoser().getUser().getLastName()!= null && !game.getLoser().getUser().getLastName().equals(""))
	        	lastname.setValue(game.getLoser().getUser().getLastName());
	        else
	        	lastname.setValue("Mustermann");
	        
	        SOAPElement password = loser.addChildElement(new QName("Password"));
	        //password.setValue(game.getLoser().getUser().getPassword());
	        SOAPElement points = loser.addChildElement(new QName("Points"));
	        points.setValue(String.valueOf(game.getLoser().getProfit()));
	        
	        SOAPElement winner = userData.addChildElement(new QName("Winner"));
	        if(game.getWinner().getUser().getGender()!=null)
	        	winner.setAttribute("Gender", game.getWinner().getUser().getGender().toString());
	        
	        if(game.getWinner().getUser().getBirthDate() != null)
	        {
	        		winner.setAttribute("BirthDate", new SimpleDateFormat("yyyy-MM-dd").format(game.getWinner().getUser().getBirthDate()));
	        }else
	        {
	        		winner.setAttribute("BirthDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	        }
	       

	        firstname = winner.addChildElement(new QName("FirstName"));
	        if(game.getWinner().getUser().getFirstName()!= null && !game.getWinner().getUser().getFirstName().equals("") )
	        	firstname.setValue(game.getWinner().getUser().getFirstName());
	        else
	        	firstname.setValue("Max");
	        
	        lastname = winner.addChildElement(new QName("LastName"));

	        if(game.getWinner().getUser().getLastName()!=null && !game.getWinner().getUser().getLastName().equals(""))
	        	lastname.setValue(game.getWinner().getUser().getLastName());
	        else
	        	lastname.setValue("Mustermann");
	        
	        password = winner.addChildElement(new QName("Password"));
	        //password.setValue(game.getWinner().getUser().getPassword());
	        points = winner.addChildElement(new QName("Points"));
	        points.setValue(String.valueOf(game.getWinner().getProfit()));
	        SOAPMessage reply = soap.call(message, "http://playground.big.tuwien.ac.at:8080/highscoreservice/PublishHighScoreService?wsdl");
	        
	        String uuid = ((SOAPElement)(reply.getSOAPBody().getChildElements(new QName("http://big.tuwien.ac.at/we/highscore/data", "HighScoreResponse")).next())).getValue();
			Logger.info("UUID vom SOA-request: " + uuid);
			
			return uuid;
		} catch (SOAPException e) {
			Logger.error("Fehler beim senden/empfangen des SOA requests.");
		}
		
		return null;
	}

}
