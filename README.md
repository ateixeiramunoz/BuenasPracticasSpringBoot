# Documentación del Repositorio: BuenasPracticasSpringBoot

## Introducción

Este documento describe en detalle la utilidad del
repositorio [BuenasPracticasSpringBoot](https://github.com/ateixeiramunoz/BuenasPracticasSpringBoot), diseñado
principalmente para estudiantes del curso de Java. 

Proporciona prácticas clave y recomendadas para el desarrollo
eficiente, limpio y escalable de aplicaciones utilizando Spring Boot.

## 1. Gestión automática de Diferentes Entornos con Docker Compose

El repositorio gestiona distintos entornos de ejecución mediante Docker Compose.

Desde la versión Spring Boot 3.1, lanzada en mayo de 2023, se introdujo soporte nativo para Docker Compose, lo que
permite que las aplicaciones detecten automáticamente un archivo `docker-compose.yaml` y gestionen los servicios
definidos en él durante el ciclo de vida de la aplicación.

En el proyecto se incluyen varios archivos para diferentes modos de ejecución:

- **Ejecución local por defecto**: Utiliza `docker-compose.yml`, que levanta automáticamente un servicio MySQL local
  para facilitar la configuración y el desarrollo de funcionalidades.
- **Entorno de desarrollo (`compose-desarrollo.yaml`)**: Simula un entorno cercano al de producción con configuraciones
  específicas, ideal para pruebas intermedias y desarrollo.
- **Entorno de producción (`compose-produccion.yaml`)**: Ajusta configuraciones optimizadas para el despliegue en
  ambientes reales, priorizando rendimiento y estabilidad.

Además, la arquitectura del proyecto incluye cargas iniciales diferenciadas según el entorno elegido, lo que facilita la
adaptación y pruebas específicas.

### Beneficios:

- Aprender a configurar entornos desde local hasta producción.
- Facilitar la experimentación y validación rápida del código.
- Incrementar el entendimiento práctico sobre cómo ajustar configuraciones según las necesidades de cada entorno.
- Permite replicar los entornos en distintos sistemas con facilidad gracias a la infraestructura como
  código.
- Optimiza el flujo de trabajo al integrarse con pipelines de CI/CD para realizar
  despliegues automatizados.
- Las configuraciones definidas pueden ser reutilizadas en diferentes proyectos o equipos,
  promoviendo consistencia.
- Simplifica la incorporación de nuevos servicios a los entornos existentes.

## 2. Diferentes Perfiles de Ejecución (Spring Profiles)

El proyecto está diseñado para soportar varios perfiles de ejecución, permitiendo una gestión eficiente de
configuraciones específicas para cada entorno:

- **default (local)**: Activo por defecto, configura automáticamente bases de datos y recursos locales para un rápido
  inicio.
- **dev**: Específico para desarrollo avanzado, incluye configuraciones para depuración y pruebas más exhaustivas.
- **prod**: Enfocado en entornos productivos, optimiza el desempeño y la estabilidad para despliegues reales.

### Beneficios:

- Comprender la utilidad y gestión de perfiles en aplicaciones Spring Boot.
- Facilitar el aprendizaje de la segmentación de configuraciones según el entorno.
- Permitir configuraciones mucho más flexibles y portables

## 3. Uso de TestContainers para Testing Integrado

La estructura del proyecto permite realizar pruebas de integración utilizando TestContainers, proporcionando entornos
aislados donde las dependencias reales se ejecutan en contenedores Docker.

Esta estrategia utiliza contenedores recreados dinámicamente en cada ejecución y emplea clases Loader para cargas
iniciales de datos, lo que garantiza consistencia y confiabilidad en las pruebas.

### Ventajas de Contenedores Recreables:

- **Aislamiento Completo:** Cada ejecución de pruebas ocurre en un entorno aislado, evitando contaminación entre pruebas
  y asegurando resultados reproducibles.
- **Consistencia de Entorno:** Los contenedores se recrean desde cero en cada prueba, minimizando variables no
  controladas y reduciendo falsos positivos o negativos.
- **Automatización Sencilla:** El proceso de configuración y limpieza posterior es completamente automatizado,
  optimizando el flujo de desarrollo.

## 4. Carga de Datos mediante Clases Loader

La carga de datos utilizando Clases Loader permite inicializar datos de forma automática y predecible en distintas
etapas de ejecución (desarrollo, pruebas, producción). Este enfoque asegura consistencia y reduce el tiempo invertido en
preparar entornos.

### Creación Programática de Entidades vs. Carga mediante SQL

- **Ventajas de Creación Programática**:
    - El código resulta más comprensible y sencillo de mantener al trabajar en el mismo lenguaje que la lógica de la
      aplicación.
    - Reduce errores por discrepancias entre el esquema de datos y el código.
    - Introduce flexibilidad para generar valores dinámicos o configurar datos según el entorno.
    - Promueve la independencia del motor de base de datos, incrementando la portabilidad y la confiabilidad.

### Beneficios:

- **Datos Predictivos:** Garantiza datos consistentes en cada ejecución, facilitando la detección de errores.
- **Menor Tiempo de Preparación:** Elimina la dependencia de scripts externos, agilizando las pruebas.
- **Facilita el Debugging:** Con datos consistentes y controlados, es más sencillo rastrear y corregir problemas.

### Consistencia entre Pruebas y Desarrollo:

- **Datos Uniformes en Entornos:** Tanto en pruebas como en desarrollo, los datos iniciales se mantienen coherentes.
- **Menos Errores en Integraciones:** Los datos consistentes minimizan errores durante ciclos de integración.
- **Calidad del Software:** Este enfoque fomenta un desarrollo más sólido y confiable, permitiendo a los equipos
  enfocarse en la lógica del negocio.

## 6. Integración con SonarQube

El proyecto incluye integración total con SonarQube, una herramienta de inspección continua que mide calidad de código,
vulnerabilidades, adherencia a buenas prácticas y cobertura de pruebas.

SonarQube analiza el código fuente en busca de problemas de calidad mediante un proceso automatizado que evalúa varios
aspectos, como bugs, vulnerabilidades, hotspots de seguridad y cobertura de pruebas. Los resultados del análisis se
presentan como métricas y reportes visuales que permiten identificar áreas de mejora.

Un concepto clave en SonarQube son las **quality gates** (puertas de calidad), que consisten en umbrales predefinidos
para métricas específicas, como la cobertura de código, el número de problemas críticos o la cantidad de líneas
duplicadas. Si el código no cumple con estos criterios, la quality gate falla, bloqueando la aprobación de cambios hasta
que los problemas sean resueltos.

En este proyecto, la integración está automatizada mediante GitHub Actions, asegurando que cada cambio pase por el
análisis de calidad en SonarQube sin intervención manual. Esto permite evaluar automáticamente la adherencia del código
a las quality gates definidas.

### Beneficios:

- Favorece la implementación de buenas prácticas desde etapas iniciales.
- Ayuda a identificar, prevenir y corregir defectos de código de manera proactiva.
- Proporciona reportes automáticos y detallados para mejorar la calidad del producto.
- Fomenta el aprendizaje práctico a través de retroalimentación inmediata.
- Asegura estándares mínimos de calidad mediante el uso de quality gates, promoviendo un desarrollo más sólido y
  confiable.

## 7. Despliegue Remoto Automatizado y Entornos de Integración

El repositorio incluye configuraciones para realizar despliegues remotos automatizados, permitiendo utilizar entornos de
integración para un flujo de trabajo confiable y colaborativo.

Un servidor con Docker y SonarQube está disponible para los alumnos, integrando directamente los cambios realizados en
ramas de desarrollo o producción. Estos cambios se evalúan automáticamente empleando pruebas y estándares definidos.
Además, esta configuración garantiza que todos los estudiantes trabajen en un entorno exactamente igual, proporcionando
consistencia y evitando problemas derivados de diferencias en los entornos locales.

### Beneficios Clave:

- Cada cambio aprobado se despliega de manera automática, optimizando tiempos y procesos.
- Entornos de Integración Controlados. Minimiza errores en colaboraciones entre equipos.
- Simulación Realista: Proporciona una experiencia cercana a entornos laborales reales, mejorando habilidades
  prácticas.
- Control de Calidad Previo al Despliegue: Asegura estándares mediante pruebas automáticas integradas en el flujo.
- Uniformidad de Entornos: Permite a todos los estudiantes trabajar en un entorno idéntico, garantizando
  consistencia y eliminando incompatibilidades relacionadas con configuraciones locales.

## 8. Buenas Prácticas en la Estructura del Proyecto

El repositorio sigue convenciones claras y patrones de diseño que priorizan la mantenibilidad y escalabilidad.

### Organización y Arquitectura del Código:

- **Separación por capas**:
    - **Controller:** Gestión de la comunicación con el cliente.
    - **Service:** Contiene la lógica de negocio.
    - **Repository:** Maneja el acceso a la base de datos.
- **Paquetes organizados por funcionalidad:** Incrementan la modularidad y facilitan la navegación del código.

### Principios y Estándares:

- **Principios SOLID**:
    - **S (Single Responsibility Principle)**: Cada clase, módulo o función dentro del código tiene una única
      responsabilidad, lo que facilita su mantenimiento y reduce el acoplamiento entre componentes.
    - **O (Open/Closed Principle)**: El diseño del código está abierto a extensiones, pero cerrado a modificaciones
      directas, permitiendo nuevas funcionalidades sin alterar el comportamiento existente.
    - **L (Liskov Substitution Principle)**: Las clases derivadas pueden ser utilizadas sin afectar el comportamiento
      esperado de las clases base, garantizando consistencia en los contratos de las interfaces.
    - **I (Interface Segregation Principle)**: Las interfaces están diseñadas de manera que los clientes no tengan que
      implementar métodos que no usan, mejorando la modularidad.
    - **D (Dependency Inversion Principle)**: El código depende de abstracciones en lugar de implementaciones concretas,
      promoviendo la independencia de módulos.

- **Principios DRY (Don't Repeat Yourself)**:
    - El código evita duplicaciones innecesarias al identificar patrones comunes y abstraer lógica compartida en
      componentes reutilizables, reduciendo el esfuerzo de mantenimiento.

- **Uso de SonarQube**:
    - Las herramientas automáticas de calidad, como SonarQube, analizan el código en busca de problemas como duplicados,
      vulnerabilidades, puntos críticos de seguridad y errores, proporcionando comentarios visuales y métricas.
    - Se definen **quality gates** que bloquean la integración de cambios si no cumplen con mínimos criterios de
      calidad,
      como la cobertura de pruebas o la ausencia de bugs críticos.

- **Patrones de Diseño**:
    - **Repositorio (Repository Pattern)**: Centraliza las operaciones de acceso a la base de datos, desacoplando la
      lógica de negocio de la persistencia.
    - **Inversión de Control (IoC)**: Utiliza frameworks como Spring para gestionar la vida útil y las dependencias de
      los
      componentes, reduciendo el código boilerplate.
    - **DTO (Data Transfer Object)**: Minimiza el acoplamiento entre capas al encapsular los datos transmitidos entre
      ellas, mejorando la extensibilidad y legibilidad.

- **Estilo de Código**:
    - Consistencia en la nomenclatura y formato, siguiendo convenciones estándar de Java.
    - Uso de comentarios claros y precisos para explicar lógica compleja o partes críticas del código.

- **Prácticas Adicionales**:
    - Cobertura mínima de pruebas establecida para garantizar que aspectos claves estén validados con tests
      automatizados.
    - Refactorización constante para mejorar el diseño del código sin modificar su funcionalidad.
    - Documentación interna con Javadoc en métodos y clases, promoviendo la comprensión colectiva del proyecto.

### Pruebas:

- **Uso de TestContainers:** Implementa pruebas en entornos aislados y confiables.
- **Separación por tipo de prueba:** Clasificación clara entre pruebas unitarias e integradas, optimizando la validación
  del código.



## Flujo de Trabajo de un Alumno con este Proyecto

A continuación se describe cada paso del flujo a seguir y los beneficios
asociados:

1. **Clonación del Repositorio y Configuración Inicial**
    - **Acciones:** Los alumnos comienzan clonando el repositorio y configurando el proyecto localmente en su entorno de
      desarrollo.
    - **Beneficios:**
        - Aprenden a preparar un entorno local desde cero.
        - Introducción al uso de Git y GitHub para la gestión de repositorios.
        - Experiencia práctica en la configuración de proyectos Spring Boot con dependencias integradas.

2. **Desarrollo de Funcionalidades en Local**
    - **Acciones:** Los alumnos crean una nueva rama a partir de la rama principal de desarrollo, donde implementarán
      la nueva funcionalidad. Durante el desarrollo en local, pueden incluir cargas de datos específicos para preparar
      las pruebas necesarias sin afectar a los demás desarrollos o miembros del equipo.
    - **Beneficios:**
        - Permite experimentar e iterar en un entorno aislado.
        - Fomenta buenas prácticas en la gestión de ramas y versiones del código.
        - Introduce habilidades para gestionar datos iniciales específicos para sus pruebas.

3. **Ejecución de Pruebas en Local**
    - **Acciones:** En el entorno local, los alumnos crean y ejecutan pruebas unitarias e integradas para validar su
      funcionalidad. Usan frameworks como JUnit y TestContainers para aislar pruebas y garantizar confiabilidad.
    - **Beneficios:**
        - Refuerzan conocimientos en la escritura de pruebas seguras y reproducibles.
        - Experimentan con la validación de funcionalidades a través de datos iniciales controlados.
        - Detectan y corrigen errores desde las primeras etapas del desarrollo.

4. **Commit y Push de Cambios al Repositorio**
    - **Acciones:** Una vez validada la funcionalidad en local, los alumnos realizan un commit de sus cambios
      (incluyendo las pruebas creadas y los datos iniciales necesarios) y hacen un push de la rama al repositorio
      remoto.
      Después, generan un pull request apuntando a la rama de desarrollo.
    - **Beneficios:**
        - Refuerzan buenas prácticas de integración mediante el uso de pull requests.
        - Aprenden a documentar y explicar sus cambios para una revisión eficaz.

5. **Ejecución en el Entorno de Desarrollo**
    - **Acciones:** Los cambios aprobados se despliegan automáticamente en el entorno de desarrollo. En este entorno,
      se ejecutan pruebas integradas y validaciones adicionales, incluyendo análisis de calidad con SonarQube, para verificar la
      funcionalidad, consistencia e impacto del código.
    - **Beneficios:**
        - Comprenden el ciclo de validación automatizado en entornos colaborativos.
        - Experimentan con herramientas de calidad continua como SonarQube para mejorar su código.
        - Simulan escenarios que reflejan ambientes productivos reales.

6. **Verificación Final y Ajustes**
    - **Acciones:** En el entorno de desarrollo, los alumnos revisan los resultados y realizan cualquier ajuste o
      corrección necesaria. Si los cambios son satisfactorios, quedan listos para ser integrados en un entorno superior.
    - **Beneficios:**
        - Refuerzan habilidades de depuración y colaboración en equipos.
        - Aprenden a identificar y documentar problemas en entornos de pruebas.

7. **Documentación y Mejora Continua**
    - **Acciones:** Los alumnos documentan el proceso, retroalimentan ajustes a la configuración y planifican mejoras
      para futuras iteraciones.
    - **Beneficios:**
        - Adquieren disciplina en la documentación de proyectos, esencial para entornos reales.
        - Aprenden la importancia de retroalimentarse desde la experiencia práctica.

### Beneficios Generales del Flujo de Trabajo:

- **Entornos Realistas:** Simula un flujo de trabajo profesional aplicando herramientas y metodologías modernas.
- **Aprendizaje Práctico:** Las tareas están orientadas a propósitos reales del desarrollo de software.
- **Preparación Profesional:** Los alumnos adquieren conocimientos aplicables directamente al mercado laboral.
- **Colaboración y Tecnología:** Experiencia integral que incluye colaboración, calidad de código, pruebas y despliegues
  automatizados.

## Conclusión

El repositorio **BuenasPracticasSpringBoot** es una herramienta educativa diseñada para estudiantes de Java, enfocada en
fortalecer conocimientos prácticos sobre el desarrollo de software moderno. A través de su estructura, los alumnos
aprenden conceptos clave como gestión de entornos, implementación de buenas prácticas, pruebas integradas, análisis de
calidad de código, y uso de herramientas como SonarQube y GitHub, garantizando un aprendizaje completo y profesional.

La organización clara y sencilla del proyecto facilita que los estudiantes se concentren en aprender y codificar,
permitiéndoles enfocarse en lo esencial sin distracciones. Esto fomenta la comprensión de principios básicos como SOLID
o DRY de manera práctica, mientras adquieren experiencia con herramientas que aseguran calidad y consistencia, como
TestContainers.

Además, el flujo de trabajo propuesto guía a los alumnos por cada etapa del ciclo de vida del desarrollo de software,
desde la configuración local hasta despliegues avanzados, reforzando habilidades técnicas, trabajo en equipo, y
documentación. Gracias a su enfoque práctico y accesible, este repositorio fomenta la preparación para entornos
laborales reales mientras promueve la capacidad de adaptarse e innovar en proyectos complejos.

