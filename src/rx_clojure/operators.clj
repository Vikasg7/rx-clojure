(ns rx-clojure.operators
  (:refer-clojure :exclude [cast count delay distinct filter first last
                            map reduce repeat take test])
  (:require [rx-clojure.functions :as fns]
            [rx-clojure.utils :refer [args-count]])
  (:import  [io.reactivex.rxjava3.core  Observable
                                        Flowable
                                        Single
                                        Maybe
                                        Completable]))

(defn all [instance f]
  (.all instance (fns/predicate f)))

(defn ambWith [instance src]
  (.ambWith instance src))

(defn any [instance f]
  (.any instance (fns/predicate f)))

(defn blockingFirst 
  ([instance]
    (.blockingFirst instance))
  ([instance val]
    (.blockingFirst instance val)))

(defn blockingForEach 
  ([instance f]
    (.blockingForEach instance (fns/consumer f)))
  ([instance f capacityHint]
    (.blockingForEach instance (fns/consumer f) capacityHint)))

(defn blockingIterable 
  ([instance]
    (.blockingIterable instance))
  ([instance capacityHint]
    (.blockingIterable instance capacityHint)))

(defn blockingLast 
  ([instance]
    (.blockingLast instance))
  ([instance val]
    (.blockingLast instance val)))

(defn blockingLatest [instance]
  (.blockingLatest instance))

(defn blockingMostRecent [instance initialItem]
  (.blockingMostRecent instance initialItem))

(defn blockingNext [instance]
  (.blockingNext instance))

(defn blockingSingle 
  ([instance]
    (.blockingSingle instance))
  ([instance val]
    (.blockingSingle instance val)))

(defn blockingStream 
  ([instance]
    (.blockingStream instance))
  ([instance capacityHint]
    (.blockingStream instance capacityHint)))

(defn blockingSubscribe 
  ([instance]
    (.blockingSubscribe instance))
  ([instance f]
    (.blockingSubscribe instance (fns/consumer f)))
  ([instance f e]
    (.blockingSubscribe instance (fns/consumer f) (fns/consumer e)))
  ([instance f e c]
    (.blockingSubscribe instance (fns/consumer f) (fns/consumer e) (fns/action c))))

(defn buffer
  ([instance count]
    (.buffer instance count))
  ([instance count skip]
    (.buffer instance count skip)))

(defn bufferByBoundary
  ([instance boundaryIndicator]
    (.buffer instance boundaryIndicator))
  ([instance boundaryIndicator initialCapacity]
    (.buffer instance boundaryIndicator initialCapacity)))

(defn bufferByTime
  ([instance timespan unit]
    (.buffer instance timespan unit))
  ([instance timespan unit count]
    (.buffer instance timespan unit count))
  ([instance timespan unit scheduler count]
    (.buffer instance timespan unit scheduler count)))

(defn bufferByTimeWithSkip
  ([instance timespan timeskip unit]
    (.buffer instance timespan timeskip unit))
  ([instance timespan timeskip unit scheduler]
    (.buffer instance timespan timeskip unit scheduler)))

(defn bufferByToggle [instance opening closing]
  (.buffer instance opening closing))

(defn cache 
  ([instance]
    (.cache instance))
  ([instance initialCapacity]
    (.cacheWithInitialCapacity instance initialCapacity)))

(defn cast [instance clazz]
  (.cast instance clazz))

(defn collect
  ([instance collector]
    (.collect instance collector))
  ([instance f g]
    (.collect instance (fns/supplier f) (fns/biConsumer g))))

(defn collectInto [instance initialItem f]
  (.collectInto instance initialItem (fns/biConsumer f)))

(defn compose [instance f]
  (cond (instance? Observable  instance) (.compose instance (fns/transformer "Observable"  f))
        (instance? Flowable    instance) (.compose instance (fns/transformer "Flowable"    f))
        (instance? Single      instance) (.compose instance (fns/transformer "Single"      f))
        (instance? Maybe       instance) (.compose instance (fns/transformer "Maybe"       f))
        (instance? Completable instance) (.compose instance (fns/transformer "Completable" f))
        :else                            (throw (Error. "op/compose not implemented."))))

(defn concatMap
  ([instance f]
    (.concatMap instance (fns/function f)))
  ([instance f bufferSize]
    (.concatMap instance (fns/function f) bufferSize))
  ([instance f bufferSize scheduler]
    (.concatMap instance (fns/function f) bufferSize scheduler)))

(defn concatMapCompletable
  ([instance f]
    (.concatMapCompletable instance (fns/function f)))
  ([instance f capacityHint]
    (.concatMapCompletable instance (fns/function f) capacityHint)))

(defn concatMapDelayError
  ([instance f]
    (.concatMapDelayError instance (fns/function f)))
  ([instance f tillTheEnd bufferSize]
    (.concatMapDelayError instance (fns/function f) tillTheEnd bufferSize))
  ([instance f tillTheEnd bufferSize scheduler]
    (.concatMapDelayError instance (fns/function f) tillTheEnd bufferSize scheduler)))

(defn concatMapCompletableDelayError
  ([instance f]
    (.concatMapCompletableDelayError instance (fns/function f)))
  ([instance f tillTheEnd]
    (.concatMapCompletableDelayError instance (fns/function f) tillTheEnd))
  ([instance f tillTheEnd bufferSize]
    (.concatMapCompletableDelayError instance (fns/function f) tillTheEnd bufferSize)))

(defn concatMapEager
  ([instance f]
    (.concatMapEager instance (fns/function f)))
  ([instance f maxConcurrency bufferSize]
    (.concatMapEager instance (fns/function f) maxConcurrency bufferSize)))

(defn concatMapEagerDelayError
  ([instance f tillTheEnd]
    (.concatMapEagerDelayError instance (fns/function f) tillTheEnd))
  ([instance f tillTheEnd maxConcurrency bufferSize]
    (.concatMapEagerDelayError instance (fns/function f) tillTheEnd maxConcurrency bufferSize)))

(defn concatMapIterable [instance f]
  (.concatMapIterable instance (fns/function f)))

(defn concatMapMaybe
  ([instance f]
    (.concatMapMaybe instance (fns/function f)))
  ([instance f bufferSize]
    (.concatMapMaybe instance (fns/function f) bufferSize)))

(defn concatMapMaybeDelayError
  ([instance f]
    (.concatMapMaybeDelayError instance (fns/function f)))
  ([instance f tillTheEnd]
    (.concatMapMaybeDelayError instance (fns/function f) tillTheEnd))
  ([instance f tillTheEnd bufferSize]
    (.concatMapMaybeDelayError instance (fns/function f) tillTheEnd bufferSize)))

(defn concatMapSingle
  ([instance f]
    (.concatMapSingle instance (fns/function f)))
  ([instance f bufferSize]
    (.concatMapSingle instance (fns/function f) bufferSize)))

(defn concatMapSingleDelayError
  ([instance f]
    (.concatMapSingleDelayError instance (fns/function f)))
  ([instance f tillTheEnd]
    (.concatMapSingleDelayError instance (fns/function f) tillTheEnd))
  ([instance f tillTheEnd bufferSize]
    (.concatMapSingleDelayError instance (fns/function f) tillTheEnd bufferSize)))

(defn concatMapStream [instance f]
  (.concatMapStream instance (fns/function f)))

(defn concatWith [instance src]
  (.concatWith instance src))

(defn contains [instance item]
  (.contains instance item))

(defn count [instance]
  (.count instance))

(defn debounce
  ([instance f]
    (.debounce instance (fns/function f)))
  ([instance timeout unit]
    (.debounce instance timeout unit))
  ([instance timeout unit scheduler]
    (.debounce instance timeout unit scheduler)))

(defn defaultIfEmpty [instance defaultItem]
  (.defaultIfEmpty instance defaultItem))

(defn delay
  ([instance f]
    (.delay instance (fns/function f)))
  ([instance time unit]
    (.delay instance time unit))
  ([instance time unit scheduler]
    (.delay instance time unit scheduler))
  ([instance time unit scheduler delayError]
    (.delay instance time unit scheduler delayError)))

(defn delaySubscription
  ([instance indicator]
    (.delaySubscription instance indicator))
  ([instance time unit]
    (.delaySubscription instance time unit))
  ([instance time unit scheduler]
    (.delaySubscription instance time unit scheduler)))

(defn dematerialize [instance f]
  (.dematerialize instance (fns/function f)))

(defn distinct
  ([instance]
    (.distinct instance))
  ([instance f]
    (.distinct instance (fns/function f))))

(defn distinctUntilChanged
  ([instance]
    (.distinctUntilChanged instance))
  ([instance f]
    (.distinctUntilChanged instance (fns/biPredicate f))))

(defn distinctUntilKeyChanged [instance f]
  (.distinctUntilChanged instance (fns/function f)))

(defn doAfterNext [instance f]
  (.doAfterNext instance (fns/consumer f)))

(defn doAfterTerminate [instance f]
  (.doAfterTerminate instance (fns/action f)))

(defn doFinally [instance f]
  (.doFinally instance (fns/action f)))

(defn doOnComplete [instance f]
  (.doOnComplete instance (fns/action f)))

(defn doOnDispose [instance f]
  (.doOnDispose instance (fns/action f)))

(defn doOnEach [instance f]
  (.doOnEach instance (fns/consumer f)))

(defn doOnError [instance f]
  (.doOnError instance (fns/consumer f)))

(defn doOnLifeCycle [instance f g]
  (.doOnLifeCycle instance (fns/consumer f) (fns/action g)))

(defn doOnNext [instance f]
  (.doOnNext instance (fns/consumer f)))

(defn doOnRequest [instance f]
  (.doOnRequest instance (fns/longConsumer f)))

(defn doOnSubscribe [instance f]
  (.doOnSubscribe instance (fns/consumer f)))

(defn doOnTermination [instance f]
  (.doOnTermination instance (fns/action f)))

(defn elementAt
  ([instance index]
    (.elementAt instance index))
  ([instance index defaultItem]
    (.elementAt instance index defaultItem)))

(defn elementAtOrError [instance index]
  (.elementAtOrError instance index))

(defn filter [instance f]
  (.filter instance (fns/predicate f)))

(defn first [instance defaultItem]
  (.first instance defaultItem))

(defn firstElement [instance]
  (.firstElement instance))

(defn firstOrError [instance]
  (.firstOrError instance))

(defn flatMap
  ([instance f]
    (.flatMap instance (fns/function f)))
  ([instance f delayError]
    (.flatMap instance (fns/function f) delayError))
  ([instance f delayError maxConcurrency]
    (.flatMap instance (fns/function f) delayError maxConcurrency))
  ([instance f delayError maxConcurrency bufferSize]
    (.flatMap instance (fns/function f) delayError maxConcurrency bufferSize)))

(defn flatMapCompletable
  ([instance f]
    (.flatMapCompletable instance (fns/function f)))
  ([instance f delayError]
    (.flatMapCompletable instance (fns/function f) delayError)))

(defn flatMapIterable
  ([instance f]
    (.flatMapIterable instance (fns/function f)))
  ([instance f g]
    (.flatMapIterable instance (fns/function f) (fns/biFunction g))))

(defn flatMapMaybe
  ([instance f]
    (.flatMapMaybe instance (fns/function f)))
  ([instance f delayError]
    (.flatMapMaybe instance (fns/function f) delayError)))

(defn flatMapSingle
  ([instance f]
    (.flatMapSingle instance (fns/function f)))
  ([instance f delayError]
    (.flatMapSingle instance (fns/function f) delayError)))

(defn flatMapStream [instance f]
  (.flatMapStream instance (fns/function f)))

(defn forEach [instance f]
  (.forEach instance (fns/consumer f)))

(defn forEachWhile
  ([instance f]
    (.forEachWhile instance (fns/predicate f)))
  ([instance f g]
    (.forEachWhile instance (fns/predicate f) (fns/consumer g)))
  ([instance f g h]
    (.forEachWhile instance (fns/predicate f) (fns/consumer g) (fns/action h))))

(defn groupBy
  ([instance f]
    (.groupBy instance (fns/function f)))
  ([instance f g]
    (cond (ifn? g) (.groupBy instance (fns/function f) (fns/function g))
          :else    (.groupBy instance (fns/function f) g)))
  ([instance f g delayError]
    (.groupBy instance (fns/function f) (fns/function g) delayError))
  ([instance f g delayError bufferSize]
    (.groupBy instance (fns/function f) (fns/function g) delayError bufferSize)))

(defn groupJoin [instance other f g h]
  (.groupJoin instance other (fns/function f) (fns/function g) (fns/biFunction h)))

(defn hide [instance]
  (.hide instance))

(defn ignoreElement [instance]
  (.ignoreElement instance))

(defn isEmpty [instance]
  (.isEmpty instance))

(defn join [instance other f g h]
  (.join instance other (fns/function f) (fns/function g) (fns/biFunction h)))

(defn last [instance defaultItem]
  (.last instance defaultItem))

(defn lastElement [instance]
  (.lastElement instance))

(defn lastOrError [instance]
  (.lastOrError instance))

(defn map [instance f]
  (.map instance (fns/function f)))

(defn mapOptional [instance f]
  (.mapOptional instance (fns/function f)))

(defn materialize [instance]
  (.materialize instance))

(defn mergeWith [instance src]
  (.mergeWith instance src))

(defn observeOn
  ([instance scheduler]
    (.observeOn instance scheduler))
  ([instance scheduler delayError]
    (.observeOn instance scheduler delayError))
  ([instance scheduler delayError bufferSize]
    (.observeOn instance scheduler delayError bufferSize)))

(defn ofType [instance clazz]
  (.ofType instance clazz))


(defn onBackpressureBuffer
  ([instance]
    (.onBackpressureBuffer instance))
  ([instance delayError]
    (.onBackpressureBuffer instance delayError))
  ([instance capacity f]
    (cond (ifn? f) (.onBackpressureBuffer instance capacity (fns/action f))
          :else    (.onBackpressureBuffer instance capacity f)))
  ([instance capacity f g]
    (cond (ifn? f) (.onBackpressureBuffer instance capacity (fns/action f) g)
          :else    (.onBackpressureBuffer instance capacity f g)))
  ([instance capacity delayError unbounded f]
    (.onBackpressureBuffer instance capacity delayError unbounded (fns/action f))))

(defn onBackpressureDrop
  ([instance]
    (.onBackpressureDrop instance))
  ([instance f]
    (.onBackpressureDrop instance (fns/function f))))

(defn onBackpressureLatest [instance]
  (.onBackpressureLatest instance))

(defn onBackpressureReduce
  ([instance f]
    (.onBackpressureReduce instance (fns/biFunction f)))
  ([instance s f]
    (.onBackpressureReduce instance (fns/supplier s) (fns/function f))))

(defn onErrorComplete
  ([instance]
    (.onErrorComplete instance))
  ([instance f]
    (.onErrorComplete instance (fns/predicate f))))

(defn onErrorResumeNext [instance f]
  (.onErrorResumeNext instance (fns/function f)))

(defn onErrorResumeWith [instance src]
  (.onErrorResumeWith instance src))

(defn onErrorReturn [instance f]
  (.onErrorReturn instance (fns/function f)))

(defn onErrorReturnItem [instance item]
  (.onErrorReturnItem instance item))

(defn onTerminateDetach [instance]
  (.onTerminateDetach instance))

(defn parallel
  ([instance]
    (.parallel instance))
  ([instance parallelism]
    (.parallel instance parallelism))
  ([instance parallelism prefetch]
    (.parallel instance parallelism prefetch)))

(defn publish 
  ([instance]
    (.publish instance))
  ([instance f]
    (.publish instance (fns/function f)))
  ([instance f prefetch]
    (.publish instance (fns/function f) prefetch)))

(defn reduce 
  ([instance f]
    (.reduce instance (fns/biFunction f)))
  ([instance seed f]
    (.reduce instance seed (fns/biFunction f))))

(defn reduceWith [instance f g]
  (.reduceWith instance (fns/supplier f) (fns/biFunction g)))

(defn repeat 
  ([instance]
    (.repeat instance))
  ([instance times]
    (.repeat instance times)))

(defn repeatUntil [instance f]
  (.repeatUntil instance (fns/booleanSupplier f)))

(defn repeatWhen [instance f]
  (.repeatWhen instance (fns/function f)))

(defn replayWithBuffer
  ([instance bufferSize]
    (.replay instance bufferSize))
  ([instance bufferSize eagerTruncate]
    (.replay instance bufferSize eagerTruncate))
  ([instance bufferSize time unit]
    (.replay instance bufferSize time unit))
  ([instance bufferSize time unit scheduler]
    (.replay instance bufferSize time unit scheduler))
  ([instance bufferSize time unit scheduler eagerTruncate]
    (.replay instance bufferSize time unit scheduler eagerTruncate)))

(defn replay
  ([instance]
    (.replay instance))
  ([instance time unit]
    (.replay instance time unit))
  ([instance time unit scheduler]
    (.replay instance time unit scheduler))
  ([instance time unit scheduler eagerTruncate]
    (.replay instance time unit scheduler eagerTruncate)))

(defn retry
  ([instance]
    (.retry instance))
  ([instance f]
    (cond (ifn? f) (case (args-count f)
                      1 (.retry instance (fns/predicate f))
                      2 (.retry instance (fns/biPredicate f))
                        (throw (Error. "op/retry: Wrong no. of parameters for a predicate")))
          :else    (.retry instance f)))
  ([instance times f]
    (.retry instance times (fns/predicate f))))

(defn retryUntil [instance f]
  (.retryUntil instance (fns/booleanSupplier f)))

(defn retryWhen [instance f]
  (.retryWhen instance (fns/function f)))

(defn runOn 
  ([instance scheduler]
    (.runOn instance scheduler))
  ([instance scheduler prefetch]
    (.runOn instance scheduler prefetch)))

(defn sample
  ([instance time unit]
    (.sample instance time unit))
  ([instance time unit scheduler]
    (.sample instance time unit scheduler))
  ([instance time unit scheduler emitLast]
    (.sample instance time unit scheduler emitLast)))

(defn scan 
  ([instance f]
    (.scan instance (fns/biFunction f)))
  ([instance seed f]
    (.scan instance seed (fns/biFunction f))))

(defn scanWith [instance f g]
  (.scanWith instance (fns/supplier f) (fns/biFunction g)))

(defn serialize [instance]
  (.serialize instance))

(defn share [instance]
  (.share instance))

(defn single [instance defaultItem]
  (.single instance defaultItem))

(defn singleElement [instance]
  (.singleElement instance))

(defn singleOrError [instance]
  (.singleOrError instance))

(defn skip
  ([instance count]
    (.skip instance count))
  ([instance time unit]
    (.skip instance time unit))
  ([instance time unit scheduler]
    (.skip instance time unit scheduler)))

(defn skipLast
  ([instance count]
    (.skipLast instance count))
  ([instance time unit]
    (.skipLast instance time unit))
  ([instance time unit scheduler]
    (.skipLast instance time unit scheduler))
  ([instance time unit scheduler delayError]
    (.skipLast instance time unit scheduler delayError))
  ([instance time unit scheduler delayError bufferSize]
    (.skipLast instance time unit scheduler delayError bufferSize)))

(defn skipUntil [instance src]
  (.skipUntil instance src))

(defn skipWhile [instance f]
  (.skipWhile instance (fns/predicate f)))

(defn sequential 
  ([instance]
    (.sequential instance))
  ([instance prefetch]
    (.sequential instance prefetch)))

(defn sequentialDelayError
  ([instance]
    (.sequentialDelayError instance))
  ([instance prefetch]
    (.sequentialDelayError instance prefetch)))

(defn sorted
  ([instance]
    (.sorted instance))
  ([instance f]
    (.sorted instance (comparator f))))

(defn startWith [instance src]
  (.startWith instance src))

(defn startWithArray [instance arr]
  (.startWithArray instance arr))

(defn startWithItem [instance item]
  (.startWithItem instance item))

(defn startWithIterable [instance iterable]
  (.startWithIterable instance iterable))

(defn subscribe
  ([instance]
    (.subscribe instance))
  ([instance f]
    (.subscribe instance (fns/consumer f)))
  ([instance f e]
    (.subscribe instance (fns/consumer f) (fns/consumer e)))
  ([instance f e c]
    (.subscribe instance (fns/consumer f) (fns/consumer e) (fns/action c))))

(defn subscribeOn [instance scheduler]
  (.subscribeOn instance scheduler))

(defn switchIfEmpty [instance src]
  (.switchIfEmpty instance src))

(defn switchMap
  ([instance f]
    (.switchMap instance (fns/function f)))
  ([instance f bufferSize]
    (.switchMap instance (fns/function f) bufferSize)))

(defn switchMapCompletable [instance f]
  (.switchMapCompletable instance (fns/function f)))

(defn switchMapCompletableDelayError [instance f]
  (.switchMapCompletableDelayError instance (fns/function f)))

(defn switchMapDelayError
  ([instance f]
    (.switchMapDelayError instance (fns/function f)))
  ([instance f bufferSize]
    (.switchMapDelayError instance (fns/function f) bufferSize)))

(defn switchMapMaybe [instance f]
  (.switchMapMaybe instance (fns/function f)))

(defn switchMapMaybeDelayError [instance f]
  (.switchMapMaybeDelayError instance (fns/function f)))

(defn switchMapSingle [instance f]
  (.switchMapSingle instance (fns/function f)))

(defn switchMapSingleDelayError [instance f]
  (.switchMapSingleDelayError instance (fns/function f)))

(defn take
  ([instance count]
    (.take instance count))
  ([instance time unit]
    (.take instance time unit))
  ([instance time unit scheduler]
    (.take instance time unit scheduler)))

(defn takeLast
  ([instance count]
    (.takeLast instance count))
  ([instance count time unit]
    (.takeLast instance count time unit))
  ([instance count time unit scheduler delayError bufferSize]
    (.takeLast instance count time unit scheduler delayError bufferSize)))

(defn takeLastByTime
  ([instance time unit]
    (.takeLast instance time unit))
  ([instance time unit delayError]
    (.takeLast instance time unit delayError))
  ([instance time unit scheduler delayError]
    (.takeLast instance time unit scheduler delayError))
  ([instance time unit scheduler delayError bufferSize]
    (.takeLast instance time unit scheduler delayError bufferSize)))

(defn takeUntil [instance f]
  (cond (ifn? f) (.takeUntil instance (fns/predicate f))
        :else    (.takeUntil instance f)))

(defn takeWhile [instance f]
  (.takeWhile instance (fns/predicate f)))

(defn test
  ([instance]
    (.test instance))
  ([instance dispose]
    (.test instance dispose)))

(defn throttleFirst
  ([instance time unit]
    (.throttleFirst instance time unit))
  ([instance time unit scheduler]
    (.throttleFirst instance time unit scheduler)))

(defn throttleLast
  ([instance time unit]
    (.throttleLast instance time unit))
  ([instance time unit scheduler]
    (.throttleLast instance time unit scheduler)))

(defn throttleLastest
  ([instance timeout unit]
    (.throttleLastest instance timeout unit))
  ([instance timeout unit scheduler]
    (.throttleLastest instance timeout unit scheduler))
  ([instance timeout unit scheduler emitLast]
    (.throttleLastest instance timeout unit scheduler emitLast)))

(defn throttleTimeout
  ([instance time unit]
    (.throttleTimeout instance time unit))
  ([instance time unit scheduler]
    (.throttleTimeout instance time unit scheduler)))

(defn timeInterval
  ([instance]
    (.timeInterval instance))
  ([instance unit]
    (.timeInterval instance unit))
  ([instance unit scheduler]
    (.timeInterval instance unit scheduler)))

(defn timeout
  ([instance timeout unit]
    (.timeout instance timeout unit))
  ([instance timeout unit scheduler]
    (.timeout instance timeout unit scheduler))
  ([instance timeout unit scheduler fallback]
    (.timeout instance timeout unit scheduler fallback)))

(defn timeoutByIndicator
  ([instance f]
    (.timeout instance (fns/function f)))
  ([instance f g]
    (cond (ifn? g) (.timeout instance (fns/function f) (fns/function g))
          :else    (.timeout instance (fns/function f) g)))
  ([instance f g fallback]
    (.timeout instance (fns/function f) (fns/function g) fallback)))

(defn timestamp
  ([instance]
    (.timestamp instance))
  ([instance unit]
    (.timestamp instance unit))
  ([instance unit scheduler]
    (.timestamp instance unit scheduler)))

(defn toFlowable [instance backPressureStrategy]
  (.toFlowable instance backPressureStrategy))

(defn toObservable [instance]
  (.toObservable instance))

(defn toFuture [instance]
  (.toFuture instance))

(defn toList
  ([instance]
    (.toList instance))
  ([instance capacityHint]
    (.toList instance capacityHint)))

(defn toMap
  ([instance f]
    (.toMap instance (fns/function f)))
  ([instance f g]
    (.toMap instance (fns/function f) (fns/function g))))

(defn toMultiMap
  ([instance f]
    (.toMultiMap instance (fns/function f)))
  ([instance f g]
    (.toMultiMap instance (fns/function f) (fns/function g))))

(defn toSortedList
  ([instance]
    (.toSortedList instance))
  ([instance f]
    (cond (ifn? f) (.toSortedList instance (comparator f))
          :else    (.toSortedList instance f)))
  ([instance f capacityHint]
    (.toSortedList instance (comparator f) capacityHint)))

(defn unsubscribeOn [instance scheduler]
  (.unsubscribeOn instance scheduler))

(defn window
  ([instance count]
    (.window instance count))
  ([instance count skip]
    (.window instance count skip))
  ([instance count skip bufferSize]
    (.window instance count skip bufferSize)))

(defn windowByBoundary
  ([instance boundaryIndicator]
    (.window instance boundaryIndicator))
  ([instance boundaryIndicator bufferSize]
    (.window instance boundaryIndicator bufferSize)))

(defn windowByTime
  ([instance timespan unit]
    (.window instance timespan unit))
  ([instance timespan unit count]
    (.window instance timespan unit count))
  ([instance timespan unit scheduler count]
    (.window instance timespan unit scheduler count))
  ([instance timespan unit scheduler count restart]
    (.window instance timespan unit scheduler count restart))
  ([instance timespan unit scheduler count restart bufferSize]
    (.window instance timespan unit scheduler count restart bufferSize)))

(defn windowByTimeWithSkip
  ([instance timespan timeskip unit]
    (.window instance timespan timeskip unit))
  ([instance timespan timeskip unit scheduler]
    (.window instance timespan timeskip unit scheduler))
  ([instance timespan timeskip unit scheduler bufferSize]
    (.window instance timespan timeskip unit scheduler bufferSize)))

(defn windowByToggle 
  ([instance opening closing]
    (.window instance opening closing))
  ([instance opening closing bufferSize]
    (.window instance opening closing bufferSize)))

(defn withLatestFrom 
  ([instance src f]
    (cond (sequential? src) (.withLatestFrom instance src (fns/function f))
          :else             (.withLatestFrom instance src (fns/biFunction f))))
  ([instance src1 src2 f]
    (.withLatestFrom instance src1 src2 (fns/function f)))
  ([instance src1 src2 src3 f]
    (.withLatestFrom instance src1 src2 src3 (fns/function f)))
  ([instance src1 src2 src3 src4 f]
    (.withLatestFrom instance src1 src2 src3 src4 (fns/function f))))

(defn zipWith
  ([instance src f]
    (.zipWith instance src (fns/biFunction f)))
  ([instance src f delayError]
    (.zipWith instance src (fns/biFunction f) delayError))
  ([instance src f delayError bufferSize]
    (.zipWith instance src (fns/biFunction f) delayError bufferSize)))
