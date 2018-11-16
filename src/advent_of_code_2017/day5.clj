(ns advent-of-code-2017.day5
  (:require [clojure.java.io :refer [resource]]
            [advent-of-code-2017.shared :refer [parse-int]]))

(def steps (atom 1))

(defn idx-inside-list [idx list]
  (and (>= idx 0) (< idx (count list))))

(defn travel-jumps [list]
  (let [list (atom list)
        idx (atom 0)
        steps (atom 0)]
    (while (idx-inside-list @idx @list)
      (do
        (swap! steps inc)
        (let [next-idx (+ @idx (get @list @idx))]
          (reset! list (update @list @idx inc))
          (reset! idx next-idx))))
    @steps))

(defn travel-steps-from-file [file]
  (->> (resource file)
      slurp
      (clojure.string/split-lines)
       (map parse-int)
       vec
       travel-jumps))