(ns evrythng.spec "Spec for EVRYTHNG.com REST API"
  (:require [clojure.spec :as spec]
            [evrythng :as evrythng]))

; Identifiers use 24x48 characters. ~10^40 combinations

(def ref-regex #"^[abcdefghkmnpqrstwxyABCDEFGHKMNPQRSTUVWXY23456789]{24}$")

(spec/def ::evrythng/ref-type (spec/and string? #(re-matches ref-regex %)))

(spec/def ::evrythng/id ::evrythng/ref-type)