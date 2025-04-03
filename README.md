
# Simulador de Cuerpos Celestes

 Se simula el movimiento de cuerpos celestes como el Sol y la Tierra mediante la interacción gravitatoria. Para ello, se implementó un motor de física que utiliza el método de Euler, así como el de Verlet.

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

## Uso
1. Configurar los parámetros en Constants.java. Aquí se pueden modificar las unidades esenciales y la escala de visualización, así como el método (EULER o VERLET)

2. Instanciar los cuerpos celestes en main asignando sus posiciones y velocidades iniciales.

2. Ejecutar el proyecto

