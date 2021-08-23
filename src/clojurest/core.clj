(ns clojurest.core
  (:gen-class)
  (:require [clojure.data.json :as json]
            [org.httpkit.server :as httpkit]))

(defn respond [& body]
  (prn "response" body)
  {:status 200
   :body body
   :headers {"Access-Control-Allow-Origin"
             "*"
             "Access-Control-Allow-Methods"
             "*"
             "Access-Control-Allow-Headers"
             "*"}})

(defn test-service []
  (prn "testing"))

(defn secret-service [method body]
  (case method
    :get
    (respond (slurp "resources/secret"))
    :put
    (do (spit "resources/secret" body)
        (respond))
    {:status 405}))

(defn handler [request]
  (let [method (:request-method request)
        body (if (some? (:body request))
               (-> (:body request)
                   slurp
                   json/read-str)
               "")
        uri (:uri request)]
    (prn "request" method body uri)
    (case method
      :options
      (respond)
      (case uri
        "/secret"
        (secret-service method body)
        "/test"
        (test-service)
        (respond "Hello, World!")))))

(defn -main [& args]
  (httpkit/run-server handler))
