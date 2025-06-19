-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 19-06-2025 a las 02:40:47
-- Versión del servidor: 8.0.17
-- Versión de PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistema_facturas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `api_keys`
--

CREATE TABLE `api_keys` (
  `id` int(11) NOT NULL,
  `api_key` varchar(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `api_keys`
--

INSERT INTO `api_keys` (`id`, `api_key`, `user_id`, `created_at`) VALUES
(1, 'your-secret-api-key-1234567890', 1, '2025-06-07 23:19:28');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `id` int(11) NOT NULL,
  `numero_factura` varchar(50) NOT NULL,
  `fecha_emision` date NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `impuesto` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `cliente_ruc` varchar(50) NOT NULL,
  `cliente_codigo` varchar(50) DEFAULT NULL,
  `cliente_nombre` varchar(255) NOT NULL,
  `cliente_direccion` varchar(255) DEFAULT NULL,
  `cliente_telefono` varchar(20) DEFAULT NULL,
  `cliente_correo` varchar(100) DEFAULT NULL,
  `item_codigo` varchar(50) DEFAULT NULL,
  `item_descripcion` text,
  `item_cantidad` int(11) DEFAULT NULL,
  `item_precio` decimal(10,2) DEFAULT NULL,
  `item_precio_total` decimal(10,2) DEFAULT NULL,
  `creado_en` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `facturas`
--

INSERT INTO `facturas` (`id`, `numero_factura`, `fecha_emision`, `subtotal`, `impuesto`, `total`, `cliente_ruc`, `cliente_codigo`, `cliente_nombre`, `cliente_direccion`, `cliente_telefono`, `cliente_correo`, `item_codigo`, `item_descripcion`, `item_cantidad`, `item_precio`, `item_precio_total`, `creado_en`) VALUES
(6, 'tfrtfr', '2025-06-07', '3334.00', '400.08', '3734.08', '0', 'gffg', 'ggfgfgf', 'hgfgfggf', 'hggg', 'gygfgfgf@live.com', 'gg', 'ggff', 1, '3334.00', '3334.00', '2025-06-07 22:50:54'),
(7, '23', '2025-06-07', '3299.00', '395.88', '3694.88', '0', 'hgghgh', 'hgghhghg', 'jhhgg', 'hghghg', 'junior@live.com', 'klk', 'vbsgh', 1, '3299.00', '3299.00', '2025-06-07 22:52:29'),
(8, 'dsds', '2025-06-07', '300.00', '36.00', '336.00', '0', 'sffs', 'sdssdddsds', 'sddssddsds', 'dffdfdfd', 'fffpreciord.com@gmail.com', 'dfdffdfdfd', 'fdfdfd', 1, '300.00', '300.00', '2025-06-07 23:25:56'),
(9, 'dsds', '2025-06-07', '300.00', '36.00', '336.00', '0', 'sffs', 'sdssdddsds', 'sddssddsds', 'dffdfdfd', 'fffpreciord.com@gmail.com', 'dfdffdfdfd', 'fdfdfd', 1, '300.00', '300.00', '2025-06-07 23:26:24'),
(10, '3333', '2025-06-19', '500.00', '60.00', '560.00', '1234', '1235', 'klk', 'joe', '8888', 'preciord.com@gmail.com', '333', 'toto', 1, '500.00', '500.00', '2025-06-19 00:30:17'),
(11, '4433434', '2025-06-19', '600.00', '72.00', '672.00', '0', 'hghghg', 'uuu', 'jhjhhj', 'iuiuu', 'preciord.com@gmail.com', '344343', 'hjhjh', 1, '300.00', '300.00', '2025-06-19 01:42:43');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `api_keys`
--
ALTER TABLE `api_keys`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `api_key` (`api_key`);

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `api_keys`
--
ALTER TABLE `api_keys`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `facturas`
--
ALTER TABLE `facturas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
