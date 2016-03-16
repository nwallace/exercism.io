(ns luhn)

(defn checksum [n]
  (mod
    (loop [n n sum 0 [factor & factors] (cycle [1 2])]
      (if (pos? n)
        (recur (quot n 10)
               (-> n
                   (mod 10)
                   (* factor)
                   (#(if (>= % 10) (- % 9) %))
                   (+ sum))
               factors)
        sum))
    10))

(def valid? (comp zero? checksum))

(defn add-check-digit [n]
  (->> (range 10)
       (map (partial + (* 10 n)))
       (filter valid?)
       first))
