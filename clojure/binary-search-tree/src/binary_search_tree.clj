(ns binary-search-tree)

(defprotocol IBinarySearchTree
  (left [this])
  (right [this])
  (value [this]))

(deftype Tree [root]
  IBinarySearchTree
  (left [this] (Tree. (:left root)))
  (right [this] (Tree. (:right root)))
  (value [this] (:value root))

  clojure.lang.IPersistentCollection
  (cons [this v]
    (letfn [(cons* [{:keys [left right value] :as node} v]
              (if node
                (if (> v value)
                  (assoc node :right (cons* right v))
                  (assoc node :left (cons* left v)))
                {:value v}))]
      (Tree. (cons* root v))))
  (empty [this] (Tree. nil))
  (equiv [this other] (= root (.root other)))

  clojure.lang.ISeq
  (seq [this] (when (contains? root :value) this))
  (first [this]
    (letfn [(first* [{:keys [left value]}]
              (if left
                (recur left)
                value))]
      (first* root)))
  (next [this]
    (letfn [(next* [{:keys [value left right] :as node} path]
              (cond
                left (recur left (conj path :left))
                (seq path) (Tree. (assoc-in root path right)) ;; right may be nil
                :else (Tree. right)))]
      (next* root [])))
  (more [this] (rest this)))

(defmethod print-method Tree [tree ^java.io.Writer w]
  (.write w (str "#Tree" (.root tree))))

(defn tree [& items]
  (reduce conj (Tree. nil) items))

(defn singleton [v]
  (tree v))

(defn insert [v tree]
  (conj tree v))

(defn from-list [items]
  (apply tree items))

(defn to-list [tree]
  (into [] tree))
