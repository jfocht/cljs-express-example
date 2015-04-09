(ns cljs-express-example.core
  (:require [cljs.nodejs :as node]))

(node/enable-util-print!)

(def express (node/require "express"))

(defn say-hello! [req res]
  (.send res "Hello world!"))

(defn greet-sender! [req res]
  (let [query (.-query req)
        greeting (.-greeting query)]
    (.send res greeting)))

(defn -main []
  (let [app (express)]
    (.get app "/" say-hello!)
    (.post app "/" greet-sender!)
    (.listen app 3000 #(println "Server started on port 3000"))))


(set! *main-cli-fn* -main)
