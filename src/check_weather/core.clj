(ns check-weather.core
  (:require [clj-http.client :as client]
            [cheshire.core :as cheshire])
  (:gen-class))

(def access-key "928b850514d4dfdc3614fb973c27ebea")
(def endpoint "http://api.weatherstack.com/")

(defn build-current-weather-request-str [query]
  (str endpoint "current?access_key=" access-key "&query=" query))

(defn send-request [request-string]
  (-> (client/get request-string)
      :body
      (cheshire/parse-string true)))

(defn get-current-weather
  "Returns a map with current weather informations from Weatherstack API. 

  A map in following format is returned:
  {:request {:type \"City\"
             :query \"New York, United States of America\"
             :language \"en\"
             :unit \"m\"}
   :location {:name \"New York\"
              :localtime \"2020-01-10 13:02\"
              :timezone_id \"America/New_York\"
              :region \"New York\"
              :utc_offset \"-5.0\"
              :lon \"-74.006\"
              :lat \"40.714\"
              :country \"United States of America\"
              :localtime_epoch 1578661320}
   :current {:weather_descriptions [\"Overcast\"]
             :is_day \"yes\"
             :pressure 1034
             :uv_index 2
             :wind_degree 0
             :weather_code 122
             :wind_speed 11
             :cloudcover 100
             :feelslike 4
             :humidity 65
             :observation_time \"06:02 PM\"
             :weather_icons [\"https://assets.weatherstack.com/images/wsymbols01_png_64/wsymbol_0004_black_low_cloud.png\"]
             :precip 0
             :wind_dir \"N\"
             :visibility 16
             :temperature 7}}"
  [query]
  (send-request (build-current-weather-request-str query)))

(defn get-temperature-for-query [query]
  (get-in (get-current-weather query)
          [:current :temperature]))

(defn get-weather [city]
  (str "In " city " there is now " (get-temperature-for-query city) " celcius."))

(defn -main
  [city]
  (prn (get-weather city)))

;; zwroc string ile stopni jest w podanym mieście 
;; przez main argument od uzytkownika
;; weather New York - "In New York there is now 24 celcius"
;; 1. get temperature value from map 
;; 2. format response [temperature, city]
;; 3. get input from user 
;; 4. return formated response 

