(ns scrabble-score
  (:require [clojure.string :as string :only [split]]))

(def ^:private letter-scores
  (let [letters->score {#{"A" "E" "I" "O" "U" "L" "N" "R" "S" "T"}  1
                        #{"D" "G"                                }  2
                        #{"B" "C" "M" "P"                        }  3
                        #{"F" "H" "V" "W" "Y"                    }  4
                        #{"K"                                    }  5
                        #{"J" "X"                                }  8
                        #{"Q" "Z"                                } 10}]
    (reduce-kv (fn [m letters score] (conj m (zipmap letters (repeat score))))
               {}
               letters->score)))

(defn score-letter [letter]
  (get letter-scores (.toUpperCase letter)))

(defn score-word [word]
  (->> (string/split word #"")
       (map score-letter)
       (reduce +)))
