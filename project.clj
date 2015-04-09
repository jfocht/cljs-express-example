(defproject cljs-express-example "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3126"]]

  :node-dependencies [[source-map-support "0.2.10"]
                      [basic-auth "1.0.0"]
                      [express "4.12.3"]]

  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-npm "0.5.0"]]

  :source-paths ["src"]
  
  :cljsbuild {
    :builds [{:id "server"
              :source-paths ["src/cljs_express_example"]
              :compiler {
                :main cljs-express-example.core
                :output-to "out/cljs_express_example/example.js"
                :output-dir "out/cljs_express_example"
                :optimizations :simple
                :target :nodejs
                :cache-analysis true
                :source-map "out/cljs_express_example/example.js.map" }}
             ]})
