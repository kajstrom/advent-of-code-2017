(ns advent-of-code-2017.day3)

(def current-value (atom 1))
(def memory-grid (atom [[1]]))

(defn next-value []
  (swap! current-value inc)
  (deref current-value))

(defn pad-current-row-ends []
  (let [memory @memory-grid]
    (doseq [y (range (- (count memory) 1) -1 -1)]
      (reset! memory-grid (assoc @memory-grid y (conj (get memory y) (next-value)))))))

(defn new-top-row []
  (let [memory @memory-grid
        top-row (into [] (reverse (for [x (range (+ 2 (count memory)) 0 -1)] (next-value))))]
    (reset! memory-grid (apply conj [top-row] memory))))

(defn prepend-current-rows []
  (let [memory @memory-grid]
    (doseq [y (range 1 (count memory) 1)]
      (reset! memory-grid (assoc @memory-grid y (apply conj [(next-value)] (get memory y)))))))

(defn new-bottom-row []
  (let [memory @memory-grid
        top-row (into [] (for [x (range (count (get memory 0)) 0 -1)] (next-value)))]
    (reset! memory-grid (conj memory top-row))))

(defn expand-until [value]
  (while (< @current-value value)
    (do
      (pad-current-row-ends)
      (new-top-row)
      (prepend-current-rows)
      (new-bottom-row))))

(defn search-top-row [value]
  (let [x (.indexOf (get @memory-grid 0) value)]
    (if (not= -1 x)
      [0 x])))

(defn search-bottom-row [value]
  (let [y (- (count @memory-grid) 1)
        x (.indexOf (nth @memory-grid y) value)]
    (if (not= -1 x)
      [y x])))

(defn search-last-column [value]
  (let [x (- (count (get @memory-grid 0)) 1)
        y (.indexOf (map last @memory-grid) value)]
    (if (not= -1 y)
      [y x])))

(defn search-first-column [value]
  (let [y (.indexOf (map first @memory-grid) value)]
    (if (not= -1 y)
      [y 0])))

(defn calculate-center-point []
  (let [y (/ (- (count @memory-grid) 1) 2)
        x y]
    [y x]))

(defn calculate-steps [to from]
  (let [toY (first to)
        toX (second to)
        fromY (first from)
        fromX (second from)
        stepsToY (- (max toY fromY) (min toY fromY))
        stepsToX (- (max toX fromX) (min toX fromX))]
    (+ stepsToY stepsToX)))

(defn find-indexes-of-value [value]
  (if-let [result (search-top-row value)]
    result
    (if-let [result (search-bottom-row value)]
      result
      (if-let [result (search-last-column value)]
        result
        (if-let [result (search-first-column value)]
          result)))))

(defn steps-to-retrieve [value]
  (expand-until value)
  (let [from (find-indexes-of-value value)
        to (calculate-center-point)]
    (calculate-steps to from)))