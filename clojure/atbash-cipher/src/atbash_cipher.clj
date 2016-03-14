(ns atbash-cipher)

(def cipher
  (let [alphabet (map char (range 97 123))
        digits (map char (range 48 58))]
    (zipmap
      (concat alphabet digits)
      (concat (reverse alphabet) digits))))

(defn encode [message]
  (->> message
       (.toLowerCase)
       (map cipher)
       (filter identity)
       (partition 5 5 nil)
       (interpose " ")
       flatten
       (apply str)))
