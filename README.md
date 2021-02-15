# mastermind

El original de este documento esta ubicado [aqui](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/README.md) 
formando parte del proyecto github [MasterMind](https://github.com/ciscoruiz/mastermind/tree/mvc-supervisor-controller).

El siguiente diagrama muestra la arquitectura del paquetes utilizada para desarrollar las tres versiones diferentes de este programa. El
muy bien conocido [Model-View-Controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) es la
base para este diseño.


* El paquete [`mastermind.model`](#mastermindmodel) contiene todas las clases necesarias para jugar Mastermind. [[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
  El componente central del patrón. Es la estructura de datos dinámica de la aplicación, independiente de la interfaz 
  de usuario. Gestiona directamente los datos, la lógica y reglas de la aplicación. Las clases [mastermind.model.Game](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/Game.java)
  y [mastermind.model.Configuration](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/Configuration.java) 
  podrían considerarse como las clases más importantes para este paquete.

* El paquete [`mastermind.view`](#mastermindview) contiene todas las definiciones abstractas para **Vistas** que interactúan con el Usuario
  y el Controlador.  Hasta ahora, este paquete tiene solo una especialización:
  * El paquete [`mastermind.view.console`](#mastermindviewconsole) contiene todas las vistas para interactuar con el usuario usando la consola y el teclado.

* El paquete [`mastermind.controller`](#mastermindcontroller) contiene todas las definiciones de los **Controladores** necesarios para interactuar con
  las diferentes **Vistas**. [[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) Acepta entrada y
  lo convierte en comandos para el modelo o la vista. El enfoque principal de estas clases es acceder al **Modelo** de la 
  forma requerida por la vista. 
 
![system overview](https://raw.githubusercontent.com/ciscoruiz/mastermind/mvc-supervisor-controller/doc/arquitectura.puml)

# Diagrama de Clases por paquetes

## mastermind.model

El **Modelo** es la estructura de datos dinámica de la aplicación, independiente de la interfaz de usuario. Gestiona 
directamente los datos, la lógica y reglas de la aplicación. Las clases [mastermind.model.Game](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/Game.java) 
y [mastermind.model.State](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/State.java) 
podrían considerarse como las clases más importantes para este paquete.

Las clases contenidas en este paquete son las siguientes:
* [Game](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/Game.java): contiene
  todos los detalles referentes al juego en curso, como número de reintentos, número máximo de reintentos, etc
* [Configuration](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/Configuration.java):
  establece los parámetros del juego, como las letras válidas para formar los códigos a descubrir, nº de reintentos.
* Mediante el valor de [State](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/State.java)
  se establece el vínculo apropiado entre una Vista y un Controlador. Cada valor de [State](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/State.java)
  tendrá asociado un controlador, que recibirá las operaciones de la vista en curso.
* [CodeMaker](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/CodeMaker.java):
  genera el código secreto [Code](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/Code.java)
  que el usuario del juego debe descubrir, además evalúa la calidad de las claves  que el usuario va seleccionando.
  La evaluación de la clave se devolverá como una instancia de [Response](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/Response.java)
  a la que se puede interrogar sobre si el código ha sido descubierto o en qué modo faltan partes por descubrir.
* [ValidFigures](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/ValidFigures.java):
  contiene la lista de caracteres válidos para generar/descubrir la clave secreta.

En el siguiente diagrama se muestran los métodos más relevantes y las relaciones entre clases:

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/mvc-supervisor-controller/doc/mastermind.model.puml)

## mastermind.controller vs mastermind.view

La **Vista**[[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) consisten en cualquier representación 
de información, como un gráfico, diagrama o tabla. Son posibles varias vistas de la misma información, como un gráfico de 
barras para la gestión y una vista tabular para los contables. 

### mastermind.view.console

Una vez que el [ControllerVisitorConsole](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/view/console/ControllerVisitorConsole.java) 
actuando como **ConcreteVisitor** recibe la petición de acceso, éste se progresa hacia un manejador dedicado que será el 
que haga de intermediario entre el usuario y el UI presentado en pantalla. 

El componente [PlayConsole](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/view/console/PlayConsole.java)
es el responsable de invocar al [MenuConsole](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/view/console/menu/MenuConsole.java)
, éste es el encargado de gestionar las opciones y verificar que el usuario elija una opción válida entre todas las posibles
opciones que pueden estar activas. Hay algunas opciones de menú como (Undo/Redo) que sólo estarán disponibles si el
correspondiente [ActivationEvaluator](https://github.com/ciscoruiz/mastermind/blob/ead474cbbe7370310082ae1a40787709aa77bb1d/src/com/upm/master/mastermind/view/console/menu/Command.java#L7)
devuelve **true**.

El siguiente diagrama muestras las clases requeridas para interactuar con el usuario mediante la consola y el teclado.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/mvc-supervisor-controller/doc/mastermind.view.menu.puml)

Las opciones de menú se han implementado siguiente el patrón [Command](https://en.wikipedia.org/wiki/Command_pattern).

## mastermind.controller

The **Controller**[[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) acepta la entrada y la convierte 
en comandos para el modelo o la vista.

En el diagrama de clases se puede apreciar que hay tres partes totalmente definidas. El paquete con las clases abstractas
que sólo definen las interfaces que serán usadas por el resto de la aplicación, el paquete de controladores que trabajan
directamente sobre las clases del modelo y el paquete de controladores que usan peticiones RMI para acceder a las diferentes
operaciones que se pueden realizar sobre el modelo.

Las distintas familias de controladores se consiguen mediante la clase [ControllerAbstractFactory](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/ControllerAbstractFactory.java)
que implementa el patrón [Abstract Factory](https://en.wikipedia.org/wiki/Abstract_factory_pattern#:~:text=The%20abstract%20factory%20pattern%20provides,without%20specifying%20their%20concrete%20classes.)
para facilitar la instanciación de los distintos controladores necesarios para cada una de las aplicaciones que podemos
ejecutar.

Las clases contenidas en este paquete
* [Controller](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/Controller.java)
  es una de las piezas fundamentales que facilita el desarrollo de la aplicación. En el patrón [Visitor](https://en.wikipedia.org/wiki/Visitor_pattern)
  actúa como *Element* del sistema de mensajes. Todos los **Controllers** deberán implementar el método 'accept(ControllerVisitor)'.
* [ControllerVisitor](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/ControllerVisitor.java)
intertace que deben implementar los **Visitors** para gestionar las operaciones requeridas.
* [ControllersContainer](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/ControllersContainer.java)
establece una asociación unívoca entre el [State](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/State.java)
  de la aplicación y el [Controller](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/Controller.java)
  que se debe usar en cada caso. Sólo es usado por la aplicación **Client** y la aplicación **Standalone**. Para instanciar
  los objetos que tiene que guardar hará uso de una [ControllerAbstractFactory](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/ControllerAbstractFactory.java)
  que recibirá como parámetro.
* [ControllerAbstractFactory](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/ControllerAbstractFactory.java)
  interfaz genérica que permitirá conseguir los distintos tipos de controladores requeridos por la aplicación, pero sin 
  conocer el tipo particular de controlador. 

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/mvc-supervisor-controller/doc/mastermind.controller.puml)

### mastermind.controller.model

Los controladores del paquete [mastermind.controller.model](https://github.com/ciscoruiz/mastermind/tree/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/model)
será usados por las aplicaciones Standalone y por la parte Server de la aplicación distribuida.

La particularidad que tiene los controladores de este paquete es que heredan de la clase base [ModelController](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/model/ModelController.java)
que tiene acceso a los objetos más relevantes del [Modelo](https://github.com/ciscoruiz/mastermind/tree/mvc-supervisor-controller/src/com/upm/master/mastermind/model)
y por tanto sabe cómo cambiar de estado usando el [State](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/model/State.java).

Especializa la factoría de controladores mediante la clase [ControllerModelFactory](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/model/ControllerModelFactory.java)
que instancia cada uno los tipos de controlador requerido en cada método de la factoría.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/mvc-supervisor-controller/doc/mastermind.controller.model.puml)

### mastermind.controller.rmi

Los controladores del paquete [mastermind.controller.rmi](https://github.com/ciscoruiz/mastermind/tree/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/rmi)
serán usados por la parte Cliente de la aplicación distribuida.

La particularidad que tiene los controladores de este paquete es que heredan de la clase base [RmiController](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/rmi/RmiController.java)
que tiene acceso la interfaz [RMI-cliente](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/rmi/MasterMindOperations.java) 
que será usada para enviar las peticiones requeridas para desarrollar el juevo.

Especializa la factoría de controladores mediante la clase [ControllerRmiFactory](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/rmi/ControllerRmiFactory.java) 
que instancia cada uno los tipos de controlador requerido en cada método de la factoría.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/mvc-supervisor-controller/doc/mastermind.controller.rmi.puml)


## mastermind.runtime

### Clases que trabajan sobre el modelo
Este paquete contiene las diferentes implementaciones para la interfaz [Mastermind](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/MasterMind.java) 
(Cliente, Servidor y Standalone).

El siguiente diagrama muestra las clases usadas en la aplicación Server y Standalone:
* El interface [Mastermind](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/MasterMind.java)
  establece cómo se realiza la ejecución del juego.
* La clase [MasterMindOverModel](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/runtime/MasterMindOverModel.java)
  implementa el interface requerido para instanciar los controladores que trabajan directamente sobre el modelo, además
  instancia las clases requeridas por el modelo,
* La clase [MasterMindStandalone](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/runtime/MasterMindStandalone.java)
  especializa su clase base para ejecutar todo el código en un mismo programa. Configura la clase [ControllersContainer](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/ControllersContainer.java)
  para que el Modelo-Vista-Controlador se desarrolle correctamente.
* La clase [MasterMindServer](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/runtime/MasterMindServer.java)
  especializa su clase base para que las operaciones requeridas se realizen mediante una la interface RMI definida, cada
  uno de estos métodos tendrá que hacer uso de un correspondiente controller que será el que actúe sobre el modelo.

Ambas versiones de la aplicación usan los mismos [ControllerVisitor](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/ControllerVisitor.java) 
y [ControllerVisitor](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/ControllerVisitor.java) 
ya que las vistas tienen que aplicar las mismas operaciones sobre los mismos controladores, lo único que cambia de una versión 
a otra es la forma de seleccionar el controlador.
* En la versión Standalone el controlador se elije en base al estado en el que está la aplicación.
* En la versión Server el controlador viene fijado por cómo se ha tenido que implementar el método concreto de la interfaz RMI.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/mvc-supervisor-controller/doc/mastermind.runtime.overmodel.puml)

### Clases que trabajan sobre el interfaz RMI

El siguiente diagrama muestras las clases usadas en la aplicación Client.

La clase [MasterMindClient](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/runtime/MasterMindClient.java)
implementa el interface requerido para instanciar los controladores que trabajan sobre el cliente RMI, además instancia
y establece el [ControllerVisitor](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/ControllerVisitor.java)
y el [ControllersContainer](https://github.com/ciscoruiz/mastermind/blob/mvc-supervisor-controller/src/com/upm/master/mastermind/controller/ControllersContainer.java)
que recibe una factoría de controladores que realizan sus operaciones mediante las llamadas a la interfaz RMI.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/mvc-supervisor-controller/doc/mastermind.runtime.client.puml)

Los controladores usados por este módulo transfieren todas sus operaciones al interfaz RMI que han recibido en su constructor.