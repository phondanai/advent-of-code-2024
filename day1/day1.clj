(require '[clojure.string :as str])

;; Part 1
(let [input (->>
             (slurp "input.txt")
             str/split-lines
             (map #(str/split % #"   ")))
      col1 (->>
            input
            (map (comp parse-long first))
            sort)
      col2 (->>
            input
            (map (comp parse-long second))
            sort)
      diff (map (comp abs -) col1 col2)]
  (prn (reduce + diff)))


;; Part 2
(let [input (->>
             (slurp "input.txt")
             str/split-lines
             (map #(str/split % #"   ")))
      col1 (->>
            input
            (map (comp parse-long first))
            sort)
      col2 (->>
            input
            (map (comp parse-long second))
            sort)
      freq2 (frequencies col2)
      apps  (->>
             col1
             (map #(freq2 % 0)))]
  (prn (reduce + (mapv * col1 apps))))
