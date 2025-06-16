import java.util.ArrayList;
import java.util.List;

// Excepciones personalizadas
class InventarioInsuficienteExcepcion extends Exception {
    public InventarioInsuficienteExcepcion(String mensaje) {
        super(mensaje);
    }
}

class PagoFallidoExcepcion extends Exception {
    public PagoFallidoExcepcion(String mensaje) {
        super(mensaje);
    }
}

class UsuarioNoAutorizadoExcepcion extends Exception {
    public UsuarioNoAutorizadoExcepcion(String mensaje) {
        super(mensaje);
    }
}

// Clase Producto
class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public void reducirStock(int cantidad) throws InventarioInsuficienteExcepcion {
        if (cantidad > stock) {
            throw new InventarioInsuficienteExcepcion("Stock insuficiente para " + nombre);
        }
        stock -= cantidad;
    }
}

// Clase Usuario
class Usuario {
    private String id;
    private String nombre;
    private boolean esPremium;

    public Usuario(String id, String nombre, boolean esPremium) {
        this.id = id;
        this.nombre = nombre;
        this.esPremium = esPremium;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public boolean esPremium() { return esPremium; }
}

// Clase Carrito
class Carrito {
    private String idUsuario;
    private List<Producto> productos;
    private List<Integer> cantidades;

    public Carrito(String idUsuario) {
        this.idUsuario = idUsuario;
        this.productos = new ArrayList<>();
        this.cantidades = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) throws InventarioInsuficienteExcepcion {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        producto.reducirStock(cantidad);
        productos.add(producto);
        cantidades.add(cantidad);
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < productos.size(); i++) {
            total += productos.get(i).getPrecio() * cantidades.get(i);
        }
        return total;
    }

    public List<Producto> getProductos() { return productos; }
    public List<Integer> getCantidades() { return cantidades; }
}

// Clase Inventario
class Inventario {
    private List<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public Producto buscarProducto(String id) throws InventarioInsuficienteExcepcion {
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new InventarioInsuficienteExcepcion("Producto no encontrado: " + id);
    }
}

// Clase Pago
class Pago {
    public boolean procesarPago(Usuario usuario, double monto) throws PagoFallidoExcepcion, UsuarioNoAutorizadoExcepcion {
        if (!usuario.esPremium()) {
            throw new UsuarioNoAutorizadoExcepcion("Usuario no autorizado para realizar pagos");
        }
        if (monto <= 0) {
            throw new PagoFallidoExcepcion("Monto inválido para el pago");
        }
        // Simulación de procesamiento de pago
        return true;
    }
}

// Clase principal con pruebas unitarias usando JUnit
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Semana8 {
    private Producto producto;
    private Usuario usuario;
    private Carrito carrito;
    private Inventario inventario;
    private Pago pago;

    @Before
    public void setUp() {
        producto = new Producto("P1", "Laptop", 1000.0, 10);
        usuario = new Usuario("U1", "Juan", true);
        carrito = new Carrito("U1");
        inventario = new Inventario();
        pago = new Pago();
        inventario.agregarProducto(producto);
    }

    @Test
    public void testAgregarProductoCarrito() throws InventarioInsuficienteExcepcion {
        carrito.agregarProducto(producto, 2);
        assertEquals(8, producto.getStock());
        assertEquals(1, carrito.getProductos().size());
        assertEquals(2000.0, carrito.calcularTotal(), 0.01);
    }

    @Test(expected = InventarioInsuficienteExcepcion.class)
    public void testAgregarProductoSinStock() throws InventarioInsuficienteExcepcion {
        carrito.agregarProducto(producto, 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAgregarProductoCantidadInvalida() throws InventarioInsuficienteExcepcion {
        carrito.agregarProducto(producto, 0);
    }

    @Test
    public void testProcesarPagoExitoso() throws PagoFallidoExcepcion, UsuarioNoAutorizadoExcepcion {
        boolean resultado = pago.procesarPago(usuario, 1000.0);
        assertTrue(resultado);
    }

    @Test(expected = PagoFallidoExcepcion.class)
    public void testProcesarPagoMontoInvalido() throws PagoFallidoExcepcion, UsuarioNoAutorizadoExcepcion {
        pago.procesarPago(usuario, 0);
    }

    @Test(expected = UsuarioNoAutorizadoExcepcion.class)
    public void testProcesarPagoUsuarioNoPremium() throws PagoFallidoExcepcion, UsuarioNoAutorizadoExcepcion {
        Usuario usuarioNoPremium = new Usuario("U2", "Ana", false);
        pago.procesarPago(usuarioNoPremium, 1000.0);
    }

    @Test
    public void testBuscarProductoInventario() throws InventarioInsuficienteExcepcion {
        Producto encontrado = inventario.buscarProducto("P1");
        assertEquals("Laptop", encontrado.getNombre());
    }

    @Test(expected = InventarioInsuficienteExcepcion.class)
    public void testBuscarProductoNoExistente() throws InventarioInsuficienteExcepcion {
        inventario.buscarProducto("P2");
    }
}