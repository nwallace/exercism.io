(ns space-age)

(def ^:private seconds-per-earth-year (* 60 60 24 365.25))

(defn- seconds->earth-years [seconds]
  (/ seconds seconds-per-earth-year))

(defn- years-on-planet-fn [years-to-earth-year-ratio]
  (fn [seconds]
     (-> seconds
         seconds->earth-years
         (/ years-to-earth-year-ratio))))

(def on-mercury (years-on-planet-fn   0.2408467 ))
(def on-venus   (years-on-planet-fn   0.61519726))
(def on-earth   (years-on-planet-fn   1         ))
(def on-mars    (years-on-planet-fn   1.8808158 ))
(def on-jupiter (years-on-planet-fn  11.862615  ))
(def on-saturn  (years-on-planet-fn  29.447498  ))
(def on-uranus  (years-on-planet-fn  84.016846  ))
(def on-neptune (years-on-planet-fn 164.79132   ))
