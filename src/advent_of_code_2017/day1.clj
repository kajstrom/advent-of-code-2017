(ns advent-of-code-2017.day1)

(defn parse-int [str]
  (Integer/parseInt str))

(defn matches-or-zero [[x y]]
  (if (= x y) x 0))

(defn str->int-vector [str]
  (map parse-int (clojure.string/split str #"")))

(defn captcha [digits]
  (let [int-digits (str->int-vector digits)
        pairs (partition 2 1 int-digits int-digits)]
     (apply + (map matches-or-zero pairs))))

(defn captcha2 [digits]
  (let [int-digits (str->int-vector digits)
        step (/ (count int-digits) 2)
        digits-cont (concat int-digits (take step int-digits))
        pairs (map-indexed (fn [idx digit] [digit (nth digits-cont (+ idx step))]) int-digits)]
    (apply + (map matches-or-zero pairs))))