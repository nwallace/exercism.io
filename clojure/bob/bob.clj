(ns bob)

(defn silence? [statement]
  (clojure.string/blank? statement))

(defn shouting? [statement]
  (and (re-matches #"[^a-z]+" statement) (re-matches #".*[A-Z].*" statement)))

(defn question? [statement]
  (re-matches #".*\?$" statement))

(defn response-for [statement]
  (if (silence? statement)
    "Fine. Be that way!"
    (if (shouting? statement)
      "Whoa, chill out!"
      (if (question? statement)
        "Sure."
        "Whatever."))))
