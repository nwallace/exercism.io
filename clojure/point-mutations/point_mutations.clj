(ns point-mutations
  (:require [clojure.data :refer [diff]]))

(defn hamming-distance [a b]
  (if (= (count a) (count b))
    (count
      (filter #(not (nil? %))
        (first (diff (seq a) (seq b)))))
    nil))
