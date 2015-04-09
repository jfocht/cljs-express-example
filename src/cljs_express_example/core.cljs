(ns cljs-express-example.core
  (:require [cljs.nodejs :as node]))

(node/enable-util-print!)

(def express (node/require "express"))

; Authorization

(def basic-auth (node/require "basic-auth"))

(defn unauthorized [res]
  (.set res "WWW-Authenticate" "Basic realm=Authorization Required")
  (.sendStatus res 401))

(defn amatch [obj fields values]
  (and (not (nil? obj))
       (every? true? (map #(= (aget obj %) %) fields values))))

(defn authenticate [user pass]
  (fn require-auth [req res next-fn]
    (let [creds (basic-auth req)]
      (cond (amatch creds ["name" "pass"] [user pass]) (next-fn)
            :else (unauthorized res)))))

; Endpoints

(defn say-hello! [req res]
  (.send res "Hello world!"))

(defn greet-sender! [req res]
  (let [greeting (-> req (aget "query") (aget "greeting"))]
    (.send res greeting)))

; App

(defn -main []
  (let [app (express)]
    (.all app "*" (authenticate "foo" "bar"))
    (.get app "/" say-hello!)
    (.post app "/" greet-sender!)
    (.listen app 3000 #(println "Server started on port 3000"))))

(set! *main-cli-fn* -main)
