(ns advent-of-code-2017.day5
  (:require [clojure.java.io :refer [resource]]
            [advent-of-code-2017.shared :refer [parse-int]]))

(def steps (atom 1))

(defn idx-inside-list [idx list]
  (and (>= idx 0) (< idx (count list))))

(defn modify-jump [jump]
  (if (>= jump 3)
    (dec jump)
    (inc jump)))

(defn travel-jumps [jump-fn list]
  (let [list (atom list)
        idx (atom 0)
        steps (atom 0)]
    (while (idx-inside-list @idx @list)
      (do
        (swap! steps inc)
        (let [next-idx (+ @idx (get @list @idx))]
          (reset! list (update @list @idx jump-fn))
          (reset! idx next-idx))))
    @steps))

(defn travel-steps-from-file1 [file]
  (->> (resource file)
       slurp
       (clojure.string/split-lines)
       (map parse-int)
       vec
       (travel-jumps inc)))

(defn travel-steps-from-file2 [file]
  (->> (resource file)
      slurp
      (clojure.string/split-lines)
       (map parse-int)
       vec
       (travel-jumps modify-jump)))