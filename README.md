Pasos para ejecutar

Pasos para configurar el entorno virtual e instalar las librerías necesarias

Abrir una terminal o consola y navegar al directorio del proyecto:

cd python_vist

Crear un entorno virtual:

python -m venv .venv

1.Activar el entorno virtual:

.\.venv\Scripts\activate

Actualizar pip (opcional pero recomendado):

python -m pip install --upgrade pip


2. Instalar las librerías desde requirements.txt:

pip install -r requirements.txt

Esto instalará Flask, requests y Flask-SQLAlchemy, entre otras dependencias.

Ejecutar la aplicación:

python.exe index.py


# Práctica: Peticiones HTTP desde el Frontend

## ✔ Objetivo de la Práctica
- Comprender y aplicar los conceptos de HTTP/HTTPS, métodos REST y CORS desde el frontend.
- Implementar una página web que realice peticiones simuladas a un servidor.
- Registrar y analizar los resultados mediante DevTools.
- Relacionar teoría con práctica dentro del proyecto integrador.

---

## ✔ Materiales y Herramientas
- VS Code + Live Server  
- HTML5, CSS3, JavaScript  
- Fetch API  
- Browser DevTools (Network)  
- Repositorio GitHub (rama `feature/http-client`)  
- Servicio mock: JSONPlaceholder  

---

## ✔ Desarrollo de la Práctica

### **Paso 1: Creación de la página básica**
Se implementó la estructura mínima indicada (HTML + botón + contenedor de resultados).

### **Paso 2: Implementación de la petición GET**
Se programó una función JavaScript que realiza una solicitud GET usando Fetch API al endpoint:




La consola registra:
- URL solicitada  
- Método utilizado  
- Tiempo de respuesta  
- Código de estado HTTP  

La respuesta se muestra también en el HTML.

### **Paso 3: Análisis en DevTools (Network)**
Se documentó:
- Request Headers  
- Response Headers  
- Política CORS  
  - El servidor JSONPlaceholder retorna:  
    ```
    access-control-allow-origin: *
    ```

### **Paso 4: Documentación de resultados**
A continuación la tabla solicitada:
Se puede resgistrar un inversionista
<img width="1279" height="684" alt="image" src="https://github.com/user-attachments/assets/10daf27b-0e0a-48c5-a799-0bfc4079c481" />

LISTA DE INVERSIONISTAS
<img width="1298" height="702" alt="image" src="https://github.com/user-attachments/assets/8faad67c-45a2-4695-9108-670c698a0dfa" />


## 📊 Resultados Obtenidos

| Método | URL | Código de estado | Tiempo respuesta | Observaciones CORS |
|--------|------|------------------|------------------|---------------------|
| GET | https://jsonplaceholder.typicode.com/posts/1 | 200 | ~120 ms | CORS permitido: `access-control-allow-origin: *` |

<img width="1275" height="654" alt="image" src="https://github.com/user-attachments/assets/85aeb1c8-853f-40be-8f8b-e0187f649402" />

---

## ✔ Resultados Esperados Cumplidos
- Página web funcional que realiza una petición GET.  
- Evidencias en consola y pestaña Network.  
- Registro de resultados en README.  
- Código subido correctamente a la rama solicitada.

---

# ❓ Preguntas de Control

### **1. Diferencia entre códigos 200, 201, 400 y 500**
- **200 OK:** petición ejecutada correctamente.  
- **201 Created:** recurso creado exitosamente (POST).  
- **400 Bad Request:** error del cliente (petición mal enviada).  
- **500 Internal Server Error:** fallo del servidor.  

---

### **2. ¿Qué función cumple CORS?**
Define qué dominios externos pueden acceder a la API.  
Protege contra accesos no autorizados de otros orígenes.

---

### **3. Diferencia entre Request Headers y Response Headers**
- **Request headers:** enviados por el cliente (navegador → servidor).  
- **Response headers:** enviados por el servidor (servidor → navegador).  

---

### **4. ¿Por qué documentar los tiempos de respuesta?**
Permite analizar rendimiento, latencia, problemas de red y eficiencia de los endpoints.

---

### **5. ¿Qué riesgos tiene exponer peticiones sin validar en el frontend?**
- Manipulación maliciosa  
- Inyección de parámetros  
- Acceso no autorizado  
- Filtración de datos  

---

### **6. Consideraciones de seguridad entre servicios**
- Validar datos en backend  
- Uso de HTTPS  
- Tokens JWT con expiración  
- Rate limiting  
- Políticas CORS restringidas  
- No exponer información sensible  

---

## ✔ Evidencias
Las evidencias solicitadas (capturas de consola, Network, código y README) deben incluirse en el PDF enviado al EVA.

---

## ✔ Rama del repositorio
