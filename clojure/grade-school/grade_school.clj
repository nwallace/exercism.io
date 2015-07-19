(ns grade-school)

(defn- update-values [coll mapping-func]
  (reduce-kv
    (fn [memo k v] (assoc memo k (mapping-func v)))
    {}
    coll))

(defn- init-or-conj [list-or-nil item]
  (conj (or list-or-nil []) item))

(defn grade [db grade-number]
  (db grade-number []))

(defn add [db student grade-number]
  (update-in db [grade-number] init-or-conj student))

(defn sorted [db]
  (into (sorted-map) (update-values db sort)))

(let [coll {:a [1 10 4] :b [7 1 2]}]
  (update-in coll
             [:b]
             sort))
