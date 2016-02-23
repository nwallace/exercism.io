(ns binary)

(defn- binary? [string]
  (every? #{\0 \1} string))

(defn- bits->dec-values [binary]
  (map #(* (Integer/parseInt (str %1)) %2)
       (reverse binary)
       (iterate (partial * 2) 1)))

(defn to-decimal [binary]
  (if (binary? binary)
    (reduce + (bits->dec-values binary))
    0))
