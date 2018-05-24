package it.polimi.se2018.networking;

import it.polimi.se2018.utils.message.Message;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerGateway extends UnicastRemoteObject implements ReceiverInterface, Remote {

    private static final int PORT = 1099; // porta di default
    private transient ReceiverInterface receiver;

    RMIServerGateway(String name, ReceiverInterface receiver) throws RemoteException {
        this.receiver = receiver;

        try {
            LocateRegistry.createRegistry(PORT);
        } catch(Exception ex) {
            throw new RemoteException("Failed creating RMI registry");
        }

        try {
            Naming.rebind(name, this);
        } catch(Exception ex) {
            throw new RemoteException("Failed creating binding rmi name");
        }
    }

    public void receiveMessage(Message message, ReceiverInterface sender) throws RemoteException{
        receiver.receiveMessage(message,sender);
    }
}
