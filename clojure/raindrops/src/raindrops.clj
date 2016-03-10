(ns raindrops
  (:require [clojure.string :as string]))

(def ^:private pl*ng {3 "Pling"
                      5 "Plang"
                      7 "Plong"})

(defn- factorization [n]
  (loop [n n
         factor 2
         factors []]
    (cond
      (< n 2) factors
      (zero? (mod n factor)) (recur (/ n factor) factor (conj factors factor))
      :else (recur n (inc factor) factors))))

(defn- select-values [coll keys]
  (map val (select-keys coll keys)))

(defn- pl*ng-erize [n]
  (let [unique-factors (apply sorted-set (factorization n))]
    (apply str (select-values pl*ng unique-factors))))

(defn convert [n]
  (let [pl*ng-erization (pl*ng-erize n)]
    (if (string/blank? pl*ng-erization)
      (str n)
      pl*ng-erization)))
