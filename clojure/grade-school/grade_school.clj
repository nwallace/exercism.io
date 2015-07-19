(ns grade-school)

(defn- map-values [mapping-func coll]
  (reduce-kv
    (fn [memo k v]
      (assoc memo k (mapping-func v)))
    {}
    coll))

(defn grade [db grade-number]
  (db grade-number []))

(defn add [db student grade-number]
  (assoc db grade-number (conj (grade db grade-number) student)))

(defn sorted [db]
  (into (sorted-map) (map-values sort db)))
