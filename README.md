# clojuREST
_A minimal REST API written in Clojure_

Unlike most Clojure backend projects, clojuREST only uses one 3rd party library to set up a web server _with_ routing. Typically you see a lot of projects using [Ring](https://github.com/ring-clojure/ring) and [Compojure](https://github.com/weavejester/compojure) which are great and really streamline server development but they introduce a lot of dependencies. clojuREST gets around this by using [http-kit](https://github.com/http-kit/http-kit). You have to write a little extra code but in return you get more control over data flow.

## Disclaimer
clojuREST is not a library, nor does it have any use as standalone software. It’s merely a proof of concept that someone may use to create an API with minimal dependencies. Feel free to download and modify as needed.

## Services
By default, http-kit starts a server at `localhost:8090`. Aside from the index `/`(which returns `Hello, World!`), there are two built-in web services:

1. Test
	- endpoint: `/test` 
	- supported methods: any
	- accepted body: any
	- prints `testing` to the commandline
	- used for verifying other services (which may fail if written incorrectly)

2. Secret
	- endpoint: `/secret`
	- supported methods: **GET**, **PUT**
	- accepted body: any JSON-formatted data
	- reads/writes the file [“secret”](resources/secret)
	- a crude example of a stateful service
  
These are handled by the functions `test-service` and `secret-service` respectively.

## Functions
In addition to the services, there are two other functions in  [core.clj](src/clojurest/core.clj):

1. `respond`
	- takes an optional body to return in an HTTP response
	- prints the body to the commandline
	- returns the body, HTTP status 200, and some headers used by [cljs-ajax](https://github.com/JulianBirch/cljs-ajax)(another minimalist library I use)

2. `handler`
	- a callback used by the http-kit server
	- captures the received request in the parameter `request`
	- parses the body using `clojure.data.json/read-str`
	- handles routing with nested `case` expressions

# Building

1. Get [Leiningen](https://github.com/technomancy/leiningen)
2. `cd` to project directory
3. Run*
	- Run with Leiningen:  `lein run`
	- Create a jar ( `lein uberjar` ) and run it with java (`java -jar target/clojurest-0.0.1-standalone.jar`
  
*you may need admin privileges to allow the application through your firewall
