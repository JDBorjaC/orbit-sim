
# Simulador de Cuerpos Celestes

 Se simula el movimiento de cuerpos celestes como el Sol y la Tierra mediante la interacción gravitatoria. Para ello, se implementó un motor de física que utiliza el método de Euler, así como el de Verlet.

 Adicionalmente, se hace comparación de dos estrategias para calcular las fuerzas con las que interactúa cada objeto:
 1. Interacción entre todos los cuerpos O(n^2)
 2. Estimación mediante el algoritmo de Barnes-Hut O(nlogn)

## Estructura del Proyecto

El código está organizado en los siguientes paquetes:

- **main**: Contiene la clase principal que inicia la simulación.
- **physics**: Contiene las clases relacionadas con la física de los cuerpos y su integración.
  - `Body2D.java`: Clase base para representar un cuerpo físico en el espacio bidimensional.
  - `Circle.java`: Representación geométrica de los cuerpos, utilizada para la visualización.
  - `Engine.java`: Motor de simulación encargado de actualizar el estado de los cuerpos en cada iteración.
- **runtime**: Contiene las clases que controlan el flujo de ejecución.
  - `Gameloop.java`: Implementa el bucle principal de la simulación.
  - `Renderer.java`: Se encarga de la representación gráfica de los cuerpos.
- **utils**: Contiene clases de utilidad y constantes.
  - `Constants.java`: Define valores constantes utilizados en la simulación.
  - `Vector2D.java`: Implementa operaciones matemáticas para vectores en 2D.
  - `QuadTree.java`: Implementación de la estructura de datos y los métodos necesitados para utilizar Barnes-Hut (particiones del espacio y cálculo de centros de masa)

## Uso
1. Configurar los parámetros en Constants.java. Aquí se pueden modificar las unidades físicas, la escala de visualización, el método de integración (EULER o VERLET), la estrategia de interacción (n^2 o Barnes-Hut), etc.

2. (OPCIONAL) en main.java se hace declaración de todos los componentes interactuando en el sistema, así que puede modficarse con mayor libertad su instanciación desde este archivo, bajo el riesgo de experimentar errores.

3. Instanciar los cuerpos celestes en main asignando sus posiciones y velocidades iniciales.

4. Ejecutar el proyecto

