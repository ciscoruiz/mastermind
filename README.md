# mastermind

El original de este documento esta ubicado [aqui](https://github.com/ciscoruiz/mastermind/blob/distributed/README.md) 
formando parte del proyecto github [MasterMind](https://github.com/ciscoruiz/mastermind/tree/distributed).

El siguiente diagrama muestra la arquitectura del paquetes utilizada para desarrollar las tres versiones diferentes de este programa. El
muy bien conocido [Model-View-Controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) es la
base para este diseño.

Estas son las tres versiones diferentes de este programa MasterMind:
* [MainStandalone](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/MainStandalone.java) y [MasterMindStandalone](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/MainStandalone.java) 
  ejecutan MasterMind como una aplicación todo en uno.
* [MainServer](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/MainServer.java) y [MasterMindServer](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/runtime/MasterMindServer.java) 
  que ejecutan el servidor RMI de MasterMind. Esta versión no tendrá ningún tipo de la consola, pero registrará todos 
  los comandos recibidos desde cliente, que actuará como GUI con el usuario.
* [MainClient](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/MainClient.java) y [MasterMindClient](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/runtime/MasterMindClient.java) 
  actúa como GUI con el usuario del juego y utiliza la interfaz RMI de MasterMind para solicitar todas las operaciones 
  necesarias al servidor RMI. Esta versión mostrará la misma consola que la versión standalone, pero las operaciones se 
  realizan en el Servidor RMI.

El siguiente diagrama muestra las diferentes partes que componen estas aplicaciones y cómo se relaciona para ejecutar 
diferentes versiones de MasterMind, ejecutando partes comunes de código. Las partes que componen estas aplicaciones son:

* El paquete [`mastermind.model`](#mastermindmodel) contiene todas las clases necesarias para jugar Mastermind. [[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
  El componente central del patrón. Es la estructura de datos dinámica de la aplicación, independiente de la interfaz 
  de usuario. Gestiona directamente los datos, la lógica y reglas de la aplicación. Las clases [mastermind.model.Game](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/model/Game.java)
  y [mastermind.model.State](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/model/State.java) 
  podrían considerarse como las clases más importantes para este paquete.

* El paquete [`mastermind.view`](#mastermindview) contiene todas las definiciones abstractas para **Vistas** que interactúan con el Usuario
  y el Controlador.  Hasta ahora, este paquete tiene solo una especialización:
  * El paquete [`mastermind.view,console`](#mastermindviewconsole) contiene todas las vistas para interactuar con el usuario usando la consola y el teclado.

* El paquete [`mastermind.controller`](#mastermindcontroller)  contiene todas las definiciones abstractas para **Controladores** necesarios para interactuar
  las diferentes **Vistas**. [[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) Acepta entrada y
  lo convierte en comandos para el modelo o la vista. El enfoque principal de estas clases es acceder al **Modelo** de la 
  forma requerida por la vista. Hasta ahora su paquete tiene dos especializaciones diferentes:
  * El paquete [mastermind.controller.model](https://github.com/ciscoruiz/mastermind/tree/distributed/src/com/upm/master/mastermind/controller/model) 
    contiene todos los **Controladores** que interactúan con el modelo. El modelo, las clases y los controladores se 
    instancian en la misma aplicación.
  * El paquete [mastermind.controller.rmi](https://github.com/ciscoruiz/mastermind/tree/distributed/src/com/upm/master/mastermind/controller/rmi) 
    contiene todos los **Controladores** que interactúan con el modelo mediante el interfaz RMI. Los controladores se i
    nstancian en la aplicación Cliente, pero las clases del modelo se instancian en la aplicación Server.

* El paquete [`mastermind.runtime`](#mastermindruntime) contiene las diferentes implementaciones para la interfaz [Mastermind](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/MasterMind.java)
  (Cliente, Servidor y Standalone).
 
![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/arquitectura.puml)

# Diagrama de Clases por paquetes

## mastermind.model

El **Modelo** es la estructura de datos dinámica de la aplicación, independiente de la interfaz de usuario. Gestiona 
directamente los datos, la lógica y reglas de la aplicación. Las clases [mastermind.model.Game](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/model/Game.java) 
y [mastermind.model.State](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/model/State.java) 
podrían considerarse como las clases más importantes para este paquete.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.model.puml)

Las clases mostradas en el diagrama son:
* [Game](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/model/Game.java): contiene 
  todos los detalles referentes al juego en curso, como número de reintentos, número máximo de reintentos, etc
* [Configuration](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/model/Configuration.java): 
  establece los parámetros del juego, como las letras válidas para formar los códigos a descubrir, nº de reintentos.
* Mediante el valor de [State](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/model/State.java) 
  se establece el vínculo apropiado entre una Vista y un Controlador. Cada valor de [State](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/model/State.java)
  tendrá asociado un controlador, que recibirá las operaciones de la vista en curso.
* [CodeMaker](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/model/CodeMaker.java): 
  genera el código secreto [Code](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/model/Code.java) 
  que el usuario del juego debe descubrir, además evalúa la calidad de las claves  que el usuario va seleccionando. 
  La evaluación de la clave se devolverá como una instancia de [Response](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/model/Response.java)
  a la que se puede interrogar sobre si el código ha sido descubierto o en qué modo faltan partes por descubrir.
* [ValidFigures](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/model/ValidFigures.java): 
  contiene la lista de caracteres válidos para generar/descubrir la clave secreta.   
* [GameHistoryKeeper](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/model/GameHistoryKeeper.java)
  y [Game.Memento](https://github.com/ciscoruiz/mastermind/blob/ead474cbbe7370310082ae1a40787709aa77bb1d/src/com/upm/master/mastermind/model/Game.java#L9) 
  son las clases usadas para implementar el [patrón memento](https://en.wikipedia.org/wiki/Memento_pattern).

## mastermind.view

La **Vista**[[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) consisten en cualquier representación 
de información, como un gráfico, diagrama o tabla. Son posibles varias vistas de la misma información, como un gráfico de 
barras para la gestión y una vista tabular para los contables. 

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.view.puml)

Para relacionar las vistas con los controladores se emplea el patrón [Visitor](https://en.wikipedia.org/wiki/Visitor_pattern). 

La siguiente secuencia de llamadas visualiza la técnica del doble disparo usada para implementar éste patrón y muestra 
como evolucionan las llamadas desde que se inician en el [ViewsContainer](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/view/ViewsContainer.java) 
hasta que llegan a ser procesadas por el [ConcreteVisitor](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/controller/ControllerVisitor.java) 
correspondiente.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.visitor.puml)

Las vistas mantienen por completo el [principio de sustitución de Liskov](https://en.wikipedia.org/wiki/Liskov_substitution_principle).

### mastermind.view.console

El siguiente diagrama muestras las clases requeridas para interactuar con el usuario mediante la consola y el teclado.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.view.menu.puml)

El componente [PlayConsole](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/view/console/PlayConsole.java) 
es el responsable de presentar las opciones y verificar que el usuario elija una opción válida entre todas las posibles 
opciones que pueden estar activas, ya qye hay algunas opciones de menú como (Undo/Redo) que sólo estarán disponibles si 
el correspondiente [ActivationEvaluator](https://github.com/ciscoruiz/mastermind/blob/ead474cbbe7370310082ae1a40787709aa77bb1d/src/com/upm/master/mastermind/view/console/menu/Command.java#L7)
devuelve **true**.

Las opciones de menú se han implementado siguiente el patrón [Command](https://en.wikipedia.org/wiki/Command_pattern).

## mastermind.controller

The **Controller**[[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) acepta la entrada y lo convierte 
en comandos para el modelo o la vista.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.controller.puml)

En el diagrama de clases se puede apreciar que hay tres partes totalmente definidas. El paquete con las clases abstractas 
que sólo definen las interfaces que serán usadas por el resto de la aplicación, el paquete de controladores que trabajan
directamente sobre las clases del modelo y el paquete de controladores que usan peticiones RMI para acceder a las diferentes
operaciones que se pueden realizar sobre el modelo.

Los controladores del paquete [mastermind.controller.model](https://github.com/ciscoruiz/mastermind/tree/distributed/src/com/upm/master/mastermind/controller/model) 
será usados por las aplicaciones Standalone y por la parte Server de la aplicación distribuida. 

Los controladores del paquete [mastermind.controller.rmi](https://github.com/ciscoruiz/mastermind/tree/distributed/src/com/upm/master/mastermind/controller/rmi) 
serán usados por la parte Cliente de la aplicación distribuida.

## mastermind.runtime

Este paquete contiene las diferentes implementaciones para la interfaz [Mastermind](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/MasterMind.java) 
(Cliente, Servidor y Standalone).

El siguiente diagrama muestra las clases usadas en la aplicación Server y Standalone:

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.runtime.overmodel.puml)

Las clases mostradas en el diagrama son:
* El interface [Mastermind](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/MasterMind.java)
  actúa como [Abstract Factory](https://en.wikipedia.org/wiki/Abstract_factory_pattern#:~:text=The%20abstract%20factory%20pattern%20provides,without%20specifying%20their%20concrete%20classes.)
  para generar los distintos _**Controllers**_ requeridos por la aplicación en curso.
* La clase [MasterMindOverModel](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/runtime/MasterMindOverModel.java) 
  implementa el interface requerido para instanciar los controladores que trabajan directamente sobre el modelo, además 
  instancia las clases requeridas por el modelo,
* La clase [MasterMindStandalone](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/MainStandalone.java)
  especializa su clase base para ejecutar todo el código en un mismo programa. Configura la clase [ControllersContainer](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/controller/ControllersContainer.java) 
  para que el Modelo-Vista-Controlador se desarrolle correctamente.
* La clase [MasterMindServer](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/runtime/MasterMindServer.java)
  especializa su clase base para que las operaciones requeridas se realizen mediante una la interface RMI definida, cada 
  uno de estos métodos tendrá que hacer uso de un correspondiente controller que será el que actúe sobre el modelo.
  
El siguiente diagrama muestras las clases usadas en la aplicación Client:

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.runtime.client.puml)

La clase [MasterMindClient](https://github.com/ciscoruiz/mastermind/blob/distributed/src/com/upm/master/mastermind/runtime/MasterMindClient.java) 
implementa el interface requerido para instanciar los controladores que trabajan sobre el cliente RMI, además instancia 
y configura el _**ViewsContainer**_ y el _**ControllersContainer**_.
  
Los controladores usados por este módulo transfieren todas sus operaciones al interfaz RMI que han recibido en su constructor.