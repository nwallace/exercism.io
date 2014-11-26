(ns word-count
  (:require [clojure.string :refer [lower-case replace split]]))

(defn word-count [input-str]
  (reduce (fn [counts word]
            (if (contains? counts word)
              (assoc counts word (inc (counts word)))
              (assoc counts word 1)))
          {}
          (split (lower-case (replace input-str #"\W" " ")) #"\s+")))
