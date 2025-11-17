# Solución para ExceptionInInitializerError

## Problema
El error `ExceptionInInitializerError` ocurre cuando hay una excepción durante la inicialización estática de una clase.

## Soluciones más comunes:

### 1. Verificar que MongoDB esté corriendo ⚠️ (MÁS COMÚN)

El error más frecuente es que MongoDB no esté corriendo cuando la aplicación intenta conectarse.

**Solución:**
1. Verifica que MongoDB esté instalado y corriendo:
   ```bash
   # En Windows, verifica el servicio:
   Get-Service MongoDB
   
   # O intenta iniciar MongoDB manualmente:
   mongod
   ```

2. Si MongoDB no está instalado, instálalo desde: https://www.mongodb.com/try/download/community

3. Verifica la conexión:
   - La aplicación intenta conectarse a: MongoDB Atlas (mongodb+srv://)
   - Asegúrate de que tu IP esté en la whitelist de MongoDB Atlas
   - Verifica que las credenciales sean correctas

### 2. Verificar configuración de Lombok

Lombok necesita estar configurado correctamente en tu IDE.

**Solución:**
1. Si usas IntelliJ IDEA:
   - Instala el plugin de Lombok desde Settings → Plugins
   - Habilita "Annotation Processing" en Settings → Build, Execution, Deployment → Compiler → Annotation Processors

2. Si usas Eclipse:
   - Descarga lombok.jar y ejecútalo como aplicación Java
   - Reinicia Eclipse

3. Recompila el proyecto:
   ```bash
   mvn clean install
   ```

### 3. Limpiar y recompilar el proyecto

A veces los archivos compilados están corruptos.

**Solución:**
```bash
mvn clean
mvn compile
mvn package
```

### 4. Verificar dependencias de Maven

Asegúrate de que todas las dependencias estén descargadas correctamente.

**Solución:**
```bash
mvn dependency:resolve
mvn clean install
```

### 5. Ver el stack trace completo

Para identificar la causa exacta, necesitas ver el stack trace completo del error. El error que compartiste es solo la definición de la clase `ExceptionInInitializerError`, pero necesitamos ver:

- La línea que dice "Caused by: ..."
- El stack trace completo que muestra qué clase está causando el problema

**Para obtener el stack trace completo:**
- Ejecuta la aplicación y copia TODO el error desde el inicio hasta el final
- O revisa los logs en la consola donde ejecutaste la aplicación

## Pasos recomendados:

1. **Primero:** Verifica que MongoDB esté corriendo
2. **Segundo:** Limpia y recompila el proyecto
3. **Tercero:** Si el error persiste, comparte el stack trace completo

## Configuración actual:

- MongoDB URI: `mongodb+srv://inventario_user:Inventario123@inventario.e3nkimy.mongodb.net/inventario_db?retryWrites=true&w=majority&appName=inventario`
- Base de datos: `inventario_db`
- Puerto del servidor: `8080`
- Spring Boot: `3.2.0`
- Java: `17`

### Nota importante para MongoDB Atlas:
- Asegúrate de que tu IP esté en la whitelist de MongoDB Atlas (Network Access)
- Verifica que el usuario `inventario_user` tenga los permisos necesarios
- La conexión usa SSL/TLS automáticamente con mongodb+srv://

