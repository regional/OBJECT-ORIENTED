package ecommerce4;

public class ProductoDigital extends Producto {
    private double tamanoArchivoMB;

    public ProductoDigital(String idProducto, String nombre, double precio, double tamanoArchivoMB) {
        super(idProducto, nombre, precio);
        this.tamanoArchivoMB = tamanoArchivoMB;
    }

    @Override
    public String mostrarDetalle() {
        return String.format("ID: %s, Nombre: %s, Precio: $%.2f, Tipo: Digital, Tamaño: %.2f MB",
                getIdProducto(), getNombre(), getPrecio(), tamanoArchivoMB);
    }
}
