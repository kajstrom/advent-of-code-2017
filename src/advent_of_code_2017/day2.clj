(ns advent-of-code-2017.day2
  (:require [advent-of-code-2017.shared :refer [parse-int]]
            [clojure.java.io :refer [resource]]))

(defn str->int-vectors [str]
  (->> (clojure.string/split-lines str)
       (map #(clojure.string/split % #"\t"))
       (map #(map parse-int %))))

(defn diff-of-min-max [row]
  (- (apply max row) (apply min row)))

(defn evenly-divisible
  "Returns division if x & y can be evenly divided and are not the same number.
  Otherwise returns nil."
  [x y]
  (let [smaller (min x y)
        larger (max x y)]
    (if-not (= smaller larger)
      (if (= 0 (rem larger smaller))
        (/ larger smaller)
        nil)
      nil)))

(defn even-division [row]
  (->> (map (fn [digit] (some #(evenly-divisible % digit) row)) row)
       (filter some?)
       first))

(defn checksum [calc-fn str]
  (->> (str->int-vectors str)
       (map calc-fn)
       (apply +)))

(defn checksum-of-file [file]
  (->> (resource file)
       slurp
       (checksum diff-of-min-max)))

(defn checksum-of-file2 [file]
  (->> (resource file)
       slurp
       (checksum even-division)))