package model;

import java.util.ArrayList;
import model.exceptions.MasDeCincoSubastasEnProgresoException;
import model.exceptions.UsuarioInvalidoException;

public class Sistema {
	ArrayList<Subasta> subastas = new ArrayList<Subasta>();
	ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); // registrados (Los anonimos solo operan ciertas funciones que no requieren saber quienes son)
	Registro registro = new Registro(); // Se encarga de registro, verificar ingresos, etc

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
	
	// Por ahora siempre esta autenticado (podria haber una clase Registro que se encargue de esto)
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

	

}
