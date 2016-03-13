(ns strain)

(defn retain [pred coll]
  (for [item coll :when (pred item)] item))

(defn discard [pred coll]
  (retain (complement pred) coll))
