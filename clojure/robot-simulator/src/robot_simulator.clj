(ns robot-simulator)

(def ^:private directions [:north :east :south :west])

(defn- turner [directions]
  (fn [initial-bearing]
    (->> (cycle directions)
         (drop-while (partial not= initial-bearing))
         second)))
(def turn-left (turner (reverse directions)))
(def turn-right (turner directions))

(defn- advance [{:keys [coordinates bearing] :as robot}]
  (let [[coordinate inc-or-dec] (bearing {:north [:y inc]
                                          :east  [:x inc]
                                          :south [:y dec]
                                          :west  [:x dec]})]
    (update-in robot [:coordinates coordinate] inc-or-dec)))

(def ^:private actions
  {\A advance
   \L #(assoc % :bearing (turn-left (:bearing %)))
   \R #(assoc % :bearing (turn-right (:bearing %)))})

(defn robot [coords bearing]
  {:coordinates coords :bearing bearing})

(defn simulate [steps robot]
  (let [game-plan (map actions steps)]
    (reduce
      (fn [robot action] (action robot))
      robot
      game-plan)))
