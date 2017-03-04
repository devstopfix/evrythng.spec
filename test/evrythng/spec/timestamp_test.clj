(ns evrythng.spec.timestamp-test
  (:require [evrythng.spec :as evt]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.spec :as spec]))

(defspec recent-future-dates-are-valid
         (let [now (System/currentTimeMillis)]
           (prop/for-all [∂t gen/s-pos-int]
                         (let [t (+ now ∂t)]
                           (is (spec/valid? ::evrythng/timestamp-type t))))))

(defspec recent-past-dates-are-valid
         (let [now (System/currentTimeMillis)]
           (prop/for-all [∂t gen/s-neg-int]
                         (let [t (+ now ∂t)]
                           (is (spec/valid? ::evrythng/timestamp-type t))))))
