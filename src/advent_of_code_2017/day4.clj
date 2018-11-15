(ns advent-of-code-2017.day4
  (:require [clojure.java.io :refer [resource]]))

(defn is-valid-passphrase [phrase]
  (not (some #(> % 1) (vals (frequencies phrase)))))

(defn file-to-seq-of-phrases [file]
  (->> (resource file)
      slurp
      clojure.string/split-lines
      (map #(clojure.string/split % #" "))))

(defn count-of-valid-phrases [file]
  (let [phrases (file-to-seq-of-phrases file)]
    (-> (map is-valid-passphrase phrases)
        frequencies
        (get true))))