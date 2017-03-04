(ns evrythng.spec-test
  (:require [clojure.test :refer :all]
            [evrythng.spec]
            [clojure.data.json :as json]
            [clojure.spec :as s]))

(defn namespace-keys [namespace m]
  "Change all of the keys of map m into keywords with given namespace.
   Does not recurse into submaps."
  (reduce (fn [m [k v]] (assoc m (keyword namespace k) v)) {} m))

(def evt-namespace (partial namespace-keys "evrythng"))

(defn fixture [filename]
  (-> (str "test/evrythng/" filename ".json")
      (slurp)
      (json/read-str)
      (evt-namespace)))

(deftest thngs
  (testing "Thng Doc"
    (let [thng (fixture "thng")]
      (is (s/valid? ::evrythng/thng thng)
          (s/explain-str ::evrythng/thng thng))))
  (testing "Thng cases"
    (let [thng (fixture "thng_cases")]
      (is (s/valid? ::evrythng/thng thng)
          (s/explain-str ::evrythng/thng thng)))))
