(ns triangle)

(defn- most-repeats [sides]
  (->> (frequencies sides)
       vals
       (apply max)))

(def ^:private type-tests
  (array-map
    :illogical   #(let [sorted (sort %)]
                    (<= (apply + (take 2 sorted))
                        (last sorted)))
    :equilateral #(apply = %)
    :scalene     #(= 1 (most-repeats %))
    :isosceles   #(= 2 (most-repeats %))))

(defn type [& sides]
  {:pre [(= 3 (count sides))]}
  (some (fn [[type type-fn]]
          (when (type-fn sides) type))
        type-tests))
