package es.deusto.ingenieria.sd.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public interface IClient extends Remote 
{

	String obtenerApartamentos(String url, String token) throws RemoteException;

	ArrayList <Reserva> FetchHistorial(Usuario usuario) throws RemoteException;

	void LogIn (String usuario, String contrasena) throws RemoteException;

	void Registrar (String usuario, String contraseña, String nombre, String email) throws RemoteException;

	void GuardarPerfil (String usuario, String contraseña, String nombre, String email) throws RemoteException;

	ArrayList <Alojamiento> FetchAlojamiento() throws RemoteException;

	HashMap<Alojamiento, Alojamiento_caracteristicas> FetchCaracteristicas( Alojamiento alojamiento) throws RemoteException;

	void ActualizarFiltros(Date fecha, Integer precio, ArrayList<Alojamiento_caracteristicas> caracteristicas) throws RemoteException;

	ArrayList<Habitacion> FetchHabitaciones(Alojamiento alojamiento) throws RemoteException;

	HashMap<Habitacion, Habitacion_caracteristicas> ActualizarFiltrosHabitacioenes(Date fecha, Integer precio, ArrayList<Habitacion_caracteristicas> caracteristica) throws RemoteException;

	void EfectuarReserva(Reserva reserva) throws RemoteException;
}

	