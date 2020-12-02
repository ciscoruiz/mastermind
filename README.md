# mastermind

El siguiente diagrama muestra la arquitectura del paquetes utilizada para desarrollar las tres versiones diferentes de este programa. El
muy bien conocido [Model-View-Controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) es la
base para este diseño.

Estas son las tres versiones diferentes de este programa MasterMind:
* **MainStandalone** y **MasterMindStandalone** ejecutan MasterMind como una aplicación todo en uno.
* **MainServer** y **MasterMindServer** que ejecutan el servidor RMI de MasterMind. Esta versión no tendrá ningún tipo 
  de la consola, pero registrará todos los comandos recibidos desde cliente, que actuará como GUI con el usuario.
* **MainClient** y **MasterMindClient** actúa como GUI con el usuario del juego y utiliza la interfaz RMI de MasterMind 
  para solicitar todas las operaciones necesarias al servidor RMI. Esta versión mostrará la misma consola que la versión 
  standalone, pero las operaciones se realizan en el Servidor RMI.

El siguiente diagrama muestra las diferentes partes que componen estas aplicaciones y cómo se relaciona para ejecutar 
diferentes versiones de MasterMind, ejecutando partes comunes de código. Las partes que componen estas aplicaciones son:

* El paquete [`mastermind.model`](#mastermindmodel) contiene todas las clases necesarias para jugar Mastermind. [[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
  El componente central del patrón. Es la estructura de datos dinámica de la aplicación, independiente de la interfaz 
  de usuario. Gestiona directamente los datos, la lógica y reglas de la aplicación. Las clases _**mastermind.model.Game**_
  y _ **mastermind.model.State**_ podrían considerarse como las clases más importantes para este paquete.

* El paquete [`mastermind.view`](#mastermindview) contiene todas las definiciones abstractas para **Vistas** que interactúan con el Usuario
  y el Controlador.  Hasta ahora, este paquete tiene solo una especialización:
  * El paquete _**mastermind.view.console**_ contiene todas las vistas para interactuar con el usuario usando la consola y el teclado.

* El paquete [`mastermind.controller`](#mastermindcontroller)  contiene todas las definiciones abstractas para **Controladores** necesarios para interactuar
  las diferentes **Vistas**. [[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) Acepta entrada y
  lo convierte en comandos para el modelo o la vista. El enfoque principal de estas clases es acceder al **Modelo** de la 
  forma requerida por la vista. Hasta ahora su paquete tiene dos especializaciones diferentes:
  * El paquete _**mastermind.controller.model**_ contiene todos los **Controladores** que interactúan con el modelo. El modelo,
    las clases y los controladores se instancian en la misma aplicación.
  * El paquete _**mastermind.controller.rmi**_ contiene todos los **Controladores** que interactúan con el modelo mediante el
    interfaz RMI. Los controladores se instancian en la aplicación Cliente, pero las clases del modelo se instancia en la
    aplicación Servidor.

* El paquete [`mastermind.runtime`](#mastermindruntime)  contiene las diferentes implementaciones para la interfaz _**Mastermind**_ (Cliente, Servidor y Standalone).
 
![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/arquitectura.puml)

# Diagrama de Clases por paquetes

## mastermind.model

El **Modelo** es la estructura de datos dinámica de la aplicación, independiente de la interfaz de usuario. Gestiona directamente los
datos, la lógica y reglas de la aplicación. Las clases _**mastermind.model.Game**_
y _ **mastermind.model.State**_ podrían considerarse como las clases más importantes para este paquete.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.model.puml)

Las clases mostradas en el diagrama son:
* _**Game**_: contiene todos los detalles referentes al juego en curso, como número de reintentos, número máximo de reintentos, etc
* _**Configuration**_: establece los parámetros del juego, como las letras válidas para formar los códigos a descubrir, nº de reintentos.
* Mediante el valor de _**State**_ se establece el vínculo apropiado entre una Vista y un Controlador. Cada valor de _**State*_ tendrá asociado
un controlador, que recibirá las operaciones de la vista en curso.
* _**CodeMaker**_: genera el código secreto (_**Code**_) que el usuario del juego debe descubrir, además evalúa la calidad de las claves
que el usuario va seleccionando. La evaluación de la clave se devolverá como una instancia de _**Response**_.
* _**ValidFigures**_: contiene la lista de caracteres válidos para generar/descubrir la clave secreta.   
* _**GameHistoryKeeper**_ y _**Game.Memento**_ son las clases usadas para implementar el [patrón memento](https://en.wikipedia.org/wiki/Memento_pattern).

## mastermind.view

La **Vista**[[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) consisten en cualquier representación 
de información, como un gráfico, diagrama o tabla. Son posibles varias vistas de la misma información, como un gráfico de 
barras para la gestión y una vista tabular para los contables. 

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.view.puml)

Para relacionar las vistas con los controladores se emplea el patrón [Visitor](https://en.wikipedia.org/wiki/Visitor_pattern). 

La siguiente secuencia de llamadas visualiza la técnica del doble disparo usada para implementar éste patrón y muestra 
como evolucionan las llamadas desde que se inician en el _**ViewContainer**_ hasta que llegan a ser procesadas por _**ConcreteVisitor**_ correspondiente.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.visitor.puml)

Las vistas mantienen por completo el [principio de sustitución de Liskov](https://en.wikipedia.org/wiki/Liskov_substitution_principle).

### mastermind.view.menu

La vista _**PlayConsole**_ es la responsable de presentar las opciones y verificar que el usuario elije una opción válida entre todas las
posible opciones que pueden estar activas.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.view.menu.puml)

Hay algunas opciones de menú como (Undo/Redo) que sólo estarán disponibles si el correspondiente _**ActivationEvaluator**_ devuelve **true**.

## mastermind.controller

The **Controller**[[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) acepta la entrada y lo convierte 
en comandos para el modelo o la vista.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.controller.puml)


