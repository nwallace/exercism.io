(ns binary-search)

(defn middle [tree]
  (assert (seq tree) "not found")
  (quot (count tree) 2))

(defn search-for
  ([target-val tree]
    (when (seq tree) (assert (apply < tree) "Tree must be sorted"))
    (search-for target-val (vec tree) (middle tree)))
  ([target-val tree result]
    (let [this-middle (middle tree)
          current-val (tree this-middle)
          left-subtree (subvec tree 0 this-middle)
          right-subtree (subvec tree (inc this-middle))]
      (cond
        (= current-val target-val) result
        (> current-val target-val)
          (recur target-val left-subtree (- result (- (count left-subtree) (middle left-subtree))))
        :else
          (recur target-val right-subtree (+ result (inc (middle right-subtree))))))))
