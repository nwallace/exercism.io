(ns difference-of-squares)

(defn- square [n]
  (* n n))

(defn square-of-sums [upto]
  (square
    (transduce (map inc) + (range upto))))

(defn sum-of-squares [upto]
  (transduce (comp (map inc) (map square)) + (range upto)))

(defn difference [upto]
  (- (square-of-sums upto) (sum-of-squares upto)))
