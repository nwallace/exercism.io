(ns prime-factors)

(def ^:private divisible? (comp zero? mod))

(defn of [i]
  (loop [i i
         factor 2
         factors []]
    (cond
      (< i 2) factors
      (divisible? i factor) (recur (/ i factor) factor (conj factors factor))
      :else (recur i (inc factor) factors))))
