(ns evrythng.spec-test
  (:require [clojure.test :refer :all]
            [evrythng.spec]
            [evrythng.spec.places]
            [evrythng.spec.user]
            [clojure.data.json :as json]
            [clojure.spec :as s]
            [clojure.walk :refer [postwalk-replace]]))

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

(deftest actions
  (testing "Action Doc"
    (let [action (fixture "action")]
      (s/explain ::evrythng/action action)
      (is (s/valid? ::evrythng/action action)
          (s/explain-str ::evrythng/action action)))))

(deftest application-users
  (testing "Application User Doc"
    (let [user (fixture "application_user")]
      (s/explain ::evrythng/application-user user)
      (is (s/valid? ::evrythng/application-user user)
          (s/explain-str ::evrythng/application-user user)))))

(deftest places
  (testing "Place Doc"
    (let [place (fixture "place")
          place2 (postwalk-replace {"type" ::geojson/type
                                    "coordinates" ::geojson/coordinates} place)]
      (clojure.pprint/pprint place)
      (clojure.pprint/pprint place2)
      (s/explain ::evrythng/place place2)
      (is (s/valid? ::evrythng/place place2)
          (s/explain-str ::evrythng/place place2)))))

(deftest projects
  (testing "Project Doc"
    (let [project (fixture "project")]
      (s/explain ::evrythng/project project)
      (is (s/valid? ::evrythng/project project)
          (s/explain-str ::evrythng/project project)))))

(deftest thngs
  (testing "Thng Doc"
    (let [thng (fixture "thng")]
      (is (s/valid? ::evrythng/thng thng)
          (s/explain-str ::evrythng/thng thng))))
  (testing "Thng cases"
    (let [thng (fixture "thng_cases")]
      (is (s/valid? ::evrythng/thng thng)
          (s/explain-str ::evrythng/thng thng)))))
