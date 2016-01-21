(ns leap)

(defn- divisible? [a b]
  (= 0 (mod a b)))

(defn leap-year? [year]
  (and (divisible? year 4)
       (or (not (divisible? year 100))
           (divisible? year 400))))
