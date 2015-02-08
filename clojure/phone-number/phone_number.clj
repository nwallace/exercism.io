(ns phone-number
  (:require [clojure.string :as str]))

(defn- digits [phone-str]
  (str/replace phone-str #"\D" ""))

(defn- phone-parts [phone-str]
  (let [parts (re-find #"\A1?(\d{3})(\d{3})(\d{4})\Z" (digits phone-str))]
    (if parts
      (rest parts)
      '("000" "000" "0000"))))

(defn number [phone-str]
  (str/join (phone-parts phone-str)))

(defn area-code [phone-str]
  (first (phone-parts phone-str)))

(defn pretty-print [phone-str]
  (let [[area-code three four] (phone-parts phone-str)]
    (str "(" area-code ") " three "-" four)))
