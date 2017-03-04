(ns evrythng.spec.properties-test
  (:require [evrythng.spec :as evt]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.spec :as spec]))

(defn string-of-chars
  "Generate a non-empty string from a generator of chars, with optional transformer"
  ([g] (gen/such-that
     (comp not empty?)
     (gen/fmap (partial apply str) (gen/vector g))))
  ([g transform] (gen/fmap transform (string-of-chars g))))

(def gen-property-key (string-of-chars gen/char-alpha clojure.string/lower-case))

(def gen-property-value (gen/one-of [gen/boolean gen/int gen/double gen/string]))

(defspec property-keys-must-be-lowercase
         (prop/for-all [props (gen/map gen-property-key gen-property-value)]
                       (is (spec/valid? ::evrythng/properties props)
                           (spec/explain-str ::evrythng/properties props))))

(def gen-bad-property-key (string-of-chars gen/char-alpha clojure.string/upper-case))

(defspec property-keys-cannot-be-uppercase
         (prop/for-all [props (gen/map gen-bad-property-key gen-property-value)]
                       (is (not (spec/valid? ::evrythng/properties props))
                           (spec/explain-str ::evrythng/properties props))))
