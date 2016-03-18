(ns queen-attack
  (:require [clojure.string :refer [upper-case join]]))

(def ^:private board-size 8)

(defn board-string [queens]
  (let [empty-board (->> "_" (repeat board-size) vec
                             (repeat board-size) vec)
        populated-board (reduce-kv (fn [board color coords]
                                     (assoc-in board coords
                                               (upper-case (name color))))
                                   empty-board queens)]
    (as-> populated-board $
          (map (partial join " ") $)
          (interleave $ (repeat "\n"))
          (apply str $))))

(defn can-attack [{:keys [w b]}]
  (let [paired-coords (partition 2 (interleave w b))
        straight-across? (some #(true? (apply = %)) paired-coords)
        diagonal? (apply = (apply map - paired-coords))]
    (or straight-across? diagonal?)))
