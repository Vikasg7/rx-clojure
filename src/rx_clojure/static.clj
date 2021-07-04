(ns rx-clojure.static
  (:refer-clojure :exclude [concat empty merge range])
  (:require [rx-clojure.functions :as fns]))

(defmacro amb [klass srcs]
  `(. ~klass amb ~srcs))

(defmacro bufferSize [klass]
  `(. ~klass bufferSize))

(defmacro combineLatest
  ([klass srcs combiner]
    `(. ~klass combineLatest ~srcs (fns/function ~combiner)))
  ([klass srcs combiner bufferSize]
    `(. ~klass combineLatest ~srcs (fns/function ~combiner) ~bufferSize)))

(defmacro combineLatestDelayError
  ([klass srcs combiner]
    `(. ~klass combineLatestDelayError ~srcs (fns/function ~combiner)))
  ([klass srcs combiner bufferSize]
    `(. ~klass combineLatestDelayError ~srcs (fns/function ~combiner) ~bufferSize)))

(defmacro concat
  ([klass srcs] 
    `(. ~klass concat ~srcs))
  ([klass srcs bufferSize] 
    `(. ~klass concat ~srcs ~bufferSize)))

(defmacro concatDelayError
  ([klass srcs] 
    `(. ~klass concatDelayError ~srcs))
  ([klass srcs bufferSize]
    `(. ~klass concatDelayError ~srcs ~bufferSize))
  ([klass srcs bufferSize tillTheEnd]
    `(. ~klass concatDelayError ~srcs ~bufferSize ~tillTheEnd)))

(defmacro concatEager
  ([klass srcs]
    `(. ~klass concatEager ~srcs))
  ([klass srcs maxConcurrency bufferSize] 
    `(. ~klass concatEager ~srcs ~maxConcurrency ~bufferSize)))

(defmacro concatEagerDelayError
  ([klass srcs]
    `(. ~klass concatEagerDelayError ~srcs))
  ([klass srcs maxConcurrency bufferSize] 
    `(. ~klass concatEagerDelayError ~srcs ~maxConcurrency ~bufferSize)))

(defmacro create
  ([klass f]
    `(. ~klass create (fns/onSubscribe ~klass ~f)))
  ([klass f backPressureStrategy]
    `(. ~klass create (fns/onSubscribe ~klass ~f) ~backPressureStrategy)))

(defmacro defer [klass f]
  `(. ~klass defer (fns/supplier ~f)))

(defmacro empty [klass]
  `(. ~klass empty))

(defmacro error [klass f]
  (cond (ifn? (eval f)) `(. ~klass error (fns/supplier ~f))
        :else           `(. ~klass error ~f)))

(defmacro fromAction [klass f]
  `(. ~klass fromAction (fns/action ~f)))

(defmacro fromArray [klass srcs]
  `(. ~klass fromArray ~srcs))

(defmacro fromCallable [klass f]
  `(. ~klass fromCallable (fns/callable ~f)))

(defmacro fromCompletable [klass f]
  `(. ~klass fromCompletable (fns/source ~'Completable ~f)))

(defmacro fromSingle [klass f]
  `(. ~klass fromSingle (fns/source ~'Single ~f)))

(defmacro fromObservable [klass f]
  `(. ~klass fromObservable (fns/source ~'Observable ~f)))

(defmacro fromMaybe [klass f]
  `(. ~klass fromMaybe (fns/source ~'Maybe ~f)))

(defmacro fromOptional [klass optional]
  `(. ~klass fromOptional ~optional))

(defmacro fromFuture
  ([klass future]
    `(. ~klass fromFuture ~future))
  ([klass future timeout unit]
    `(. ~klass fromFuture ~future ~timeout ~unit)))

(defmacro fromIterable [klass iterable]
  `(. ~klass fromIterable ~iterable))

(defmacro fromPublisher [klass f]
  `(. ~klass fromPublisher (fns/publisher ~f)))

(defmacro fromRunnable [klass f]
  `(. ~klass fromRunnable (fns/runnable ~f)))

(defmacro fromStream [klass stream]
  `(. ~klass fromStream ~stream))

(defmacro fromSupplier [klass f]
  `(. ~klass fromSupplier (fns/supplier ~f)))

(defmacro generate
  ([klass c]
    `(. ~klass generate (fns/consumer ~c)))
  ([klass s bc]
    `(. ~klass generate (fns/supplier ~s) (fns/biConsumer ~bc)))
  ([klass s bc c]
    `(. ~klass generate (fns/supplier ~s) (fns/biConsumer ~bc) (fns/consumer c))))

(defmacro interval
  ([klass period unit]
    `(. ~klass interval ~period ~unit))
  ([klass initialDelay period unit]
    `(. ~klass interval ~initialDelay ~period ~unit))
  ([klass initialDelay period unit scheduler]
    `(. ~klass interval ~initialDelay ~period ~unit ~scheduler)))

(defmacro intervalRange
  ([klass start count intialDelay period unit]
    `(. ~klass intervalRange ~start ~count ~intialDelay ~period ~unit))
  ([klass start count intialDelay period unit schedular]
    `(. ~klass intervalRange ~start ~count ~intialDelay ~period ~unit ~schedular)))

(defmacro just [klass & srcs]
  `(. ~klass fromIterable ~(vec srcs)))

(defmacro merge
  ([klass srcs]
    `(. ~klass merge ~srcs))
  ([klass srcs maxConcurrency] 
    `(. ~klass merge ~srcs ~maxConcurrency))
  ([klass srcs maxConcurrency bufferSize] 
    `(. ~klass merge ~srcs ~maxConcurrency ~bufferSize)))

(defmacro mergeDelayError
  ([klass srcs]
    `(. ~klass mergeDelayError ~srcs))
  ([klass srcs maxConcurrency] 
    `(. ~klass mergeDelayError ~srcs ~maxConcurrency))
  ([klass srcs maxConcurrency bufferSize] 
    `(. ~klass mergeDelayError ~srcs ~maxConcurrency ~bufferSize)))

(defmacro never [klass]
  `(. ~klass never))

(defmacro range [klass start count] 
  `(. ~klass range ~start ~count))

(defmacro rangeLong [klass start count] 
  `(. ~klass rangeLong ~start ~count))

(defmacro sequenceEqual
  ([klass src1 src2]
    `(. ~klass sequenceEqual ~src1 ~src2))
  ([klass src1 src2 f] 
    `(. ~klass sequenceEqual ~src1 ~src2 (fns/biPredicate ~f)))
  ([klass src1 src2 f bufferSize]
    `(. ~klass sequenceEqual ~src1 ~src2 (fns/biPredicate ~f) ~bufferSize)))

(defmacro switchOnNext
  ([klass srcs]
    `(. ~klass switchOnNext ~srcs))
  ([klass srcs bufferSize] 
    `(. ~klass switchOnNext ~srcs ~bufferSize)))

(defmacro switchOnNextDelayError
  ([klass srcs]
    `(. ~klass switchOnNextDelayError ~srcs))
  ([klass srcs bufferSize] 
    `(. ~klass switchOnNextDelayError ~srcs ~bufferSize)))

(defmacro timer
  ([klass delay unit]
    `(. ~klass timer ~delay ~unit))
  ([klass initialDelay delay unit scheduler]
    `(. ~klass timer ~initialDelay ~delay ~unit ~scheduler)))

(defmacro using
  ([klass resource src cleanup]
    `(. ~klass using (fns/supplier ~resource) (fns/function ~src) (fns/consumer ~cleanup)))
  ([klass resource src cleanup eager]
    `(. ~klass using (fns/supplier ~resource) (fns/function ~src) (fns/consumer ~cleanup) ~eager)))

(defmacro wrap [klass f]
  `(. ~klass wrap (fns/source ~klass ~f)))

(defmacro zip
  ([klass srcs f]
    `(. ~klass zip ~srcs (fns/function ~f)))
  ([klass srcs f delayError bufferSize]
    `(. ~klass zip ~srcs (fns/function ~f) ~delayError ~bufferSize)))