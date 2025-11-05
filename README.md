# 🧩 Configuración Inicial del Proyecto

Este documento detalla los pasos necesarios para configurar y ejecutar correctamente los microservicios del proyecto.  
Asegúrate de seguir cada paso en orden antes de iniciar los servicios desde **Spring Dashboard**.

---

## 📋 Requisitos Previos

### 1. Instalar Apache Maven
Este proyecto utiliza **Maven** para la gestión de dependencias y el ciclo de vida de compilación.  
Sigue este tutorial para instalarlo correctamente en tu entorno local:

🔗 [Video guía de instalación de Maven (YouTube)](https://www.youtube.com/watch?v=rl5-yyrmp-0)

> 💡 Verifica la instalación ejecutando en consola:
> ```bash
> mvn -v
> ```

---

### 2. Instalar la extensión **Spring Boot Dashboard**
Desde **Visual Studio Code**, instala la extensión oficial:  
🧩 **Spring Boot Dashboard**

Esto te permitirá iniciar y administrar los microservicios de forma visual y centralizada.

---

### 3. Instalar Docker Desktop
El proyecto puede usar contenedores para servicios externos como PostgreSQL u otros recursos.  
Descarga e instala Docker desde:

🔗 [https://www.docker.com/products/docker-desktop/](https://www.docker.com/products/docker-desktop/)

> 💡 Una vez instalado, asegúrate de que Docker Desktop se esté ejecutando antes de levantar los servicios.

---

## ⚙️ Configuración del archivo `.env`

Crea un archivo llamado `.env` en la raíz del proyecto y coloca los siguientes valores:

```env
DB_USERNAME=postgres
DB_PASSWORD=pg314
DB_PORT=5432
DB_NAME=postgres
```

---


## 🗄️ Configuración de PostgreSQL

- Inicia Docker Desktop o utiliza una instalación local de PostgreSQL.
- Crea un servidor con las credenciales indicadas en el .env.
- Dentro del servidor, crea las siguientes bases de datos:

```
CREATE DATABASE patients_db;
CREATE DATABASE files_db;
```
---
## 🚀 Inicio de los Microservicios

1. Abre el proyecto en Visual Studio Code.

2. Asegúrate de que Docker esté corriendo.

3. Abre la extensión Spring Boot Dashboard.

4. Inicia los siguientes microservicios (uno por uno o en conjunto):

- patients-service
- files-service
- gateway-service (no aplica por el momento)


⚠️ Si un servicio no inicia, revisa que las variables del .env coincidan con tu configuración local y que la base de datos esté corriendo.

---
## ✅ Verificación

- Accede a http://localhost:4001 o http://localhost:4002 según el puerto de cada microservicio.

- Comprueba en logs que las conexiones a la base de datos fueron exitosas:

---
## 📦 Notas adicionales

1. Si realizas cambios en el .env, reinicia los microservicios para que se apliquen.

2. Se recomienda mantener las versiones actualizadas de:

- Java 17 o superior
- Maven 3.9+
- PostgreSQL 14+

3. Cada microservicio puede tener su propio archivo application.properties.


---