(ns hexadecimal)

(defn- char-range [from to]
  (let [from-int (int from) to-int (inc (int to))]
    (map char (range from-int to-int))))

(defn- char->dec-value [base]
  (into {}
        (take base
              (map vector
                   (concat (char-range \0 \9)
                           (char-range \a \z))
                   (range)))))

(defn- make-decimal-converter [base]
  (let [char->dec (char->dec-value base)]
    (fn [string]
      (if (every? char->dec string)
        (->> string
             reverse
             (map char->dec)
             (map * (iterate (partial * base) 1))
             (reduce +))
        0))))

(def hex-to-int (make-decimal-converter 16))
