(ns crypto-square
  (:require [clojure.string :as string]))

(defn normalize-plaintext [message]
  (string/replace (string/lower-case message) #"[^a-z\d]" ""))

(defn square-size [normalized-msg]
  (-> normalized-msg count Math/sqrt Math/ceil int))

(defn plaintext-segments [message]
  (let [normalized-msg (normalize-plaintext message)
        size (square-size normalized-msg)]
    (->> normalized-msg
         (partition-all size)
         (map #(apply str %)))))

(defn normalize-ciphertext
  ([message] (normalize-ciphertext message " "))
  ([message separator]
    (let [plaintext (normalize-plaintext message)
          size (square-size plaintext)]
      (->> plaintext
           (partition size size (repeat nil))
           (apply (partial map str))
           (string/join separator)))))

(defn ciphertext [message]
  (normalize-ciphertext message nil))
