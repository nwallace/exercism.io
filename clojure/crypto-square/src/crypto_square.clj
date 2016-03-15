(ns crypto-square
  (:require [clojure.string :as string]))

(defn normalize-plaintext [message]
  (-> message
      (.toLowerCase)
      (string/replace #"[^a-z\d]" "")))

(defn square-size [normalized-msg]
  (loop [size 0]
    (if (>= (* size size) (count normalized-msg))
      size
      (recur (inc size)))))

(defn plaintext-segments [message]
  (let [normalized-msg (normalize-plaintext message)
        size (square-size normalized-msg)]
    (->> normalized-msg
         (partition-all size)
         (map #(apply str %)))))

(defn- square-off-segments [segments]
  (let [max-size (count (first segments))]
    (for [segment segments]
      (loop [segment segment]
        (if (< (count segment) max-size)
          (recur (conj (vec segment) nil))
          segment)))))

(defn normalize-ciphertext [message]
  (let [segments (plaintext-segments message)]
    (->> segments
         square-off-segments
         (apply (partial map str))
         (interpose " ")
         (apply str))))

(defn ciphertext [message]
  (string/replace (normalize-ciphertext message) #" " ""))
