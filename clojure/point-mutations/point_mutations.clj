(ns point-mutations
  (:require [clojure.data :refer [diff]]))

(defn hamming-distance [a b]
  (if (= (count a) (count b))
    (count (remove identity (map = a b)))
    nil))
