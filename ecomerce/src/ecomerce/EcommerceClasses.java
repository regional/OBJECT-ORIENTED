package ecomerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcommerceClasses {

    // Clase Producto Base
    public static class Producto {
        private String id;
        private String nombre;
        private String descripcion;
        private double precio;
        private int stock;

        // Constructor
        public Producto(String id, String nombre, String descripcion, double precio, int stock) {
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.setPrecio(precio); // Usar setter para validación
            this.setStock(stock);   // Usar setter para validación
        }

        // Getters y Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
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

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            if (stock >= 0) {
                this.stock = stock;
            } else {
                throw new IllegalArgumentException("El stock no puede ser negativo");
            }
        }

        // Método para mostrar información general del producto (puede ser sobrescrito)
        public void mostrarInformacion() {
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombre);
            System.out.println("Descripción: " + descripcion);
            System.out.println("Precio: $" + precio);
            System.out.println("Stock: " + stock);
        }
    }

    // Clase Derivada: ProductoDigital
    public static class ProductoDigital extends Producto {
        private String formatoArchivo; // Ej: PDF, MP3, MP4
        private double tamanoArchivoMB; // Tamaño en Megabytes

        // Constructor
        public ProductoDigital(String id, String nombre, String descripcion, double precio, int stock,
                               String formatoArchivo, double tamanoArchivoMB) {
            super(id, nombre, descripcion, precio, stock); // Llama al constructor de la clase base
            this.formatoArchivo = formatoArchivo;
            this.tamanoArchivoMB = tamanoArchivoMB;
        }

        // Getters y Setters específicos para ProductoDigital
        public String getFormatoArchivo() {
            return formatoArchivo;
        }

        public void setFormatoArchivo(String formatoArchivo) {
            this.formatoArchivo = formatoArchivo;
        }

        public double getTamanoArchivoMB() {
            return tamanoArchivoMB;
        }

        public void setTamanoArchivoMB(double tamanoArchivoMB) {
            if (tamanoArchivoMB >= 0) {
                this.tamanoArchivoMB = tamanoArchivoMB;
            } else {
                throw new IllegalArgumentException("El tamaño del archivo no puede ser negativo");
            }
        }

        // Sobrescritura del método para mostrar información específica
        @Override
        public void mostrarInformacion() {
            super.mostrarInformacion(); // Llama al método de la clase base
            System.out.println("Formato de Archivo: " + formatoArchivo);
            System.out.println("Tamaño de Archivo: " + tamanoArchivoMB + " MB");
        }
    }

    // Clase Derivada: ProductoFisico
    public static class ProductoFisico extends Producto {
        private double pesoKg; // Peso en Kilogramos
        private String dimensiones; // Ej: "10x20x5 cm"

        // Constructor
        public ProductoFisico(String id, String nombre, String descripcion, double precio, int stock,
                              double pesoKg, String dimensiones) {
            super(id, nombre, descripcion, precio, stock); // Llama al constructor de la clase base
            this.setPesoKg(pesoKg); // Usar setter para validación
            this.dimensiones = dimensiones;
        }

        // Getters y Setters específicos para ProductoFisico
        public double getPesoKg() {
            return pesoKg;
        }

        public void setPesoKg(double pesoKg) {
            if (pesoKg >= 0) {
                this.pesoKg = pesoKg;
            } else {
                throw new IllegalArgumentException("El peso no puede ser negativo");
            }
        }

        public String getDimensiones() {
            return dimensiones;
        }

        public void setDimensiones(String dimensiones) {
            this.dimensiones = dimensiones;
        }

        // Sobrescritura del método para mostrar información específica
        @Override
        public void mostrarInformacion() {
            super.mostrarInformacion(); // Llama al método de la clase base
            System.out.println("Peso: " + pesoKg + " Kg");
            System.out.println("Dimensiones: " + dimensiones);
        }
    }

    // Clase Usuario Base
    public static class Usuario {
        private String id;
        private String nombre;
        private String email;
        private String contrasena; // Considerar usar char[] o encriptación en una aplicación real

        // Constructor
        public Usuario(String id, String nombre, String email, String contrasena) {
            this.id = id;
            this.nombre = nombre;
            this.email = email; // Idealmente validar formato de email
            this.contrasena = contrasena;
        }

        // Finalizer (equivalente a destructor) - Generalmente no es necesario en Java moderno
        // La recolección de basura se encarga de la memoria.
        // Si se necesita liberar recursos específicos, se usa try-with-resources o el método close() de AutoCloseable.
        @Override
        protected void finalize() throws Throwable {
            try {
                // System.out.println("Usuario " + nombre + " está siendo finalizado."); // Para depuración
            } finally {
                super.finalize();
            }
        }

        // Getters y Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            // Aquí se podría agregar validación de formato de email
            this.email = email;
        }

        public String getContrasena() {
            return contrasena;
        }

        public void setContrasena(String contrasena) {
            // Aquí se podría agregar lógica para encriptar la contraseña
            this.contrasena = contrasena;
        }

        public void login() {
            System.out.println("Usuario " + nombre + " ha iniciado sesión.");
        }

        public void logout() {
            System.out.println("Usuario " + nombre + " ha cerrado sesión.");
        }
    }

    // Clase Derivada: Cliente
    public static class Cliente extends Usuario {
        private List<String> historialCompras; // Lista de IDs de pedidos o descripciones
        private Map<String, String> preferencias; // Ej: {"categoriaFavorita": "Electronica", "notificaciones": "activadas"}
        private Carrito carrito;

        // Constructor
        public Cliente(String id, String nombre, String email, String contrasena) {
            super(id, nombre, email, contrasena);
            this.historialCompras = new ArrayList<>();
            this.preferencias = new HashMap<>();
            this.carrito = new Carrito(); // Cada cliente tiene su propio carrito
        }

        // Getters y Setters específicos para Cliente
        public List<String> getHistorialCompras() {
            return historialCompras;
        }

        public void agregarCompraAlHistorial(String idPedido) {
            this.historialCompras.add(idPedido);
            System.out.println("Compra " + idPedido + " agregada al historial de " + getNombre());
        }

        public Map<String, String> getPreferencias() {
            return preferencias;
        }

        public void setPreferencia(String clave, String valor) {
            this.preferencias.put(clave, valor);
            System.out.println("Preferencia '" + clave + ": " + valor + "' establecida para " + getNombre());
        }

        public String getPreferencia(String clave) {
            return this.preferencias.get(clave);
        }

        public Carrito getCarrito() {
            return carrito;
        }

        // Métodos específicos de Cliente
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

        public void agregarProductoAlCarrito(Producto producto, int cantidad) {
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
            for (Producto p : carrito.getProductos()) {
                System.out.println("  - " + p.getNombre() + " ($" + p.getPrecio() + ")");
            }
            System.out.println("Total del carrito: $" + carrito.calcularTotal());
        }
    }

    // Clase Derivada: Administrador
    public static class Administrador extends Usuario {
        private List<String> permisos; // Lista de permisos, ej: "gestionar_inventario", "establecer_promociones"

        // Constructor
        public Administrador(String id, String nombre, String email, String contrasena) {
            super(id, nombre, email, contrasena);
            this.permisos = new ArrayList<>();
        }

        // Getters y Setters específicos para Administrador
        public List<String> getPermisos() {
            return permisos;
        }

        public void agregarPermiso(String permiso) {
            if (!this.permisos.contains(permiso)) {
                this.permisos.add(permiso);
                System.out.println("Permiso '" + permiso + "' agregado al administrador " + getNombre());
            }
        }

        public void removerPermiso(String permiso) {
            if (this.permisos.remove(permiso)) {
                System.out.println("Permiso '" + permiso + "' removido del administrador " + getNombre());
            }
        }

        public boolean tienePermiso(String permiso) {
            return this.permisos.contains(permiso);
        }

        // Métodos específicos de Administrador
        public void gestionarInventario(Producto producto, int nuevoStock) {
            if (tienePermiso("gestionar_inventario")) {
                System.out.println("Administrador " + getNombre() + " está gestionando el inventario.");
                producto.setStock(nuevoStock);
                System.out.println("Stock del producto '" + producto.getNombre() + "' actualizado a " + nuevoStock);
            } else {
                System.out.println("El administrador " + getNombre() + " no tiene permiso para gestionar inventario.");
            }
        }

        public void establecerPromocion(Producto producto, double nuevoPrecio, String descripcionPromocion) {
            if (tienePermiso("establecer_promociones")) {
                System.out.println("Administrador " + getNombre() + " está estableciendo una promoción.");
                producto.setPrecio(nuevoPrecio);
                producto.setDescripcion(producto.getDescripcion() + " (Promoción: " + descripcionPromocion + ")");
                System.out.println("Promoción aplicada al producto '" + producto.getNombre() + "'. Nuevo precio: $" + nuevoPrecio);
            } else {
                System.out.println("El administrador " + getNombre() + " no tiene permiso para establecer promociones.");
            }
        }

        public void agregarProducto(List<Producto> catalogo, Producto producto) {
             if (tienePermiso("gestionar_productos")) { // Asumimos un permiso para esto
                catalogo.add(producto);
                System.out.println("Producto '" + producto.getNombre() + "' agregado al catálogo por " + getNombre());
            } else {
                System.out.println(getNombre() + " no tiene permiso para agregar productos.");
            }
        }

        public void eliminarProducto(List<Producto> catalogo, String idProducto) {
            if (tienePermiso("gestionar_productos")) {
                Producto productoAEliminar = null;
                for (Producto p : catalogo) {
                    if (p.getId().equals(idProducto)) {
                        productoAEliminar = p;
                        break;
                    }
                }
                if (productoAEliminar != null) {
                    catalogo.remove(productoAEliminar);
                    System.out.println("Producto '" + productoAEliminar.getNombre() + "' eliminado del catálogo por " + getNombre());
                } else {
                    System.out.println("Producto con ID '" + idProducto + "' no encontrado.");
                }
            } else {
                System.out.println(getNombre() + " no tiene permiso para eliminar productos.");
            }
        }
    }

    // Clase Carrito (ya existente, pero la incluyo para que el código sea completo y funcional)
    public static class Carrito {
        private java.util.ArrayList<Producto> productos; // Lista de instancias de Producto
        private Map<String, Integer> cantidadProductos; // Para rastrear la cantidad de cada producto
        private double total;

        // Constructor
        public Carrito() {
            this.productos = new java.util.ArrayList<>();
            this.cantidadProductos = new HashMap<>();
            this.total = 0.0;
        }

        // Getters
        public java.util.ArrayList<Producto> getProductos() {
            // Devuelve una nueva lista para evitar modificaciones externas directas a la lista interna
            // Si se quiere devolver las instancias reales para modificar (ej. stock), no clonar.
            // Por simplicidad aquí devolvemos la referencia directa.
            return productos;
        }

        public double getTotal() {
            calcularTotal(); // Asegurar que el total esté actualizado
            return total;
        }

        public int getCantidadDeProducto(String idProducto) {
            return cantidadProductos.getOrDefault(idProducto, 0);
        }


        // Métodos
        public void agregarProducto(Producto producto, int cantidad) {
            if (producto == null) {
                throw new IllegalArgumentException("El producto no puede ser nulo");
            }
            if (cantidad <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser positiva");
            }
            if (cantidad > producto.getStock()) {
                throw new IllegalArgumentException("Cantidad solicitada (" + cantidad + ") excede el stock disponible (" + producto.getStock() + ") para " + producto.getNombre());
            }

            // Verificar si el producto ya está en el carrito para actualizar cantidad
            boolean encontrado = false;
            for(Producto pEnCarrito : productos) {
                if (pEnCarrito.getId().equals(producto.getId())) {
                    // Aquí podrías optar por reemplazar o sumar, dependiendo de la lógica de negocio.
                    // Por ahora, si se agrega el mismo producto, se trata como una nueva adición
                    // con su propia cantidad, lo cual no es lo ideal para un carrito típico.
                    // Mejor sería actualizar la cantidad del producto existente.
                    // Vamos a modificar esto para que actualice la cantidad si ya existe.
                    encontrado = true;
                    break;
                }
            }

            // Si no se encuentra, se añade como nuevo. Si se encuentra, actualiza cantidad.
            // Esta lógica es un poco simplificada. Un carrito real manejaría mejor las cantidades.
            // Por ahora, la clase Carrito parece agregar instancias individuales de Producto.
            // Vamos a mantener esa lógica por simplicidad con la estructura dada.

            for (int i = 0; i < cantidad; i++) {
                // Si solo quieres una instancia por producto con una cantidad, la lógica debe cambiar
                // Aquí se añade 'cantidad' veces la referencia al mismo objeto 'producto'
                // lo cual puede no ser lo deseado si luego se quiere modificar la cantidad de ESE producto en el carrito.
                // Una mejor aproximación es tener una clase ItemCarrito(producto, cantidadEnCarrito)
                // Pero siguiendo la estructura simple de agregar Producto directamente:
                productos.add(producto); // Agrega la referencia al producto 'cantidad' veces
                                        // Esto significa que removerProducto quitará una sola instancia.
                producto.setStock(producto.getStock() - 1); // Disminuye el stock real del producto
            }
            cantidadProductos.put(producto.getId(), cantidadProductos.getOrDefault(producto.getId(), 0) + cantidad);
            calcularTotal(); // Recalcula el total
        }


        public void removerProducto(Producto producto, int cantidadARemover) {
            if (producto == null) {
                throw new IllegalArgumentException("El producto no puede ser nulo");
            }
            if (cantidadARemover <= 0) {
                throw new IllegalArgumentException("La cantidad a remover debe ser positiva");
            }

            int removidosExitosamente = 0;
            // Iterar y remover 'cantidadARemover' instancias del producto
            for (int i = 0; i < cantidadARemover; i++) {
                if (productos.remove(producto)) { // remove() quita la primera ocurrencia
                    producto.setStock(producto.getStock() + 1); // Devuelve el stock
                    removidosExitosamente++;
                } else {
                    // Si se intentan remover más de los que hay (basado en instancias)
                    break; // Salir si no hay más instancias de este producto para remover
                }
            }

            if (removidosExitosamente == 0) {
                 throw new IllegalArgumentException("El producto '" + producto.getNombre() + "' no está en el carrito o no en la cantidad solicitada para remover.");
            }

            int cantidadActual = cantidadProductos.getOrDefault(producto.getId(), 0);
            if (cantidadActual - removidosExitosamente <= 0) {
                cantidadProductos.remove(producto.getId());
            } else {
                cantidadProductos.put(producto.getId(), cantidadActual - removidosExitosamente);
            }
            calcularTotal(); // Recalcula el total
        }

        // Método sobrecargado para remover todas las instancias de un producto
        public void removerProducto(Producto producto) {
            if (producto == null) {
                throw new IllegalArgumentException("El producto no puede ser nulo");
            }
            int cantidadEnCarrito = 0;
            for (Producto p : new ArrayList<>(productos)) { // Iterar sobre una copia para evitar ConcurrentModificationException
                if (p.getId().equals(producto.getId())) {
                    cantidadEnCarrito++;
                }
            }
            if (cantidadEnCarrito > 0) {
                removerProducto(producto, cantidadEnCarrito);
            } else {
                 throw new IllegalArgumentException("El producto '" + producto.getNombre() + "' no está en el carrito.");
            }
        }


        public double calcularTotal() {
            total = 0.0;
            // La forma en que 'productos' almacena múltiples referencias al mismo objeto si se agregan
            // varias unidades del mismo producto significa que este bucle es correcto.
            for (Producto p : productos) {
                total += p.getPrecio();
            }
            return total;
        }

        public void vaciarCarrito() {
            for (Producto productoEnCarrito : new ArrayList<>(productos)) { // Iterar sobre una copia
                // Determinar cuántas unidades de este producto hay realmente en el carrito
                int cantidadOriginalEnCarrito = 0;
                for(Producto p : productos) {
                    if(p.getId().equals(productoEnCarrito.getId())) {
                        cantidadOriginalEnCarrito++;
                    }
                }

                // Devolver el stock correspondiente a todas las unidades de este producto
                productoEnCarrito.setStock(productoEnCarrito.getStock() + cantidadOriginalEnCarrito);

                // Remover todas las instancias de este producto del carrito
                // Esto es ineficiente, mejor sería usar removeAll con una colección de los productos a eliminar
                // o simplemente limpiar 'productos' y ajustar stock fuera, pero para seguir la lógica de 'removerProducto':
                while(productos.remove(productoEnCarrito)); // Quita todas las ocurrencias
            }
            productos.clear(); // Asegura que la lista esté vacía
            cantidadProductos.clear();
            total = 0.0;
            System.out.println("El carrito ha sido vaciado.");
        }
    }


    // ----- MAIN DE EJEMPLO PARA PROBAR LAS CLASES -----
    public static void main(String[] args) {
        System.out.println("--- Probando Productos ---");
        ProductoFisico libro = new ProductoFisico("PF001", "El Señor de los Anillos", "Trilogía completa", 25.99, 50, 1.2, "15x22x7 cm");
        ProductoDigital ebook = new ProductoDigital("PD001", "El Señor de los Anillos (eBook)", "Versión digital PDF", 9.99, 1000, "PDF", 5.5);
        ProductoDigital software = new ProductoDigital("PD002", "Antivirus Pro", "Licencia anual", 49.99, 500, "EXE", 250.0);

        libro.mostrarInformacion();
        System.out.println();
        ebook.mostrarInformacion();
        System.out.println();
        software.mostrarInformacion();

        System.out.println("\n--- Probando Usuarios ---");
        Cliente cliente1 = new Cliente("C001", "Ana Pérez", "ana.perez@email.com", "cliente123");
        Administrador admin1 = new Administrador("A001", "Carlos López", "carlos.lopez@admin.com", "adminSecure");

        cliente1.login();
        admin1.login();
        System.out.println();

        // Permisos para el admin
        admin1.agregarPermiso("gestionar_inventario");
        admin1.agregarPermiso("establecer_promociones");
        admin1.agregarPermiso("gestionar_productos");

        // Admin gestiona inventario
        System.out.println("Stock inicial del libro: " + libro.getStock());
        admin1.gestionarInventario(libro, 45);
        System.out.println("Stock del libro después de gestión: " + libro.getStock());
        System.out.println();

        // Admin establece promoción
        System.out.println("Precio inicial del eBook: $" + ebook.getPrecio());
        admin1.establecerPromocion(ebook, 7.99, "Oferta de Verano");
        ebook.mostrarInformacion();
        System.out.println();

        // Cliente interactúa con su carrito
        cliente1.agregarProductoAlCarrito(libro, 2);
        cliente1.agregarProductoAlCarrito(ebook, 1);
        try {
            System.out.println("Intentando agregar más libros de los que hay en stock (después de la compra de 2):");
            cliente1.agregarProductoAlCarrito(libro, 50); // Intentar agregar más del stock restante (45 - 2 = 43)
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
        cliente1.verCarrito();
        System.out.println("Stock del libro después de añadir al carrito: " + libro.getStock()); // Debería ser 43
        System.out.println("Stock del eBook después de añadir al carrito: " + ebook.getStock()); // Debería ser 999
        System.out.println();

        // Cliente remueve un producto del carrito
        System.out.println("Removiendo 1 unidad de 'El Señor de los Anillos' del carrito...");
        cliente1.getCarrito().removerProducto(libro, 1);
        cliente1.verCarrito();
        System.out.println("Stock del libro después de remover del carrito: " + libro.getStock()); // Debería ser 44
        System.out.println();

        // Cliente y sus preferencias e historial
        cliente1.setPreferencia("categoriaFavorita", "Fantasía");
        cliente1.agregarCompraAlHistorial("PEDIDO#12345");
        cliente1.verHistorialCompras();
        System.out.println("Preferencia de categoría: " + cliente1.getPreferencia("categoriaFavorita"));
        System.out.println();

        // Admin gestiona catálogo
        List<Producto> catalogo = new ArrayList<>();
        catalogo.add(libro);
        catalogo.add(ebook);
        admin1.agregarProducto(catalogo, software);
        System.out.println("Catálogo actual:");
        for (Producto p : catalogo) {
            System.out.println(" - " + p.getNombre());
        }
        System.out.println();
        admin1.eliminarProducto(catalogo, "PD001"); // Eliminar eBook
        System.out.println("Catálogo después de eliminar producto:");
         for (Producto p : catalogo) {
            System.out.println(" - " + p.getNombre());
        }
        System.out.println();

        // Logout
        cliente1.logout();
        admin1.logout();

        // Prueba de vaciar carrito
        System.out.println("\n--- Probando vaciar carrito ---");
        Cliente cliente2 = new Cliente("C002", "Juan Rodríguez", "juan.r@email.com", "juanito");
        ProductoFisico camiseta = new ProductoFisico("PF002", "Camiseta Logo", "Camiseta de algodón", 19.99, 100, 0.2, "M");
        ProductoDigital musica = new ProductoDigital("PD003", "Album Hits", "Album en MP3", 9.99, 500, "MP3", 80.0);

        System.out.println("Stock inicial Camiseta: " + camiseta.getStock());
        System.out.println("Stock inicial Música: " + musica.getStock());

        cliente2.agregarProductoAlCarrito(camiseta, 3);
        cliente2.agregarProductoAlCarrito(musica, 1);
        cliente2.verCarrito();
        System.out.println("Stock Camiseta después de agregar: " + camiseta.getStock()); // 97
        System.out.println("Stock Música después de agregar: " + musica.getStock());   // 499

        cliente2.getCarrito().vaciarCarrito();
        cliente2.verCarrito();
        System.out.println("Stock Camiseta después de vaciar: " + camiseta.getStock()); // Debería volver a 100
        System.out.println("Stock Música después de vaciar: " + musica.getStock());   // Debería volver a 500

    }
}