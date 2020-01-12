(ns check-weather.core
  (:require [check-weather.weatherstack :as weatherstack])
  (:gen-class))

(defn current-weather->location [current-weather]
  (get-in current-weather [:request :query]))

(defn current-weather->temperature [current-weather]
  (get-in current-weather [:current :temperature]))

(defn current-weather->pressure [current-weather]
  (get-in current-weather [:current :pressure]))

(defn current-weather->windspeed [current-weather]
  (get-in current-weather [:current :wind_speed]))

(defn get-weather [query]
  (let [current-weather (weatherstack/get-current-weather query)]
    (str "In " (current-weather->location current-weather) " there is now " (current-weather->temperature current-weather) " celcius, " (current-weather->pressure current-weather) " hPa and wind is " (current-weather->windspeed current-weather) " km/h.")))

(defn -main
  [query]
  (prn (get-weather query)))