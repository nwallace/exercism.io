(ns meetup
  (:import (java.util Calendar GregorianCalendar)))

(defn- get-day [date]
  (.get date Calendar/DAY_OF_MONTH))
(defn- get-month [date]
  (inc (.get date Calendar/MONTH)))
(defn- get-year [date]
  (.get date Calendar/YEAR))
(defn- get-day-of-the-week [date]
  (.get date Calendar/DAY_OF_WEEK))

(def ^{:private true} days-of-the-week
  {:sunday    1
   :monday    2
   :tuesday   3
   :wednesday 4
   :thursday  5
   :friday    6
   :saturday  7})

(def ^{:private true} selector-fns
  {:teenth (fn [dates] (first (filter #(<= 13 (get-day %) 19) dates)))
   :first  first
   :second #(nth % 1)
   :third  #(nth % 2)
   :fourth #(nth % 3)
   :last   last})

(defn- get-dates-in-month [year month]
  (let [first-of-the-month (doto (GregorianCalendar.) (.set year (dec month) 1))]
    (take-while #(= (get-month %) (get-month first-of-the-month))
      (iterate
        #(doto (.clone %) (.add Calendar/DAY_OF_MONTH 1))
        first-of-the-month))))

(defn- find-meetup-date [year month day selector-fn]
  (let [day-of-the-week (day days-of-the-week)
        dates-in-month (get-dates-in-month year month)
        day-dates-in-month (filter #(= day-of-the-week (get-day-of-the-week %))
                                   dates-in-month)]
    (selector-fn day-dates-in-month)))

(defn meetup [month year day which]
  (let [selector-fn (which selector-fns)
        date-of-the-meetup (find-meetup-date year month day selector-fn)]
    ((juxt get-year get-month get-day) date-of-the-meetup)))
