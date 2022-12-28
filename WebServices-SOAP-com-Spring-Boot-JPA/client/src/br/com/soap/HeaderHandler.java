package br.com.soap;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean outboudProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (outboudProperty) {
			try {
				   SOAPEnvelope envelope = context.getMessage().getSOAPPart().getEnvelope();
	                SOAPHeader header = envelope.getHeader();

	                SOAPElement security = header.addChildElement("Security","wsse","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");

	                SOAPElement usernameToken = security.addChildElement("UsernameToken","wsse");

	                SOAPElement username = usernameToken.addChildElement("Username","wsse");
	                username.addTextNode("gf");

	                SOAPElement password = usernameToken.addChildElement("Password","wsse");
	                password.addTextNode("123");
			} catch (SOAPException e) {
				e.printStackTrace();
			}
		}
		return outboudProperty;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
