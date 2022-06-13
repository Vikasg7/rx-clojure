(defproject org.clojars.vikasg7/rx-clojure "0.1.4-SNAPSHOT"
  :description "RxJava bindings for clojure"
  :url "https://github.com/Vikasg7/rx-clojure"
  :license {:name "Eclipse Public License"
            :url "https://www.eclipse.org/legal/epl-v10.html"}
  :deploy-repositories {"releases" {:url "https://repo.clojars.org" 
                                    :creds :gpg}}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [io.reactivex.rxjava3/rxjava "3.1.5"]
                 [org.reactivestreams/reactive-streams "1.0.4"]]
  :repl-options {:init-ns rx-clojure.core})
