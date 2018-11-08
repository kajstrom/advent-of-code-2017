(ns advent-of-code-2017.day2-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2017.day2 :refer [checksum-of-file checksum-of-file2]]))

(deftest checksum-1-test
  (testing "checksum calculation"
    (is (= 36766 (checksum-of-file "day2-1.txt")))))

(deftest checksum-2-test
  (testing "checksum calculation"
    (is (= 261 (checksum-of-file2 "day2-1.txt")))))
