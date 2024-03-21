package rmi.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;


public class Client extends UnicastRemoteObject implements IClient {

	private static final long serialVersionUID = 1L;
	private int cont = 0;
	private HashMap <String, String> registeredUsers = null;
	private String responseBody;

	protected Client() throws RemoteException 
	{
		super();
		registeredUsers = new HashMap<String, String> ();
	}

	@Override
	public String obtenerApartamentos(String url, String token) throws RemoteException {
		try {
			// Configurar las cabeceras de la solicitud HTTP
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(url))
					.header("Authorization", "Bearer " + token)
					.build();
			
			// Realizar la solicitud HTTP y obtener la respuesta
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			
			// Verificar el código de estado de la respuesta
			if (response.statusCode() == 200) {
				
				// Parsear la respuesta JSON manualmente
				responseBody = response.body();
				
				return responseBody; // Devuelve el responseBody
			} else {
				System.out.println("Error al hacer la solicitud: Código de estado " + response.statusCode());
			}
		} catch (IOException | InterruptedException | URISyntaxException e) {
			System.out.println("Error al hacer la solicitud: " + e.getMessage());
			responseBody = null;
		}

		return responseBody;
		
	}

	 @Override
    public ArrayList<Reserva> FetchHistorial(Usuario usuario) throws RemoteException {
        return new ArrayList<Reserva>(); 
    }

	@Override
	public void LogIn (String usuario, String contrasena) throws RemoteException{
	}

	@Override
	public void Registrar (String usuario, String contraseña, String nombre, String email) throws RemoteException{
	}
	
	@Override
	public void GuardarPerfil (String usuario, String contraseña, String nombre, String email) throws RemoteException{
	}

	@Override
	public ArrayList <Alojamiento> FetchAlojamiento() throws RemoteException{
		return new ArrayList<Alojamiento>();
	}

	@Override
	public HashMap<Alojamiento, Alojamiento_caracteristicas> FetchCaracteristicas( Alojamiento alojamiento) throws RemoteException{
		return new HashMap<Alojamiento, Alojamiento_caracteristicas>();
	}

	@Override
	public void ActualizarFiltros(Date fecha, Integer precio, ArrayList<Alojamiento_caracteristicas> caracteristicas) throws RemoteException{
	}

	@Override
	public ArrayList<Habitacion> FetchHabitaciones(Alojamiento alojamiento) throws RemoteException{
		return new ArrayList<Habitacion>();
	}

	@Override
	public HashMap<Habitacion, Habitacion_caracteristicas> ActualizarFiltrosHabitacioenes(Date fecha, Integer precio, ArrayList<Habitacion_caracteristicas> caracteristica) throws RemoteException{
		return new HashMap<Habitacion, Habitacion_caracteristicas>();
	}

	@Override
	public void EfectuarReserva(Reserva reserva) throws RemoteException{
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Client [host] [port] [server]");
			System.exit(0);
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try 
		{	
			IClient objServer = new Client();
			Registry registry = LocateRegistry.createRegistry((Integer.valueOf(args[1])));
			//Naming.rebind(name, objServer);
			registry.rebind(name, objServer);
			System.out.println("* Server '" + name + "' active and waiting...");			
		} 
		catch (Exception e) 
		{
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}