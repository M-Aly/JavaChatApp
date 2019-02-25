/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.tests.xml;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import com.jets.message.*;

/**
 *
 * @author lapshop
 */
public class SaveMessageTest {

    public SaveMessageTest() {

        try {
            JAXBContext context = JAXBContext.newInstance("com.jets.message");
            Unmarshaller unmarshal = (Unmarshaller) context.createUnmarshaller();
            JAXBElement jAXBElement = (JAXBElement) unmarshal.unmarshal(new File("src/main/java/com/jets/tests/xml/message.xml"));
            SessionType session = (SessionType) jAXBElement.getValue();

            session.setHeader("frindsChat");
            List<MsgContent> messageList = session.getMsg();
            ObjectFactory factory = new ObjectFactory();
            MsgContent addmMsg = factory.createMsgContent();

            addmMsg.setFrom("zainab");
            addmMsg.setBody("project java will come and we'll die!");
            addmMsg.setDate(new XMLGregorianCalendarImpl(new GregorianCalendar(2019, 5, 10)));
            addmMsg.getTo().add("Mayadaa");
            addmMsg.getTo().add("Noran");
            messageList.add(addmMsg);

            JAXBElement newSession = factory.createSession(session);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(newSession, new FileOutputStream("src/main/java/com/jets/tests/xml/outputSession.xml"));

        } catch (JAXBException ex) {
            Logger.getLogger(SaveMessageTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveMessageTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new SaveMessageTest();
        System.out.print("zenab");
    }

}
