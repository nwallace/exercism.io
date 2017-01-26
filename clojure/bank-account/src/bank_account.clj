(ns bank-account)

(defn open-account []
  {:balance (atom 0)})

(defn get-balance [account]
  @(:balance account))

(defn update-balance [account adjustment]
  (swap! (:balance account) + adjustment))

(defn close-account [account]
  (reset! (:balance account) nil))
