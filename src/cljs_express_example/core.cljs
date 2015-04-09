(ns cljs-express-example.core
  (:require [cljs.nodejs :as node]))

(node/enable-util-print!)

(def express (node/require "express"))

; Authorization

(def basic-auth (node/require "basic-auth"))

(defn unauthorized [res]
  (.set res "WWW-Authenticate" "Basic realm=Authorization Required")
  (.sendStatus res 401))

(defn match-obj [obj fields values]
  (and (not (nil? obj))
       (every? true? (map (fn [k v] (= (aget obj k) v)) fields values))))

(defn authenticate [user pass]
  (fn [req res next-fn]
    (if (match-obj (basic-auth req) ["name" "pass"] [user pass])
      (next-fn)
      (unauthorized res))))

; Endpoints

(defn say-hello! [req res]
  (.send res "Hello world!"))

(defn parse-greeting [req]
  (.. req -query -greeting))

(defn validate-greeting! [req res callback]
   (if (parse-greeting req)
     (callback)
     (-> res
         (.status 400)
         (.send "Please provide a greeting"))))

(defn greet-sender! [req res]
    (.send res (parse-greeting req)))

; App

(defn -main []
  (let [app (express)]
    (.all app "*" (authenticate "foo" "bar"))
    (.get app "/" say-hello!)
    (.post app "/" validate-greeting!)
    (.post app "/" greet-sender!)
    (.listen app 3000 #(println "Server started on port 3000"))))

(set! *main-cli-fn* -main)
