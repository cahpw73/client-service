# Client Management Service 🚀

Microservicio desarrollado en Java 21 + Spring Boot 3.3.10 para la gestión de clientes, cumpliendo buenas prácticas de arquitectura, seguridad, pruebas y despliegue en la nube.

---

## 📦 Tech Stack

- Java 21
- Spring Boot 3.3.10
- Spring Web, Data JPA, Validation, Security
- MySQL 8
- Flyway (migración de base de datos)
- Swagger (documentación)
- Spring Boot Actuator (monitoreo)
- Docker / Docker Compose
- JUnit 5 + Mockito + AssertJ

---

## ⚙️ Funcionalidades principales

- Registro de clientes con validaciones cruzadas
- Estadísticas de edad (promedio y desviación estándar)
- Seguridad con autenticación básica
- Manejo global de excepciones con estructura JSON
- Endpoints REST versionados (`/v1/api/clients`)
- Soporte para múltiples perfiles: `dev`, `test`, `prod`

---

## 🛠️ Cómo ejecutar con Docker Compose

### 1. Clonar el proyecto
```bash
git clone https://github.com/cahpw73/client-service.git
cd client-service
```

### 2. Crear archivo `.env`
```env
SPRING_PROFILES_ACTIVE=dev
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/clientdb
SPRING_DATASOURCE_DATABASE_NAME=clientdb
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=superclave
```

### 3. Ejecutar
```bash
docker-compose up --build
```

### 4. Detener
```bash
docker-compose down
```

La app estará disponible en: [http://localhost:8080](http://localhost:8080)

---

## 🔐 Seguridad

La aplicación está protegida con autenticación básica:

- Usuario: `apiuser`
- Contraseña: `apipass`

Swagger UI queda libre para facilitar documentación, pero todos los endpoints `/api/**` requieren autenticación.

---

## 📊 Swagger

Documentación disponible en:
```
http://localhost:8080/swagger-ui/index.html
```

---

## 📈 Spring Boot Actuator

La aplicación incluye **Spring Boot Actuator** para monitoreo y chequeos de salud.

**Endpoints habilitados:**
- `/actuator/health` – Estado de la aplicación
- `/actuator/info` – Información del proyecto
- `/actuator` – Índice con todos los endpoints disponibles

**Seguridad:**
Los endpoints están protegidos con autenticación básica. Se utilizan las mismas credenciales configuradas para acceder a la API:

- Usuario: `apiuser`
- Contraseña: `apipass`

Esto asegura que solo usuarios autorizados puedan consultar el estado del servicio.

**Preparado para integración futura con herramientas como:**
- Spring Boot Admin
- Prometheus + Grafana
- GCP Monitoring / AWS CloudWatch

---

## 🧪 Pruebas

Pruebas unitarias disponibles para:
- `ClientController`
- `ClientServiceImpl`
- `StatisticsUtil`
- `LifeExpectancyUtil`
- `DateUtil`

Ejecutar con:
```bash
./mvnw test
```

---

## ☁️ Preparado para despliegue en la nube

- Dockerizado completamente (`Dockerfile` multi-stage)
- Uso de `application-{profile}.properties`
- Variables de entorno para conexión a DB y perfiles
- Listo para GCP, AWS, Azure o cualquier plataforma compatible con Docker

---

## 📂 Estructura de proyecto

```
└── controller.v1           # API REST v1
└── dto                     # Request/Response DTOs
└── entity                  # Modelo de persistencia
└── repository              # Spring Data JPA
└── service                 # Lógica de negocio
└── util                    # Clases utilitarias
└── config                  # Seguridad, Swagger
└── exception               # Manejo de errores
```

---

## ✍️ Autor

> Desarrollado por Christian Rene Alba Herrera