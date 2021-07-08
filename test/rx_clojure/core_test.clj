(ns rx-clojure.core-test
  (:use clojure.test)
  (:require [rx-clojure.statics :as rx]
            [rx-clojure.operators :as op]
            [rx-clojure.functions :as fns])
  (:import  [io.reactivex.rxjava3.core Observable Flowable]))

(set! *warn-on-reflection* true)

;; TODO: Write tests

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(run-tests)
