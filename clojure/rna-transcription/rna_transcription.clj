(ns rna-transcription)

(defn to-rna [dna_strand]
  (def complements {"C" "G" "G" "C" "A" "U" "T" "A"})
  (assert (re-matches (re-pattern (str "[" (keys complements) "]+")) dna_strand))
  (clojure.string/replace dna_strand #"." complements))
