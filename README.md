# mastermind

I have designed this program following the model-view-controller paradigm and applying some easy rules:

* A Controller can create an instance of this related model.
* A Controller receives while construction its own view. This view will be used to related this controller to all
instances associates to this controller.
* When a controller-A needs some functionality from any other model entity-B it will be request this functionality 
by using the associated controller-B.


