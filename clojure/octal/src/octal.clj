(ns octal)

(defn char->dec-value [base]
  (into {}
        (take base
              (map vector
                   (concat (map char (range 48 58))
                           (map char (range 65 91)))
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

(def to-decimal (make-decimal-converter 8))
