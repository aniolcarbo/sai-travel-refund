package message;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageSenderGateway {

    //for sending requests
    Connection connection; // to connect to the ActiveMQ
    Session session; // session for creating messages, producers and
    Destination sendDestination; // reference to a queue/topic destination
    MessageProducer producer; // for sending messages

    public MessageSenderGateway(String queue) {
        try {
            ConnectionFactory connectionFactory;
            connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            sendDestination = session.createQueue(queue);
            producer = session.createProducer(sendDestination);

            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public Message createTextMessage(String body) {
        try {
            Message msg = session.createTextMessage(body);
            return msg;
        } catch (JMSException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void send(Message msg) {
        try {
            producer.send(msg);
            System.out.println("in the sender: " + msg.getJMSMessageID());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
