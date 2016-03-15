(ns pascals-triangle)

(defn- next-row [prev-row]
  (->> prev-row
       (partition 2 1 nil)
       (map (partial reduce +'))
       (concat [1])))

(def triangle (iterate next-row [1]))

(defn row [n]
  (last (take n triangle)))
