(ns evrythng.spec.domain-test
  (:require [evrythng.spec :as evt]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.spec :as spec]))

(defn string-of-chars
  "Generate a non-empty string from a generator of chars, with optional transformer"
  ([g n] (gen/such-that
         (comp not empty?)
         (gen/fmap (partial apply str) (gen/vector g 1 n))))
  ([g n transform] (gen/fmap transform (string-of-chars g n))))

(def gen-segment (string-of-chars gen/char-alpha 63 clojure.string/lower-case))

(def gen-short-domain (gen/fmap (partial clojure.string/join ".") (gen/vector gen-segment 2 4)))

(defspec valid-list-of-short-domains
         (prop/for-all [short-domains (gen/vector gen-short-domain)]
                       (is
                         (spec/valid? ::evrythng/shortDomains short-domains)
                         (spec/explain-str ::evrythng/shortDomains short-domains))))