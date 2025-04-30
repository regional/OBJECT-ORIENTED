public class EcommerceClasses {
    // Clase Producto
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
            this.precio = precio;
            this.stock = stock;
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
    }

    // Clase Usuario
    public static class Usuario {
        private String id;
        private String nombre;
        private String email;
        private String contrasena;

        // Constructor
        public Usuario(String id, String nombre, String email, String contrasena) {
            this.id = id;
            this.nombre = nombre;
            this.email = email;
            this.contrasena = contrasena;
        }

        // Finalizer (equivalente a destructor)
        @Override
        protected void finalize() throws Throwable {
            try {
                System.out.println("Usuario " + nombre + " ha sido eliminado");
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
            this.email = email;
        }

        public String getContrasena() {
            return contrasena;
        }

        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }
    }

    // Clase Carrito
    public static class Carrito {
        private java.util.ArrayList<Producto> productos;
        private double total;

        // Constructor
        public Carrito() {
            this.productos = new java.util.ArrayList<>();
            this.total = 0.0;
        }

        // Getters
        public java.util.ArrayList<Producto> getProductos() {
            return productos;
        }

        public double getTotal() {
            return total;
        }

        // Métodos
        public void agregarProducto(Producto producto, int cantidad) {
            if (producto == null) {
                throw new IllegalArgumentException("El producto no puede ser nulo");
            }
            if (cantidad > producto.getStock()) {
                throw new IllegalArgumentException("Cantidad solicitada excede el stock disponible");
            }

            for (int i = 0; i < cantidad; i++) {
                productos.add(producto);
                total += producto.getPrecio();
                producto.setStock(producto.getStock() - 1);
            }
        }

        public void removerProducto(Producto producto) {
            if (producto == null) {
                throw new IllegalArgumentException("El producto no puede ser nulo");
            }
            if (productos.remove(producto)) {
                total -= producto.getPrecio();
                producto.setStock(producto.getStock() + 1);
            } else {
                throw new IllegalArgumentException("El producto no está en el carrito");
            }
        }

        public double calcularTotal() {
            total = 0.0;
            for (Producto p : productos) {
                total += p.getPrecio();
            }
            return total;
        }
    }
}