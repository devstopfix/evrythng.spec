(ns evrythng.spec.tags-test
  (:require [evrythng.spec :as evt]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.spec :as spec]))

(defspec tags-are-lists-of-strings
         (prop/for-all [tags (gen/vector gen/string)]
                       (is (spec/valid? ::evrythng/tags tags)
                           (spec/explain-str ::evrythng/tags tags))))

(defn gen-non-empty-vector-of [gs] (gen/such-that (comp not empty?) (gen/vector (gen/one-of gs))))

(defspec tags-are-only-of-strings
         (prop/for-all [tags (gen-non-empty-vector-of [gen/int gen/boolean gen/double])]
                       (is (not (spec/valid? ::evrythng/tags tags))
                           (spec/explain-str ::evrythng/tags tags))))
