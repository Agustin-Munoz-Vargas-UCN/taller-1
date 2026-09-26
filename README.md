# Taller 01: POO - El Grupo de POO

Software hecho en Java para organizar la entrada de estudiantes autorizados a un grupo de mensajería hecho en WhatsApp, manejando en base a leer, crear y modificar distintos archivos de textos, solo utilizando la librería "java.io.File" como único apoyo externo.
---

## Integrantes
* **Nombre:** Agustín Muñoz | **RUT:** 22.387.587-4 | **Usuario GitHub:** Agustin-Munoz-Vargas-UCN (https://github.com/Agustin-Munoz-Vargas-UCN) | **Carrera:** Ingeniería Civil en Computación e Informática (ICCI)

---

## Estructuras de Datos y Decisiones Técnicas

El proyecto presente no utilizo Programación Orientada a Objetos (POO) ni colecciones dinámicas, así que se manejo de esta forma:

1. **Lista de alumnos de la inscritos al curso (String[][] archAlumnos):**
   * Maneja los integrantes inscritos al curso que se encuentran en el archivo "Alumnos.txt", se rellena automáticamente y se puede modificar bajo ciertas instrucciones, ordenandolo por integrante (i) y tipo de información (j) [archAlumnos[i][j]], donde los tipos de información se dividen en:
   * - j = 0 -> Nombre
   * - j = 1 -> Apellido
   * - j = 2 -> RUT
   * - j = 3 -> Paralelo
   * Y tiene un máximo de 100 integrantes.
2. **Lista de solicitudes (String[][] archSolicitudes):**
   * Maneja la lista de solicitudes del curso que se encuentran en el archivo "Solicitudes.txt", no se puede modificar dentro del programa, aunque si se pueden hacer solicitudes individuales, ordenándolo por integrante (i) y tipo de información (j) [archAlumnos[i][j]], donde los tipos de información se dividen en:
   * - j = 0 -> Nombre
   * - j = 1 -> Apellido
   * Y tiene un máximo de 100 integrantes.
3. **Lista de alumnos que solicitaron entrar y fueron aceptados (String[][] inWhasa):**
   * Maneja la lista de solicitudes aceptadas e integradas del curso, se puede rellenar automáticamente o ser modificada bajo ciertas instrucciones, ordenándolo por integrante (i) y tipo de información (j) [archAlumnos[i][j]], donde los tipos de información se dividen en:
   * - j = 0 -> Nombre
   * - j = 1 -> Apellido
   * - j = 2 -> RUT
   * - j = 3 -> Paralelo
   * Y tiene un máximo de 100 integrantes.
 
---

## Estructura del Repositorio


```text
.
├── README.md
├── Agustin-Munoz-Vargas-UCN_POO_Taller01_Eclipse.zip
├── Agustin-Munoz-Vargas-UCN_POO_Taller01_Terminal.zip
└── src/
    ├── reportes/
    │   ├── //ReporteC1-VX (Cantidad variable de archivos de reporte C1)
    │   └── //ReporteC2-VX (Cantidad variable de archivos de reporte C2)
    ├── textos/
    │   ├── Alumnos.txt
    │   └── Solicitudes.txt
    ├── taller01/
    │   └── Main.java
    └── module-info.class
```


---

## Requisitos del Entorno

* Se requiere una computadora con Java instalado previamente. 

---

## Instrucciones de Ejecución

> **Importante:** 
* Como el directorio de trabajo varía entre si uno usa la terminal y el programa utilizado para crear los archivos, ***la versión presente en el archivo .zip fue creada con la intención de que funcione en la terminal.***
* Si se decide utilizar Eclipse, se recomienda que se ejecute como aplicación de java, dándole click derecho a la carpeta src/ y dándole a Run As... 

### Opción 1: Terminal.

- Descargue y descomprima (Click derecho/Extraer aquí) el
  "Agustin-Munoz-Vargas-UCN_POO_Taller01_Terminal.zip"

- Abra la carpeta raíz del archivo en la terminal

- Escriba "java Main.java"

- Y con eso el archivo tendría que estar ejecutándose la terminal.

### Opción 2: Eclipse IDE.

- Descargue y descomprima (Click derecho/Extraer aquí) el
  "Agustin-Munoz-Vargas-UCN_POO_Taller01_Eclipse.zip"

- Abra Eclipse y dele a "Importar"

- Seleccione "General/Archive File"

- Busque el archivo descargado "Agustin-Munoz-Vargas-UCN_POO_Taller01_Eclipce.zip" y selecciónelo y continue.

- Ya con el proyecto iniciado, dele a la carpeta raíz, click derecho, "Run As/Java aplication" 

- Y con eso el archivo tendría que estar ejecutándose en Eclipse.



## Manejo de Casos Borde y Validaciones

* **Control de Archivo:** Si no esta disponible, el programa tirara un aviso, pero sera capaz de funcionar mayormente sin la necesidad de los archivos, permitiendo generar guardados hechos puramente por el programa.
* **Fallas a la hora de ingresar inputs:** Si se presenta un argumento de formato incorrecto durante la navegación de los menus, el programa generara un reporte especial y te consultara si quieres hacer un guardado de la "lista de alumnos de la inscritos al curso" modificada antes de cerrar el programa (tanto si se genera un error como si se sale de forma intencionada), y en ciertos casos especiales, te pedirá ingresar un input forzado (como a la hora de poner un paralelo al inscribir a alguien al curso).
* **Alumnos Duplicados:** Se verifica si los alumnos están duplicados tanto al agregar a al grupo como en la lista de alumnos.
* **Limpieza de archivos de guardado:** Los espacios vacíos se manejan como "Srings nullos", pero si se elimina un integrante, este sera guardado con todas las entradas como "ELIMINADO", a la hora de guardar un archivo o agregar un nuevo integrante, los espacios nulos o los que dicen "ELIMINADO" serán rellenados o eliminados.
* **Máximo de integrantes del grupo de WhatsApp:** A la hora de intentar añadir mas de 100 integrantes tirara el aviso de "[!] No quedan cupos en el grupo.".
