(ns all-your-base)

(defn- valid-input? [base digits new-base]
  (and (every? #(> % 1) [base new-base])
       (every? integer? (conj digits base new-base))
       (every? #(> base % -1) digits)))

(defn- to-decimal [base digits]
  (loop [[d & ds] (reverse digits)
         sum 0 power 0]
    (if (nil? d)
      (int sum)
      (recur ds
             (+ sum (* d (Math/pow base power)))
             (inc power)))))

(defn- to-base [base decimal]
  (loop [digits '()
         remaining decimal
         exp 0]
    (let [modded (mod decimal (Math/pow base (inc exp)))]
      (if (pos? remaining)
        (recur (conj digits (int (quot modded (Math/pow base exp))))
               (- remaining modded)
               (inc exp))
        digits))))

(defn convert [base digits new-base]
  (if (valid-input? base digits new-base)
    (let [decimal-value (to-decimal base digits)
          converted (to-base new-base decimal-value)]
      (if (and (seq digits) (empty? converted))
        '(0)
        converted))))
