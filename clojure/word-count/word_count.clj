(ns word-count
  (:require [clojure.string :refer [lower-case]]))

(defn word-count [input-str]
  (frequencies (re-seq #"\w+" (lower-case input-str))))
