(ns robot-name
  (:require [clojure.string :refer [join]]))

(defn- random-number [base]
  (int (mod (* (rand) base 100) base)))

(defn- random-letter []
  (char (+ (random-number 26) 65)))

(defn- random-digit []
  (random-number 10))

(defn- generate-name []
  (str
    (join (take 2 (repeatedly random-letter)))
    (join (take 3 (repeatedly random-digit)))))

(defn robot []
  (transient {:name (generate-name)}))

(defn robot-name [robot]
  (:name robot))

(defn reset-name [robot]
  (assoc! robot :name (generate-name)))
