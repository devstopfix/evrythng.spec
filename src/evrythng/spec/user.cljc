(ns evrythng.spec.user
  (:require [evrythng :as evrythng]
            [clojure.spec :as spec]))

; Filterable
(spec/def ::evrythng/email string?)

; Filterable
(spec/def ::evrythng/firstName string?)

; Filterable
(spec/def ::evrythng/lastName string?)

(spec/def ::evrythng/password string?)

(spec/def ::evrythng/gender #{"male" "female"})

; Birthday

(spec/def ::evrythng/day   (spec/int-in 1 31))
(spec/def ::evrythng/month (spec/int-in 1 12))
(spec/def ::evrythng/year  (spec/int-in 1800 2999))

(spec/def ::evrythng/birthday
  (spec/keys :req [::evrythng/day
                   ::evrythng/month
                   ::evrythng/year]))

; TODO regex
(spec/def ::evrythng/locale string?)

; Photos are BASE64 (http://stackoverflow.com/a/475217)
(def base64-regex #"^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$")
(spec/def ::evrythng/base64-type (spec/and string? #(re-matches base64-regex %)))
(spec/def ::evrythng/photo ::evrythng/base64-type)

; TODO regex
(spec/def ::evrythng/timezone string?)

; User

(spec/def ::evrythng/application-user
  (spec/keys :req [::evrythng/email
                   ::evrythng/firstName
                   ::evrythng/lastName
                   ::evrythng/password]
             :opt [::evrythng/birthday
                   ::evrythng/customFields
                   ::evrythng/gender
                   ::evrythng/locale
                   ::evrythng/photo
                   ::evrythng/tags
                   ::evrythng/timezone]))
