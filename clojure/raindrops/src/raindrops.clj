(ns raindrops
  (:require [clojure.string :as string]))

(def ^:private pl*ng {3 "Pling"
                      5 "Plang"
                      7 "Plong"})

(defn convert [n]
  (if-let [pl*ngs (seq (for [[k v] pl*ng :when (zero? (mod n k))] v))]
    (apply str pl*ngs)
    (str n)))

