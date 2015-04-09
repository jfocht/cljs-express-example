(defproject cljs-express-example "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3126"]]

  :node-dependencies [[source-map-support "0.2.10"]
                      [basic-auth "1.0.0"]
                      [express "4.12.3"]]

  :plugins [[lein-cljsbuild "1.0.5"]
            [com.cemerick/clojurescript.test "0.3.3"]
            [lein-npm "0.5.0"]]

  :source-paths ["src"]

  :cljsbuild {
    :builds [{:id "server"
              :source-paths ["src/cljs_express_example"]
              :compiler {
                :main cljs-express-example.core
                :output-to "build/server/cljs_express_example/example.js"
                :output-dir "build/server/cljs_express_example"
                :optimizations :simple
                :target :nodejs
                :cache-analysis true
                :source-map "build/server/cljs_express_example/example.js.map" }}
             {:id "test"
              :source-paths ["src" "test"]
              :compiler {:output-to "build/test/test.js"
                         :output-dir "build/test"
                         :optimizations :none
                         :target :nodejs
                         :hashbang false
                         :source-map "build/test/test.js.map"}}]
    :test-commands {"tests"  ["node"  "test/bin/runner-none.js"  "build/test"  "build/test/test.js"]}
  }

  :aliases {"auto-test" ["do" "clean," "cljsbuild" "auto" "test"]}

  :clean-targets ["build"]
)
