(ns trinary)

(defn- trinary? [string]
  (every? #{\0 \1 \2} string))

(defn- trinary-digits->dec-values [trinary]
  (map #(* (Integer/parseInt (str %1)) %2)
       (reverse trinary)
       (iterate (partial * 3) 1)))

(defn to-decimal [trinary]
  (if (trinary? trinary)
    (reduce + (trinary-digits->dec-values trinary))
    0))
