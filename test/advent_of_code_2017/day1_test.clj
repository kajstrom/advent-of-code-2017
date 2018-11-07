(ns advent-of-code-2017.day1-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2017.day1 :refer [captcha captcha2]]))

(deftest captcha-test
  (testing "captcha calculation"
    (is (= 4 (captcha "1111")))
    (is (= 3 (captcha "1122")))
    (is (= 0 (captcha "1234")))
    (is (= 9 (captcha "91212129")))))

(deftest captcha-part2-test
  (testing "captcha calculation"
    (is (= 6 (captcha2 "1212")))
    (is (= 0 (captcha2 "1221")))
    (is (= 4 (captcha2 "123425")))
    (is (= 12 (captcha2 "123123")))
    (is (= 4 (captcha2 "12131415")))))