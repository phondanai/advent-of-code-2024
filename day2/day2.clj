(require '[clojure.string :as str])


(defn inc-or-dec? [col]
  (some
   true?
   [(= (sort col) col) (= (sort > col) col)]))


(def not-inc-or-dec? (complement inc-or-dec?))

(defn safe? [part]
  (empty? (filter (fn [x]
                    (let [diff (abs (- (first x) (second x)))]
                      (or
                      (< diff 1)
                      (> diff 3)))) part)))

;; Part 1
(let [input (->>
             (slurp "input.txt")
             str/split-lines
             (map #(str/split % #" ")))
      col1 (->>
            input
            (map (fn [x]
                   (map parse-long x))))
      col2 (filter inc-or-dec? col1)
      part (map #(partition 2 1 %) col2)
      cnt (map safe? part)]
  (count (filter identity cnt)))


(defn remove-element-at-index [v idx]
  (concat (subvec v 0 idx) (subvec v (inc idx))))

(defn iterate-with-removal [v]
   (for [idx (range (count v))]
     (remove-element-at-index v idx)))

(defn filter-unsafe [col]
  (->>
  col
  vec
  iterate-with-removal
  (filter inc-or-dec?)
  (map #(partition 2 1 %))
  (map safe?)
  (some true?)))

;; Part 2
(let [input (->>
             (slurp "input.txt")
             str/split-lines
             (map #(str/split % #" ")))
      col1 (->>
            input
            (map (fn [x]
                   (map parse-long x))))
      col2 (filter inc-or-dec? col1)
      part1 (map #(partition 2 1 %) col2)
      cnt1 (map safe? part1)
      count1 (count (filter identity cnt1))]
  (count (filter identity (map filter-unsafe col1)))
)
