(ns minesweeper
  (:require [clojure.string :refer [split]]))

(defn- neighbors-of-coord [rows row col]
  (let [neighbor-coords (for [x [(dec row) row (inc row)]
                              y [(dec col) col (inc col)]
                              :when (not= [row col] [x y])]
                          [x y])]
    (filter identity (map (partial get-in rows) neighbor-coords))))

(defn- bomb? [ch]
  (= \* ch))

(defn- marking [ch neighbors]
  (if (bomb? ch)
    ch
    (let [bomb-count (-> (filter bomb? neighbors) count)]
      (if (pos? bomb-count)
        bomb-count
        " "))))

(defn draw [string]
  (let [rows (split string #"\n")]
    (->> (map-indexed
           (fn [x row]
             (->> (map-indexed
                    (fn [y ch]
                      (->> (neighbors-of-coord rows x y)
                           (marking ch)))
                    row)
                  (apply str)))
           rows)
         (interpose "\n")
         (apply str))))
