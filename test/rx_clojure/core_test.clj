(ns rx-clojure.core-test
  (:use clojure.test)
  (:require [rx-clojure.static :as rx]
            [rx-clojure.operators :as rxop]
            [rx-clojure.functions :as fns])
  (:import  [io.reactivex.rxjava3.core Observable Flowable]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(set! *warn-on-reflection* true)

(def a (Observable/fromIterable [1 2 3]))
(def b (Observable/fromIterable [4 5 6]))

(-> Observable
    (rx/zip [a b] vec)
    (rxop/subscribe println))

;; ;; (macroexpand-1 `(-> Observable
;; ;;     (rx/combineLatest [a b] vec 3)
;; ;;     (rxop/subscribe println)))

;; (-> Observable
;;     (rx/concat (Observable/just a b) 5)
;;     (rxop/subscribe println))

;; (-> (Observable/combineLatest [a b] (fns/function [x] (vec x)) 5)
;;     (rxop/subscribe println))

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
;;     (rxop/subscribe println))

;; (-> Observable (rx/error (Error. "Hello"))
;;     (rxop/subscribe println println))

;; (-> Observable (rx/wrap (fn [e] ())))

;; (run-tests)