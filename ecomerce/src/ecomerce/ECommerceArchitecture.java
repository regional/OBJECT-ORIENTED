import java.util.*;

// Clase abstracta para productos
abstract class Product {
    private String id;
    private String name;
    private double price;
    private String description;
    private String category;

    public Product(String id, String name, double price, String description, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }

    public abstract double calculateShippingCost();
}

// Subclase para productos físicos
class PhysicalProduct extends Product {
    private double weight;

    public PhysicalProduct(String id, String name, double price, String description, String category, double weight) {
        super(id, name, price, description, category);
        this.weight = weight;
    }

    @Override
    public double calculateShippingCost() {
        return weight * 5.0; // Ejemplo: $5 por kg
    }
}

// Subclase para productos digitales
class DigitalProduct extends Product {
    public DigitalProduct(String id, String name, double price, String description, String category) {
        super(id, name, price, description, category);
    }

    @Override
    public double calculateShippingCost() {
        return 0.0; // Sin costo de envío
    }
}

// Clase para gestionar el inventario (Singleton)
class Inventory {
    private static Inventory instance;
    private Map<String, Product> products;

    private Inventory() {
        products = new HashMap<>();
    }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void addProduct(Product product) throws InventoryException {
        if (products.containsKey(product.getId())) {
            throw new InventoryException("El producto con ID " + product.getId() + " ya existe.");
        }
        products.put(product.getId(), product);
    }

    public void removeProduct(String id) throws InventoryException {
        if (!products.containsKey(id)) {
            throw new InventoryException("El producto con ID " + id + " no existe.");
        }
        products.remove(id);
    }

    public Product getProduct(String id) throws InventoryException {
        Product product = products.get(id);
        if (product == null) {
            throw new InventoryException("El producto con ID " + id + " no existe.");
        }
        return product;
    }
}

// Excepción personalizada para inventario
class InventoryException extends Exception {
    public InventoryException(String message) {
        super(message);
    }
}

// Interfaz para estrategia de pago
interface PaymentStrategy {
    boolean processPayment(double amount);
}

// Implementación de estrategia de pago con tarjeta
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        // Simulación de procesamiento de pago
        System.out.println("Procesando pago de $" + amount + " con tarjeta " + cardNumber);
        return true;
    }
}

// Implementación de estrategia de pago con PayPal
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public boolean processPayment(double amount) {
        // Simulación de procesamiento de pago
        System.out.println("Procesando pago de $" + amount + " con PayPal " + email);
        return true;
    }
}

// Clase para el carrito de compras
class ShoppingCart {
    private Map<Product, Integer> items;
    private PaymentStrategy paymentStrategy;

    public ShoppingCart() {
        items = new HashMap<>();
    }

    public void addItem(Product product, int quantity) throws InventoryException {
        if (quantity <= 0) {
            throw new InventoryException("La cantidad debe ser mayor a 0.");
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void removeItem(Product product) throws InventoryException {
        if (!items.containsKey(product)) {
            throw new InventoryException("El producto no está en el carrito.");
        }
        items.remove(product);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue() + entry.getKey().calculateShippingCost();
        }
        return total;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean checkout() {
        if (items.isEmpty()) {
            System.out.println("El carrito está vacío.");
            return false;
        }
        double total = calculateTotal();
        return paymentStrategy != null && paymentStrategy.processPayment(total);
    }
}

// Clase abstracta para usuarios
abstract class User {
    private String id;
    private String name;
    private String email;
    private List<Order> orderHistory;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.orderHistory = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Order> getOrderHistory() { return orderHistory; }

    public abstract double getDiscount();
}

// Subclase para usuarios regulares
class RegularUser extends User {
    public RegularUser(String id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public double getDiscount() {
        return 0.0; // Sin descuento
    }
}

// Subclase para usuarios premium
class PremiumUser extends User {
    public PremiumUser(String id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public double getDiscount() {
        return 0.1; // 10% de descuento
    }
}

// Clase para pedidos
class Order {
    private String id;
    private User user;
    private Map<Product, Integer> items;
    private String status;
    private String shippingAddress;

    public Order(String id, User user, Map<Product, Integer> items, String shippingAddress) {
        this.id = id;
        this.user = user;
        this.items = new HashMap<>(items);
        this.status = "Pendiente";
        this.shippingAddress = shippingAddress;
    }

    public void updateStatus(String status) {
        this.status = status;
        // Notificar a los observadores (patrón Observer)
        notifyObservers();
    }

    private void notifyObservers() {
        System.out.println("Notificando al usuario " + user.getName() + ": El estado del pedido " + id + " cambió a " + status);
    }
}

// Clase principal para probar la plataforma
public class ECommercePlatform {
    public static void main(String[] args) {
        try {
            // Crear inventario
            Inventory inventory = Inventory.getInstance();

            // Crear productos
            Product laptop = new PhysicalProduct("1", "Laptop", 1000.0, "Laptop de alta gama", "Electrónica", 2.0);
            Product ebook = new DigitalProduct("2", "eBook", 20.0, "Libro digital", "Libros", 0.0);
            inventory.addProduct(laptop);
            inventory.addProduct(ebook);

            // Crear usuario
            User user = new PremiumUser("U1", "Juan Pérez", "juan@example.com");

            // Crear carrito y agregar productos
            ShoppingCart cart = new ShoppingCart();
            cart.addItem(laptop, 1);
            cart.addItem(ebook, 2);

            // Establecer estrategia de pago
            cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));

            // Realizar checkout
            boolean success = cart.checkout();
            System.out.println("Checkout exitoso: " + success);

            // Crear pedido
            Order order = new Order("O1", user, cart.items, "Calle Falsa 123");
            order.updateStatus("Enviado");

        } catch (InventoryException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}