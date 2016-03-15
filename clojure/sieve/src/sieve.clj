(ns sieve)

(defn- divisible? [numerator denominator]
  (zero? (mod numerator denominator)))

(defn sieve [upto]
  (loop [primes []
         possible-primes (range 2 (inc upto))]
    (if (seq possible-primes)
      (recur
        (conj primes (first possible-primes))
        (remove #(divisible? % (first possible-primes)) (rest possible-primes)))
      primes)))
