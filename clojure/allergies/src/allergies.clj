(ns allergies)

(def allergens
  [:eggs
   :peanuts
   :shellfish
   :strawberries
   :tomatoes
   :chocolate
   :pollen
   :cats])

(defn allergies [score]
  (for [[allergen i] (zipmap allergens (range))
        :when (bit-test score i)]
    allergen))

(defn allergic-to? [score allergen]
  (some #{allergen} (allergies score)))
