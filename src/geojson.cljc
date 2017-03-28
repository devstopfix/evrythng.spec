(ns geojson "http://geojson.org/geojson-spec.html"
  (:require [clojure.spec :as spec]
            [clojure.spec :as spec]))

; Position is GenJson point
; http://geojson.org/geojson-spec.html#id2

(spec/def ::geojson/longitude (spec/double-in -180.0 180))
(spec/def ::geojson/latitude (spec/double-in -90.0 90))

(spec/def ::coordinates (spec/tuple ::geojson/longitude ::geojson/latitude))

(spec/def ::type #{"Point"} )

(spec/def ::geojson/point
  (spec/keys :req [::coordinates ::type]))
