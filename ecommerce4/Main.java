package ecommerce4;

public class Main {
    public static void main(String[] args) {
        // Create a cart
        Carrito carrito = new Carrito();

        // Demonstrate method overloading
        System.out.println(carrito.agregarProducto("P001")); // By ID
        System.out.println(carrito.agregarProducto(new ProductoDigital("D001", "PDF Book", 15.99, 100.0))); // By Producto object
        System.out.println(carrito.agregarProducto("F001", "Physical Book", 25.99, "fisico", 1.5)); // By details

        // Show cart contents
        System.out.println(carrito.mostrarContenido());
        System.out.printf("Total del carrito: $%.2f\n", carrito.calcularTotal());

        // Create an order
        Pedido pedido = new Pedido("ORD001");
        pedido.agregarProducto(new ProductoDigital("D002", "Software License", 49.99, 200.0));
        pedido.agregarProducto(new ProductoFisico("F002", "Headphones", 89.99, 0.5));

        // Demonstrate polymorphism
        System.out.println("\nDetalles del pedido:");
        System.out.println(pedido.mostrarDetallePedido());
    }
}