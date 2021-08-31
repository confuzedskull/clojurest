(defproject clojurest "0.0.2"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.json "1.0.0"]
                 [http-kit "2.5.0"]]
  :main clojurest.core
  :profiles {:uberjar {:aot :all}})
