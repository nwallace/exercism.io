(ns roman-numerals)

(def ^:private value->numeral
  {1    \I
   4   "IV"
   5    \V
   9    "IX"
   10   \X
   40   "XL"
   50   \L
   90   "XC"
   100  \C
   400  "CD"
   500  \D
   900  "CM"
   1000 \M})

(defn numerals [n]
  (loop [i n result ""]
    (if (pos? i)
      (let [max-value-less-than-i (apply max (filter (partial >= i) (keys value->numeral)))]
        (recur (- i max-value-less-than-i) (str result (get value->numeral max-value-less-than-i))))
      result)))
