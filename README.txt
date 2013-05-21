MongoDB and SpringSVM HTTP GET logging demostration.

Author: Juan Camilo Tafurth Zuñiga

This programas sets up a root URL that receives a parameter, eg. /parameter and creates a collection in a local MongoDB database (default: localhost 27017). The first time a given parameter is passed the collection will be created and every subsequent time the request will be logged as a document in the collection.

To call the log the user must enter the following URL in the explorer: /parameter?inspect.