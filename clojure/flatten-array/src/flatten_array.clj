(ns flatten-array
  (:refer-clojure :exclude [flatten]))

(defn flatten [array]
  (letfn [(_flatten [thusfar array-or-item]
            (cond
              (nil? array-or-item) thusfar
              (vector? array-or-item) (reduce _flatten thusfar array-or-item)
              :else (conj thusfar array-or-item)))]
    (_flatten [] array)))
