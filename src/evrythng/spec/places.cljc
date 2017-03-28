(ns evrythng.spec.places
  (:require [evrythng :as evrythng]
            [geojson]
            [clojure.spec :as spec]))

(spec/def ::evrythng/address
  (spec/keys :opt [::evrythng/extension
                   ::evrythng/street
                   ::evrythng/postalCode
                   ::evrythng/city
                   ::evrythng/county
                   ::evrythng/state
                   ::evrythng/country
                   ::evrythng/countryCode
                   ::evrythng/district
                   ::evrythng/buildingName
                   ::evrythng/buildingFloor
                   ::evrythng/buildingRoom
                   ::evrythng/buildingZone
                   ::evrythng/crossing1
                   ::evrythng/crossing2]))

; Description

(spec/def ::evrythng/description string?)

; Icon URL

(spec/def ::evrythng/url-type string?)

(spec/def ::evrythng/icon ::evrythng/url-type)

; Position is GenJson point
(spec/def ::evrythng/position ::geojson/point)

; Place

(spec/def ::evrythng/place
  (spec/keys :req [::evrythng/name]
             :opt [::evrythng/address
                   ::evrythng/birthday
                   ::evrythng/createdAt
                   ::evrythng/customFields
                   ::evrythng/description
                   ::evrythng/icon
                   ::evrythng/id
                   ::evrythng/identifiers ; filterable
                   ::evrythng/position
                   ::evrythng/tags        ; filterable
                   ::evrythng/updatedAt]))
