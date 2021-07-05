(ns rx-clojure.utils)

(defn args-count [f]
  (-> f class .getDeclaredMethods first .getParameterTypes alength))
