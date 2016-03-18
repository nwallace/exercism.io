(ns kindergarten-garden
  (:require [clojure.string :as string]))

(def students ["Alice" "Bob" "Charlie" "David" "Eve" "Fred" "Ginny"
               "Harriet" "Ileana" "Joseph" "Kincaid" "Larry"])
(def plants {\C :clover
             \G :grass
             \R :radishes
             \V :violets})
(def ^:private name->keyword (comp keyword string/lower-case))

(defn- name-plants [window-sills-str]
  (map (partial map plants) (string/split window-sills-str #"\n")))

(defn- group-plants [window-sills]
  (->> window-sills
       (map (partial partition 2))
       (apply interleave)
       (partition 2)
       (map flatten)))

(defn garden
  ([window-sills-str] (garden window-sills-str students))
  ([window-sills-str students]
   (let [window-sills (name-plants window-sills-str)
         grouped-plants (group-plants window-sills)
         student-keywords (map name->keyword (sort students))]
     (zipmap student-keywords grouped-plants))))
