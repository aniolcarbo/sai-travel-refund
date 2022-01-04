package message;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageReceiverGateway {

    //for receiving requests
    Connection connection; // to connect to the JMS
    Session session; // session for creating consumers
    Destination receiveDestination; // reference to a queue/topic destination
    MessageConsumer consumer; // for receiving messages

    public MessageReceiverGateway(String queue) {
        try {
            ConnectionFactory connectionFactory;
            connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            receiveDestination = session.createQueue(queue);
            consumer = session.createConsumer(receiveDestination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void setListener(MessageListener messageListener) {
        try {
            consumer.setMessageListener(messageListener);
            connection.start();
            //System.out.println("set message listener");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            consumer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
