(ns sum-of-multiples)

(defn- divisible-by-any? [denominators numerator]
  (some #(zero? (mod numerator %)) denominators))

(defn sum-of-multiples
  ([n] (sum-of-multiples [3 5] n))
  ([bases n]
   (->> (range 1 n)
        (filter (partial divisible-by-any? bases))
        (reduce +))))
