(ns evrythng.spec "Spec for EVRYTHNG.com REST API"
  (:require [clojure.spec :as spec]
            [evrythng :as evrythng]))

; Identifiers use 24x48 characters. ~10^40 combinations

(def ref-regex #"^[abcdefghkmnpqrstwxyABCDEFGHKMNPQRSTUVWXY23456789]{24}$")

(spec/def ::evrythng/ref-type (spec/and string? #(re-matches ref-regex %)))

(spec/def ::evrythng/id ::evrythng/ref-type)

; Timestamps

(spec/def ::evrythng/timestamp-type (spec/and integer? (spec/int-in 410227200000 4102444799000)))

(spec/def ::evrythng/createdAt ::evrythng/timestamp-type)
(spec/def ::evrythng/timestamp ::evrythng/timestamp-type)
(spec/def ::evrythng/updatedAt ::evrythng/timestamp-type)
