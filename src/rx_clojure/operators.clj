(ns rx-clojure.operators
  (:require [rx-clojure.functions :as fns]))

(defn subscribe 
  ([instance f]
    (.subscribe instance (fns/consumer f)))
  ([instance f e]
    (.subscribe instance (fns/consumer f) (fns/consumer e)))
  ([instance f e c]
    (.subscribe instance (fns/consumer f) (fns/consumer e) (fns/action c))))
