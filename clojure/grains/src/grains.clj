(ns grains)

(defn square [i]
  (reduce * (repeat (dec i) 2N)))

(defn total []
  (reduce + (map square (range 1 65))))
