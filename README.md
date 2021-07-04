# rx-clojure
RxJava bindings for Clojure.

## Usage
[Documentation](http://reactivex.io/RxJava/3.x/javadoc/)

## Notable differences from RxJava
* `buffer` operator has been divided into `bufferByCount`, `bufferByBoundary`, `bufferByTime`, `bufferByTimeWithSkip`, `bufferToggle`. Check [source](https://github.com/Vikasg7/rx-clojure/blob/main/src/rx_clojure/operators.clj#L81) for more details.
* `cacheWithInitialCapacity` operator is `cache(initialCapacity)` 
* Added `distinctUntilKeyChanged(keySelectorFunction)` operator
* `firstElement` operator is `first()`
* `lastElement` operator is `last()`
* `replay` operator is divided into `replay` and `replayWithBuffer`