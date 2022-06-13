(ns rx-clojure.core-test
  (:use clojure.test)
  (:require [rx-clojure.statics :as rx]
            [rx-clojure.operators :as op]
            [rx-clojure.functions :as fns])
  (:import  [io.reactivex.rxjava3.core Observable Scheduler Flowable ObservableEmitter]
            [io.reactivex.rxjava3.schedulers Schedulers]
            [io.reactivex.rxjava3.disposables Disposable]
            [java.util.concurrent TimeUnit]))

(set! *warn-on-reflection* true)

;; TODO: Write tests

;; Testing cancellable
(def obs (-> Observable (rx/create (fn [^ObservableEmitter e]
                                      (let [f (future (.onNext e 1)
                                                      (Thread/sleep 250)
                                                      (.onNext e 2)
                                                      (Thread/sleep 250)
                                                      (.onNext e 3)
                                                      (Thread/sleep 250)
                                                      (.onComplete e))]
                                      (fn [] (println "Disposing") (future-cancel f))))))) 
;; (op/blockingSubscribe obs #(println "Value" %) identity #(println "Done!"))

(defn repeat-latest-on-interval 
  ([source delay unit]
    (repeat-latest-on-interval source delay unit (Schedulers/io)))
  ([source delay unit ^Scheduler scheduler]
    (let [sub (fn [^ObservableEmitter e]
                (let [dsp (atom (Disposable/empty))
                      nxt (fn nxt [val]
                            (when (not (.isDisposed e))
                              (.dispose ^Disposable @dsp)
                              (reset! dsp (.schedulePeriodicallyDirect scheduler #(.onNext e val) delay delay unit)))
                              (.onNext e val))
                      err (fn err [error]
                            (.dispose ^Disposable @dsp)
                            (.onError e error))
                      com (fn com []
                            (.dispose ^Disposable @dsp)
                            (.onComplete e))]
                (-> source (op/subscribe nxt err com))))]
    (-> Observable (rx/create sub)))))

(-> obs
    (repeat-latest-on-interval 100 TimeUnit/MILLISECONDS)
    (op/takeWhile #(< % 3))
    (op/blockingSubscribe println identity #(println "Done!")))

;; (deftest a-test
;;   (testing "FIXME, I fail."
;;     (is (= 0 1))))

;; (run-tests)
