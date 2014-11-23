(ns bob)

(defn silence? [statement]
  (clojure.string/blank? statement))

(defn shouting? [statement]
  (and (re-matches #"[^a-z]+" statement) (re-find #"[A-Z]" statement)))

(defn question? [statement]
  (re-find #"\?$" statement))

(defn response-for [statement]
  (cond
    (silence?  statement) "Fine. Be that way!"
    (shouting? statement) "Whoa, chill out!"
    (question? statement) "Sure."
    :else                 "Whatever."))
