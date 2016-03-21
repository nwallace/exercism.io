(ns binary-search-tree)

(def left :left)
(def right :right)
(def value :value)

(defn singleton [v]
  {:value v
   :left  nil
   :right nil})

(defn insert [v {:keys [left right value] :as node}]
  (if node
    (if (> v value)
      (assoc node :right (insert v right))
      (assoc node :left (insert v left)))
    (singleton v)))

(defn from-list [values]
  (reduce #(insert %2 %1) nil values))

(defn to-list [node]
  (letfn [(reduce-tree [{:keys [left right value] :as node} result]
            (if node
              (concat result (reduce-tree left []) (reduce-tree right [value]))
              result))]
    (reduce-tree node [])))
