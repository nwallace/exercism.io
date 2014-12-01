(ns beer-song
  (:require [clojure.string :refer [capitalize join]]))

(defn num-bottles [verse-num]
  (cond
    (= verse-num 1) (str "1 bottle")
    (= verse-num 0) (str "no more bottles")
    :else           (str verse-num " bottles")))

(defn action [verse-num]
  (cond
    (= verse-num 1) "Take it down and pass it around"
    (= verse-num 0) "Go to the store and buy some more"
    :else           "Take one down and pass it around"))

(defn dec-or-loop [verse-num]
  (if (< 0 verse-num)
    (dec verse-num) 99))

(defn verse [verse-num]
  (str (capitalize (num-bottles verse-num)) " of beer on the wall, "
       (num-bottles verse-num) " of beer.\n"
       (str (action verse-num) ", ")
       (num-bottles (dec-or-loop verse-num)) " of beer on the wall.\n"))

(defn sing
  ([first-verse]
    (sing first-verse 0))
  ([first-verse last-verse]
    (join "\n" (map verse (range first-verse (dec last-verse) -1)))))
