# Invex Employee Service

Microservicio backend desarrollado como parte de la evaluación técnica para la posición Desarrollador Java Senior.

El servicio gestiona operaciones CRUD sobre la entidad Empleado, aplicando buenas prácticas de arquitectura de microservicios, principios SOLID y fundamentos DevSecOps.

---

## 🛠️ Tecnologías Utilizadas

*   ☕ **Java 17**
*   🍃 **Spring Boot 3.x**
*   💾 **Spring Data JPA / Hibernate**
*   🗄️ **Base de datos H2** (en memoria)
*   📄 **OpenAPI / Swagger**
*   🧪 **JUnit 5 + Mockito**
*   📊 **JaCoCo** (Cobertura mínima configurada 80 %)
*   🐳 **Docker**
*   ⚙️ **Spring Boot Actuator**
*   📦 **Maven Wrapper**

---

## 📡 Endpoints Implementados

**Base URL:** `http://localhost:8080/api/v1/employees`

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/` | Obtener todos los empleados |
| `GET` | `/{id}` | Obtener empleado por ID |
| `POST` | `/` | Crear uno o varios empleados |
| `PUT` | `/{id}` | Actualizar empleado |
| `DELETE` | `/{id}` | Eliminar empleado |
| `GET` | `/search?name=` | Buscar empleados por nombre (búsqueda parcial) |

---

## 🏗️ Arquitectura del Proyecto

El proyecto sigue una estructura por capas para asegurar la separación de responsabilidades:

*   `controller`
*   `service-impl`
*   `repository`
*   `entity`
*   `dto`
*   `mapper`
*   `config`
*   `exception`
*   `util`

---

## 🧪 Pruebas y Cobertura

Para ejecutar las pruebas y verificar la cobertura:
```bash
mvnw clean verify
```

**Configuración de JaCoCo:**
*   📄 Genera reporte en: `target/site/jacoco/index.html`
*   🚫 **Falla el build** si la cobertura es menor al **80%**.
*   ❌ Excluye paquetes técnicos (`config`, `util`, `exception`, `mapper`, `dto`, `entity`, `repository`).

---

## 🐳 EJECUCIÓN CON DOCKER (PASO A PASO)

### 🔹 Requisito previo

Tener instalado **Docker Desktop** y **Docker** ejecutándose.

Verificar con:
```bash
docker --version
```

### 🔹 Paso 1 – Construir el JAR

Desde la raíz del proyecto:
```bash
mvnw clean package
```
Esto generará: `target/invex-employee-service-0.0.1.jar`

### 🔹 Paso 2 – Construir la imagen Docker

Ubicado en la raíz del proyecto (donde está el `Dockerfile`), ejecutar:
```bash
docker build -t invex-employee-service .
```
Si todo es correcto verás: `Successfully tagged invex-employee-service:latest`

### 🔹 Paso 3 – Ejecutar el contenedor

```bash
docker run -p 8080:8080 invex-employee-service
```

Esto hace:
1.  Levanta el contenedor.
2.  Expone el puerto `8080`.
3.  Inicia el microservicio dentro del contenedor.

### 🔹 Paso 4 – Verificar que está funcionando

Abrir en navegador:

*   **Swagger UI:** http://localhost:8080/swagger-ui.html
*   **Health Check:** http://localhost:8080/actuator/health

Si responde:
```json
{"status":"UP"}
```
El servicio está funcionando correctamente.