(ns advent-of-code-2017.day1-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2017.day1 :refer [captcha]]))

(deftest captcha-test
  (testing "captcha calculation"
    (is (= 4 (captcha "1111")))
    (is (= 3 (captcha "1122")))
    (is (= 0 (captcha "1234")))
    (is (= 9 (captcha "91212129")))))