(ns jfocht.cljs-express-example.tests
  (:require-macros [cemerick.cljs.test
                    :refer (is deftest with-test run-tests testing test-var)])
  (:require [cemerick.cljs.test :as t]
            [cljs-express-example.core :as c ]))

(deftest greeting-parsed-correctly
  (let [req (js-obj "query" (js-obj "greeting" "hello"))]
   (is (= "hello" (c/parse-greeting req)))))

;;; needed to use the :target :nodejs
(set! *main-cli-fn* #())              ;;  <---------------
