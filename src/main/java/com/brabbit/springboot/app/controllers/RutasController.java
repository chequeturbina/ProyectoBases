package com.brabbit.springboot.app.controllers;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.brabbit.springboot.app.models.entity.Chofer;
import com.brabbit.springboot.app.models.entity.Cilindros;
import com.brabbit.springboot.app.models.entity.Destino;
import com.brabbit.springboot.app.models.entity.Direccion;
import com.brabbit.springboot.app.models.entity.Duenio;
import com.brabbit.springboot.app.models.entity.LlantaRefaccion;
import com.brabbit.springboot.app.models.entity.Multa;
import com.brabbit.springboot.app.models.entity.NPuertas;
import com.brabbit.springboot.app.models.entity.Origen;
import com.brabbit.springboot.app.models.entity.TipoCarro;
import com.brabbit.springboot.app.models.entity.TipoGas;
import com.brabbit.springboot.app.models.service.ChoferDaoImplement;
import com.brabbit.springboot.app.models.service.CilindrosDaoImplement;
import com.brabbit.springboot.app.models.service.DestinoDaoImplement;
import com.brabbit.springboot.app.models.service.DireccionDaoImplement;
import com.brabbit.springboot.app.models.service.DuenioDaoImplement;
import com.brabbit.springboot.app.models.service.InterfaceUsuarioDao;
import com.brabbit.springboot.app.models.service.LlantaRefaccionDaoImplement;
import com.brabbit.springboot.app.models.service.MultaDaoImplement;
import com.brabbit.springboot.app.models.service.NPuertasDaoImplement;
import com.brabbit.springboot.app.models.service.OrigenDaoImplement;
import com.brabbit.springboot.app.models.service.TipoCarroDaoImplement;
import com.brabbit.springboot.app.models.service.TipoGasDaoImplement;
import com.brabbit.springboot.app.models.service.TipoUsuarioDaoImplement;
import com.brabbit.springboot.app.models.service.UsuarioDaoImplement;
import com.brabbit.springboot.app.models.service.VehiculoDaoImplement;
import com.brabbit.springboot.app.models.service.ViajeDaoImplement;
import com.brabbit.springboot.app.models.entity.TipoUsuario;
import com.brabbit.springboot.app.models.entity.Usuario;
import com.brabbit.springboot.app.models.entity.Vehiculo;
import com.brabbit.springboot.app.models.entity.Viaje;
import com.brabbit.springboot.app.models.entity.Origen;
import com.brabbit.springboot.app.models.entity.Destino;
import com.brabbit.springboot.app.models.service.OrigenDaoImplement;
import com.brabbit.springboot.app.models.service.DestinoDaoImplement;
import com.brabbit.springboot.app.models.service.ViajeDaoImplement;

//AQUI SE DEFINIRAN LAS RUTAS HACIA LAS VISTAS.
@Controller

public class RutasController {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	
	@Autowired
	private TipoUsuarioDaoImplement tipoDao;
	
	@Autowired
	private CilindrosDaoImplement cilindrosDao;
	
	@Autowired
	private LlantaRefaccionDaoImplement refaccionDao;
	
	@Autowired
	private TipoCarroDaoImplement tipoCarroDao;
	
	@Autowired
	private NPuertasDaoImplement puertasDao;
	
	@Autowired
	private TipoGasDaoImplement tipoGasDao;
	
	@Autowired
	private DireccionDaoImplement direccionDao;
	
	@Autowired
	private UsuarioDaoImplement usuarioDao;
	
	@Autowired
	private OrigenDaoImplement origenDao;
	
	@Autowired
	private DestinoDaoImplement destinoDao;
	
	@Autowired
	private ViajeDaoImplement viajeDao;
	
	@Autowired
	private ChoferDaoImplement choferDao;
	
	@Autowired
	private DuenioDaoImplement duenioDao;
	
	@Autowired
	private VehiculoDaoImplement vehiculoDao;
	
	@Autowired
	private MultaDaoImplement multaDao;
	
	/*-------------------------------------VISTAS-------------------------------------*/
	@GetMapping("/")
	public String inicio(Model model) {
		
		return "index";
	}
	
	@GetMapping("/registro_duenio")
	public String RegistroDuenio(Model model) {
		return "registro_dueño";
	}
	
	@GetMapping("/registro_usuario")
	public String RegistroUsuario(Model model) {
		List<TipoUsuario> um = tipoDao.findAll();
		model.addAttribute("niveles", um);
		return "registro_usuario";
	}
	
	@GetMapping("/registro_chofer")
	public String RegistroChofer(Model model) {
		return "registro_chofer";
	}
	
	@GetMapping("/registro_vehiculo")
	public String RegistroVehiculo(Model model) {
		List<Cilindros> um = cilindrosDao.findAll();
		model.addAttribute("cilindreros", um);
		
		List<LlantaRefaccion> am = refaccionDao.findAll();
		model.addAttribute("refacciones", am);
		
		List<TipoCarro> em = tipoCarroDao.findAll();
		model.addAttribute("tipejos", em);
		
		List<TipoGas> im = tipoGasDao.findAll();
		model.addAttribute("gasolinari", im);
		
		List<NPuertas> om = puertasDao.findAll();
		model.addAttribute("portas", om);
		
		return "registro_vehiculo";
	}
	
	@GetMapping("/viaje_solo")
	public String Viaje(Model model) {
		List<Origen> am = origenDao.findAll();
		model.addAttribute("origen", am);
		List<Destino> em = destinoDao.findAll();
		model.addAttribute("destino", em);
		return "viaje_solo";
	}
	
	@GetMapping("/viaje_chofer")
	public String ViajeChofer(Model model) {
		
		List<Viaje> Viajes = viajeDao.listarViajes();
		model.addAttribute("viajes", Viajes);
		
		return "viaje_chofer";
	}
	
	@GetMapping("/viaje_compartido")
	public String ViajeCompartido(Model model) {
		List<Origen> am = origenDao.findAll();
		model.addAttribute("origen", am);
		List<Destino> em = destinoDao.findAll();
		model.addAttribute("destino", em);
		return "viaje_compartido";
	}
	
	@GetMapping("/viaje_exitoso")
	public String VaijeExitoso(Model model) {
		return "viajeExitoso";
	}
	
	/*-------------------------------------REGISTROS-------------------------------------*/
	
	@RequestMapping(value="registro/usuario", method = RequestMethod.POST)
	public String RegistrarUsuario(@RequestParam("tipo_usuario") long tipo,
			                @RequestParam("nombre")  String nombre,
			                @RequestParam("paterno") String paterno,
			                @RequestParam("materno") String materno,
			                @RequestParam("correo")  String correo,
			                @RequestParam("instituto")  String instituto,
			                @RequestParam("celular") String celular,
			                @RequestParam("casa")    String casa,
			                @RequestParam("entrada") String entrada,
			                @RequestParam("salida")  String salida,
			                @RequestParam("calle")   String calle,
			                @RequestParam("colonia") String colonia,
			                @RequestParam("municipio") String municipio,
			                @RequestParam("estado")    String estado,
			                @RequestParam("cp")       String cp,
			                @RequestParam("exterior") String exterior,
			                @RequestParam("interior") String interior,
			                RedirectAttributes ra) {
		
		
		Direccion direccion = new Direccion();
		direccion.setCalle(calle);
		direccion.setColonia(colonia);
		direccion.setMunicipio(municipio);
		direccion.setEstado(estado);
		direccion.setCp(Integer.parseInt(cp));
		direccion.setExterior(Integer.parseInt(exterior));
		direccion.setInterior(interior);
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre(nombre);
		usuario.setPaterno(paterno);
		usuario.setMaterno(materno);
		usuario.setCorreo(correo);
		
		TipoUsuario tipUsuario = tipoDao.findOne(tipo);
		usuario.setID_TIPO(tipUsuario);
		
		usuario.setInstituto(instituto);
		usuario.setCelular(celular);
		usuario.setCasa(casa);
		usuario.setHora_ingreso(entrada);
		usuario.setHora_salida(salida);
		usuario.setID_DIRECCION(direccion);
		
		Date now = new Date();
        usuario.setfRegistro(now);
		
		usuarioDao.save(usuario);
	
		ra.addFlashAttribute("success", "Exito al Registrar");
		
		return"redirect:/registro_usuario";
		
	}
	
	@RequestMapping(value="registro/chofer", method = RequestMethod.POST)
	public String RegistrarChofer(@RequestParam("nombre")  String nombre,
			                @RequestParam("paterno") String paterno,
			                @RequestParam("materno") String materno,
			                @RequestParam("correo")  String correo,
			                @RequestParam("licencia")  String licencia,
			                @RequestParam("celular") String celular,
			                
			                @RequestParam("calle")   String calle,
			                @RequestParam("colonia") String colonia,
			                @RequestParam("municipio") String municipio,
			                @RequestParam("estado")    String estado,
			                @RequestParam("cp")       String cp,
			                @RequestParam("exterior") String exterior,
			                @RequestParam("interior") String interior,
			                RedirectAttributes ra) {
		
		
		Direccion direccion = new Direccion();
		direccion.setCalle(calle);
		direccion.setColonia(colonia);
		direccion.setMunicipio(municipio);
		direccion.setEstado(estado);
		direccion.setCp(Integer.parseInt(cp));
		direccion.setExterior(Integer.parseInt(exterior));
		direccion.setInterior(interior);
		
		Chofer chofer = new Chofer();
		
		chofer.setNombre(nombre);
		chofer.setPaterno(paterno);
		chofer.setMaterno(materno);
		chofer.setCorreo(correo);
	
		chofer.setCelular(celular);
		chofer.setLicencia(licencia);
		
		chofer.setID_DIRECCION(direccion);
		
		Date now = new Date();
        chofer.setfRegistro(now);
		
		choferDao.save(chofer);
	
		ra.addFlashAttribute("success", "Exito al Registrar");
		
		return"redirect:/registro_chofer";
		
	}
	
	@RequestMapping(value="registro/duenio", method = RequestMethod.POST)
	public String RegistrarDuenio(@RequestParam("rfc")  String rfc,
			                      @RequestParam("nombre")  String nombre,
			                      @RequestParam("paterno") String paterno,
			                      @RequestParam("materno") String materno,
			                      @RequestParam("correo")  String correo,
			                      @RequestParam("licencia")  String licencia,
			                      @RequestParam("celular") String celular,
			                      @RequestParam("calle")   String calle,
			                      @RequestParam("colonia") String colonia,
			                      @RequestParam("municipio") String municipio,
			                      @RequestParam("estado")    String estado,
			                      @RequestParam("cp")       String cp,
			                      @RequestParam("exterior") String exterior,
			                      @RequestParam("interior") String interior,
			                      @RequestParam("opcion") String opcion,
			                      RedirectAttributes ra) {
		
		
		Direccion direccion = new Direccion();
		direccion.setCalle(calle);
		direccion.setColonia(colonia);
		direccion.setMunicipio(municipio);
		direccion.setEstado(estado);
		direccion.setCp(Integer.parseInt(cp));
		direccion.setExterior(Integer.parseInt(exterior));
		direccion.setInterior(interior);
		
		Duenio duenio = new Duenio();
		
		duenio.setRfc(rfc);
		duenio.setNombre(nombre);
		duenio.setPaterno(paterno);
		duenio.setMaterno(materno);
		duenio.setCorreo(correo);
	
		duenio.setCelular(celular);
		duenio.setLicencia(licencia);
		
		duenio.setID_DIRECCION(direccion);
		
		Date now = new Date();
        duenio.setfRegistro(now);
		
		duenioDao.save(duenio);
		
		if(opcion.equals("SI")) {
			
			Chofer chofer = new Chofer();
			chofer.setNombre(nombre);
			chofer.setPaterno(paterno);
			chofer.setMaterno(materno);
			chofer.setCorreo(correo);
		
			chofer.setCelular(celular);
			chofer.setLicencia(licencia);
			
			chofer.setID_DIRECCION(direccion);
			
	        chofer.setfRegistro(now);
			
			choferDao.save(chofer);
			
			ra.addFlashAttribute("success", "Ahora Eres Dueño y Chofer");
			
			return"redirect:/registro_duenio";
			
		} else {
	
		ra.addFlashAttribute("success", "Exito al Registrar");
		
		return"redirect:/registro_duenio";
		}
	}
	
	@RequestMapping(value="registro/vehiculo", method = RequestMethod.POST)
	public String RegistrarVehiculo(@RequestParam("modelo")  String modelo,
		 	                        @RequestParam("marca")  String marca,
		 	                       @RequestParam("matricula")  String matricula,
		 	                        @RequestParam("anio")  String anio,
		 	                        @RequestParam("capacidad")  String capacidad,
		 	                        @RequestParam("npuertas")  long npuertas,
		 	                        @RequestParam("cilindros")  long cilindros,
		 	                        @RequestParam("refaccion")  long refaccion,
		 	                        @RequestParam("tipocarro")  long tipocarro,
		 	                        @RequestParam("tipogas")  long tipogas,
		 	                        @RequestParam("seguro")  String seguro,
		 	                        @RequestParam("cobertura")  String cobertura,
		 	                        @RequestParam("rfc")  String rfc,
		 	                        @RequestParam("licencia")  String licencia,
		 	                        RedirectAttributes ra) {
		
		Duenio duenio = duenioDao.porNombre(rfc);
		Chofer chofer = choferDao.porLicencia(licencia);
		
		if(duenio != null && chofer != null) {
		
		Vehiculo vehiculo = new Vehiculo();
		
		vehiculo.setModelo(modelo);
		vehiculo.setMarca(marca);
		vehiculo.setMatricula(matricula);
		vehiculo.setAnio(Integer.parseInt(anio));
		vehiculo.setCapacidad(Integer.parseInt(capacidad));
		
		vehiculo.setSeguro(seguro);
		vehiculo.setTipo_seguro(cobertura);
		
		NPuertas puertas = puertasDao.findOne(npuertas);
		vehiculo.setID_PUERTAS(puertas);
		
		Cilindros cilindro = cilindrosDao.findOne(cilindros);
		vehiculo.setID_CILINDROS(cilindro);
		
		LlantaRefaccion llanta = refaccionDao.findOne(refaccion);
		vehiculo.setID_REFACCION(llanta);
		
		TipoCarro estilo = tipoCarroDao.findOne(tipocarro);
		vehiculo.setID_TIPOCARRO(estilo);
		
		TipoGas gaso = tipoGasDao.findOne(tipogas);
		vehiculo.setID_TIPOGAS(gaso);
		
		vehiculo.setRFC(duenio);
		vehiculo.setID_CHOFER(chofer);
		vehiculoDao.save(vehiculo);
	    
		ra.addFlashAttribute("success", "Exito al Registrar");
		
		return"redirect:/registro_vehiculo";
		}else {
			
			ra.addFlashAttribute("warning", "El Dueño No Existe En La Base De Datos");
			
			return"redirect:/registro_vehiculo";
			
		}
		
	}
	
	
	/*-------------------------------------CONSULTAS-------------------------------------*/
	
	@RequestMapping("/consulta_chofer")
	public String consultaChofer(Model model) {
		List<Chofer> Choferes=choferDao.listarChoferes();
		model.addAttribute("choferes",Choferes);
		return"consulta_chofer";
	}
	
	@RequestMapping("/consulta_duenio")
	public String consultaDuenio(Model model) {
		List<Duenio> Duenios=duenioDao.listarDuenios();
		model.addAttribute("duenios",Duenios);
		return"consulta_duenio";
	}
	
	@RequestMapping("/consulta_usuario")
	public String consultaUsuario(Model model) {
		List<Usuario> Usuarios=usuarioDao.listarUsuarios();
		model.addAttribute("usuarios",Usuarios);
		return"consulta_usuario";
	}
	
	@RequestMapping("/consulta_vehiculo")
	public String consultaVehiculo(Model model) {
		List<Vehiculo> Vehiculos=vehiculoDao.listarVehiculos();
		model.addAttribute("vehiculos",Vehiculos);
		return"consulta_vehiculo";
	}
	
	@RequestMapping("/consulta_multa")
	public String consultaMulta(Model model) {
		List<Multa> Multas=multaDao.listarMultas();
		model.addAttribute("multas",Multas);
		return"consulta_multa";
	}
	
	
	/*-------------------------------------VIAJES-------------------------------------*/
	
	/*PEDIR VIAJE UNA PERSONA SOLA-------------------------PARTE DEL USUARIO*/
	
	@RequestMapping(value="/pedir/viaje", method = RequestMethod.POST)
	public String PedirViaje(@RequestParam("correo")  String correo,
		 	                 @RequestParam("distancia")  String distancia,
	 	                     @RequestParam("tiempo")  String tiempo,
	 	                     @RequestParam("pasajeros")  String pasajeros,
	 	                     @RequestParam("origen")  long origen,
	 	                     @RequestParam("origin")  String origin,
	 	                     @RequestParam("destino")  long destino,
	 	                     @RequestParam("destiny")  String destiny,
		 	                 RedirectAttributes ra) {
		
		
		/*BUSCAR USUARIO POR CORREO*/
		Usuario usuario = usuarioDao.porCorreo(correo);
	
   	    if(usuario != null) {
   	    	
             Viaje viaje = new Viaje();
             
             /*Contador para los viajes*/
     		 Integer contador = usuario.getContadorusuario() + 1;
     		 
     		/*SAber el numero de pasajeros para el primer if*/
     		Integer numpasajeros = Integer.parseInt(pasajeros);
     		viaje.setPasajeros(numpasajeros);
     		
     			if(numpasajeros == 1) {
     				
     				/*SABER EL ORIGEN Y EL DESTINO PARA SABER SI ES DENTRO DE CU O NO */
     		   	    Origen origenif = origenDao.findOne(origen);
     		   	    Destino destinoif = destinoDao.findOne(destino);
     		   	    /*String para saber si el viaje es dentro de CU o no*/
     		   	    String orin = origenif.getOrigen();
     		   	    String destn = destinoif.getDestino();
     		   	    	
     		   	    		if(orin.equals("SI") && destn.equals("SI")) {
     		   	    			
     		   	    		Integer calculodistancia = Integer.parseInt(distancia);
     		   	    		viaje.setDistancia(calculodistancia);
     		   	    		viaje.setTiempo(Integer.parseInt(tiempo));
     		   	    		viaje.setID_ORIGEN(origenif);
     		   	    		viaje.setID_DESTINO(destinoif);
     		   	    		viaje.setOrigin(origin);
     		   	    		viaje.setDestiny(destiny);
     		   	    			
     		   	    		Integer cobro = 15;
	    	  				viaje.setCobro(cobro);
	    	  				
	    	  				viajeDao.save(viaje);
	    	  				
	    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
	    	  				usuario.getViajes().add(viaje);
	    	  				usuarioDao.save(usuario);
	    	  				ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
	    	  				ra.addFlashAttribute("pago", cobro);
	    	  				return"redirect:/viaje_exitoso";
     		   	    			
     		   	    		}else {
     		   	    			
     		   	    		Integer calculodistancia = Integer.parseInt(distancia);
     		   	    		viaje.setDistancia(calculodistancia);
     		   	    		viaje.setTiempo(Integer.parseInt(tiempo));
     		   	    		viaje.setID_ORIGEN(origenif);
     		   	    		viaje.setID_DESTINO(destinoif);
     		   	    		viaje.setOrigin(origin);
     		   	    		viaje.setDestiny(destiny);
     		   	    		Integer cobrofinal = 15 + (calculodistancia * 8);
	  						viaje.setCobro(cobrofinal);
	  						
	  						viajeDao.save(viaje);
	    	  				
	    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
	    	  				usuario.getViajes().add(viaje);
	    	  				usuarioDao.save(usuario);
	  						ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
	  						ra.addFlashAttribute("pago", cobrofinal);
	  						return"redirect:/viaje_exitoso";
     		   	    			
     		   	    		}
     				
     			}else {
     				
     				/*SABER EL ORIGEN Y EL DESTINO PARA SABER SI ES DENTRO DE CU O NO */
     		   	    Origen origenif = origenDao.findOne(origen);
     		   	    Destino destinoif = destinoDao.findOne(destino);
     		   	    /*String para saber si el viaje es dentro de CU o no*/
     		   	    String orin = origenif.getOrigen();
     		   	    String destn = destinoif.getDestino();
     		   	    
     		   	    		if(contador%5 == 1) {
     		   	    			
     		   	    			TipoUsuario tipUsuario = usuario.getID_TIPO();
     		   	    			String user = tipUsuario.getTipo();
     		   	    			System.out.println(user);
     		   	    			
     		   	    					if(orin.equals("SI") && destn.equals("SI")) {
     		   	    						
     		   	    						if(user.equals("Estudiante")) {
     		   	    							
     		   	    								Integer calculodistancia = Integer.parseInt(distancia);
     		   	    								viaje.setDistancia(calculodistancia);
     		   	    								viaje.setTiempo(Integer.parseInt(tiempo));
     		   	    								viaje.setID_ORIGEN(origenif);
     		   	    								viaje.setID_DESTINO(destinoif);
     		   	    								viaje.setOrigin(origin);
     		   	    								viaje.setDestiny(destiny);
     		   	    								Integer cobromedio = 10;
     		   	    								Integer porcentaje = 15;
     		   	    								Integer porcen = (cobromedio*porcentaje)/100;
     		   	    								Integer cobrofinal = 10 - porcen;
     		   	    								viaje.setCobro(cobrofinal);
     		   	    								
     		   	    								
     		   	    								viajeDao.save(viaje);
     		   		    	  				
     		   	    									usuarioDao.update(contador, usuario.getID_USUARIO());
     		   	    									usuario.getViajes().add(viaje);
     		   	    									usuarioDao.save(usuario);
     		   	    								ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
     		   	    								ra.addFlashAttribute("pago", cobrofinal);
     		   	    								return"redirect:/viaje_exitoso";
     		   	    							
     		   	    						}else {
     		   	    							
     		   	    								Integer calculodistancia = Integer.parseInt(distancia);
     		   	    								viaje.setDistancia(calculodistancia);
     		   	    								viaje.setTiempo(Integer.parseInt(tiempo));
     		   	    								viaje.setID_ORIGEN(origenif);
     		   	    								viaje.setID_DESTINO(destinoif);
     		   	    								viaje.setOrigin(origin);
     		   	    								viaje.setDestiny(destiny);
     		   	    								Integer cobromedio = 10;
     		   	    								Integer porcentaje = 10;
     		   	    								Integer porcen = (cobromedio*porcentaje)/100;
     		   	    								Integer cobrofinal = 10 - porcen;
			        
     		   	    								viaje.setCobro(cobrofinal);
     		   	    								viajeDao.save(viaje);
     		   		    	  				
     		   	    								usuarioDao.update(contador, usuario.getID_USUARIO());
     		   	    								usuario.getViajes().add(viaje);
     		   	    								usuarioDao.save(usuario);
     		   	    								ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
     		   	    								ra.addFlashAttribute("pago", cobrofinal);
     		   	    								return"redirect:/viaje_exitoso";
     		   	    							
     		   	    						}
     		   	    					}else {
     		   	    						
     		   	    						if(user.equals("Estudiante")) {
     		   	    							
     		   	    								Integer calculodistancia = Integer.parseInt(distancia);
     		   	    								viaje.setDistancia(calculodistancia);
     		   	    								viaje.setTiempo(Integer.parseInt(tiempo));
     		   	    								viaje.setID_ORIGEN(origenif);
     		   	    								viaje.setID_DESTINO(destinoif);
     		   	    								viaje.setOrigin(origin);
     		   	    								viaje.setDestiny(destiny);
     		   	    								Integer cobromedio = 15 + (calculodistancia * 6);
     		   	    								Integer porcentaje = 15;
     		   	    								Integer porcen = (cobromedio*porcentaje)/100;
     		   	    								Integer cobrofinal = cobromedio - porcen;
		        
     		   	    								viaje.setCobro(cobrofinal);
     		   	    								viajeDao.save(viaje);
     		   		    	  				
     		   	    								usuarioDao.update(contador, usuario.getID_USUARIO());
     		   	    								usuario.getViajes().add(viaje);
     		   	    								usuarioDao.save(usuario);
     		   	    								ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
     		   	    								ra.addFlashAttribute("pago", cobrofinal);
     		   	    								return"redirect:/viaje_exitoso";
     		   	    							
     		   	    						}else {
     		   	    							
     		   	    								Integer calculodistancia = Integer.parseInt(distancia);
     		   	    								viaje.setDistancia(calculodistancia);
     		   	    								viaje.setTiempo(Integer.parseInt(tiempo));
     		   	    								viaje.setID_ORIGEN(origenif);
     		   	    								viaje.setID_DESTINO(destinoif);
     		   	    								viaje.setOrigin(origin);
     		   	    								viaje.setDestiny(destiny);
     		   	    								Integer cobromedio = 15 + (calculodistancia * 6);
     		   	    								Integer porcentaje = 10;
     		   	    								Integer porcen = (cobromedio*porcentaje)/100;
     		   	    								Integer cobrofinal = cobromedio - porcen;
		        
     		   	    								viaje.setCobro(cobrofinal);
     		   	    								viajeDao.save(viaje);
     		   		    	  				
     		   	    								usuarioDao.update(contador, usuario.getID_USUARIO());
     		   	    								usuario.getViajes().add(viaje);
     		   	    								usuarioDao.save(usuario);
     		   	    								ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
     		   	    								ra.addFlashAttribute("pago", cobrofinal);
     		   	    								return"redirect:/viaje_exitoso";
     		   	    							
     		   	    						}
     		   	    						
     		   	    					}
     		   	    			
     		   	    		}else {
     		   	    		
								TipoUsuario tipUsuario = usuario.getID_TIPO();
								String user = tipUsuario.getTipo();
								System.out.println(user);
								
								if(orin.equals("SI") && destn.equals("SI")) {
									
									if(user.equals("Estudiante")) {
										
											Integer calculodistancia = Integer.parseInt(distancia);
	   	    								viaje.setDistancia(calculodistancia);
	   	    								viaje.setTiempo(Integer.parseInt(tiempo));
	   	    								viaje.setID_ORIGEN(origenif);
	   	    								viaje.setID_DESTINO(destinoif);
	   	    								viaje.setOrigin(origin);
	   	    								viaje.setDestiny(destiny);
	   	    								Integer cobromedio = 15;
	   	    								Integer porcentaje = 15;
	   	    								Integer porcen = (cobromedio*porcentaje)/100;
	   	    								Integer cobrofinal = 15 - porcen;
    
	   	    								viaje.setCobro(cobrofinal);
	   	    								viajeDao.save(viaje);
	   	    		    	  				
	   	    		    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
	   	    		    	  				usuario.getViajes().add(viaje);
	   	    		    	  				usuarioDao.save(usuario);
	   	    								ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
	   	    								ra.addFlashAttribute("pago", cobrofinal);
	   	    								return"redirect:/viaje_exitoso";
										
									}else {
										
											Integer calculodistancia = Integer.parseInt(distancia);
											viaje.setDistancia(calculodistancia);
											viaje.setTiempo(Integer.parseInt(tiempo));
											viaje.setID_ORIGEN(origenif);
											viaje.setID_DESTINO(destinoif);
   	    									viaje.setOrigin(origin);
   	    									viaje.setDestiny(destiny);
   	    									Integer cobromedio = 15;
   	    									Integer porcentaje = 10;
   	    									Integer porcen = (cobromedio*porcentaje)/100;
   	    									Integer cobrofinal = 15 - porcen;
    
   	    									viaje.setCobro(cobrofinal);
   	    									viajeDao.save(viaje);
   	    			    	  				
   	    			    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
   	    			    	  				usuario.getViajes().add(viaje);
   	    			    	  				usuarioDao.save(usuario);
   	    									ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
   	    									ra.addFlashAttribute("pago", cobrofinal);
   	    									return"redirect:/viaje_exitoso";
										
									}
									
								}else {
									
									if(user.equals("Estudiante")) {
										
											Integer calculodistancia = Integer.parseInt(distancia);
											viaje.setDistancia(calculodistancia);
											viaje.setTiempo(Integer.parseInt(tiempo));
											viaje.setID_ORIGEN(origenif);
											viaje.setID_DESTINO(destinoif);
	    									viaje.setOrigin(origin);
	    									viaje.setDestiny(destiny);
	    									Integer cobromedio = 15 + (calculodistancia * 8);
	    									Integer porcentaje = 15;
	    									Integer porcen = (cobromedio*porcentaje)/100;
	    									Integer cobrofinal = cobromedio - porcen;

	    									viaje.setCobro(cobrofinal);
	    									viajeDao.save(viaje);
	    			    	  				
	    			    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
	    			    	  				usuario.getViajes().add(viaje);
	    			    	  				usuarioDao.save(usuario);
	    									ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
	    									ra.addFlashAttribute("pago", cobrofinal);
	    									return"redirect:/viaje_exitoso";
										
									}else {
										
											Integer calculodistancia = Integer.parseInt(distancia);
											viaje.setDistancia(calculodistancia);
											viaje.setTiempo(Integer.parseInt(tiempo));
											viaje.setID_ORIGEN(origenif);
											viaje.setID_DESTINO(destinoif);
    										viaje.setOrigin(origin);
    										viaje.setDestiny(destiny);
    										Integer cobromedio = 15 + (calculodistancia * 8);
    										Integer porcentaje = 10;
    										Integer porcen = (cobromedio*porcentaje)/100;
    										Integer cobrofinal = cobromedio - porcen;

    										viaje.setCobro(cobrofinal);
    										viajeDao.save(viaje);
    				    	  				
    				    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
    				    	  				usuario.getViajes().add(viaje);
    				    	  				usuarioDao.save(usuario);
    										ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
    										ra.addFlashAttribute("pago", cobrofinal);
    										return"redirect:/viaje_exitoso";

										
									}
									
								}
     		   	    			
     		   	    		}
     				
     			}
     		
   	    	
   	    }else {
   	    	
   	    	ra.addFlashAttribute("error", "El Usuario No Existe En La Base De Datos");
   			
   	    	return"redirect:/viaje_solo";
   	    }
   	    }
	
	/*-------------------------------------VIAJES PARA CHOFERES--------------------------------------------*/
	@RequestMapping(value="/viajeChofer", method = RequestMethod.POST)
	public String Viajechofer(@RequestParam("ID_VIAJE")  long ID_VIAJE,
							  @RequestParam("correo")  String correo,
							  RedirectAttributes ra) {
		
		/*BUSCAR CHOFER POR CORREO*/
		Chofer chofer = choferDao.porCorreo(correo);
		
		if(chofer != null) {
			 
    		 Viaje viaje = viajeDao.porId(ID_VIAJE);
    		 
    		 Origen origen = viaje.getID_ORIGEN();
    		 String origin = origen.getOrigen();
    		 
    		 Destino destino = viaje.getID_DESTINO();
    		 String destiny = destino.getDestino();
    		 
    		 			if(origin.equals("SI") && destiny.equals("SI")) {
    		 				
    		 					/*Contador para los viajes y chofer*/
    		 	    		 	Integer contadorchofer = chofer.getContadorchofer() + 1;
    			 
    		 					/*DAOTS DE LA GANANCIA DEL CHOFER Y EL COBRO DEL VIAJE*/
    		 					Integer ganancia = chofer.getGanancia();
    		 					Integer cobroviaje = viaje.getCobro();
    			 
    		 					Integer porcentaje = 8;
    		 					Integer porcen = (cobroviaje*porcentaje)/100;
    		 					Integer gananciafinal = ganancia + porcen;
    			 
    		 					choferDao.update(gananciafinal, chofer.getID_CHOFER());
    		 					
    		 					choferDao.updateContador(contadorchofer, chofer.getID_CHOFER());
    		 					
    		 					viaje.setID_CHOFER(chofer);
								viajeDao.save(viaje);
    			 
    		 								if(contadorchofer > 20) {
    				 
    		 										/*Contador para los viajes y chofer*/
    		 										Integer gananciabono = chofer.getGanancia();
    		 										Integer porcentajebono = 10;
    		 										Integer porcenbono = (gananciabono*porcentajebono)/100;
    		 										Integer bono = chofer.getBono() + porcenbono;
					 
    		 										choferDao.updateBono(bono, chofer.getID_CHOFER());
    		 										
    		 										ra.addFlashAttribute("success", "Viaje Asignado, Felicidades obtuviste tu bono de 20 viajes");
    		 							   			ra.addFlashAttribute("pago", bono);
    		 							   	    	return"redirect:/viaje_chofer";
    		 										
    		 								}else {
    		 									
								ra.addFlashAttribute("success", "Viaje Asignado");
								ra.addFlashAttribute("pago", gananciafinal);
		 						return"redirect:/viaje_chofer";						
    		 								}
    		 			}else {
    		 				
    		 					/*Contador para los viajes y chofer*/
		 	    		 		Integer contadorchofer = chofer.getContadorchofer() + 1;
			 
		 	    		 		/*DAOTS DE LA GANANCIA DEL CHOFER Y EL COBRO DEL VIAJE*/
		 	    		 		Integer ganancia = chofer.getGanancia();
		 	    		 		Integer cobroviaje = viaje.getCobro();
			 
		 	    		 		Integer porcentaje = 12;
		 	    		 		Integer porcen = (cobroviaje*porcentaje)/100;
		 						Integer gananciafinal = ganancia + porcen;
			 
		 						choferDao.update(gananciafinal, chofer.getID_CHOFER());
		 					
		 						choferDao.updateContador(contadorchofer, chofer.getID_CHOFER());
		 					
		 						viaje.setID_CHOFER(chofer);
		 						viajeDao.save(viaje);
			 
		 								if(contadorchofer > 20) {
				 
		 										/*Contador para los viajes y chofer*/
		 										Integer gananciabono = chofer.getGanancia();
		 										Integer porcentajebono = 10;
		 										Integer porcenbono = (gananciabono * porcentajebono)/100;
		 										Integer bono = chofer.getBono() + porcenbono;
				 
		 										choferDao.updateBono(bono, chofer.getID_CHOFER());
		 										
		 										ra.addFlashAttribute("success", "Viaje Asignado, Felicidades obtuviste tu bono de 20 viajes");
		 										ra.addFlashAttribute("pago", bono);
		 							   	    	return"redirect:/viaje_chofer";
		 										
		 								}else {
		 									
		 										ra.addFlashAttribute("success", "Viaje Asignado");
		 										ra.addFlashAttribute("pago", "Tu Pago es de $" + gananciafinal);
		 										return"redirect:/viaje_chofer";						
		 								}
    		 				
    		 			}
    		 			
			}else{
			
				ra.addFlashAttribute("error", "El Chofer No Existe En La Base De Datos");
				return"redirect:/viaje_chofer";
			}
		}	
	
	
/*PEDIR VIAJE COMPARTIDO-------------------------PARTE DEL USUARIO*/
	
	@RequestMapping(value="/pedir/viaje_compartido", method = RequestMethod.POST)
	public String PedirViajeCompartido(@RequestParam("correo")  String correo,
		 	                 @RequestParam("distancia")  String distancia,
	 	                     @RequestParam("tiempo")  String tiempo,
	 	                     @RequestParam("origen")  long origen,
	 	                     @RequestParam("origin")  String origin,
	 	                     @RequestParam("destino")  long destino,
	 	                     @RequestParam("destiny")  String destiny,
		 	                 RedirectAttributes ra) {
		
		/*INTEGER DE ENTRE 2 A 4 PARA COMPARTIR UN VIAJE*/
		int randomNum = ThreadLocalRandom.current().nextInt(2, 4 + 1);
		System.out.println(randomNum);
		
		/*BUSCAR USUARIO POR CORREO*/
		Usuario usuario = usuarioDao.porCorreo(correo);
	
   	    if(usuario != null) {
   	    	
             Viaje viaje = new Viaje();
             
             /*Contador para los viajes*/
     		 Integer contador = usuario.getContadorusuario() + 1;
     		 
     		/*SAber el numero de pasajeros para el primer if*/
     		
     		viaje.setPasajeros(1);
     			
     				/*SABER EL ORIGEN Y EL DESTINO PARA SABER SI ES DENTRO DE CU O NO */
     		   	    Origen origenif = origenDao.findOne(origen);
     		   	    Destino destinoif = destinoDao.findOne(destino);
     		   	    /*String para saber si el viaje es dentro de CU o no*/
     		   	    String orin = origenif.getOrigen();
     		   	    String destn = destinoif.getDestino();
     		   	    
     		   	    		if(contador%5 == 1) {
     		   	    			
     		   	    			TipoUsuario tipUsuario = usuario.getID_TIPO();
     		   	    			String user = tipUsuario.getTipo();
     		   	    			System.out.println(user);
     		   	    			
     		   	    					if(orin.equals("SI") && destn.equals("SI")) {
     		   	    						
     		   	    						if(user.equals("Estudiante")) {
     		   	    							
     		   	    								Integer calculodistancia = Integer.parseInt(distancia);
     		   	    								viaje.setDistancia(calculodistancia);
     		   	    								viaje.setTiempo(Integer.parseInt(tiempo));
     		   	    								viaje.setID_ORIGEN(origenif);
     		   	    								viaje.setID_DESTINO(destinoif);
     		   	    								viaje.setOrigin(origin);
     		   	    								viaje.setDestiny(destiny);
     		   	    								Integer cobromedio = 10;
     		   	    								Integer porcentaje = 15;
     		   	    								Integer porcen = (cobromedio*porcentaje)/100;
     		   	    								Integer cobrofinal = 10 - porcen;
     		   	    								
     		   	    								switch(randomNum) {
     		   	    								
     		   	    								case 2: 
     		   	    									
     		   	    									Integer porcentajecompartido = 10;
     		   	    									Integer porcencompartido = (cobrofinal*porcentajecompartido)/100;
     		   	    									Integer cobrocompartido = cobrofinal - porcencompartido;
		        
     		   	    									viaje.setCobro(cobrocompartido);
     		   	    									viajeDao.save(viaje);
     		   	    									Integer random =ThreadLocalRandom.current().nextInt(2, 200);
     		   	    									Integer random2 =ThreadLocalRandom.current().nextInt(2, 200);
     		   	    									Integer random3 =ThreadLocalRandom.current().nextInt(1, 3);
     		   	    									Integer random4 =ThreadLocalRandom.current().nextInt(1, 3);
     		   	    									PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere",random4,"somewhere",10);
     		   	    									usuarioDao.update(contador, usuario.getID_USUARIO());
     		   	    									usuario.getViajes().add(viaje);
     		   	    									usuarioDao.save(usuario);
     		   	    									ra.addFlashAttribute("pago", cobrocompartido);
     		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
     		   	    									break;
     		   	    								
     		   	    								case 3:
     		   	    									
     		   	    									Integer porcentajecompartido2 = 20;
     		   	    									Integer porcencompartido2 = (cobrofinal*porcentajecompartido2)/100;
     		   	    									Integer cobrocompartido2 = cobrofinal - porcencompartido2;
     		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    										random2 =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    										random3 =ThreadLocalRandom.current().nextInt(1, 3);
 		   	    										random4 =ThreadLocalRandom.current().nextInt(1, 3);
 		   	    										PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",20);
 		   	    										random =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    										random2 =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    										random3 =ThreadLocalRandom.current().nextInt(1, 3);
 		   	    										random4 =ThreadLocalRandom.current().nextInt(1, 3);
 		   	    										PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",20);
     		   	    									viaje.setCobro(cobrocompartido2);
     		   	    									viajeDao.save(viaje);
     		   	    									usuarioDao.update(contador, usuario.getID_USUARIO());
 		   	    										usuario.getViajes().add(viaje);
 		   	    										usuarioDao.save(usuario);
     		   	    									ra.addFlashAttribute("pago", cobrocompartido2);
     		   	    									ra.addFlashAttribute("people","Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
     		   	    									break;
     		   	    								
     		   	    								case 4:
     		   	    									
     		   	    									Integer porcentajecompartido3 = 30;
     		   	    									Integer porcencompartido3 = (cobrofinal*porcentajecompartido3)/100;
     		   	    									Integer cobrocompartido3 = cobrofinal - porcencompartido3;
     		   	    									viaje.setCobro(cobrocompartido3);
     		   	    									viajeDao.save(viaje);
     		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
     		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
	   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	   	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",30);
	   	    											random =ThreadLocalRandom.current().nextInt(2, 200);
	   	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
	   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	   	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",30);
	   	    											random =ThreadLocalRandom.current().nextInt(2, 200);
		   	    										random2 =ThreadLocalRandom.current().nextInt(2, 200);
		   	    										random3 =ThreadLocalRandom.current().nextInt(1, 3);
		   	    										random4 =ThreadLocalRandom.current().nextInt(1, 3);
		   	    										PedirViaje2("Nullam@interdumNunc.net",random+"",random2+"",random3,"nowhere4",random4,"somewhere4",30);
		   	    										
     		   	    									usuarioDao.update(contador, usuario.getID_USUARIO());
     		   	    									usuario.getViajes().add(viaje);
     		   	    									usuarioDao.save(usuario);
     		   	    									ra.addFlashAttribute("pago", cobrocompartido3);
     		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
     		   	    									break;
     		   	    								}
     		   	    								ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
     		   	    								return"redirect:/viaje_exitoso";
     		   	    								
     		   	    							
     		   	    						}else {
     		   	    							
     		   	    								Integer calculodistancia = Integer.parseInt(distancia);
     		   	    								viaje.setDistancia(calculodistancia);
     		   	    								viaje.setTiempo(Integer.parseInt(tiempo));
     		   	    								viaje.setID_ORIGEN(origenif);
     		   	    								viaje.setID_DESTINO(destinoif);
     		   	    								viaje.setOrigin(origin);
     		   	    								viaje.setDestiny(destiny);
     		   	    								Integer cobromedio = 10;
     		   	    								Integer porcentaje = 10;
     		   	    								Integer porcen = (cobromedio*porcentaje)/100;
     		   	    								Integer cobrofinal = 10 - porcen;
     		   	    								
     		   	    							switch(randomNum) {
 		   	    								
 		   	    								case 2: 
 		   	    									
 		   	    									Integer porcentajecompartido = 10;
 		   	    									Integer porcencompartido = (cobrofinal*porcentajecompartido)/100;
 		   	    									Integer cobrocompartido = cobrofinal - porcencompartido;
	        
 		   	    									viaje.setCobro(cobrocompartido);
 		   	    									viajeDao.save(viaje);
 		   	    									Integer random =ThreadLocalRandom.current().nextInt(2, 200);
		   	    									Integer random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											Integer random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											Integer random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",10);
	    											
 		   	    									usuarioDao.update(contador, usuario.getID_USUARIO());
 		   	    									usuario.getViajes().add(viaje);
 		   	    									usuarioDao.save(usuario);
 		   	    									ra.addFlashAttribute("pago", cobrocompartido);
 		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
 		   	    									break;
 		   	    								
 		   	    								case 3:
 		   	    									
 		   	    									Integer porcentajecompartido2 = 20;
 		   	    									Integer porcencompartido2 = (cobrofinal*porcentajecompartido2)/100;
 		   	    									Integer cobrocompartido2 = cobrofinal - porcencompartido2;
 		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",20);
	    											random =ThreadLocalRandom.current().nextInt(2, 200);
	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",20);
	    											
 		   	    									viaje.setCobro(cobrocompartido2);
 		   	    									viajeDao.save(viaje);
 		   	    	    	  				
 		   	    									usuarioDao.update(contador, usuario.getID_USUARIO());
	   	    										usuario.getViajes().add(viaje);
	   	    										usuarioDao.save(usuario);
 		   	    									ra.addFlashAttribute("pago", cobrocompartido2);
 		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
 		   	    									break;
 		   	    								
 		   	    								case 4:
 		   	    									
 		   	    									Integer porcentajecompartido3 = 30;
 		   	    									Integer porcencompartido3 = (cobrofinal*porcentajecompartido3)/100;
 		   	    									Integer cobrocompartido3 = cobrofinal - porcencompartido3;
    
 		   	    									viaje.setCobro(cobrocompartido3);
 		   	    									viajeDao.save(viaje);
 		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",30);
	    											random =ThreadLocalRandom.current().nextInt(2, 200);
	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",30);
	    											random =ThreadLocalRandom.current().nextInt(2, 200);
	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("Nullam@interdumNunc.net",random+"",random2+"",random3,"nowhere4",random4,"somewhere4",30);
   	    										
	   	    										usuarioDao.update(contador, usuario.getID_USUARIO());
	   	    										usuario.getViajes().add(viaje);
	   	    										usuarioDao.save(usuario);
 		   	    									ra.addFlashAttribute("pago", cobrocompartido3);
 		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
 		   	    									break;
 		   	    								}
 		   	    								ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
 		   	    								return"redirect:/viaje_exitoso";
     		   	    							
     		   	    						}
     		   	    					}else {
     		   	    						
     		   	    						if(user.equals("Estudiante")) {
     		   	    							
     		   	    								Integer calculodistancia = Integer.parseInt(distancia);
     		   	    								viaje.setDistancia(calculodistancia);
     		   	    								viaje.setTiempo(Integer.parseInt(tiempo));
     		   	    								viaje.setID_ORIGEN(origenif);
     		   	    								viaje.setID_DESTINO(destinoif);
     		   	    								viaje.setOrigin(origin);
     		   	    								viaje.setDestiny(destiny);
     		   	    								Integer cobromedio = 15 + (calculodistancia * 6);
     		   	    								Integer porcentaje = 15;
     		   	    								Integer porcen = (cobromedio*porcentaje)/100;
     		   	    								Integer cobrofinal = cobromedio - porcen;
		        
     		   	    							switch(randomNum) {
 		   	    								
 		   	    								case 2: 
 		   	    									
 		   	    									Integer porcentajecompartido = 10;
 		   	    									Integer porcencompartido = (cobrofinal*porcentajecompartido)/100;
 		   	    									Integer cobrocompartido = cobrofinal - porcencompartido;
	        
 		   	    									viaje.setCobro(cobrocompartido);
 		   	    									viajeDao.save(viaje);
 		   	    									Integer random =ThreadLocalRandom.current().nextInt(2, 200);
		   	    									Integer random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											Integer random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											Integer random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",10);
	    										
 		   	    									usuarioDao.update(contador, usuario.getID_USUARIO());
 		   	    									usuario.getViajes().add(viaje);
 		   	    									usuarioDao.save(usuario);
 		   	    									ra.addFlashAttribute("pago", cobrocompartido);
 		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
 		   	    									break;
 		   	    								
 		   	    								case 3:
 		   	    									
 		   	    									Integer porcentajecompartido2 = 20;
 		   	    									Integer porcencompartido2 = (cobrofinal*porcentajecompartido2)/100;
 		   	    									Integer cobrocompartido2 = cobrofinal - porcencompartido2;
        
 		   	    									viaje.setCobro(cobrocompartido2);
 		   	    									viajeDao.save(viaje);
 		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",20);
	    											random =ThreadLocalRandom.current().nextInt(2, 200);
	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",20);
	    											
 		   	    									usuarioDao.update(contador, usuario.getID_USUARIO());
 		   	    									usuario.getViajes().add(viaje);
 		   	    									usuarioDao.save(usuario);
 		   	    									ra.addFlashAttribute("pago", cobrocompartido2);
 		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
 		   	    									break;
 		   	    								
 		   	    								case 4:
 		   	    									
 		   	    									Integer porcentajecompartido3 = 30;
 		   	    									Integer porcencompartido3 = (cobrofinal*porcentajecompartido3)/100;
 		   	    									Integer cobrocompartido3 = cobrofinal - porcencompartido3;
 		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",30);
	    											random =ThreadLocalRandom.current().nextInt(2, 200);
	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",30);
	    											random =ThreadLocalRandom.current().nextInt(2, 200);
	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("Nullam@interdumNunc.net",random+"",random2+"",random3,"nowhere4",random4,"somewhere4",30);
   	    										
 		   	    									viaje.setCobro(cobrocompartido3);
 		   	    									viajeDao.save(viaje);
 		   	    	    	  				
 		   	    									usuarioDao.update(contador, usuario.getID_USUARIO());
 		   	    									usuario.getViajes().add(viaje);
 		   	    									usuarioDao.save(usuario);
 		   	    									ra.addFlashAttribute("pago", cobrocompartido3);
 		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
 		   	    									break;
 		   	    								}
 		   	    								ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
 		   	    								return"redirect:/viaje_exitoso";
     		   	    							
     		   	    						}else {
     		   	    							
     		   	    								Integer calculodistancia = Integer.parseInt(distancia);
     		   	    								viaje.setDistancia(calculodistancia);
     		   	    								viaje.setTiempo(Integer.parseInt(tiempo));
     		   	    								viaje.setID_ORIGEN(origenif);
     		   	    								viaje.setID_DESTINO(destinoif);
     		   	    								viaje.setOrigin(origin);
     		   	    								viaje.setDestiny(destiny);
     		   	    								Integer cobromedio = 15 + (calculodistancia * 6);
     		   	    								Integer porcentaje = 10;
     		   	    								Integer porcen = (cobromedio*porcentaje)/100;
     		   	    								Integer cobrofinal = cobromedio - porcen;
		        
     		   	    							switch(randomNum) {
 		   	    								
 		   	    								case 2: 
 		   	    									
 		   	    									Integer porcentajecompartido = 10;
 		   	    									Integer porcencompartido = (cobrofinal*porcentajecompartido)/100;
 		   	    									Integer cobrocompartido = cobrofinal - porcencompartido;
	        
 		   	    									viaje.setCobro(cobrocompartido);
 		   	    									viajeDao.save(viaje);
 		   	    									Integer random =ThreadLocalRandom.current().nextInt(2, 200);
		   	    									Integer random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											Integer random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											Integer random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",10);
	    											
 		   	    									usuarioDao.update(contador, usuario.getID_USUARIO());
 		   	    									usuario.getViajes().add(viaje);
 		   	    									usuarioDao.save(usuario);
 		   	    									ra.addFlashAttribute("pago", cobrocompartido);
 		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
 		   	    									break;
 		   	    								
 		   	    								case 3:
 		   	    									
 		   	    									Integer porcentajecompartido2 = 20;
 		   	    									Integer porcencompartido2 = (cobrofinal*porcentajecompartido2)/100;
 		   	    									Integer cobrocompartido2 = cobrofinal - porcencompartido2;
        
 		   	    									viaje.setCobro(cobrocompartido2);
 		   	    									viajeDao.save(viaje);
 		   	    								random =ThreadLocalRandom.current().nextInt(2, 200);
		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",20);
	    											random =ThreadLocalRandom.current().nextInt(2, 200);
	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",20);
	    											
 		   	    									usuarioDao.update(contador, usuario.getID_USUARIO());
 		   	    									usuario.getViajes().add(viaje);
 		   	    									usuarioDao.save(usuario);
 		   	    									ra.addFlashAttribute("pago", cobrocompartido2);
 		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
 		   	    									break;
 		   	    								
 		   	    								case 4:
 		   	    									
 		   	    									Integer porcentajecompartido3 = 30;
 		   	    									Integer porcencompartido3 = (cobrofinal*porcentajecompartido3)/100;
 		   	    									Integer cobrocompartido3 = cobrofinal - porcencompartido3;
    
 		   	    									viaje.setCobro(cobrocompartido3);
 		   	    									viajeDao.save(viaje);
 		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",30);
	    											random =ThreadLocalRandom.current().nextInt(2, 200);
	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",30);
	    											random =ThreadLocalRandom.current().nextInt(2, 200);
	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("Nullam@interdumNunc.net",random+"",random2+"",random3,"nowhere4",random4,"somewhere4",30);
   	    										
 		   	    									usuarioDao.update(contador, usuario.getID_USUARIO());
	   	    										usuario.getViajes().add(viaje);
	   	    										usuarioDao.save(usuario);
 		   	    									ra.addFlashAttribute("pago", cobrocompartido3);
 		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
 		   	    									break;
 		   	    								}
 		   	    								ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
 		   	    								return"redirect:/viaje_exitoso";
     		   	    							
     		   	    						}
     		   	    						
     		   	    					}
     		   	    			
     		   	    		}else {
     		   	    		
								TipoUsuario tipUsuario = usuario.getID_TIPO();
								String user = tipUsuario.getTipo();
								System.out.println(user);
								
								if(orin.equals("SI") && destn.equals("SI")) {
									
									if(user.equals("Estudiante")) {
										
											Integer calculodistancia = Integer.parseInt(distancia);
	   	    								viaje.setDistancia(calculodistancia);
	   	    								viaje.setTiempo(Integer.parseInt(tiempo));
	   	    								viaje.setID_ORIGEN(origenif);
	   	    								viaje.setID_DESTINO(destinoif);
	   	    								viaje.setOrigin(origin);
	   	    								viaje.setDestiny(destiny);
	   	    								Integer cobromedio = 15;
	   	    								Integer porcentaje = 15;
	   	    								Integer porcen = (cobromedio*porcentaje)/100;
	   	    								Integer cobrofinal = 15 - porcen;
    
	   	    								switch(randomNum) {
		   	    								
		   	    								case 2: 
		   	    									
		   	    									Integer porcentajecompartido = 10;
		   	    									Integer porcencompartido = (cobrofinal*porcentajecompartido)/100;
		   	    									Integer cobrocompartido = cobrofinal - porcencompartido;
        
		   	    									viaje.setCobro(cobrocompartido);
		   	    									viajeDao.save(viaje);
		   	    									Integer random =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    									Integer random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											Integer random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											Integer random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",10);
   	    											
		   	    			    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
		   	    			    	  				usuario.getViajes().add(viaje);
		   	    			    	  				usuarioDao.save(usuario);
		   	    									ra.addFlashAttribute("pago", cobrocompartido);
		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
		   	    									break;
		   	    								
		   	    								case 3:
		   	    									
		   	    									Integer porcentajecompartido2 = 20;
		   	    									Integer porcencompartido2 = (cobrofinal*porcentajecompartido2)/100;
		   	    									Integer cobrocompartido2 = cobrofinal - porcencompartido2;
    
		   	    									viaje.setCobro(cobrocompartido2);
		   	    									viajeDao.save(viaje);
		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",20);
   	    											random =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",20);
   	    											
		   	    			    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
		   	    			    	  				usuario.getViajes().add(viaje);
		   	    			    	  				usuarioDao.save(usuario);
		   	    									ra.addFlashAttribute("pago", cobrocompartido2);
		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
		   	    									break;
		   	    								
		   	    								case 4:
		   	    									
		   	    									Integer porcentajecompartido3 = 30;
		   	    									Integer porcencompartido3 = (cobrofinal*porcentajecompartido3)/100;
		   	    									Integer cobrocompartido3 = cobrofinal - porcencompartido3;

		   	    									viaje.setCobro(cobrocompartido3);
		   	    									viajeDao.save(viaje);
		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",30);
   	    											random =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",30);
   	    											random =ThreadLocalRandom.current().nextInt(2, 200);
	   	    										random2 =ThreadLocalRandom.current().nextInt(2, 200);
	   	    										random3 =ThreadLocalRandom.current().nextInt(1, 3);
	   	    										random4 =ThreadLocalRandom.current().nextInt(1, 3);
	   	    										PedirViaje2("Nullam@interdumNunc.net",random+"",random2+"",random3,"nowhere4",random4,"somewhere4",30);
	   	    										
		   	    			    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
		   	    			    	  				usuario.getViajes().add(viaje);
		   	    			    	  				usuarioDao.save(usuario);
		   	    									ra.addFlashAttribute("pago", cobrocompartido3);
		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
		   	    									break;
		   	    								}
		   	    								ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
		   	    								return"redirect:/viaje_exitoso";
										
									}else {
										
											Integer calculodistancia = Integer.parseInt(distancia);
											viaje.setDistancia(calculodistancia);
											viaje.setTiempo(Integer.parseInt(tiempo));
											viaje.setID_ORIGEN(origenif);
											viaje.setID_DESTINO(destinoif);
   	    									viaje.setOrigin(origin);
   	    									viaje.setDestiny(destiny);
   	    									Integer cobromedio = 15;
   	    									Integer porcentaje = 10;
   	    									Integer porcen = (cobromedio*porcentaje)/100;
   	    									Integer cobrofinal = 15 - porcen;
    
   	    									switch(randomNum) {
		   	    								
		   	    								case 2: 
		   	    									
		   	    									Integer porcentajecompartido = 10;
		   	    									Integer porcencompartido = (cobrofinal*porcentajecompartido)/100;
		   	    									Integer cobrocompartido = cobrofinal - porcencompartido;
        
		   	    									viaje.setCobro(cobrocompartido);
		   	    									viajeDao.save(viaje);
		   	    									Integer random =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    									Integer random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											Integer random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											Integer random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",10);
   	    											
		   	    			    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
		   	    			    	  				usuario.getViajes().add(viaje);
		   	    			    	  				usuarioDao.save(usuario);
		   	    									ra.addFlashAttribute("pago", cobrocompartido);
		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
		   	    									break;
		   	    								
		   	    								case 3:
		   	    									
		   	    									Integer porcentajecompartido2 = 20;
		   	    									Integer porcencompartido2 = (cobrofinal*porcentajecompartido2)/100;
		   	    									Integer cobrocompartido2 = cobrofinal - porcencompartido2;
    
		   	    									viaje.setCobro(cobrocompartido2);
		   	    									viajeDao.save(viaje);
		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",20);
   	    											random =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",20);
   	    											
	   	    										
		   	    			    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
		   	    			    	  				usuario.getViajes().add(viaje);
		   	    			    	  				usuarioDao.save(usuario);
		   	    									ra.addFlashAttribute("pago", cobrocompartido2);
		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
		   	    									break;
		   	    								
		   	    								case 4:
		   	    									
		   	    									Integer porcentajecompartido3 = 30;
		   	    									Integer porcencompartido3 = (cobrofinal*porcentajecompartido3)/100;
		   	    									Integer cobrocompartido3 = cobrofinal - porcencompartido3;

		   	    									viaje.setCobro(cobrocompartido3);
		   	    									viajeDao.save(viaje);
		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",30);
   	    											random =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",30);
   	    											random =ThreadLocalRandom.current().nextInt(2, 200);
	   	    										random2 =ThreadLocalRandom.current().nextInt(2, 200);
	   	    										random3 =ThreadLocalRandom.current().nextInt(1, 3);
	   	    										random4 =ThreadLocalRandom.current().nextInt(1, 3);
	   	    										PedirViaje2("Nullam@interdumNunc.net",random+"",random2+"",random3,"nowhere4",random4,"somewhere4",30);
	   	    										
		   	    			    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
		   	    			    	  				usuario.getViajes().add(viaje);
		   	    			    	  				usuarioDao.save(usuario);
		   	    									ra.addFlashAttribute("pago", cobrocompartido3);
		   	    									ra.addFlashAttribute("people","Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
		   	    									break;
		   	    								}
		   	    								ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
		   	    								return"redirect:/viaje_exitoso";
										
									}
									
								}else {
									
									if(user.equals("Estudiante")) {
										
											Integer calculodistancia = Integer.parseInt(distancia);
											viaje.setDistancia(calculodistancia);
											viaje.setTiempo(Integer.parseInt(tiempo));
											viaje.setID_ORIGEN(origenif);
											viaje.setID_DESTINO(destinoif);
	    									viaje.setOrigin(origin);
	    									viaje.setDestiny(destiny);
	    									Integer cobromedio = 15 + (calculodistancia * 8);
	    									Integer porcentaje = 15;
	    									Integer porcen = (cobromedio*porcentaje)/100;
	    									Integer cobrofinal = cobromedio - porcen;

	    									switch(randomNum) {
		   	    								
		   	    								case 2: 
		   	    									
		   	    									Integer porcentajecompartido = 10;
		   	    									Integer porcencompartido = (cobrofinal*porcentajecompartido)/100;
		   	    									Integer cobrocompartido = cobrofinal - porcencompartido;
        
		   	    									viaje.setCobro(cobrocompartido);
		   	    									viajeDao.save(viaje);
		   	    									Integer random =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    									Integer random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											Integer random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											Integer random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",10);
   	    											
		   	    			    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
		   	    			    	  				usuario.getViajes().add(viaje);
		   	    			    	  				usuarioDao.save(usuario);
		   	    									ra.addFlashAttribute("pago", cobrocompartido);
		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
		   	    									break;
		   	    								
		   	    								case 3:
		   	    									
		   	    									Integer porcentajecompartido2 = 20;
		   	    									Integer porcencompartido2 = (cobrofinal*porcentajecompartido2)/100;
		   	    									Integer cobrocompartido2 = cobrofinal - porcencompartido2;
    
		   	    									viaje.setCobro(cobrocompartido2);
		   	    									viajeDao.save(viaje);
		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",20);
   	    											random =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",20);
   	    											
		   	    			    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
		   	    			    	  				usuario.getViajes().add(viaje);
		   	    			    	  				usuarioDao.save(usuario);
		   	    									ra.addFlashAttribute("pago", cobrocompartido2);
		   	    									ra.addFlashAttribute("people","Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
		   	    									break;
		   	    								
		   	    								case 4:
		   	    									
		   	    									Integer porcentajecompartido3 = 30;
		   	    									Integer porcencompartido3 = (cobrofinal*porcentajecompartido3)/100;
		   	    									Integer cobrocompartido3 = cobrofinal - porcencompartido3;

		   	    									viaje.setCobro(cobrocompartido3);
		   	    									viajeDao.save(viaje);
		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",30);
   	    											random =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",30);
   	    											random =ThreadLocalRandom.current().nextInt(2, 200);
	   	    										random2 =ThreadLocalRandom.current().nextInt(2, 200);
	   	    										random3 =ThreadLocalRandom.current().nextInt(1, 3);
	   	    										random4 =ThreadLocalRandom.current().nextInt(1, 3);
	   	    										PedirViaje2("Nullam@interdumNunc.net",random+"",random2+"",random3,"nowhere4",random4,"somewhere4",30);
	   	    										
		   	    			    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
		   	    			    	  				usuario.getViajes().add(viaje);
		   	    			    	  				usuarioDao.save(usuario);
		   	    									ra.addFlashAttribute("pago", cobrocompartido3);
		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
		   	    									break;
		   	    								}
		   	    								ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
		   	    								return"redirect:/viaje_exitoso";
										
									}else {
										
											Integer calculodistancia = Integer.parseInt(distancia);
											viaje.setDistancia(calculodistancia);
											viaje.setTiempo(Integer.parseInt(tiempo));
											viaje.setID_ORIGEN(origenif);
											viaje.setID_DESTINO(destinoif);
    										viaje.setOrigin(origin);
    										viaje.setDestiny(destiny);
    										Integer cobromedio = 15 + (calculodistancia * 8);
    										Integer porcentaje = 10;
    										Integer porcen = (cobromedio*porcentaje)/100;
    										Integer cobrofinal = cobromedio - porcen;

    										switch(randomNum) {
		   	    								
		   	    								case 2: 
		   	    									
		   	    									Integer porcentajecompartido = 10;
		   	    									Integer porcencompartido = (cobrofinal*porcentajecompartido)/100;
		   	    									Integer cobrocompartido = cobrofinal - porcencompartido;
        
		   	    									viaje.setCobro(cobrocompartido);
		   	    									viajeDao.save(viaje);
		   	    									Integer random =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    									Integer random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											Integer random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											Integer random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",10);
   	    											
		   	    			    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
		   	    			    	  				usuario.getViajes().add(viaje);
		   	    			    	  				usuarioDao.save(usuario);
		   	    									ra.addFlashAttribute("pago", cobrocompartido);
		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
		   	    									break;
		   	    								
		   	    								case 3:
		   	    									
		   	    									Integer porcentajecompartido2 = 20;
		   	    									Integer porcencompartido2 = (cobrofinal*porcentajecompartido2)/100;
		   	    									Integer cobrocompartido2 = cobrofinal - porcencompartido2;
    
		   	    									viaje.setCobro(cobrocompartido2);
		   	    									viajeDao.save(viaje);
		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",20);
   	    											random =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",20);
   	    											
		   	    			    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
		   	    			    	  				usuario.getViajes().add(viaje);
		   	    			    	  				usuarioDao.save(usuario);
		   	    									ra.addFlashAttribute("pago", cobrocompartido2);
		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
		   	    									break;
		   	    								
		   	    								case 4:
		   	    									
		   	    									Integer porcentajecompartido3 = 30;
		   	    									Integer porcencompartido3 = (cobrofinal*porcentajecompartido3)/100;
		   	    									Integer cobrocompartido3 = cobrofinal - porcencompartido3;

		   	    									viaje.setCobro(cobrocompartido3);
		   	    									viajeDao.save(viaje);
		   	    									random =ThreadLocalRandom.current().nextInt(2, 200);
 		   	    									random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("malesuada@erat.net",random+"",random2+"",random3,"nowhere2",random4,"somewhere2",30);
   	    											random =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random2 =ThreadLocalRandom.current().nextInt(2, 200);
   	    											random3 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											random4 =ThreadLocalRandom.current().nextInt(1, 3);
   	    											PedirViaje2("rutrum.magna.Cras@nibh.net",random+"",random2+"",random3,"nowhere3",random4,"somewhere3",30);
   	    											random =ThreadLocalRandom.current().nextInt(2, 200);
	   	    										random2 =ThreadLocalRandom.current().nextInt(2, 200);
	   	    										random3 =ThreadLocalRandom.current().nextInt(1, 3);
	   	    										random4 =ThreadLocalRandom.current().nextInt(1, 3);
	   	    										PedirViaje2("Nullam@interdumNunc.net",random+"",random2+"",random3,"nowhere4",random4,"somewhere4",30);
	   	    										
		   	    			    	  				usuarioDao.update(contador, usuario.getID_USUARIO());
		   	    			    	  				usuario.getViajes().add(viaje);
		   	    			    	  				usuarioDao.save(usuario);
		   	    									ra.addFlashAttribute("pago", cobrocompartido3);
		   	    									ra.addFlashAttribute("people", "Compartiste el viaje con" + " " + (randomNum-1) + " " + "personas");
		   	    									break;
		   	    								}
		   	    								ra.addFlashAttribute("success", "Tu Viaje se realizo con exito!");
		   	    								return"redirect:/viaje_exitoso";
									
								}
     		   	    			
     		   	    		}
     				
     			}
     		
   	    	
   	    }else {
   	    	
   	    	ra.addFlashAttribute("error", "El Usuario No Existe En La Base De Datos");
   			
   	    	return"redirect:/viaje_compartido";
   	    }
   	    }
	/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	public void PedirViaje2(String correo,String distancia,String tiempo,long origen,String origin,long destino,String destiny,int descuento) {


/*BUSCAR USUARIO POR CORREO*/
Usuario usuario = usuarioDao.porCorreo(correo);

Viaje viaje = new Viaje();

/*Contador para los viajes*/
Integer contador = usuario.getContadorusuario() + 1;

/*SAber el numero de pasajeros para el primer if*/
	
	/*SABER EL ORIGEN Y EL DESTINO PARA SABER SI ES DENTRO DE CU O NO */
	    Origen origenif = origenDao.findOne(origen);
	    Destino destinoif = destinoDao.findOne(destino);
	    /*String para saber si el viaje es dentro de CU o no*/
	    String orin = origenif.getOrigen();
	    String destn = destinoif.getDestino();
	    	
	    		if(orin.equals("SI") && destn.equals("SI")) {
	    			
	    		Integer calculodistancia = Integer.parseInt(distancia);
	    		viaje.setDistancia(calculodistancia);
	    		viaje.setTiempo(Integer.parseInt(tiempo));
	    		viaje.setID_ORIGEN(origenif);
	    		viaje.setID_DESTINO(destinoif);
	    		viaje.setOrigin(origin);
	    		viaje.setDestiny(destiny);
	    			
	    		Integer cobro = 15;
	    		Integer porcentajecompartido = descuento;
				Integer porcencompartido = (cobro*porcentajecompartido)/100;
				Integer cobrocompartido = cobro - porcencompartido;
				viaje.setCobro(cobrocompartido);
				
				viajeDao.save(viaje);
				
				usuarioDao.update(contador, usuario.getID_USUARIO());
				usuario.getViajes().add(viaje);
				usuarioDao.save(usuario);
	    		}else {
	    			
	    		Integer calculodistancia = Integer.parseInt(distancia);
	    		viaje.setDistancia(calculodistancia);
	    		viaje.setTiempo(Integer.parseInt(tiempo));
	    		viaje.setID_ORIGEN(origenif);
	    		viaje.setID_DESTINO(destinoif);
	    		viaje.setOrigin(origin);
	    		viaje.setDestiny(destiny);
	    		Integer cobrofinal = 15 + (calculodistancia * 8);
	    		Integer porcentajecompartido = descuento;
	    		Integer porcencompartido = (cobrofinal*porcentajecompartido)/100;
	    		Integer cobrocompartido = cobrofinal - porcencompartido;
				viaje.setCobro(cobrocompartido);
				
				viajeDao.save(viaje);
				
				usuarioDao.update(contador, usuario.getID_USUARIO());
				usuario.getViajes().add(viaje);
				usuarioDao.save(usuario);
	    		}
	
}
}