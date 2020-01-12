(ns check-weather.weatherstack
  (:require [clj-http.client :as client]
            [cheshire.core :as cheshire]))

(def access-key "928b850514d4dfdc3614fb973c27ebea")
(def endpoint "http://api.weatherstack.com/")

(defn- build-current-weather-request-str [query]
  (str endpoint "current?access_key=" access-key "&query=" query))

(defn- send-request [request-string]
  (-> (client/get request-string)
      :body
      (cheshire/parse-string true)))

(defn get-current-weather
  "If city found returns a map with current weather informations from Weatherstack API. 

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
             :temperature 7}}

  Will throw an error if city is not found"
  [query]
  (let [response (send-request (build-current-weather-request-str query))]
    (if (false? (get response :success))
      (throw (ex-info "Please provide existing city" {:code (get-in response [:error :code])}))
      response)))
