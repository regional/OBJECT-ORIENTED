<?php
     header('Content-Type: application/json; charset=utf-8');
     header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE');
     header('Access-Control-Allow-Headers: Content-Type, X-API-Key');

     $conn = new mysqli('localhost', 'root', 'Manuel15', 'sistema_facturas');

     if ($conn->connect_error) {
         http_response_code(500);
         echo json_encode(['error' => 'Conexión fallida: ' . $conn->connect_error]);
         exit;
     }

     $conn->set_charset('utf8mb4');

     // Validate API Key
     $api_key = $_SERVER['HTTP_X_API_KEY'] ?? '';
     $stmt = $conn->prepare("SELECT id FROM api_keys WHERE api_key = ?");
     $stmt->bind_param("s", $api_key);
     $stmt->execute();
     $result = $stmt->get_result();
     if (!$result->fetch_assoc()) {
         http_response_code(401);
         echo json_encode(['error' => 'Clave API inválida']);
         exit;
     }
     $stmt->close();

     // Crear factura
     if ($_SERVER['REQUEST_METHOD'] === 'POST') {
         $data = json_decode(file_get_contents('php://input'), true);
         
         if (!$data || !isset($data['numero_factura']) || !isset($data['fecha_emision'])) {
             http_response_code(400);
             echo json_encode(['error' => 'Datos incompletos']);
             exit;
         }

         $stmt = $conn->prepare("INSERT INTO facturas (
             numero_factura, fecha_emision, subtotal, impuesto, total,
             cliente_ruc, cliente_codigo, cliente_nombre, cliente_direccion, cliente_telefono, cliente_correo,
             item_codigo, item_descripcion, item_cantidad, item_precio, item_precio_total
         ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
         
         $stmt->bind_param(
             "ssddddsssssssidd",
             $data['numero_factura'],
             $data['fecha_emision'],
             $data['subtotal'],
             $data['impuesto'],
             $data['total'],
             $data['cliente_ruc'],
             $data['cliente_codigo'],
             $data['cliente_nombre'],
             $data['cliente_direccion'],
             $data['cliente_telefono'],
             $data['cliente_correo'],
             $data['item_codigo'],
             $data['item_descripcion'],
             $data['item_cantidad'],
             $data['item_precio'],
             $data['item_precio_total']
         );
         
         if ($stmt->execute()) {
             http_response_code(201);
             echo json_encode(['mensaje' => 'Factura creada exitosamente', 'id' => $conn->insert_id]);
         } else {
             http_response_code(500);
             echo json_encode(['error' => 'Error al crear factura: ' . $stmt->error]);
         }
         $stmt->close();
     }

     // Obtener todas las facturas
     if ($_SERVER['REQUEST_METHOD'] === 'GET' && !isset($_GET['id'])) {
         $result = $conn->query("SELECT * FROM facturas");
         $facturas = [];
         while ($row = $result->fetch_assoc()) {
             $facturas[] = $row;
         }
         echo json_encode($facturas);
     }

     // Obtener una factura
     if ($_SERVER['REQUEST_METHOD'] === 'GET' && isset($_GET['id'])) {
         $id = filter_var($_GET['id'], FILTER_VALIDATE_INT);
         if ($id === false) {
             http_response_code(400);
             echo json_encode(['error' => 'ID inválido']);
             exit;
         }
         
         $stmt = $conn->prepare("SELECT * FROM facturas WHERE id = ?");
         $stmt->bind_param("i", $id);
         $stmt->execute();
         $result = $stmt->get_result();
         $factura = $result->fetch_assoc();
         
         if ($factura) {
             echo json_encode($factura);
         } else {
             http_response_code(404);
             echo json_encode(['error' => 'Factura no encontrada']);
         }
         $stmt->close();
     }

     // Actualizar factura
     if ($_SERVER['REQUEST_METHOD'] === 'PUT') {
         $data = json_decode(file_get_contents('php://input'), true);
         
         if (!$data || !isset($data['id']) || !isset($data['numero_factura']) || !isset($data['fecha_emision'])) {
             http_response_code(400);
             echo json_encode(['error' => 'Datos incompletos']);
             exit;
         }

         $stmt = $conn->prepare("UPDATE facturas SET 
             numero_factura = ?, fecha_emision = ?, subtotal = ?, impuesto = ?, total = ?,
             cliente_ruc = ?, cliente_codigo = ?, cliente_nombre = ?, cliente_direccion = ?, 
             cliente_telefono = ?, cliente_correo = ?, item_codigo = ?, item_descripcion = ?,
             item_cantidad = ?, item_precio = ?, item_precio_total = ?
             WHERE id = ?");
         
         $stmt->bind_param(
             "ssddddsssssssiddi",
             $data['numero_factura'],
             $data['fecha_emision'],
             $data['subtotal'],
             $data['impuesto'],
             $data['total'],
             $data['cliente_ruc'],
             $data['cliente_codigo'],
             $data['cliente_nombre'],
             $data['cliente_direccion'],
             $data['cliente_telefono'],
             $data['cliente_correo'],
             $data['item_codigo'],
             $data['item_descripcion'],
             $data['item_cantidad'],
             $data['item_precio'],
             $data['item_precio_total'],
             $data['id']
         );
         
         if ($stmt->execute()) {
             echo json_encode(['mensaje' => 'Factura actualizada exitosamente']);
         } else {
             http_response_code(500);
             echo json_encode(['error' => 'Error al actualizar factura: ' . $stmt->error]);
         }
         $stmt->close();
     }

     // Eliminar factura
     if ($_SERVER['REQUEST_METHOD'] === 'DELETE') {
         $data = json_decode(file_get_contents('php://input'), true);
         
         if (!isset($data['id']) || !filter_var($data['id'], FILTER_VALIDATE_INT)) {
             http_response_code(400);
             echo json_encode(['error' => 'ID inválido']);
             exit;
         }
         
         $id = $data['id'];
         $stmt = $conn->prepare("DELETE FROM facturas WHERE id = ?");
         $stmt->bind_param("i", $id);
         
         if ($stmt->execute()) {
             echo json_encode(['mensaje' => 'Factura eliminada exitosamente']);
         } else {
             http_response_code(500);
             echo json_encode(['error' => 'Error al eliminar factura: ' . $stmt->error]);
         }
         $stmt->close();
     }

     $conn->close();
     ?>