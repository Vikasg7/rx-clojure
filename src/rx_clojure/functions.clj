(ns rx-clojure.functions
   (:import [io.reactivex.rxjava3.functions Consumer
                                            Function
                                            Action
                                            Predicate
                                            BiConsumer
                                            BiFunction
                                            BiPredicate
                                            Supplier
                                            Cancellable
                                            BooleanSupplier
                                            LongConsumer]
            [io.reactivex.rxjava3.core  CompletableSource
                                        MaybeSource
                                        ObservableSource
                                        SingleSource]
            [org.reactivestreams  Publisher]))

(defn- truthy? [x]
  (if x true false))

(defmacro callable [f]
  `(reify Callable (call [_#] (~f))))

(defmacro runnable [f]
  `(reify Runnable (run [_#] (~f))))

(defmacro consumer [f]
  `(reify Consumer (accept [_# a#] (~f a#))))

(defmacro function [f]
  `(reify Function (apply [_# a#] (~f a#))))

(defmacro action [f]
  `(reify Action (run [_#] (~f))))

(defmacro predicate [f]
  `(reify Predicate (test [_# a#] (truthy? (~f a#)))))

(defmacro biConsumer [f]
  `(reify BiConsumer (accept [_# a# b#] (~f a# b#))))

(defmacro biFunction [f]
  `(reify BiFunction (apply [_# a# b#] (~f a# b#))))

(defmacro biPredicate [f]
  `(reify BiPredicate (test [_# a# b#] (truthy? (~f a# b#)))))

(defmacro supplier [f]
  `(reify Supplier (get [_#] (~f))))

(defmacro cancellable [f]
  `(reify Cancellable (cancel [_#] (~f))))

(defmacro booleanSupplier [f]
  `(reify BooleanSupplier (getAsBoolean [_#] (truthy? (~f)))))

(defmacro onSubscribe [klass f]
  (let [method `(subscribe [_# e#] 
                  (let [cancel# (~f e#)]
                  (when (ifn? cancel#) (.setCancellable e# (cancellable cancel#)))))
        interface `~(symbol (str "io.reactivex.rxjava3.core." klass "OnSubscribe"))]
  `(reify ~interface ~method)))

(defmacro source [klass f]
  (let [method    `(subscribe [_# e#] (~f e#))
        interface `~(symbol (str "io.reactivex.rxjava3.core." klass "Source"))]
  `(reify ~interface ~method)))

(defmacro transformer [klass f]
  (let [method    `(apply [_# e#] (~f e#))
        interface `~(symbol (str "io.reactivex.rxjava3.core." klass "Transformer"))]
  `(reify ~interface ~method)))

(defmacro publisher [f]
  `(reify Publisher (subscribe [_# s#] (~f s#))))

(defmacro longConsumer [f]
  `(reify LongConsumer (accept [_# a#] (~f a#))))
