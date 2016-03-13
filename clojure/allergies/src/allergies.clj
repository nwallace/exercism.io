(ns allergies)

(def allergens
  [:cats       128
   :pollen      64
   :chocolate   32
   :tomatoes    16
   :strawberries 8
   :shellfish    4
   :peanuts      2
   :eggs         1])

(defn- remove-unknown-scores [score]
  (let [max-single-score (* 2 (second allergens))]
    (loop [score score]
      (if (>= score max-single-score)
        (recur (- score max-single-score))
        score))))

(defn allergies [score]
  (loop [score (remove-unknown-scores score)
         allergens allergens
         result '()]
    (let [allergen (first allergens)
          allergen-score (second allergens)]
      (cond
        (or (< score 1) (empty? allergens)) result
        (>= score allergen-score) (recur (- score allergen-score) (nnext allergens) (conj result allergen))
        :else (recur score (nnext allergens) result)))))

(defn allergic-to? [score allergen]
  (some #{allergen} (allergies score)))
