# API CRUD de Vehículos

API REST para gestión de vehículos usando Spring Boot, MongoDB Atlas y Swagger/OpenAPI.

## Requisitos previos

- Java 17
- Maven 3.9+
- Cuenta en [MongoDB Atlas](https://www.mongodb.com/atlas)

## Configuración de MongoDB Atlas

La aplicación está configurada para conectarse a MongoDB Atlas. La configuración se encuentra en `src/main/resources/application.yml`:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb+srv://vehiculos_user:Vehiculos123@inventario.e3nkimy.mongodb.net/vehiculos_db?retryWrites=true&w=majority&appName=inventario
      database: vehiculos_db
```

**Nota importante:** Asegúrate de que tu IP esté autorizada en la sección *Network Access* de MongoDB Atlas para poder conectarte al cluster.

## Ejecución del proyecto

```bash
mvn clean install
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080/api/vehicles`.

## Endpoints principales

- `GET /api/vehicles`
- `GET /api/vehicles/{id}`
- `GET /api/vehicles/brand/{brand}`
- `POST /api/vehicles`
- `PUT /api/vehicles/{id}`
- `DELETE /api/vehicles/{id}`

## Ejemplo de creación de vehículo

```json
{
  "brand": "Toyota",
  "model": "RAV4",
  "year": 2024,
  "price": 156000000,
  "color": "Rojo",
  "kilometers": 0,
  "type": "SUV"
}
```

Puedes probar los endpoints con herramientas como Postman o curl.

## Swagger / OpenAPI

Una vez que la aplicación esté levantada, accede a la documentación interactiva en:

```
http://localhost:8080/swagger-ui/index.html
```

## Estructura del proyecto

- `controller`: Controladores REST.
- `service`: Interfaces de negocio.
- `service.impl`: Implementaciones de servicios.
- `repository`: Repositorios Spring Data MongoDB.
- `model`: Entidades (colecciones).
- `dto`: Objetos de transferencia y validaciones.
- `config`: Configuraciones (OpenAPI).
- `exception`: Excepciones y manejadores globales.
- `validation`: Validaciones personalizadas.

## Pruebas

Se incluyen pruebas unitarias (Mockito) y de integración (MockMvc). Ejecuta `mvn test` para correrlas.

## Actualizar a Java 21 (instrucciones)

Este proyecto ya está preparado para compilarse con Java 21 (propiedad `java.version` y `maven-compiler-plugin` configuradas). Para completar la migración en tu entorno local sigue estos pasos:

1. Instala JDK 21 en Windows. Puedes descargarlo desde Eclipse Adoptium (Temurin) o usar el gestor de paquetes que prefieras.

2. En PowerShell configura temporalmente la variable de entorno `JAVA_HOME` (ajusta la ruta según tu instalación) y verifica la versión:

```powershell
# Ejemplo: ajustar la ruta según tu instalación
$env:JAVA_HOME = 'C:\\Program Files\\Eclipse Adoptium\\jdk-21.0.0'
$env:PATH = "$env:JAVA_HOME\\bin;$env:PATH"

# Verificar Maven y Java
mvn -v
```

3. Compila y prueba el proyecto (desde la raíz del proyecto):

```powershell
mvn clean package
mvn test
```

Si tu sistema no tiene JDK 21 instalado, la compilación fallará y el `maven-enforcer-plugin` añadirá un mensaje claro sobre la versión requerida.

Si quieres que intente instalar JDK 21 automáticamente, indícalo; en este entorno la instalación automática requiere un plan Pro/Enterprise y por eso no se pudo ejecutar aquí.

