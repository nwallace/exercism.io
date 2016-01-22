(ns etl
  (:require [clojure.string :refer [lower-case]]))

(defn- transformer-and-ungrouper [old-key transformation]
  (fn [result value]
    (assoc result (transformation value) old-key)))

(defn- ungroup-and-transform-values [transformation accumulator [old-key values]]
  (conj accumulator
        (reduce (transformer-and-ungrouper old-key transformation) {} values)))

(defn transform [data]
  (reduce (partial ungroup-and-transform-values lower-case) {} data))
