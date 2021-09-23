# rx-clojure
RxJava bindings for Clojure.  

## Installation
```console
[org.clojars.vikasg7/rx-clojure "0.1.0-SNAPSHOT"]
```

## Usage
[Documentation](http://reactivex.io/RxJava/3.x/javadoc/)

## Notable differences from RxJava
* Following operators are broken due to extensive method overloading.  

  | Original                   | Broken into                                                                          |
  |----------------------------|--------------------------------------------------------------------------------------|
  |`buffer`                    |`buffer`, `bufferByBoundary`, `bufferByTime`, `bufferByTimeWithSkip`, `bufferByToggle`|
  |`window`                    |`window`, `windowByBoundary`, `windowByTime`, `windowByTimeWithSkip`, `windowByToggle`|
  |`cacheWithInitialCapacity`  |`cache(initialCapacity)`                                                              | 
  |`replay`                    |`replay`, `replayWithBuffer`                                                          |
  |`takeLast`                  |`takeLast`, `takeLastByTime`                                                          |
  |`timeout`                   |`timeout`, `timeoutByIndicator`                                                       |  
* Added `distinctUntilKeyChanged(keySelectorFunction)` operator