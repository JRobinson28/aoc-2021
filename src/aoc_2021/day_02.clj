(ns aoc-2021.day-02
  (:require [aoc-2021.common :refer :all]
            [clojure.string :as s]))

(defn parse-instructions
  [filename]
  (->> (parse-input filename)
       (s/split-lines)
       (map #(s/split % #" "))
       (map (fn [[direction val]]
              [(keyword direction) (parse-long val)]))))

;; Part 1

(defn handle-instruction-1
  [[x y] [kw val]]
  (case kw
    :forward [(+ x val) y]
    :up [x (- y val)]
    :down [x (+ y val)]))

(defn part-1
  [data]
  (->> (parse-instructions data)
       (reduce handle-instruction-1 [0 0])
       (apply *)))

(part-1 "day_02")

;; Part 2

(defn handle-instruction-2
  [[aim x y] [kw val]]
  (case kw
    :forward [aim (+ x val) (+ y (* aim val))]
    :up [(- aim val) x y]
    :down [(+ aim val) x y]))

(defn part-2
  [filename]
  (->> (parse-instructions filename)
       (reduce handle-instruction-2 [0 0 0])
       rest
       (apply *)))

(part-2 "day_02")
