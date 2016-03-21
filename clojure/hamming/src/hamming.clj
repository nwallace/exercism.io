(ns hamming)

(defn distance [a b]
  (when (= (count a) (count b))
    (->> (map = a b)
         (filter false?)
         count)))
