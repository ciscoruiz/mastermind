# mastermind

Next diagram shows the design package architecture used to develop the three different version of this program. The 
very well know [Model-View-Controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) is the
base for this design. 

* **MainStandalone** and **MasterMindStandalone** which execute the MasterMind as an all-in-one application.
* **MainServer** and **MasterMindServer** which execute the RMI-Server of MasterMind. This version will not have any kind
of console, but it will log all commands received from the client.
* **MainClient** and **MasterMindClient** which uses the RMI-interface of MasterMind to request all needed operations to
the RMI-Server. This version will show the same console that standalone version, but operations are performed into the
RMI-Server.  

The different parts that compose these applications and how it relates to run different versions
of MasterMind running common parts of the code. Parts which composes these applications are:

* The _**mastermind.runtime**_ package contains the different implementations for the interface _**Mastermind**_ 
(Client, Server and Standalone).

* The _**mastermind.model**_ package contains all classes needed to play Mastermind. [[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
The central component of the pattern. It is the application's dynamic data structure, independent of the user interface. It directly manages the data, logic 
and rules of the application.Classes _**mastermind.model.Game**_ 
and _**mastermind.model.State**_ could be considered as the more important classes for this package.

* The _**mastermind.controller**_ package contains all abstract definitions for **Controllers** needed to interact with 
the different **Views**. [[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) Accepts input and 
converts it to commands for the model or vieW. The main focus of these classes is to access the **Model** in the way required
for views. So far his package has two different specializations: 
    * The _**mastermind.controller.model**_ package contains all **Controllers** that interact with the model. The Model 
    classes and the controllers are instantiate on the same application.
    * The _**mastermind.controller.rmi**_ package contains all **Controllers** that interact with the model by using the
    RMI-interface. The controllers are instantiated in the Client application but the model are defined in the Server application.
    
* The _**mastermind.view**_ package contains all abstract definitions for **Views** that interact with the User and the Controller.
So far this package has only one specialization:
    * The _**mastermind.view.console**_ package contains all view to interact with the user by using te console and the keyboard.
  
![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/arquitectura.puml)

# Class diagrams

## mastermind.model

The **Model** [[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) are the central component of the 
pattern. It is the application's dynamic data structure, independent of the user interface. It directly manages the data, 
logic and rules of the application.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.model.puml)

## mastermind.view

The **View** [[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) enables any representation of 
information such as a chart, diagram or table. Multiple views of the same information are possible, such as a bar chart 
for management and a tabular view for accountants.

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.view.puml)

These views will work with the interface of the different **controllers**, this way they keep the [Liskov substitution principle](https://en.wikipedia.org/wiki/Liskov_substitution_principle)
in the best way.

## mastermind.view

The **Controller** [[1]](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) accepts input and converts
it to commands for the model or view. 

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ciscoruiz/mastermind/distributed/doc/mastermind.controller.puml)


