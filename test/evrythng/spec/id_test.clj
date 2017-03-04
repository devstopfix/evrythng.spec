(ns evrythng.spec.id-test
  (:require [evrythng.spec :as evt]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.spec :as spec]))

(defspec evrythng-ids-are-not-most-strings
         (prop/for-all [s gen/string]
                       (is (not (spec/valid? ::evrythng/id s)) (spec/explain-str ::evrythng/id s))))

(defspec evrythng-ids-are-length-24
         (let [gen-var-len-id (gen/fmap (partial apply str) (gen/let [n gen/int] (gen/vector (gen/elements [\U \T \H \N \G]) n)))]
           (prop/for-all [id gen-var-len-id]
                        (let [n (count id)
                              valid (= 24 n)]
                          (is (= valid (spec/valid? ::evrythng/id id)) (spec/explain-str ::evrythng/id id))))))

(defspec evrythng-ids-do-not-contain-ambiguous-chars
            (prop/for-all [id (gen/fmap (partial apply str) (gen/vector (gen/elements [\i \j \l \o \u \v \I \J \O \1 \0]) 24))]
                          (is (not (spec/valid? ::evrythng/id id)) (spec/explain-str ::evrythng/id id))))
