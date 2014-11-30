(ns bob)

(defn response-for [statement]

  (defn silence? [statement]
    (clojure.string/blank? statement))

  (defn shouting? [statement]
    (and (re-matches #"[^a-z]+" statement) (re-find #"[A-Z]" statement)))

  (defn question? [statement]
    (re-find #"\?$" statement))

  (def responses [{:check silence?  :response "Fine. Be that way!"}
                  {:check shouting? :response "Whoa, chill out!"}
                  {:check question? :response "Sure."}])

  (:response (or (first (filter #((:check %) statement) responses))
                 {:response "Whatever."})))
