(ns etl
  (:require [clojure.string :refer [lower-case]]))

(defn- ungroup-and-transform-values [transformation accumulator [k values]]
  (conj accumulator
        (reduce (fn [result value]
                  (conj result {(transformation value) k}))
                {} values)))


(defn transform [data]
  (reduce (partial ungroup-and-transform-values lower-case) {} data))
