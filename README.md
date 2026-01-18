# Gestión de Tareas

Proyecto full stack para la gestión de tareas, desarrollado con **Angular** en el frontend y **Spring Boot** en el backend.  
La aplicación permite crear, consultar, actualizar, eliminar y buscar tareas, además de validar la existencia de una tarea por su identificador.

---

## 🛠️ Tecnologías utilizadas

### Frontend
- Angular
- TypeScript
- HTML5
- CSS
- Bootstrap

### Backend
- Java
- Spring Boot
- Spring Web

### Persistencia
- Archivo de texto (`.txt`) para almacenamiento de datos

---

## 📁 Estructura del proyecto


---

## ⚙️ Decisiones técnicas

- Se utilizó **Angular** para el frontend por su estructura basada en componentes y facilidad de consumo de APIs REST.
- El backend fue desarrollado con **Spring Boot** para exponer servicios REST de forma clara y organizada.
- Se implementó el **patrón DTO** para la transferencia de datos entre frontend y backend.
- La persistencia se realizó mediante archivos de texto (`.txt`) como una solución simple y ligera, adecuada para el alcance del proyecto.

---

## 📌 Suposiciones realizadas

- El proyecto se ejecuta en un entorno local. 
- No se requiere autenticación de usuarios.
- El archivo `.txt` es suficiente para la persistencia de datos (no se contempló base de datos).

---

## ▶️ Cómo ejecutar el proyecto

### Backend
1. Abrir la carpeta `gestion-tareas` en el IDE.
2. Ejecutar la aplicación Spring Boot.
3. El backend se levanta en el puerto **1497**
http://localhost:1497/


### Frontend
1. Entrar a la carpeta `front`:
cd front

2. Se instalan dependencias 
npm install

3. Ejecutar aplicacion
ng serve

4. Abrir en el navegador
http://localhost:4200


### Mejoras futuras
Con más tiempo se podrian implementar las siguientes mejoras:

1. Uso de una base de datos (MySql o Postgress)
2. Autenticación o autorizaciones ya que facilitaria con base de datos 
3. Validaciones más robustas en frontend y backend.
4. Manejo de errores centralizado.
5. Implementar contenedores Docker para estandarizar el entorno de ejecución de la aplicación

### Autor

Proyecto realizado por: Bryan Camilo Montes Gonzalez


