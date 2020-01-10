(ns check-weather.core
  (:require [clj-http.client :as client])
  (:gen-class))

(def access-key "928b850514d4dfdc3614fb973c27ebea")
(def endpoint "http://api.weatherstack.com/")

(defn build-request-string [query]
  (str endpoint "current?access_key=" access-key "&query=" query))

(defn send-request [request-string]
  (-> (client/get request-string)
      :body))

(defn get-weather []
  (send-request (build-request-string "New York")))

(defn -main
  [& args]
  (println (get-weather)))

;;testy