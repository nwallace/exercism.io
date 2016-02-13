(ns grains)

(defn square [i]
  (reduce *' (repeat (dec i) 2)))

(defn total []
  (reduce + (map square (range 1 65))))
