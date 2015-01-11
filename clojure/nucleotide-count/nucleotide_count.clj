(ns nucleotide-count)

(defn count [nucleotide strand]
  (if (not-any? #{nucleotide} [\A \C \T \G \U])
    (throw (Exception. "invalid nucleotide")))

  (clojure.core/count (filter #(= nucleotide %) (seq strand))))

(defn nucleotide-counts [strand]
  (reduce (fn [result nucleotide]
            (assoc result nucleotide (count nucleotide strand)))
          {} [\A \G \T \C]))
