(ns check-weather.core
  (:require [clj-http.client :as client])
  (:gen-class))

(def access-key "928b850514d4dfdc3614fb973c27ebea")
(def endpoint "http://api.weatherstack.com/")

(defn build-request-string [query]
  (str endpoint "current?access_key=" access-key "&query=" query))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
