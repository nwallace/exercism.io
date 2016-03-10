(ns raindrops
  (:require [clojure.string :as string]))

(def ^:private pl*ng {3 "Pling"
                      5 "Plang"
                      7 "Plong"})

(defn convert [n]
  (if-let
    [pl*ngs (seq (map val
                      (filter
                        (comp zero? (partial mod n) first)
                        pl*ng)))]
    (apply str pl*ngs)
    (str n)))

