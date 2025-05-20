package ecommerce4;

public abstract class Producto {
    private String idProducto;
    private String nombre;
    private double precio;

    public Producto(String idProducto, String nombre, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    // Abstract method to be overridden by subclasses
    public abstract String mostrarDetalle();

    @Override
    public String toString() {
        return mostrarDetalle();
    }
}