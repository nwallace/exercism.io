(ns sieve)

(defn- divisible? [numerator denominator]
  (zero? (mod numerator denominator)))

(defn sieve [upto]
  (loop [primes []
         [prime & possible-primes] (range 2 (inc upto))]
    (if prime
      (recur
        (conj primes prime)
        (remove #(divisible? % prime) possible-primes))
      primes)))
