package rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDoorServer extends Remote 
{
	Boolean OK(String codigo) throws RemoteException;

}