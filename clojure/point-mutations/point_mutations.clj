(ns point-mutations
  (:require [clojure.data :refer [diff]]))

(defn hamming-distance [a b]
  (if (= (count a) (count b))
    (count (filter false? (map = a b)))))
