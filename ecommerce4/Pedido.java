package ecommerce4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String idPedido;
    private List<Producto> productos;
    private LocalDateTime fecha;

    public Pedido(String idPedido) {
        this.idPedido = idPedido;
        this.productos = new ArrayList<>();
        this.fecha = LocalDateTime.now();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public String mostrarDetallePedido() {
        StringBuilder detalles = new StringBuilder();
        detalles.append(String.format("Pedido ID: %s\nFecha: %s\nProductos:\n",
                idPedido, fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        for (Producto producto : productos) {
            detalles.append(producto.mostrarDetalle()).append("\n");
        }
        double total = productos.stream().mapToDouble(Producto::getPrecio).sum();
        detalles.append(String.format("Total: $%.2f", total));
        return detalles.toString();
    }
}