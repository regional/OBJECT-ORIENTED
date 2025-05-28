package ecomerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class EcommerceClasses {

    // Clase Abstracta Item (reemplaza a Producto)
    public abstract static class Item {
        private String id;
        private String nombre;
        private String descripcion;
        private double precio;
        private int stock;

        // Constructor
        public Item(String id, String nombre, String descripcion, double precio, int stock) {
            setId(id);
            setNombre(nombre);
            setDescripcion(descripcion);
            setPrecio(precio);
            setStock(stock);
        }

        // Getters
        public String getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public double getPrecio() {
            return precio;
        }

        public int getStock() {
            return stock;
        }

        // Setters con validaciones
        public void setId(String id) {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID no puede ser nulo ni vacío");
            }
            this.id = id;
        }

        public void setNombre(String nombre) {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede ser nulo ni vacío");
            }
            this.nombre = nombre;
        }

        public void setDescripcion(String descripcion) {
            if (descripcion == null || descripcion.trim().isEmpty()) {
                throw new IllegalArgumentException("La descripción no puede ser nula ni vacía");
            }
            this.descripcion = descripcion;
        }

        public void setPrecio(double precio) {
            if (precio < 0) {
                throw new IllegalArgumentException("El precio no puede ser negativo");
            }
            this.precio = precio;
        }

        public void setStock(int stock) {
            if (stock < 0) {
                throw new IllegalArgumentException("El stock no puede ser negativo");
            }
            this.stock = stock;
        }

        // Método abstracto para polimorfismo
        public abstract void mostrarInformacion();
    }

    // Clase Derivada: ProductoDigital
    public static class ProductoDigital extends Item {
        private String formatoArchivo;
        private double tamanoArchivoMB;

        public ProductoDigital(String id, String nombre, String descripcion, double precio, int stock,
                               String formatoArchivo, double tamanoArchivoMB) {
            super(id, nombre, descripcion, precio, stock);
            setFormatoArchivo(formatoArchivo);
            setTamanoArchivoMB(tamanoArchivoMB);
        }

        public String getFormatoArchivo() {
            return formatoArchivo;
        }

        public void setFormatoArchivo(String formatoArchivo) {
            if (formatoArchivo == null || formatoArchivo.trim().isEmpty()) {
                throw new IllegalArgumentException("El formato de archivo no puede ser nulo ni vacío");
            }
            this.formatoArchivo = formatoArchivo;
        }

        public double getTamanoArchivoMB() {
            return tamanoArchivoMB;
        }

        public void setTamanoArchivoMB(double tamanoArchivoMB) {
            if (tamanoArchivoMB < 0) {
                throw new IllegalArgumentException("El tamaño del archivo no puede ser negativo");
            }
            this.tamanoArchivoMB = tamanoArchivoMB;
        }

        @Override
        public void mostrarInformacion() {
            System.out.println("ID: " + getId());
            System.out.println("Nombre: " + getNombre());
            System.out.println("Descripción: " + getDescripcion());
            System.out.println("Precio: $" + getPrecio());
            System.out.println("Stock: " + getStock());
            System.out.println("Formato de Archivo: " + formatoArchivo);
            System.out.println("Tamaño de Archivo: " + tamanoArchivoMB + " MB");
        }
    }

    // Clase Derivada: ProductoFisico
    public static class ProductoFisico extends Item {
        private double pesoKg;
        private String dimensiones;

        public ProductoFisico(String id, String nombre, String descripcion, double precio, int stock,
                              double pesoKg, String dimensiones) {
            super(id, nombre, descripcion, precio, stock);
            setPesoKg(pesoKg);
            setDimensiones(dimensiones);
        }

        public double getPesoKg() {
            return pesoKg;
        }

        public void setPesoKg(double pesoKg) {
            if (pesoKg < 0) {
                throw new IllegalArgumentException("El peso no puede ser negativo");
            }
            this.pesoKg = pesoKg;
        }

        public String getDimensiones() {
            return dimensiones;
        }

        public void setDimensiones(String dimensiones) {
            if (dimensiones == null || dimensiones.trim().isEmpty()) {
                throw new IllegalArgumentException("Las dimensiones no pueden ser nulas ni vacías");
            }
            this.dimensiones = dimensiones;
        }

        @Override
        public void mostrarInformacion() {
            System.out.println("ID: " + getId());
            System.out.println("Nombre: " + getNombre());
            System.out.println("Descripción: " + getDescripcion());
            System.out.println("Precio: $" + getPrecio());
            System.out.println("Stock: " + getStock());
            System.out.println("Peso: " + pesoKg + " Kg");
            System.out.println("Dimensiones: " + dimensiones);
        }
    }

    // Clase Abstracta Usuario
    public abstract static class Usuario {
        private String id;
        private String nombre;
        private String email;
        private String contrasena;
        private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

        public Usuario(String id, String nombre, String email, String contrasena) {
            setId(id);
            setNombre(nombre);
            setEmail(email);
            setContrasena(contrasena);
        }

        public String getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getEmail() {
            return email;
        }

        public String getContrasena() {
            return contrasena;
        }

        public void setId(String id) {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID no puede ser nulo ni vacío");
            }
            this.id = id;
        }

        public void setNombre(String nombre) {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede ser nulo ni vacío");
            }
            this.nombre = nombre;
        }

        public void setEmail(String email) {
            if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
                throw new IllegalArgumentException("El email debe tener un formato válido");
            }
            this.email = email;
        }

        public void setContrasena(String contrasena) {
            if (contrasena == null || contrasena.length() < 8) {
                throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
            }
            this.contrasena = contrasena; // En producción, encriptar aquí
        }

        public abstract void realizarAccion();

        public void login() {
            System.out.println("Usuario " + nombre + " ha iniciado sesión.");
        }

        public void logout() {
            System.out.println("Usuario " + nombre + " ha cerrado sesión.");
        }
    }

    // Clase Carrito
    public static class Carrito {
        private final List<Item> productos; // Cambiado a Item
        private final Map<String, Integer> cantidadProductos;
        private double total;

        public Carrito() {
            this.productos = new ArrayList<>();
            this.cantidadProductos = new HashMap<>();
            this.total = 0.0;
        }

        public List<Item> getProductos() {
            return Collections.unmodifiableList(productos);
        }

        public double getTotal() {
            return total;
        }

        public int getCantidadDeProducto(String idProducto) {
            return cantidadProductos.getOrDefault(idProducto, 0);
        }

        public void agregarProducto(Item producto, int cantidad) {
            if (producto == null) {
                throw new IllegalArgumentException("El producto no puede ser nulo");
            }
            if (cantidad <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser positiva");
            }
            if (cantidad > producto.getStock()) {
                throw new IllegalArgumentException("Cantidad solicitada (" + cantidad + ") excede el stock disponible (" + producto.getStock() + ") para " + producto.getNombre());
            }

            cantidadProductos.put(producto.getId(), cantidadProductos.getOrDefault(producto.getId(), 0) + cantidad);
            for (int i = 0; i < cantidad; i++) {
                productos.add(producto);
                producto.setStock(producto.getStock() - 1);
            }
            calcularTotal();
        }

        public void removerProducto(Item producto, int cantidadARemover) {
            if (producto == null) {
                throw new IllegalArgumentException("El producto no puede ser nulo");
            }
            if (cantidadARemover <= 0) {
                throw new IllegalArgumentException("La cantidad a remover debe ser positiva");
            }

            int cantidadEnCarrito = cantidadProductos.getOrDefault(producto.getId(), 0);
            if (cantidadARemover > cantidadEnCarrito) {
                throw new IllegalArgumentException("No hay suficientes unidades de '" + producto.getNombre() + "' en el carrito");
            }

            for (int i = 0; i < cantidadARemover; i++) {
                productos.remove(producto);
                producto.setStock(producto.getStock() + 1);
            }
            cantidadProductos.put(producto.getId(), cantidadEnCarrito - cantidadARemover);
            if (cantidadEnCarrito - cantidadARemover == 0) {
                cantidadProductos.remove(producto.getId());
            }
            calcularTotal();
        }

        public void removerProducto(Item producto) {
            int cantidadEnCarrito = cantidadProductos.getOrDefault(producto.getId(), 0);
            if (cantidadEnCarrito > 0) {
                removerProducto(producto, cantidadEnCarrito);
            } else {
                throw new IllegalArgumentException("El producto '" + producto.getNombre() + "' no está en el carrito.");
            }
        }

        private double calcularTotal() {
            total = 0.0;
            for (Item p : productos) {
                total += p.getPrecio();
            }
            return total;
        }

        public void vaciarCarrito() {
            for (Item producto : new ArrayList<>(productos)) {
                int cantidad = cantidadProductos.getOrDefault(producto.getId(), 0);
                producto.setStock(producto.getStock() + cantidad);
            }
            productos.clear();
            cantidadProductos.clear();
            total = 0.0;
            System.out.println("El carrito ha sido vaciado.");
        }
    }

    // Clase Cliente (ajustada para usar Item)
    public static class Cliente extends Usuario {
        private List<String> historialCompras;
        private Map<String, String> preferencias;
        private Carrito carrito;

        public Cliente(String id, String nombre, String email, String contrasena) {
            super(id, nombre, email, contrasena);
            this.historialCompras = new ArrayList<>();
            this.preferencias = new HashMap<>();
            this.carrito = new Carrito();
        }

        public List<String> getHistorialCompras() {
            return Collections.unmodifiableList(historialCompras);
        }

        public void agregarCompraAlHistorial(String idPedido) {
            this.historialCompras.add(idPedido);
            System.out.println("Compra " + idPedido + " agregada al historial de " + getNombre());
        }

        public Map<String, String> getPreferencias() {
            return new HashMap<>(preferencias);
        }

        public void setPreferencia(String clave, String valor) {
            if (clave == null || clave.trim().isEmpty() || valor == null || valor.trim().isEmpty()) {
                throw new IllegalArgumentException("La clave y el valor no pueden ser nulos ni vacíos");
            }
            this.preferencias.put(clave, valor);
            System.out.println("Preferencia '" + clave + ": " + valor + "' establecida para " + getNombre());
        }

        public String getPreferencia(String clave) {
            return this.preferencias.get(clave);
        }

        public Carrito getCarrito() {
            return carrito;
        }

        public void agregarProductoAlCarrito(Item producto, int cantidad) {
            try {
                this.carrito.agregarProducto(producto, cantidad);
                System.out.println(cantidad + " unidad(es) de '" + producto.getNombre() + "' agregada(s) al carrito de " + getNombre());
            } catch (IllegalArgumentException e) {
                System.err.println("Error al agregar al carrito: " + e.getMessage());
            }
        }

        public void verCarrito() {
            System.out.println("Carrito de " + getNombre() + ":");
            if (carrito.getProductos().isEmpty()) {
                System.out.println("  El carrito está vacío.");
                return;
            }
            for (Item p : carrito.getProductos()) {
                System.out.println("  - " + p.getNombre() + " ($" + p.getPrecio() + ")");
            }
            System.out.println("Total del carrito: $" + carrito.getTotal());
        }

        public void verHistorialCompras() {
            System.out.println("Historial de compras de " + getNombre() + ":");
            if (historialCompras.isEmpty()) {
                System.out.println("  No hay compras registradas.");
            } else {
                for (String compra : historialCompras) {
                    System.out.println("  - " + compra);
                }
            }
        }

        @Override
        public void realizarAccion() {
            verCarrito();
        }
    }

    // Clase Administrador (ajustada para usar Item)
    public static class Administrador extends Usuario {
        private List<String> permisos;

        public Administrador(String id, String nombre, String email, String contrasena) {
            super(id, nombre, email, contrasena);
            this.permisos = new ArrayList<>();
        }

        public List<String> getPermisos() {
            return Collections.unmodifiableList(permisos);
        }

        public void agregarPermiso(String permiso) {
            if (permiso == null || permiso.trim().isEmpty()) {
                throw new IllegalArgumentException("El permiso no puede ser nulo ni vacío");
            }
            if (!this.permisos.contains(permiso)) {
                this.permisos.add(permiso);
                System.out.println("Permiso '" + permiso + "' agregado al administrador " + getNombre());
            }
        }

        public void removerPermiso(String permiso) {
            if (permiso == null || permiso.trim().isEmpty()) {
                throw new IllegalArgumentException("El permiso no puede ser nulo ni vacío");
            }
            if (this.permisos.remove(permiso)) {
                System.out.println("Permiso '" + permiso + "' removido del administrador " + getNombre());
            }
        }

        public boolean tienePermiso(String permiso) {
            return this.permisos.contains(permiso);
        }

        public void gestionarInventario(Item item, int nuevoStock) {
            if (tienePermiso("gestionar_inventario")) {
                System.out.println("Administrador " + getNombre() + " está gestionando el inventario.");
                item.setStock(nuevoStock);
                System.out.println("Stock del item '" + item.getNombre() + "' actualizado a " + nuevoStock);
            } else {
                System.out.println("El administrador " + getNombre() + " no tiene permiso para gestionar inventario.");
            }
        }

        public void establecerPromocion(Item item, double nuevoPrecio, String descripcionPromocion) {
            if (tienePermiso("establecer_promociones")) {
                System.out.println("Administrador " + getNombre() + " está estableciendo una promoción.");
                item.setPrecio(nuevoPrecio);
                item.setDescripcion(item.getDescripcion() + " (Promoción: " + descripcionPromocion + ")");
                System.out.println("Promoción aplicada al item '" + item.getNombre() + "'. Nuevo precio: $" + nuevoPrecio);
            } else {
                System.out.println("El administrador " + getNombre() + " no tiene permiso para establecer promociones.");
            }
        }

        public void agregarProducto(List<Item> catalogo, Item item) {
            if (tienePermiso("gestionar_productos")) {
                catalogo.add(item);
                System.out.println("Item '" + item.getNombre() + "' agregado al catálogo por " + getNombre());
            } else {
                System.out.println(getNombre() + " no tiene permiso para agregar items.");
            }
        }

        public void eliminarProducto(List<Item> catalogo, String idItem) {
            if (tienePermiso("gestionar_productos")) {
                Item itemAEliminar = null;
                for (Item p : catalogo) {
                    if (p.getId().equals(idItem)) {
                        itemAEliminar = p;
                        break;
                    }
                }
                if (itemAEliminar != null) {
                    catalogo.remove(itemAEliminar);
                    System.out.println("Item '" + itemAEliminar.getNombre() + "' eliminado del catálogo por " + getNombre());
                } else {
                    System.out.println("Item con ID '" + idItem + "' no encontrado.");
                }
            } else {
                System.out.println(getNombre() + " no tiene permiso para eliminar items.");
            }
        }

        @Override
        public void realizarAccion() {
            System.out.println("Administrador " + getNombre() + " está gestionando el sistema.");
        }
    }

    // Método main ajustado para usar Item
    public static void main(String[] args) {
        System.out.println("--- Probando Items ---");
        Item libro = new ProductoFisico("PF001", "El Señor de los Anillos", "Trilogía completa", 25.99, 50, 1.2, "15x22x7 cm");
        Item ebook = new ProductoDigital("PD001", "El Señor de los Anillos (eBook)", "Versión digital PDF", 9.99, 1000, "PDF", 5.5);
        Item software = new ProductoDigital("PD002", "Antivirus Pro", "Licencia anual", 49.99, 500, "EXE", 250.0);

        libro.mostrarInformacion();
        System.out.println();
        ebook.mostrarInformacion();
        System.out.println();
        software.mostrarInformacion();

        System.out.println("\n--- Probando Usuarios ---");
        Cliente cliente1 = new Cliente("C001", "Ana Pérez", "ana.perez@email.com", "cliente123456");
        Administrador admin1 = new Administrador("A001", "Carlos López", "carlos.lopez@admin.com", "adminSecure123");

        cliente1.login();
        admin1.login();
        System.out.println();

        admin1.agregarPermiso("gestionar_inventario");
        admin1.agregarPermiso("establecer_promociones");
        admin1.agregarPermiso("gestionar_productos");

        System.out.println("Stock inicial del libro: " + libro.getStock());
        admin1.gestionarInventario(libro, 45);
        System.out.println("Stock del libro después de gestión: " + libro.getStock());
        System.out.println();

        System.out.println("Precio inicial del eBook: $" + ebook.getPrecio());
        admin1.establecerPromocion(ebook, 7.99, "Oferta de Verano");
        ebook.mostrarInformacion();
        System.out.println();

        cliente1.agregarProductoAlCarrito(libro, 2);
        cliente1.agregarProductoAlCarrito(ebook, 1);
        try {
            System.out.println("Intentando agregar más libros de los que hay en stock:");
            cliente1.agregarProductoAlCarrito(libro, 50);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
        cliente1.verCarrito();
        System.out.println("Stock del libro después de añadir al carrito: " + libro.getStock());
        System.out.println("Stock del eBook después de añadir al carrito: " + ebook.getStock());
        System.out.println();

        System.out.println("Removiendo 1 unidad de 'El Señor de los Anillos' del carrito...");
        cliente1.getCarrito().removerProducto(libro, 1);
        cliente1.verCarrito();
        System.out.println("Stock del libro después de remover del carrito: " + libro.getStock());
        System.out.println();

        cliente1.setPreferencia("categoriaFavorita", "Fantasía");
        cliente1.agregarCompraAlHistorial("PEDIDO#12345");
        cliente1.verHistorialCompras();
        System.out.println("Preferencia de categoría: " + cliente1.getPreferencia("categoriaFavorita"));
        System.out.println();

        List<Item> catalogo = new ArrayList<>();
        catalogo.add(libro);
        catalogo.add(ebook);
        admin1.agregarProducto(catalogo, software);
        System.out.println("Catálogo actual:");
        for (Item p : catalogo) {
            System.out.println(" - " + p.getNombre());
        }
        System.out.println();
        admin1.eliminarProducto(catalogo, "PD001");
        System.out.println("Catálogo después de eliminar item:");
        for (Item p : catalogo) {
            System.out.println(" - " + p.getNombre());
        }
        System.out.println();

        cliente1.realizarAccion();
        admin1.realizarAccion();

        cliente1.logout();
        admin1.logout();

        System.out.println("\n--- Probando vaciar carrito ---");
        Cliente cliente2 = new Cliente("C002", "Juan Rodríguez", "juan.r@email.com", "juanito123456");
        Item camiseta = new ProductoFisico("PF002", "Camiseta Logo", "Camiseta de algodón", 19.99, 100, 0.2, "M");
        Item musica = new ProductoDigital("PD003", "Album Hits", "Album en MP3", 9.99, 500, "MP3", 80.0);

        System.out.println("Stock inicial Camiseta: " + camiseta.getStock());
        System.out.println("Stock inicial Música: " + musica.getStock());

        cliente2.agregarProductoAlCarrito(camiseta, 3);
        cliente2.agregarProductoAlCarrito(musica, 1);
        cliente2.verCarrito();
        System.out.println("Stock Camiseta después de agregar: " + camiseta.getStock());
        System.out.println("Stock Música después de agregar: " + musica.getStock());

        cliente2.getCarrito().vaciarCarrito();
        cliente2.verCarrito();
        System.out.println("Stock Camiseta después de vaciar: " + camiseta.getStock());
        System.out.println("Stock Música después de vaciar: " + musica.getStock());
    }
}