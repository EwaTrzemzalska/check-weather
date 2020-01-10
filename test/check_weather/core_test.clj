(ns check-weather.core-test
  (:require [clojure.test :refer :all]
            [check-weather.core :as core]))

(deftest testing-build-request-string
  (is (= "http://api.weatherstack.com/current?access_key=928b850514d4dfdc3614fb973c27ebea&query=New York" (core/build-request-string "New York"))))
