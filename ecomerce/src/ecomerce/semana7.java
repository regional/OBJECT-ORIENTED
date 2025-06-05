package econmerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Clase Singleton para Configuración del Sistema
class ConfiguracionSistema {
    private static volatile ConfiguracionSistema instancia = null;
    private String urlBaseDatos;
    private String temaUI;
    private int maxIntentosLogin;

    // Constructor privado
    private ConfiguracionSistema() {
        // Prevenir instanciación externa
        if (instancia != null) {
            throw new RuntimeException("Usa getInstancia() para obtener la única instancia.");
        }
        // Configuraciones por defecto
        this.urlBaseDatos = "jdbc:mysql://localhost:3306/ecommerce";
        this.temaUI = "Claro";
        this.maxIntentosLogin = 3;
    }

    // Método estático para obtener la única instancia
    public static ConfiguracionSistema getInstancia() {
        if (instancia == null) {
            synchronized (ConfiguracionSistema.class) {
                if (instancia == null) {
                    instancia = new ConfiguracionSistema();
                }
            }
        }
        return instancia;
    }

    // Getters y Setters
    public String getUrlBaseDatos() {
        return urlBaseDatos;
    }

    public void setUrlBaseDatos(String urlBaseDatos) {
        this.urlBaseDatos = urlBaseDatos;
    }

    public String getTemaUI() {
        return temaUI;
    }

    public void setTemaUI(String temaUI) {
        this.temaUI = temaUI;
    }

    public int getMaxIntentosLogin() {
        return maxIntentosLogin;
    }

    public void setMaxIntentosLogin(int maxIntentosLogin) {
        if (maxIntentosLogin > 0) {
            this.maxIntentosLogin = maxIntentosLogin;
        } else {
            throw new IllegalArgumentException("Máximo de intentos de login debe ser positivo");
        }
    }
}

// Interfaz para el patrón Observer
interface Observador {
    void actualizar(String mensaje);
}

// Sujeto (Observable) para el patrón Observer
class Pedido {
    private String id;
    private String estado;
    private List<Observador> observadores;

    public Pedido(String id, String estado) {
        this.id = id;
        this.estado = estado;
        this.observadores = new ArrayList<>();
    }

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void removerObservador(Observador observador) {
        observadores.remove(observador);
    }

    public void setEstado(String estado) {
        this.estado = estado;
        notificarObservadores();
    }

    public String getEstado() {
        return estado;
    }

    private void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.actualizar("El pedido " + id + " ha cambiado al estado: " + estado);
        }
    }
}

// Clase concreta para Observador: Interfaz de Usuario
class InterfazUsuario implements Observador {
    private String nombre;

    public InterfazUsuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("Interfaz de Usuario [" + nombre + "]: " + mensaje);
    }
}

// Clase concreta para Observador: Sistema de Inventario
class SistemaInventario implements Observador {
    @Override
    public void actualizar(String mensaje) {
        System.out.println("Sistema de Inventario: " + mensaje);
        // Aquí se podría actualizar el inventario según el estado del pedido
    }
}

// Clase Producto Base
abstract class Producto {
    protected String id;
    protected String nombre;
    protected double precio;

    public Producto(String id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.setPrecio(precio);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        } else {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
    }

    public abstract void mostrarInformacion();
}

// Clase ProductoDigital
class ProductoDigital extends Producto {
    private String formatoArchivo;

    public ProductoDigital(String id, String nombre, double precio, String formatoArchivo) {
        super(id, nombre, precio);
        this.formatoArchivo = formatoArchivo;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Producto Digital - ID: " + id + ", Nombre: " + nombre + ", Precio: $" + precio + ", Formato: " + formatoArchivo);
    }
}

// Clase ProductoFisico
class ProductoFisico extends Producto {
    private double pesoKg;

    public ProductoFisico(String id, String nombre, double precio, double pesoKg) {
        super(id, nombre, precio);
        this.pesoKg = pesoKg;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Producto Físico - ID: " + id + ", Nombre: " + nombre + ", Precio: $" + precio + ", Peso: " + pesoKg + " kg");
    }
}

// Clase Usuario Base
abstract class Usuario {
    protected String id;
    protected String nombre;
    protected String email;

    public Usuario(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}

// Clase Cliente
class Cliente extends Usuario {
    private Map<String, String> preferencias;

    public Cliente(String id, String nombre, String email) {
        super(id, nombre, email);
        this.preferencias = new HashMap<>();
    }

    public void setPreferencia(String clave, String valor) {
        preferencias.put(clave, valor);
    }
}

// Clase Administrador
class Administrador extends Usuario {
    private List<String> permisos;

    public Administrador(String id, String nombre, String email) {
        super(id, nombre, email);
        this.permisos = new ArrayList<>();
    }

    public void agregarPermiso(String permiso) {
        permisos.add(permiso);
    }
}

// Interfaz para la Fábrica
interface Fabrica {
    Producto crearProducto(String tipo, String id, String nombre, double precio, Map<String, Object> atributos);
    Usuario crearUsuario(String tipo, String id, String nombre, String email);
}

// Clase Factory para crear Productos y Usuarios
class FabricaEntidades implements Fabrica {
    @Override
    public Producto crearProducto(String tipo, String id, String nombre, double precio, Map<String, Object> atributos) {
        switch (tipo.toLowerCase()) {
            case "digital":
                String formatoArchivo = (String) atributos.getOrDefault("formatoArchivo", "PDF");
                return new ProductoDigital(id, nombre, precio, formatoArchivo);
            case "fisico":
                double pesoKg = (double) atributos.getOrDefault("pesoKg", 1.0);
                return new ProductoFisico(id, nombre, precio, pesoKg);
            default:
                throw new IllegalArgumentException("Tipo de producto no soportado: " + tipo);
        }
    }

    @Override
    public Usuario crearUsuario(String tipo, String id, String nombre, String email) {
        switch (tipo.toLowerCase()) {
            case "cliente":
                return new Cliente(id, nombre, email);
            case "administrador":
                return new Administrador(id, nombre, email);
            default:
                throw new IllegalArgumentException("Tipo de usuario no soportado: " + tipo);
        }
    }
}

// Clase principal para probar la implementación
public class semana7 {
    public static void main(String[] args) {
        // Prueba Singleton: Configuración del Sistema
        ConfiguracionSistema config1 = ConfiguracionSistema.getInstancia();
        ConfiguracionSistema config2 = ConfiguracionSistema.getInstancia();
        System.out.println("¿Son la misma instancia? " + (config1 == config2)); // true
        config1.setTemaUI("Oscuro");
        System.out.println("Tema UI: " + config1.getTemaUI()); // Oscuro
        System.out.println("Tema UI (desde config2): " + config2.getTemaUI()); // Oscuro

        // Prueba Factory: Creación de Productos y Usuarios
        FabricaEntidades fabrica = new FabricaEntidades();
        Map<String, Object> atributosProducto = new HashMap<>();
        atributosProducto.put("formatoArchivo", "MP3");
        Producto productoDigital = fabrica.crearProducto("digital", "PD001", "Música Hits", 9.99, atributosProducto);
        atributosProducto.put("pesoKg", 2.5);
        Producto productoFisico = fabrica.crearProducto("fisico", "PF001", "Libro Físico", 25.99, atributosProducto);

        Usuario cliente = fabrica.crearUsuario("cliente", "C001", "Ana Pérez", "ana@email.com");
        Usuario admin = fabrica.crearUsuario("administrador", "A001", "Carlos López", "carlos@admin.com");

        productoDigital.mostrarInformacion();
        productoFisico.mostrarInformacion();
        System.out.println("Usuario creado: " + cliente.getNombre());
        System.out.println("Administrador creado: " + admin.getNombre());

        // Prueba Observer: Notificaciones de cambio de estado de pedido
        Pedido pedido = new Pedido("PED001", "Pendiente");
        Observador ui = new InterfazUsuario("UI Principal");
        Observador inventario = new SistemaInventario();

        pedido.agregarObservador(ui);
        pedido.agregarObservador(inventario);

        pedido.setEstado("Enviado");
        pedido.setEstado("Entregado");
    }
}