(ns advent-of-code-2017.day2
  (:require [advent-of-code-2017.shared :refer [parse-int]]
            [clojure.java.io :refer [resource]]))

(defn str->int-vectors [str]
  (->> (clojure.string/split-lines str)
       (map #(clojure.string/split % #"\t"))
       (map #(map parse-int %))))

(defn diff-of-smallest-and-largest [row]
  (- (apply max row) (apply min row)))

(defn checksum [str]
  (->> (str->int-vectors str)
       (map diff-of-smallest-and-largest)
       (apply +)))

(defn checksum-of-file [file]
  (-> (resource file)
      slurp
      checksum))