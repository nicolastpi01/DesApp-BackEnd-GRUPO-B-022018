package model;

import java.util.ArrayList;
import model.exceptions.MasDeCincoSubastasEnProgresoException;
import model.exceptions.UsuarioInvalidoException;

public class Sistema {
	ArrayList<Subasta> subastas = new ArrayList<Subasta>();
	ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); // registrados (Los anonimos solo operan ciertas funciones que no requieren saber quienes son)
	Registro registro = new Registro(); // Se encarga de registro, verificar ingresos, etc
	Home home = new Home(); // Se encarga de aplicar filtros sobre las subastas y mostrarlos ordenados

	public void crear(Subasta subasta, Usuario usuario) {
		if (puedeCrearSubasta(usuario))
			agregar(subasta, usuario);  
	}
		
	public ArrayList<Subasta> getSubastas() {
		return this.subastas;
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
	
	private Boolean estaAutenticado(Usuario usuario) {
		return registro.estaRegistrado(usuario);
	}

	private void agregar(Subasta subasta, Usuario usuario) {
		subasta.setEstado(new NuevaSubasta());
		subasta.setPropietario(usuario);
		subastas.add(subasta);
	}

	public ArrayList<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void registrarse(Usuario usuario) {
		if (registro.sePuedeRegistrar(usuario)) {
			registro.registrar(usuario);
			this.agregar(usuario);
		}
	}

	private void agregar(Usuario usuario) {
		usuarios.add(usuario);
		//usuario.setPerfil(new Registrado()); // Si se setea el valor del perfil de usuario acá
		// y luego se chequea el perfil de un usuario para, por ej: que pueda editar una subasta
		// se estaria exponiendo un msj publico demasiado peligroso para la app
	}
	
	///////////////////////////// BUSQUEDAS SUBASTAS  ////////////////////////////////////////////	
	public ArrayList<Subasta> buscarPorTitulo(String titulo) {
		return home.buscarPorTitulo(titulo, subastas);
	}
	
	public ArrayList<Subasta> buscarPorDescripcion(String descripcion) {
		return home.buscarPorDescripcion(descripcion, subastas);
	}

	// FALTA CODEAR --> (Para simplif. se podria fijar un nro [mayores a 10 postores ya la hace popular])
	public ArrayList<Subasta> buscarPopulares() {
		// TODO Auto-generated method stub
		return home.subastasPopulares(subastas); // Se hace una media entre la cantidad de pujas por subasta y se utiliza ese nro para comparar
		// o se fija un nro (Por ej: el que tenga mas de 15 usuarios pujando ya la hace popular)
	}

	// ACÁ SI SE FIJA LO QUE ES PROXIMA A FINALIZAR (2 DIAS, 10 HRS, ETC)
	public ArrayList<Subasta> buscarProximasAFinalizar() {
		// TODO Auto-generated method stub
		return home.subastasPorTerminar(subastas);
	}
	
	// ACÁ SI SE FIJA UN VALOR DE SUBASTA RECIENTE
	public ArrayList<Subasta> buscarRecientes() {
		// TODO Auto-generated method stub
		return home.subastasRecientes(subastas);
	}

	////////////////////////////////////////////////////////////////////////////////////////////

}
