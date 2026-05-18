# Mahuahua - Plataforma de Gestión para Consultorios Psicológicos

## 📖 Descripción del Proyecto
**Mahuahua** es un sistema backend integral de nivel empresarial desarrollado en **Spring Boot**. Está diseñado específicamente para digitalizar, centralizar y optimizar la administración y el flujo de trabajo de consultorios y clínicas de psicología. 

El proyecto proporciona una API RESTFul robusta, segura y escalable que sirve como pilar para conectar aplicaciones cliente (Frontend) modernas (Web o Móvil).

## 🎯 Público Objetivo
Esta plataforma está pensada para resolver las necesidades operativas de:
1. **Psicólogos y Terapeutas Independientes**: Profesionales que buscan llevar un control digital y organizado de sus pacientes, notas de sesión e ingresos, abandonando los registros en papel o Excel.
2. **Clínicas Psicológicas y Centros de Bienestar**: Instituciones que necesitan gestionar agendas compartidas de múltiples especialistas, unificar el acceso a las historias clínicas y centralizar la administración financiera.
3. **Personal Administrativo y Recepcionistas**: Personal encargado de la asignación de citas, control de asistencia y verificación de pagos en el día a día.

## 🚀 Alcance del Sistema
El backend abarca todos los procesos fundamentales de la clínica a través de los siguientes módulos:

- **Gestión de Pacientes**: Registro completo de datos personales y de contacto.
- **Módulo de Especialistas**: Administración de perfiles de psicólogos y sus especialidades.
- **Motor de Agendamiento (Citas)**: 
  - Control dinámico de estados (Pendiente, Confirmada, Completada, Cancelada).
  - Lógica de validación estricta para evitar superposición de turnos para un mismo psicólogo.
- **Gestión Clínica Profesional**:
  - **Historias Clínicas**: Generación automática al dar de alta un paciente, garantizando un registro único.
  - **Evoluciones (Notas de Sesión)**: Espacio seguro para registrar notas clínicas, observaciones y diagnósticos vinculados a cada cita.
- **Módulo de Facturación (Cobros)**:
  - Control financiero integrado directamente a cada cita.
  - Gestión de diferentes métodos de pago (Efectivo, Tarjeta, Transferencia) y estados transaccionales.

## 🛠️ Arquitectura y Tecnologías
El sistema está construido siguiendo las mejores prácticas de la industria para garantizar su mantenibilidad:
- **Lenguaje**: Java 17
- **Framework**: Spring Boot 3.x (Spring Web, Spring Data JPA, Spring Validation)
- **Base de Datos**: PostgreSQL
- **Patrón de Diseño**: Arquitectura Multicapa (Controller, Service, Repository)
- **Transferencia de Datos**: Uso exclusivo de **DTOs** (Data Transfer Objects) mediante *Java Records* para ocultar y proteger las entidades de la base de datos.
- **Manejo de Errores**: Excepciones globales personalizadas (GlobalExceptionHandler) para devolver respuestas coherentes y fáciles de consumir.

## 💻 Instalación y Configuración Local

1. Asegúrate de tener instalado **Java 17** y el motor de base de datos **PostgreSQL**.
2. Crea una base de datos local llamada `mahuahua_db`. Las credenciales por defecto configuradas son `postgres` / `root`. (Puedes modificar esto en `src/main/resources/application.properties`).
3. Abre una terminal en la raíz del proyecto.
4. Ejecuta el servidor usando Maven Wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
5. La API estará disponible en `http://localhost:8080`.
