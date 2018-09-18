package model;

import java.util.ArrayList;
import model.exceptions.MasDeCincoSubastasEnProgresoException;
import model.exceptions.UsuarioInvalidoException;

public class Sistema {
	ArrayList<Subasta> subastas = new ArrayList<Subasta>();
	ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); // registrados (Los anonimos solo operan ciertas funciones que no requieren saber quienes son)
	Registro registro = new Registro(); // Se encarga de registro, verificar ingresos, etc
	Home home = new Home(); // Se encarga de aplicar filtros sobre las subastas y mostrarlos ordenados

	
	//////////////////////////////// CRUD  //////////////////////////////////////////////////////////////////
	// UN USUARIO REGISTRADO TIENE EL COMP. DE CREAR, BORRAR Y EDITAR
	// CUANDO SE REGISTRA UN OBJ. CAMBIA SU ESTADO A REGISTRADO
	// LUEGO PARA CREAR, EDITAR, BORRAR SE PREGUNTA SI EL USUARIO ESTA REGISTRADO Y SE DELEGA EL COMP. EN EL
	// OBJETO ESTADO --> ASI DESACOPLAMOS COMPORTAMIENTO EN SISTEMA
	
	
	// No hay que verificar nada de subasta ni usuario, solo que el usuario sea valido y haya iniciado sesion
	public void crear(Subasta subasta, Usuario usuario) {
		if (puedeCrearSubasta(usuario))
			agregar(subasta, usuario);  
	}
	 
	private Boolean puedeCrearSubasta(Usuario usuario) {
		if (! estaAutenticado(usuario)) 
			throw new UsuarioInvalidoException();
		
		if (cantidadDeSubastasEnProgreso(usuario) == 5) 
			throw new MasDeCincoSubastasEnProgresoException();
		
		return true;
	}
	
	private int cantidadDeSubastasEnProgreso(Usuario usuario) {
		int cantidadEnProgreso = 0;
		for (int i=0; i < subastas.size(); i++) {
			if (subastas.get(i).estaEnProgresoPara(usuario)) {
				cantidadEnProgreso++;
			}
		}
		return cantidadEnProgreso;
	}
	
	private void agregar(Subasta subasta, Usuario usuario) {
		subasta.setEstado(new NuevaSubasta());
		subasta.setPropietario(usuario);
		subastas.add(subasta);
	}

	////////////////////////////////// 	REGISTRO     ///////////////////////////////////////////////////////////
	
	
	// ya inicio sesión?
	private Boolean estaAutenticado(Usuario usuario) {
		return registro.inicioSesion(usuario);
	}
	
	// Autenticarse
	// Con cuenta de Gmail o Usuario
	public void iniciarSesion() {}

	public void registrarse(Usuario usuario) {
		if (registro.sePuedeRegistrar(usuario)) {
			registro.registrar(usuario);
			this.agregar(usuario);
		}
	}
	
	///////////////////////////// BUSQUEDAS SUBASTAS  /////////////////////////////////////////////////////
	
	public ArrayList<Subasta> subastasEnProgreso() {
		ArrayList<Subasta> enProgreso = new ArrayList<Subasta>();
		for(int i=0; i < subastas.size(); i++) {
			if(subastas.get(i).estaEnProgreso()) enProgreso.add(subastas.get(i));
		}
		return enProgreso;
	}
	
	public ArrayList<Subasta> buscarPorTitulo(String titulo) {
		return home.buscarPorTitulo(titulo, subastasEnProgreso());
	}
	
	public ArrayList<Subasta> buscarPorDescripcion(String descripcion) {
		return home.buscarPorDescripcion(descripcion, subastasEnProgreso());
	}

	// (Las pop. son aquellas subasta en progreso cuya cant. de postores es mayor a la media)
	// Tambien hay un valor fijo. Ej 5. De modo que si la cant. de subastas en progreso es igual o menor a ese
	// nro entonces se muestran todas, ya que un filtro de pop. sobre un nro de subastas disp. tan chico
	// es innecesario (Las subastas se muestran en ord. desde mas pop a menos) --> Esto ultimo no esta codeado aún
	public ArrayList<Subasta> buscarPopulares() {
		return home.subastasPopulares(subastasEnProgreso());
	}

	public ArrayList<Subasta> buscarProximasAFinalizar() {
		return home.subastasPorTerminar(subastas);
	}
	
	public ArrayList<Subasta> buscarRecientes() {
		return home.subastasRecientes(subastas);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// private methods
	
	public ArrayList<Subasta> getSubastas() {
		return this.subastas;
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return this.usuarios;
	}
	
	private void agregar(Usuario usuario) {
		usuarios.add(usuario);
		usuario.setPerfil(new Registrado()); // Acá esta el comportamiento de edicion, creacion, y modificacion de subastas
	}

}
