(ns advent-of-code-2017.day4
  (:require [clojure.java.io :refer [resource]]
            [clojure.string :as s]
            [clojure.data :refer [diff]]))

(defn is-valid-passphrase [phrase]
  (not (some #(> % 1) (vals (frequencies phrase)))))

(defn string-to-charmap
  [s]
  (apply hash-map (flatten (map (fn [[k v]] (list k (count v)))(group-by identity (seq s))))))

(defn anagrams [word1 word2]
  (let [diffs (diff (string-to-charmap word1) (string-to-charmap word2))]
    (and (nil? (first diffs)) (nil? (second diffs)))))

(defn merge-anagrams
  "Merges word counts on anagrams. It ain't pretty but it works"
  [word-counts]
  (reduce (fn [m [word count]]
            (let [anagrams (filter #(anagrams word %) (keys m))
                  updated-m (atom (assoc m word count))]
              (doseq [anagram anagrams]
                (reset! updated-m (assoc @updated-m word (+ count (get word-counts anagram)))))
              @updated-m)
            ) {} word-counts))

(defn is-valid-passphrase2 [phrase]
  (not (some #(> % 1) (vals (merge-anagrams (frequencies phrase))))))

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

(defn count-of-valid-phrases2 [file]
  (let [phrases (file-to-seq-of-phrases file)]
    (-> (map is-valid-passphrase2 phrases)
        frequencies
        (get true))))