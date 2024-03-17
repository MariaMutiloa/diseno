package es.deusto.ingenieria.sd.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import es.deusto.ingenieria.sd.rmi.server.IClient;
import es.deusto.ingenieria.sd.rmi.server.IDoorServer;
import es.deusto.ingenieria.sd.rmi.server.InvalidUser;

public class Door {
	public static void main(String[] args) {
		if (args.length != 3) 
		{
			System.out.println("uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
			System.exit(0);
		}
	}
}