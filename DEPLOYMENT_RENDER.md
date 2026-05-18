# Guía de Despliegue en Render (Spring Boot + PostgreSQL)

Este documento detalla el paso a paso necesario para llevar el proyecto Mahuahua desde un entorno local hacia la nube utilizando **Render**, una plataforma PaaS (Platform as a Service) muy amigable para aplicaciones Java.

---

## 🛠️ Paso 1: Modificaciones en el Código

Antes de subir el proyecto a la nube, es necesario preparar el código para que sea dinámico y no dependa de credenciales locales (localhost).

### 1.1 Variables de Entorno en `application.properties`
Abre el archivo `src/main/resources/application.properties` y reemplaza las credenciales locales por variables de entorno. Además, debes configurar el puerto para que Render pueda asignarlo dinámicamente.

Cambia esto:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mahuahua_db
spring.datasource.username=postgres
spring.datasource.password=root
```

Por esto:
```properties
# Puerto dinámico asignado por Render (o 8080 en local)
server.port=${PORT:8080}

# Conexión a Base de Datos mediante Variables de Entorno
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

### 1.2 Configuración de CORS para Producción
En el archivo `src/main/java/com/example/Mahuahua/config/CorsConfig.java`, asegúrate de añadir la URL real donde estará alojado tu frontend (por ejemplo, Vercel o Netlify) para que el navegador no bloquee las peticiones.

```java
registry.addMapping("/**")
        .allowedOrigins(
            "http://localhost:3000",
            "http://localhost:4200", 
            "http://localhost:5173",
            "https://mi-clinica-frontend.vercel.app" // <- Añadir la URL de producción aquí
        )
        // ...resto del código
```

---

## ☁️ Paso 2: Configuración en la Nube (Render)

Una vez que hayas guardado los cambios y subido tu código a un repositorio en **GitHub**, sigue estos pasos en Render:

### 2.1 Crear la Base de Datos (PostgreSQL)
1. Inicia sesión en [Render.com](https://render.com/).
2. Haz clic en **New** y selecciona **PostgreSQL**.
3. Ponle un nombre (ej. `mahuahua_db`), selecciona la región más cercana a tus usuarios y la capa gratuita (Free).
4. Haz clic en **Create Database**.
5. Una vez creada, copia el **Internal Database URL**. Lo usarás en el siguiente paso.

### 2.2 Crear el Web Service (El Backend)
1. Haz clic en **New** y selecciona **Web Service**.
2. Conecta tu cuenta de GitHub y selecciona el repositorio donde está este proyecto.
3. Configura los siguientes campos:
   - **Name**: mahuahua-backend (o el que prefieras).
   - **Environment**: Java
   - **Build Command**: `./mvnw clean package -DskipTests` *(Esto compila el proyecto y genera el .jar)*
   - **Start Command**: `java -jar target/Mahuahua-0.0.1-SNAPSHOT.jar` *(Esto enciende el servidor)*

### 2.3 Configurar las Variables de Entorno en Render
Antes de darle a "Create Web Service", desplázate hacia abajo hasta la sección **Advanced** y haz clic en **Add Environment Variable**. Añade las siguientes claves:

| Key | Value | Descripción |
| :--- | :--- | :--- |
| `DB_URL` | `jdbc:postgresql://<Internal_Database_URL>` | Reemplaza `<Internal_Database_URL>` por la URL interna que te dio Render en el paso 2.1. Ojo: asegúrate de que empiece con `jdbc:postgresql://` en lugar de solo `postgres://`. |
| `DB_USERNAME` | *(El usuario de la BD en Render)* | Lo encuentras en el panel de tu base de datos en Render. |
| `DB_PASSWORD` | *(La contraseña de la BD)* | Lo encuentras en el panel de tu base de datos en Render. |

4. Finalmente, haz clic en **Create Web Service**.

---

## ✅ Paso 3: Verificación
Render comenzará el proceso de "Build" (compilación). Esto puede tardar un par de minutos. Cuando termine y diga **"Live"**, podrás acceder a la URL pública que Render te ha asignado (ej. `https://mahuahua-backend.onrender.com`).

Para probar que todo funciona, puedes hacer una petición GET a un endpoint público que hayas creado, o intentar acceder a tu base de datos remotamente si lo habilitaste.
