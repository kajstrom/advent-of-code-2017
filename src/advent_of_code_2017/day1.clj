(ns advent-of-code-2017.day1)

(defn parse-int [str]
  (Integer/parseInt str))

(defn matches-or-zero [[x y]]
  (if (= x y) x 0))

(defn captcha [digits]
  (let [int-digits (map parse-int (clojure.string/split digits #""))
        pairs (partition 2 1 int-digits int-digits)]
     (apply + (map matches-or-zero pairs))))