# mastermind

El original de este documento esta ubicado [aqui](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/README.md) 
formando parte del proyecto github [MasterMind](https://github.com/ciscoruiz/mastermind/tree/mvc-supervisor-controller).

El siguiente diagrama muestra la arquitectura del paquetes utilizada para desarrollar las tres versiones diferentes de este programa. El
muy bien conocido [Model-View-Controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) es la
base para este diseño.


* El paquete [`mastermind.model`](#mastermindmodel) contiene todas las clases necesarias para jugar Mastermind. [[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
  El componente central del patrón. Es la estructura de datos dinámica de la aplicación, independiente de la interfaz 
  de usuario. Gestiona directamente los datos, la lógica y reglas de la aplicación. Las clases [mastermind.model.Game](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/Game.java)
  , [mastermind.model.Configuration](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/Configuration.java) 
  y [mastermind.model.MasterMind](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/MasterMind.java) 
  podrían considerarse como las clases más importantes para este paquete.

* El paquete [`mastermind.view`](#mastermindview) contiene todas las definiciones abstractas para **Vistas** que interactúan con el Usuario
  y el Controlador.  Hasta ahora, este paquete tiene solo una especialización:
  * El paquete [`mastermind.view.console`](#mastermindviewconsole) contiene todas las vistas para interactuar con el usuario usando la consola y el teclado.

* El paquete [`mastermind.controller`](#mastermindcontroller) contiene todas las definiciones de los **Controladores** necesarios para interactuar con
  las diferentes **Vistas**. [[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) Acepta entrada y
  lo convierte en comandos para el modelo o la vista. El enfoque principal de estas clases es acceder al **Modelo** de la 
  forma requerida por la vista. 
 
![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/mvc-supervisor-controller/doc/arquitectura.puml)

# Diagrama de Clases por paquetes

## mastermind.model

El **Modelo** es la estructura de datos dinámica de la aplicación, independiente de la interfaz de usuario. Gestiona 
directamente los datos, la lógica y reglas de la aplicación.

Las clases contenidas en este paquete son las siguientes:
* [Game](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/Game.java): contiene
  todos los detalles referentes al juego en curso, como número de reintentos, número máximo de reintentos, etc
* [Configuration](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/Configuration.java):
  establece los parámetros del juego, como las letras válidas para formar los códigos a descubrir, nº de reintentos.
* [CodeMaker](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/CodeMaker.java):
  genera el código secreto [Code](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/Code.java)
  que el usuario del juego debe descubrir, además evalúa la calidad de las claves  que el usuario va seleccionando.
  La evaluación de la clave se devolverá como una instancia de [Response](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/Response.java)
  a la que se puede interrogar sobre si el código ha sido descubierto o en qué modo faltan partes por descubrir.
* [ValidFigures](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/ValidFigures.java):
  contiene la lista de caracteres válidos para generar/descubrir la clave secreta.
* [MasterMind](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/MasterMind.java): Lleva la cuenta
  del número de partidas que han ganado el jugador y/o la máquina, además al terminar cada uno de los juegos pregunta si el 
  jugador desea comenzar una nueva partida.

En el siguiente diagrama se muestran los métodos más relevantes y las relaciones entre clases:

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/mvc-supervisor-controller/doc/mastermind.model.puml)

## mastermind.controller vs mastermind.view

La **Vista**[[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) consisten en cualquier representación 
de información, como un gráfico, diagrama o tabla. Son posibles varias vistas de la misma información, como un gráfico de 
barras para la gestión y una vista tabular para los contables. 

The **Controller**[[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) acepta la entrada y la convierte
en comandos para el modelo o la vista.

Cada clase del modelo tiene asociado su propio controlador, que a su vez trabajará con una vista determinada. Por ahora las
únicas vistas necesarias han sido las requeridas para mostrar datos por pantalla y recoger las respuestas del jugador
mediante el teclado.

Las clases más relevantes de esta asociación son:
* [MasterMindController](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/MasterMindController.java): 
  Inicia el juego cada vez que fuera necesario y usa la vista asociada [MasterMainView](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/view/MasterMindView.java)
  para interaccionar con la consola y el jugador.
* [GameContoller](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/GameController.java): desarrolla
  las iteraciones que van dando la jugador la posibilidad de descubir el código que se ha calculado. Usa la vista  [GameView](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/view/GameView.java)
  para interaccionar con la consola y el jugador. 

En el siguiente diagrama se muestran los métodos más relevantes y las relaciones entre clases:

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/mvc-supervisor-controller/doc/mastermind.controller.puml)

