(ns bracket-push)

(def ^:private opening-brackets (sorted-set \( \[ \{))
(def ^:private closing-brackets (sorted-set \) \] \}))
(def ^:private bracket-pairs (zipmap opening-brackets closing-brackets))

(defn valid? [string]
  (loop [[ch & chs] string
         unclosed []]
    (cond
      (nil? ch) (empty? unclosed)
      (contains? opening-brackets ch) (recur chs (conj unclosed ch))
      (contains? closing-brackets ch) (if (= ch (get bracket-pairs (peek unclosed)))
                                        (recur chs (pop unclosed))
                                        false)
      :else (recur chs unclosed))))
