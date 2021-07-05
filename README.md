# rx-clojure
RxJava bindings for Clojure.

## Usage
[Documentation](http://reactivex.io/RxJava/3.x/javadoc/)

## Notable differences from RxJava
* `buffer` operator has been divided into `buffer`, `bufferByBoundary`, `bufferByTime`, `bufferByTimeWithSkip`, `bufferByToggle`.
* `window` operator has been divided into `window`, `windowByBoundary`, `windowByTime`, `windowByTimeWithSkip`, `windowByToggle`.
* `cacheWithInitialCapacity` operator is `cache(initialCapacity)` 
* Added `distinctUntilKeyChanged(keySelectorFunction)` operator
* `replay` operator is divided into `replay` and `replayWithBuffer`
* `takeLast` is divided into `takeLast` and `takeLastByTime`
* `timeout` is divided into `timeout` and `timeoutByIndicator`