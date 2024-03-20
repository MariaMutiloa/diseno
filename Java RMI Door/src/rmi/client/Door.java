package rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.server.IDoorServer;


public class Door {
	public static void main(String[] args) {
		if (args.length != 3) 
		{
			System.out.println("uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
			System.exit(0);
		}
	}
}