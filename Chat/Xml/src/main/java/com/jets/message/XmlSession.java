package com.jets.message;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 
 * @author zeinab
 * @author Mohamed Ali
 *
 */
public class XmlSession {
	public SessionType readSession(File file) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance("com.jets.message");
        Unmarshaller unmarshal = (Unmarshaller) context.createUnmarshaller();
        JAXBElement jAXBElement = (JAXBElement) unmarshal.unmarshal(file);
        SessionType session = (SessionType) jAXBElement.getValue();
        return session;
	}
	
	public void writeSession(SessionType session, FileOutputStream fileOutputStream) throws JAXBException {
		ObjectFactory factory = new ObjectFactory();
		JAXBElement newSession = factory.createSession(session);
		JAXBContext context = JAXBContext.newInstance("com.jets.message");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(newSession, fileOutputStream);
	}
	
	public SessionType createSessionType() {
		ObjectFactory objectFactory=new ObjectFactory();
        return objectFactory.createSessionType();
    }

    public MsgContent createMsgContent() {
    	ObjectFactory objectFactory=new ObjectFactory();
        return objectFactory.createMsgContent();
    }
}
