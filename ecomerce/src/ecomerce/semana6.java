package ecomerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class EcommerceClasses {

    // Interfaz ProcesoPago
    public interface ProcesoPago {
        boolean iniciarPago(double monto);
        boolean verificarPago();
        boolean confirmarPago();
    }

    // Clase PagoTarjeta
    public static class PagoTarjeta implements ProcesoPago {
        private String numeroTarjeta;
        private String fechaExpiracion;
        private String codigoCVV;

        public PagoTarjeta(String numeroTarjeta, String fechaExpiracion, String codigoCVV) {
            if (numeroTarjeta == null || !numeroTarjeta.matches("\\d{16}")) {
                throw new IllegalArgumentException("Número de tarjeta inválido (debe tener 16 dígitos)");
            }
            if (fechaExpiracion == null || !fechaExpiracion.matches("\\d{2}/\\d{2}")) {
                throw new IllegalArgumentException("Fecha de expiración inválida (formato MM/AA)");
            }
            if (codigoCVV == null || !codigoCVV.matches("\\d{3}")) {
                throw new IllegalArgumentException("Código CVV inválido (debe tener 3 dígitos)");
            }
            this.numeroTarjeta = numeroTarjeta;
            this.fechaExpiracion = fechaExpiracion;
            this.codigoCVV = codigoCVV;
        }

        @Override
        public boolean iniciarPago(double monto) {
            if (monto <= 0) {
                System.out.println("Error: El monto del pago debe ser positivo");
                return false;
            }
            System.out.println("Iniciando pago con tarjeta por $" + monto);
            return true;
        }

        @Override
        public boolean verificarPago() {
            System.out.println("Verificando pago con tarjeta (número: **** **** **** " + numeroTarjeta.substring(12) + ")");
            // Simulación de verificación
            return true;
        }

        @Override
        public boolean confirmarPago() {
            System.out.println("Pago con tarjeta confirmado");
            return true;
        }
    }

    // Clase PagoPayPal
    public static class PagoPayPal implements ProcesoPago {
        private String emailCuenta;

        public PagoPayPal(String emailCuenta) {
            if (emailCuenta == null || !Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(emailCuenta).matches()) {
                throw new IllegalArgumentException("Correo de PayPal inválido");
            }
            this.emailCuenta = emailCuenta;
        }

        @Override
        public boolean iniciarPago(double monto) {
            if (monto <= 0) {
                System.out.println("Error: El monto del pago debe ser positivo");
                return false;
            }
            System.out.println("Iniciando pago con PayPal por $" + monto + " (cuenta: " + emailCuenta + ")");
            return true;
        }

        @Override
        public boolean verificarPago() {
            System.out.println("Verificando pago con PayPal para " + emailCuenta);
            // Simulación de verificación
            return true;
        }

        @Override
        public boolean confirmarPago() {
            System.out.println("Pago con PayPal confirmado");
            return true;
        }
    }

    // Clase Abstracta GestorInventario
    public abstract static class GestorInventario {
        protected List<Item> catalogo;

        public GestorInventario() {
            this.catalogo = new ArrayList<>();
        }

        public List<Item> getCatalogo() {
            return Collections.unmodifiableList(catalogo);
        }

        public abstract void añadirProducto(Item item);
        public abstract void eliminarProducto(String idItem);
        public abstract void actualizarStock(String idItem, int nuevoStock);
    }

    // Clase GestorInventarioDigital
    public static class GestorInventarioDigital extends GestorInventario {
        @Override
        public void añadirProducto(Item item) {
            if (!(item instanceof ProductoDigital)) {
                throw new IllegalArgumentException("Solo se pueden añadir productos digitales al inventario digital");
            }
            if (catalogo.stream().anyMatch(p -> p.getId().equals(item.getId()))) {
                throw new IllegalArgumentException("El producto con ID " + item.getId() + " ya existe en el inventario");
            }
            catalogo.add(item);
            System.out.println("Producto digital '" + item.getNombre() + "' añadido al inventario digital");
        }

        @Override
        public void eliminarProducto(String idItem) {
            Item itemAEliminar = null;
            for (Item p : catalogo) {
                if (p.getId().equals(idItem)) {
                    itemAEliminar = p;
                    break;
                }
            }
            if (itemAEliminar != null) {
                catalogo.remove(itemAEliminar);
                System.out.println("Producto digital '" + itemAEliminar.getNombre() + "' eliminado del inventario digital");
            } else {
                throw new IllegalArgumentException("Producto con ID " + idItem + " no encontrado en el inventario digital");
            }
        }

        @Override
        public void actualizarStock(String idItem, int nuevoStock) {
            if (nuevoStock < 0) {
                throw new IllegalArgumentException("El stock no puede ser negativo");
            }
            Item item = catalogo.stream()
                    .filter(p -> p.getId().equals(idItem))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Producto con ID " + idItem + " no encontrado"));
            if (!(item instanceof ProductoDigital)) {
                throw new IllegalArgumentException("El producto no es digital");
            }
            item.setStock(nuevoStock);
            System.out.println("Stock del producto digital '" + item.getNombre() + "' actualizado a " + nuevoStock);
        }
    }

    // Clase GestorInventarioFisico
    public static class GestorInventarioFisico extends GestorInventario {
        @Override
        public void añadirProducto(Item item) {
            if (!(item instanceof ProductoFisico)) {
                throw new IllegalArgumentException("Solo se pueden añadir productos físicos al inventario físico");
            }
            if (catalogo.stream().anyMatch(p -> p.getId().equals(item.getId()))) {
                throw new IllegalArgumentException("El producto con ID " + item.getId() + " ya existe en el inventario");
            }
            catalogo.add(item);
            System.out.println("Producto físico '" + item.getNombre() + "' añadido al inventario físico");
        }

        @Override
        public void eliminarProducto(String idItem) {
            Item itemAEliminar = null;
            for (Item p : catalogo) {
                if (p.getId().equals(idItem)) {
                    itemAEliminar = p;
                    break;
                }
            }
            if (itemAEliminar != null) {
                catalogo.remove(itemAEliminar);
                System.out.println("Producto físico '" + itemAEliminar.getNombre() + "' eliminado del inventario físico");
            } else {
                throw new IllegalArgumentException("Producto con ID " + idItem + " no encontrado en el inventario físico");
            }
        }

        @Override
        public void actualizarStock(String idItem, int nuevoStock) {
            if (nuevoStock < 0) {
                throw new IllegalArgumentException("El stock no puede ser negativo");
            }
            Item item = catalogo.stream()
                    .filter(p -> p.getId().equals(idItem))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Producto con ID " + idItem + " no encontrado"));
            if (!(item instanceof ProductoFisico)) {
                throw new IllegalArgumentException("El producto no es físico");
            }
            item.setStock(nuevoStock);
            System.out.println("Stock del producto físico '" + item.getNombre() + " actualizado a " + nuevoStock);
        }
    }

    // Clase Abstracta Item (sin cambios relevantes, incluida para referencia)
    public abstract static class Item {
        private String id;
        private String nombre;
        private String descripcion;
        private double precio;
        private int stock;

        public Item(String id, String nombre, String descripcion, double precio, int stock) {
            setId(id);
            setNombre(nombre);
            setDescripcion(descripcion);
            setPrecio(precio);
            setStock(stock);
        }

        public String getId() { return id; }
        public String getNombre() { return nombre; }
        public String getDescripcion() { return descripcion; }
        public double getPrecio() { return precio; }
        public int getStock() { return stock; }

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

        public abstract void mostrarInformacion();
    }

    // Clase ProductoDigital (sin cambios relevantes, ajustada para Item)
    public static class ProductoDigital extends Item {
        private String formatoArchivo;
        private double tamanoArchivoMB;

        public ProductoDigital(String id, String nombre, String descripcion, double precio, int stock,
                               String formatoArchivo, double tamanoArchivoMB) {
            super(id, nombre, descripcion, precio, stock);
            setFormatoArchivo(formatoArchivo);
            setTamanoArchivoMB(tamanoArchivoMB);
        }

        public String getFormatoArchivo() { return formatoArchivo; }
        public double getTamanoArchivoMB() { return tamanoArchivoMB; }

        public void setFormatoArchivo(String formatoArchivo) {
            if (formatoArchivo == null || formatoArchivo.trim().isEmpty()) {
                throw new IllegalArgumentException("El formato de archivo no puede ser nulo ni vacío");
            }
            this.formatoArchivo = formatoArchivo;
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

    // Clase ProductoFisico (sin cambios relevantes, ajustada para Item)
    public static class ProductoFisico extends Item {
        private double pesoKg;
        private String dimensiones;

        public ProductoFisico(String id, String nombre, String descripcion, double precio, int stock,
                              double pesoKg, String dimensiones) {
            super(id, nombre, descripcion, precio, stock);
            setPesoKg(pesoKg);
            setDimensiones(dimensiones);
        }

        public double getPesoKg() { return pesoKg; }
        public String getDimensiones() { return dimensiones; }

        public void setPesoKg(double pesoKg) {
            if (pesoKg < 0) {
                throw new IllegalArgumentException("El peso no puede ser negativo");
            }
            this.pesoKg = pesoKg;
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

    // Clase Abstracta Usuario (sin cambios relevantes, incluida para referencia)
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

        public String getId() { return id; }
        public String getNombre() { return nombre; }
        public String getEmail() { return email; }
        public String getContrasena() { return contrasena; }

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
            this.contrasena = contrasena;
        }

        public abstract void realizarAccion();

        public void login() {
            System.out.println("Usuario " + nombre + " ha iniciado sesión.");
        }

        public void logout() {
            System.out.println("Usuario " + nombre + " ha cerrado sesión.");
        }
    }

    // Clase Carrito (ajustada para soportar pagos)
    public static class Carrito {
        private final List<Item> productos;
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

        public boolean procesarPago(ProcesoPago metodoPago) {
            if (productos.isEmpty()) {
                System.out.println("Error: El carrito está vacío, no se puede procesar el pago");
                return false;
            }
            double monto = calcularTotal();
            if (metodoPago.iniciarPago(monto)) {
                if (metodoPago.verificarPago()) {
                    if (metodoPago.confirmarPago()) {
                        System.out.println("Pago procesado exitosamente por $" + monto);
                        return true;
                    }
                }
            }
            System.out.println("Error: No se pudo procesar el pago");
            return false;
        }
    }

    // Clase Cliente (ajustada para soportar pagos)
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

        public boolean realizarCompra(ProcesoPago metodoPago, String idPedido) {
            if (carrito.procesarPago(metodoPago)) {
                agregarCompraAlHistorial(idPedido);
                carrito.vaciarCarrito();
                return true;
            }
            return false;
        }

        @Override
        public void realizarAccion() {
            verCarrito();
        }
    }

    // Clase Administrador (ajustada para usar GestorInventario)
    public static class Administrador extends Usuario {
        private List<String> permisos;
        private GestorInventario gestorDigital;
        private GestorInventario gestorFisico;

        public Administrador(String id, String nombre, String email, String contrasena) {
            super(id, nombre, email, contrasena);
            this.permisos = new ArrayList<>();
            this.gestorDigital = new GestorInventarioDigital();
            this.gestorFisico = new GestorInventarioFisico();
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
            if (!tienePermiso("gestionar_inventario")) {
                System.out.println("El administrador " + getNombre() + " no tiene permiso para gestionar inventario.");
                return;
            }
            try {
                if (item instanceof ProductoDigital) {
                    gestorDigital.actualizarStock(item.getId(), nuevoStock);
                } else if (item instanceof ProductoFisico) {
                    gestorFisico.actualizarStock(item.getId(), nuevoStock);
                } else {
                    throw new IllegalArgumentException("Tipo de producto no soportado");
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error al gestionar inventario: " + e.getMessage());
            }
        }

        public void establecerPromocion(Item item, double nuevoPrecio, String descripcionPromocion) {
            if (!tienePermiso("establecer_promociones")) {
                System.out.println("El administrador " + getNombre() + " no tiene permiso para establecer promociones.");
                return;
            }
            try {
                item.setPrecio(nuevoPrecio);
                item.setDescripcion(item.getDescripcion() + " (Promoción: " + descripcionPromocion + ")");
                System.out.println("Promoción aplicada al item '" + item.getNombre() + "'. Nuevo precio: $" + nuevoPrecio);
            } catch (IllegalArgumentException e) {
                System.err.println("Error al establecer promoción: " + e.getMessage());
            }
        }

        public void agregarProducto(Item item) {
            if (!tienePermiso("gestionar_productos")) {
                System.out.println(getNombre() + " no tiene permiso para agregar items.");
                return;
            }
            try {
                if (item instanceof ProductoDigital) {
                    gestorDigital.añadirProducto(item);
                } else if (item instanceof ProductoFisico) {
                    gestorFisico.añadirProducto(item);
                } else {
                    throw new IllegalArgumentException("Tipo de producto no soportado");
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error al agregar producto: " + e.getMessage());
            }
        }

        public void eliminarProducto(String idItem) {
            if (!tienePermiso("gestionar_productos")) {
                System.out.println(getNombre() + " no tiene permiso para eliminar items.");
                return;
            }
            try {
                Item item = gestorDigital.getCatalogo().stream()
                        .filter(p -> p.getId().equals(idItem))
                        .findFirst()
                        .orElseGet(() -> gestorFisico.getCatalogo().stream()
                                .filter(p -> p.getId().equals(idItem))
                                .findFirst()
                                .orElse(null));
                if (item == null) {
                    throw new IllegalArgumentException("Producto con ID " + idItem + " no encontrado");
                }
                if (item instanceof ProductoDigital) {
                    gestorDigital.eliminarProducto(idItem);
                } else {
                    gestorFisico.eliminarProducto(idItem);
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error al eliminar producto: " + e.getMessage());
            }
        }

        @Override
        public void realizarAccion() {
            System.out.println("Administrador " + getNombre() + " está gestionando el sistema.");
        }
    }

    // Método main ajustado para probar las nuevas funcionalidades
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

        System.out.println("--- Probando Gestión de Inventario ---");
        admin1.agregarProducto(libro);
        admin1.agregarProducto(ebook);
        admin1.agregarProducto(software);
        System.out.println("Stock inicial del libro: " + libro.getStock());
        admin1.gestionarInventario(libro, 45);
        System.out.println("Stock del libro después de gestión: " + libro.getStock());
        System.out.println();

        System.out.println("Precio inicial del eBook: $" + ebook.getPrecio());
        admin1.establecerPromocion(ebook, 7.99, "Oferta de Verano");
        ebook.mostrarInformacion();
        System.out.println();

        System.out.println("--- Probando Carrito y Pagos ---");
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

        System.out.println("--- Probando Proceso de Pago ---");
        ProcesoPago pagoTarjeta = new PagoTarjeta("1234567890123456", "12/25", "123");
        ProcesoPago pagoPayPal = new PagoPayPal("cliente@paypal.com");
        cliente1.realizarCompra(pagoTarjeta, "PEDIDO#12345");
        cliente1.verCarrito();
        cliente1.verHistorialCompras();
        System.out.println();

        System.out.println("--- Probando Eliminación de Producto ---");
        admin1.eliminarProducto("PD001");
        System.out.println("Catálogo digital después de eliminar:");
        for (Item p : admin1.gestorDigital.getCatalogo()) {
            System.out.println(" - " + p.getNombre());
        }
        System.out.println("Catálogo físico después de eliminar:");
        for (Item p : admin1.gestorFisico.getCatalogo()) {
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

        cliente2.realizarCompra(pagoPayPal, "PEDIDO#67890");
        cliente2.verCarrito();
        cliente2.verHistorialCompras();
        System.out.println("Stock Camiseta después de compra: " + camiseta.getStock());
        System.out.println("Stock Música después de compra: " + musica.getStock());
    }
}