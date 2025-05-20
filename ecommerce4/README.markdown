Plataforma de Comercio Electrónico: Polimorfismo y Sobrecarga
Descripción del Proyecto
Este proyecto implementa una plataforma de comercio electrónico en Java para demostrar conceptos de programación orientada a objetos, específicamente polimorfismo, sobrecarga de métodos y sobreescritura de métodos. El sistema gestiona productos (digitales y físicos), un carrito de compras y pedidos, mostrando cómo diferentes tipos de productos pueden ser manejados de manera uniforme a través de una clase base mientras se permite un comportamiento especializado en las clases derivadas.
Tecnologías Utilizadas

Java: Versión 17 (JDK 17) para la programación principal.
Maven: Para la gestión de proyectos y dependencias (opcional, no utilizado en esta implementación por simplicidad).
Git: Para el control de versiones y alojamiento en GitHub.

Detalles de la Implementación
Polimorfismo

La clase Producto es una clase base abstracta con clases derivadas ProductoDigital y ProductoFisico.
El polimorfismo se demuestra en la clase Pedido, donde el método agregarProducto acepta cualquier objeto Producto, independientemente de si es ProductoDigital o ProductoFisico. El método mostrarDetalle se invoca de manera polimórfica, mostrando detalles específicos según el tipo real del objeto.

Sobrecarga de Métodos

La clase Carrito implementa tres métodos sobrecargados agregarProducto:
Acepta un objeto Producto.
Acepta un ID, nombre, precio, tipo y un parámetro adicional (tamaño del archivo o peso).
Acepta solo un ID, simulando una búsqueda en una base de datos.


Esto permite agregar productos al carrito de manera flexible según los datos disponibles.

Sobreescritura de Métodos

El método mostrarDetalle en la clase Producto es sobreescrito en ProductoDigital y ProductoFisico para proporcionar detalles específicos (por ejemplo, tamaño del archivo para productos digitales, peso para productos físicos).

Configuración y Ejecución

Clona el repositorio desde GitHub.
Asegúrate de tener instalado JDK 17.
Navega al directorio src y compila los archivos Java:javac model/*.java service/*.java Main.java


Ejecuta el programa:java Main



Desafíos y Soluciones

Desafío: El tipado estático de Java requirió un diseño cuidadoso de los métodos sobrecargados agregarProducto para manejar diferentes combinaciones de entrada sin duplicar código.
Solución: Se utilizó una estructura clara de parámetros y verificación de tipos dentro de la clase Carrito para crear instancias de productos apropiadas según el parámetro tipo.


Desafío: Garantizar un comportamiento polimórfico en la clase Pedido sin necesidad de conversión de tipos (casting).
Solución: Se basó en la clase abstracta Producto y su método mostrarDetalle, permitiendo que la clase Pedido maneje cualquier tipo de producto de manera genérica.



Capturas de Pantalla
*(Nota: Las capturas de pantalla se incluirían aquí en una entrega real, mostrando la salida de la consola de la ejecución de la clase Main. Para esta respuesta, imagina salidas
