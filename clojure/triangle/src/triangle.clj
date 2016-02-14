(ns triangle)

(defn- most-repeats [sides]
  (->> (frequencies sides)
       vals
       (apply max)))

(def ^:private type-by-count-of-equal-sides
  {1 :scalene
   2 :isosceles
   3 :equilateral})

(defn- illogical? [sides]
  (let [ordered-sides (sort sides)]
    (<= (reduce + (take 2 ordered-sides))
        (last ordered-sides))))

(defn type [& sides]
  {:pre [(= 3 (count sides))]}
  (if (illogical? sides)
    :illogical
    (get type-by-count-of-equal-sides (most-repeats sides))))
