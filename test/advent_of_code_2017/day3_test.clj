(ns advent-of-code-2017.day3-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2017.day3 :refer :all]))

(deftest test-find-indexes-of-value
  (expand-until 25)
  (testing "finding index of a value on top row"
    (is (= 0 (compare [0 1] (find-indexes-of-value 16)))))
  (testing "finding index of a value on bottom row"
    (is (= 0 (compare [4 1] (find-indexes-of-value 22)))))
  (testing "finding index of a value on last column"
    (is (= 0 (compare [1 4] (find-indexes-of-value 12)))))
  (testing "finding index of a value on first column"
    (is (= 0 (compare [1 0] (find-indexes-of-value 18))))))

(deftest test-steps-to-retrieve
  (testing "getting steps to retrieve value"
    (is (= 2 (steps-to-retrieve 15)))
    (is (= 4 (steps-to-retrieve 17)))))