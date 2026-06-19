# ShopAPI - API de Comercio Electrónico

Este es un proyecto de backend REST para comercio electrónico desarrollado con **Java 21** y **Spring Boot 3**. Implementa una arquitectura en capas limpia, seguridad mediante tokens JWT, manejo global de excepciones y soporte CORS listo para conectar con aplicaciones frontend como Angular.

---

## 🚀 Características Principales

* **CRUD Completo de Recursos**: Operaciones RESTful completas (incluyendo `PUT` de actualización y `DELETE` seguro) para `Producto` y `Categoria`.
* **Seguridad con Spring Security y JWT**:
  * Registro de usuarios (`/api/v1/auth/register`) con cifrado de contraseñas mediante `BCryptPasswordEncoder`.
  * Inicio de sesión (`/api/v1/auth/login`) para obtención de tokens.
  * Autenticación sin estado (Stateless) mediante tokens JWT firmados digitalmente (HS256) con la API moderna de **JJWT 0.12.6**.
  * Autorización a nivel de endpoints (Peticiones `GET` públicas para catálogos, peticiones de escritura `POST/PUT/DELETE` restringidas a usuarios autenticados).
* **Configuración CORS**: Integración para permitir que clientes en otros orígenes (por defecto configurado para Angular en `http://localhost:4200`) consuman los endpoints y manejen peticiones de pre-verificación (`OPTIONS`).
* **Manejo Global de Errores**: Intercepción centralizada de excepciones a través de `@ControllerAdvice`, retornando siempre un JSON estandarizado con el error y detalles descriptivos.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje**: Java 21
* **Framework**: Spring Boot 3.5.15 (Spring Web, Spring Data JPA, Spring Security, Validation)
* **Base de Datos**: PostgreSQL
* **Librería JWT**: io.jsonwebtoken (JJWT 0.12.6)
* **Utilidades**: Lombok

---

## 📋 Arquitectura de Capas

El proyecto está organizado en paquetes estructurados siguiendo buenas prácticas de diseño:
* `com.shop.api.config`: Configuraciones generales de la aplicación (CORS, etc.).
* `com.shop.api.controller`: Controladores REST exponiendo los endpoints.
* `com.shop.api.dto`: Objetos de Transferencia de Datos (DTO) para solicitudes y respuestas limpias.
* `com.shop.api.entity`: Modelos persistentes de JPA mapeados a la base de datos.
* `com.shop.api.exception`: Clases de excepciones personalizadas y el interceptor global de errores.
* `com.shop.api.mapper`: Mapeadores manuales de entidades a DTOs.
* `com.shop.api.repository`: Interfaces JPA para el acceso a datos.
* `com.shop.api.security`: Filtro de seguridad JWT, cargador de usuarios y ajustes de Spring Security.
* `com.shop.api.service`: Interfaces y clases de implementación para la lógica de negocio.

---

## ⚙️ Configuración y Ejecución

### Requisitos Previos
1. Tener instalado un JDK compatible con **Java 21**.
2. Tener una base de datos PostgreSQL activa.

### Configurar la Base de Datos
Edita el archivo `src/main/resources/application.yaml` para establecer tus credenciales de PostgreSQL:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/shop_api_db
    username: tu_usuario
    password: tu_contraseña
    driver-class-name: org.postgresql.Driver
```

> [!IMPORTANT]
> Asegúrate de crear primero la base de datos `shop_api_db` en tu motor PostgreSQL antes de arrancar el servidor.

### Iniciar la Aplicación
Ejecuta el proyecto utilizando el Maven Wrapper provisto en la raíz de la carpeta:

```bash
# En Windows (PowerShell o CMD)
.\mvnw.cmd spring-boot:run

# En Linux o macOS
./mvnw spring-boot:run
```

El servidor web Tomcat arrancará por defecto en el puerto `8080` (`http://localhost:8080`).

---

## 🧪 Guía de Pruebas Rápidas (Postman)

Puedes encontrar una guía de pruebas paso a paso y la justificación de la arquitectura de integración con Angular en el archivo generado localmente en tu proyecto:
📄 **[Reporte_ShopAPI.docx](Reporte_ShopAPI.docx)** (Formato Word).

### Endpoints Principales
* **Públicos**:
  * `POST /api/v1/auth/register` - Registrar un usuario.
  * `POST /api/v1/auth/login` - Iniciar sesión (retorna token).
  * `GET /api/v1/productos` - Listar productos.
  * `GET /api/v1/productos/{id}` - Buscar producto por ID.
  * `GET /api/v1/categorias` - Listar categorías.
* **Protegidos (Requieren Cabecera `Authorization: Bearer <token>`)**:
  * `POST /api/v1/productos` - Crear producto.
  * `PUT /api/v1/productos/{id}` - Actualizar producto.
  * `DELETE /api/v1/productos/{id}` - Eliminar producto.
  * `POST /api/v1/categorias` - Crear categoría.
  * `PUT /api/v1/categorias/{id}` - Actualizar categoría.
