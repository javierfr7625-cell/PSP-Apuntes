# PSP Java - Apuntes, Ejercicios y Simulacro de Examen ☕🚀

Apuntes interactivos web desarrollados con el framework **Astro** para la asignatura de **Programación de Servicios y Procesos (PSP)** de 2º DAM (Desarrollo de Aplicaciones Multiplataforma).

👉 **Sitio Web en GitHub Pages**: [https://javierfr7625-cell.github.io/PSP-Apuntes/](https://javierfr7625-cell.github.io/PSP-Apuntes/)

---

## 📚 Contenido del Proyecto

1. **Tema 1: Programación de Procesos**
   - Conceptos del SO (Kernel, Interrupciones, Modo Dual 0/1, Estados y Colas).
   - Planificación de CPU y Cambio de Contexto (*Context Switch*).
   - `ProcessBuilder`, control del proceso y comunicación bidireccional por **Streams** (`OutputStream` vs `InputStream`).
   - 5 Ejercicios oficiales del Tema 1 resueltos.
2. **Tema 2: Programación de Hilos y Sincronización**
   - Hilos (`Thread`, `Runnable`), ciclo de vida (`start` vs `run`, `join`, `sleep`).
   - Condiciones de Carrera, Inconsistencia de Memoria y Sección Crítica (Reglas de Dijkstra).
   - Semáforos (`Semaphore`): permisos, exclusión mutua, semáforos estáticos vs instancia y semáforos independientes.
   - 5 Ejercicios oficiales del Tema 2 resueltos.
3. **Tutorial Oficial: Creación de Artefactos JAR en IntelliJ IDEA**
   - Paso a paso para empaquetar subprocesos con su `MANIFEST.MF` y enlazarlos con rutas relativas desde el proceso Padre.
4. **Simulacro de Examen Oficial (Modelo A - 10 Puntos) Completo**
   - **Ejercicio 1 (4 pts):** Sistema de Registro y Login con subprocesos de cifrado César (+2 / -2).
   - **Ejercicio 2 (2.5 pts):** Contador concurrente de dígitos (0 al 9) con 10 hilos y semáforo estático.
   - **Ejercicio 3 (3.5 pts):** Clasificador de palabras ("2", "4", "6", "8") con semáforos independientes para `menos5` y `_5omas`, y pausas de 5ms.
5. **Chuleta Rápida de Examen**
   - Checklist de 10 puntos pre-entrega, plantillas mínimas para memorizar y preguntas teóricas clave resueltas.

---

## 💻 Código Fuente Java

En la carpeta [`codigo-java/simulacro`](./codigo-java/simulacro) dispones del código fuente en Java completo, corregido y compilable para abrir en tu IDE (IntelliJ IDEA o VS Code).

---

## 🛠️ Cómo Ejecutar en Local

Para levantar la web en tu máquina:

```bash
# 1. Instalar dependencias
pnpm install

# 2. Iniciar servidor de desarrollo
pnpm dev
```

La web estará disponible en: `http://localhost:4321/PSP-Apuntes/`

Para compilar el sitio estático para producción:

```bash
pnpm build
```

Los ficheros estáticos listos para desplegar se generarán en la carpeta `dist/`.

---

## 🚀 Despliegue en GitHub Pages (Sin errores con Jekyll)

Este repositorio está 100% configurado para GitHub Pages:

1. **Astro + GitHub Actions (Automático)**:
   - Se incluye el workflow oficial `.github/workflows/deploy.yml`.
   - En tu repositorio de GitHub, ve a **Settings > Pages > Build and deployment > Source** y selecciona **GitHub Actions**.
   - Cada vez que hagas `git push`, GitHub compilará y desplegará la web automáticamente.
2. **Compatibilidad con Jekyll (`.nojekyll`)**:
   - Se incluye el archivo `.nojekyll` en `public/` y en la compilación para evitar que el motor Jekyll de GitHub Pages ignore las carpetas que empiezan por `_` (como `_astro/`).
3. **Ruta base configurada**:
   - `astro.config.mjs` tiene definido `base: '/PSP-Apuntes'` y `site: 'https://javierfr7625-cell.github.io'` para que todos los enlaces, estilos CSS y scripts funcionen perfectamente sin enlaces rotos.
