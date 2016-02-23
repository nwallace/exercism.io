(ns binary)

(defn- binary? [string]
  (re-find #"\A[01]*\Z" string))

(defn- pair-with-powers [binary]
  (map vector (reverse binary) (iterate inc 0)))

(defn- bit-pair->val [[bit power]]
  (* (Integer/parseInt (str bit)) (int (Math/pow 2 power))))

(defn to-decimal [binary]
  (if (binary? binary)
    (->> binary
         pair-with-powers
         (map bit-pair->val)
         (reduce +))
    0))
