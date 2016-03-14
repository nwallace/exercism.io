(ns atbash-cipher)

(defn- char-range [from to]
  (map char (range (int from) (inc (int to)))))

(def ^:private cipher
  (let [alphabet (char-range \a \z)
        digits   (char-range \0 \9)]
    (zipmap
      (concat alphabet digits)
      (concat (reverse alphabet) digits))))

(defn encode [message]
  (->> message
       (.toLowerCase)
       (keep cipher)
       (partition-all 5)
       (interpose " ")
       flatten
       (apply str)))
