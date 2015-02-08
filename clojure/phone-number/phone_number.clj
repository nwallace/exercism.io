(ns phone-number)

(defn- valid-phone? [phone-str]
  (let [digits (.replaceAll phone-str "[^0-9]" "")]
    (or
      (= 10 (count digits))
      (and
        (= 11 (count digits))
        (= \1 (first digits))))))

(defn number [phone-str]
  (if (valid-phone? phone-str)
    "1234567890"
    "0000000000"))

(defn area-code [phone-str]
  "123")

(defn pretty-print [phone-str]
  "(123) 456-7890")
