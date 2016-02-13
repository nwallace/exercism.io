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
  {:sunday    Calendar/SUNDAY
   :monday    Calendar/MONDAY
   :tuesday   Calendar/TUESDAY
   :wednesday Calendar/WEDNESDAY
   :thursday  Calendar/THURSDAY
   :friday    Calendar/FRIDAY
   :saturday  Calendar/SATURDAY})

(def ^{:private true} selector-fns
  {:teenth (fn [dates] (first (filter #(<= 13 (get-day %) 19) dates)))
   :first  first
   :second second
   :third  #(nth % 2)
   :fourth #(nth % 3)
   :last   last})

(defn- get-dates-in-month [year month]
  (let [first-of-the-month (GregorianCalendar. year (dec month) 1)]
    (take-while #(= (get-month %) month)
      (iterate
        #(doto (.clone %) (.add Calendar/DAY_OF_MONTH 1))
        first-of-the-month))))

(defn- select-dates-matching-weekday [dates weekday]
  (filter
    #(= (days-of-the-week weekday) (get-day-of-the-week %))
    dates))

(defn meetup [month year weekday which]
  (let [selector-fn (which selector-fns)
        dates-in-month (get-dates-in-month year month)
        matching-weekdays (select-dates-matching-weekday dates-in-month weekday)
        date-of-the-meetup (selector-fn matching-weekdays)]
    ((juxt get-year get-month get-day) date-of-the-meetup)))
