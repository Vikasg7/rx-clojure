(ns rx-clojure.core-test
  (:use clojure.test)
  (:require [rx-clojure.static :as rx]
            [rx-clojure.operators :as op]
            [rx-clojure.functions :as fns])
  (:import  [io.reactivex.rxjava3.core Observable Flowable]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(set! *warn-on-reflection* true)

(def ^Observable a (Observable/fromIterable [1 2 3]))
(def ^Observable b (Observable/fromIterable [4 5 6]))

(-> a
    (op/compose #(-> ^Observable % 
                     (op/concatWith b)))
    (op/subscribe println))

;; ;; (macroexpand-1 `(-> Observable
;; ;;     (rx/combineLatest [a b] vec 3)
;; ;;     (op/subscribe println)))

;; (-> Observable
;;     (rx/concat (Observable/just a b) 5)
;;     (op/subscribe println))

;; (-> (Observable/combineLatest [a b] (fns/function [x] (vec x)) 5)
;;     (op/subscribe println))

;; (defn ^Observable test-obs []
;;   (-> Observable (rx/create (fn [^ObservableEmitter e]
;;                               (loop [i 0]
;;                                 (when (and (< i 10) (not (.isDisposed e)))
;;                                   (.onNext e i)
;;                                   (recur (inc i))))
;;                               (.onComplete e)
;;                               (fn [] (println "I am Disposed!"))))))

;; (-> Observable (rx/just 1 2 3 4 5 6)
;;     (.filter (fns/predicate even?))
;;     (op/subscribe println))

;; (-> Observable (rx/error (Error. "Hello"))
;;     (op/subscribe println println))

;; (-> Observable (rx/wrap (fn [e] ())))

;; (run-tests)