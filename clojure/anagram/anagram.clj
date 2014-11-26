(ns anagram
  (:require [clojure.string :refer [lower-case]]))

(defn anagrams-for [word possible-anagrams]
  (defn sorted-letters [word]
    (sort (seq (lower-case word))))
  (defn anagram? [possible-anagram]
    (and
      (= (sorted-letters word) (sorted-letters possible-anagram))
      (not= (lower-case word) (lower-case possible-anagram))))
  (filter anagram? possible-anagrams))
