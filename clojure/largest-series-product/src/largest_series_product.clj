(ns largest-series-product)

(defn largest-product [length input-str]
  (if (pos? (count input-str))
    (->> input-str
         (map str)
         (map read-string)
         (partition length 1)
         (map (partial apply *))
         (apply max))
    (if (zero? length)
      1
      (throw (java.lang.IllegalArgumentException.)))))
