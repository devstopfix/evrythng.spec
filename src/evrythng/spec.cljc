(ns evrythng.spec "Spec for EVRYTHNG.com REST API"
  (:require [clojure.spec :as spec]
            [evrythng :as evrythng]))

; Identifiers use 24x48 characters. ~10^40 combinations

(def ref-regex #"^[abcdefghkmnpqrstwxyABCDEFGHKMNPQRSTUVWXY23456789]{24}$")
(spec/def ::evrythng/ref-type (spec/and string? #(re-matches ref-regex %)))

(spec/def ::evrythng/id ::evrythng/ref-type)

; Properties:
;   keys are converted to lowercase on write
;   values can be boolean, numbers and strings

(def snake-lower-case-regex #"^[a-z][a-z0-9_]*$")
(spec/def ::evrythng/property-name-type
  (spec/and string? #(re-matches snake-lower-case-regex %)))

(spec/def ::evrythng/properties-type
  (spec/map-of ::evrythng/property-name-type (spec/or :b boolean? :i int? :f float? :s string?)))

(spec/def ::evrythng/properties ::evrythng/properties-type)

; Tags

(spec/def ::evrythng/tags (spec/coll-of string?))

; Timestamps

(spec/def ::evrythng/timestamp-type (spec/and integer? (spec/int-in 410227200000 4102444799000)))

(spec/def ::evrythng/createdAt ::evrythng/timestamp-type)
(spec/def ::evrythng/timestamp ::evrythng/timestamp-type)
(spec/def ::evrythng/updatedAt ::evrythng/timestamp-type)

; Actions

(def action-spec
  "Specification of an Action"
  (spec/def ::evrythng/action
    (spec/keys :req [::evrythng/id]
            :opt [::evrythng/collection
                  ::evrythng/createdAt
                  ::evrythng/customFields
                  ::evrythng/product
                  ::evrythng/tags
                  ::evrythng/updatedAt]
            )))

; Thngs

(def thng-spec
  "Specification of a Thng"
  (spec/def ::evrythng/thng
    (spec/keys :req [::evrythng/id
                     ::evrythng/description
                     ::evrythng/name]
            :opt [::evrythng/createdAt
                  ::evrythng/customFields
                  ::evrythng/identifiers
                  ::evrythng/product
                  ::evrythng/properties
                  ::evrythng/tags
                  ::evrythng/timestamp
                  ::evrythng/updatedAt
                  ::evrythng/user])))
