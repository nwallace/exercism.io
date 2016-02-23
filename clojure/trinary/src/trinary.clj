(ns trinary)

(def char->dec-value
  (apply array-map
         (interleave (concat (map char (range 48 58))
                             (map char (range 65 91)))
                     (iterate inc 0))))

(defn- make-decimal-converter [base]
  (letfn [(valid? [string]
            (let [valid-chars (keys (take base char->dec-value))]
              (every? (into #{} valid-chars) string)))
          (digits->dec-values [string]
            (map #(* (char->dec-value %1) %2)
                 (reverse string)
                 (iterate (partial * base) 1)))]
    (fn [string]
      (if (valid? string)
        (reduce + (digits->dec-values string))
        0))))

(def to-decimal (make-decimal-converter 3))
