package ecommerce4;

public class ProductoFisico extends Producto {
    private double pesoKg;

    public ProductoFisico(String idProducto, String nombre, double precio, double pesoKg) {
        super(idProducto, nombre, precio);
        this.pesoKg = pesoKg;
    }

    @Override
    public String mostrarDetalle() {
        return String.format("ID: %s, Nombre: %s, Precio: $%.2f, Tipo: Físico, Peso: %.2f kg",
                getIdProducto(), getNombre(), getPrecio(), pesoKg);
    }
}