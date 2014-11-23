(ns rna-transcription)

(defn to-rna [dna_strand]
  (def complements {"C" "G" "G" "C" "A" "U" "T" "A"})
  (def rna_complements (str "[" (keys complements) "]"))
  (assert (re-matches (re-pattern (str rna_complements "+")) dna_strand))
  (clojure.string/replace dna_strand (re-pattern rna_complements) complements))
